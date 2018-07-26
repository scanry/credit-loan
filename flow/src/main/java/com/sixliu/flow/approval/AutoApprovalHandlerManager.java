package com.sixliu.flow.approval;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sixliu.flow.ApprovalResult;
import com.sixliu.flow.entity.FlowTask;

/**
 * @author:MG01867
 * @date:2018年7月18日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe 自动审批handler管理
 */
public class AutoApprovalHandlerManager {

	private final static Logger log = LoggerFactory.getLogger(AutoApprovalHandlerManager.class);

	/** 工作线程名字索引 **/
	private final static AtomicInteger WORKER_THREAD_INDEX = new AtomicInteger(0);
	/** 工作线程名字前缀 **/
	private final static String WORKER_THREAD_NAME_PRE = "";

	/** 定时检查线程名字索引 **/
	private final static AtomicInteger SCHEDULED_THREAD_INDEX = new AtomicInteger(0);
	/** 定时检查线程名字前缀 **/
	private final static String SCHEDULED_THREAD_NAME_PRE = "";

	/** 初始化延迟随机器 **/
	private Random initialDelayRandom;
	/** 异步handler缓存 **/
	private Map<String, AutoApprovalHandler> cache;
	/** 工作线程池 **/
	private ExecutorService workerThreadPool;
	/** 定时检查线程池 **/
	private ScheduledExecutorService scheduledThreadPool;

	public AutoApprovalHandlerManager(int workerThreads, int scheduledThreads) {
		this.initialDelayRandom = new Random();
		this.cache = new HashMap<>();
		this.workerThreadPool = Executors.newFixedThreadPool(workerThreads, this::newWorkerThread);
		this.scheduledThreadPool = Executors.newScheduledThreadPool(scheduledThreads, this::newScheduledThread);
	}

	private Thread newWorkerThread(Runnable r) {
		String newName = WORKER_THREAD_NAME_PRE + WORKER_THREAD_INDEX.getAndIncrement();
		Thread newThread = new Thread(r);
		newThread.setDaemon(false);
		newThread.setName(newName);
		return newThread;
	}

	private Thread newScheduledThread(Runnable r) {
		String newName = SCHEDULED_THREAD_NAME_PRE + SCHEDULED_THREAD_INDEX.getAndIncrement();
		Thread newThread = new Thread(r);
		newThread.setDaemon(true);
		newThread.setName(newName);
		return newThread;
	}

	public <A extends AutoApprovalHandler> void register(A asyHandler) {
		Objects.requireNonNull(asyHandler);
		cache.put(asyHandler.getClass().getName(), new AsyHandlerProxy(asyHandler));
	}

	public <A extends AutoApprovalHandler> AutoApprovalHandler get(Class<A> clz) {
		Objects.requireNonNull(clz);
		return get(clz.getName());
	}

	public <A extends AutoApprovalHandler> AutoApprovalHandler get(String clzName) {
		Objects.requireNonNull(clzName);
		return cache.get(clzName);
	}

	public void shutdown() {
		if (null != scheduledThreadPool) {
			scheduledThreadPool.shutdown();
		}
		if (null != workerThreadPool) {
			workerThreadPool.shutdown();
		}
	}

	class AsyHandlerProxy implements AutoApprovalHandler {

		private AutoApprovalHandler asyHandler;

		private AsyHandlerProxy(AutoApprovalHandler asyHandler) {
			this.asyHandler = asyHandler;
			long initialDelay = initialDelayRandom.nextInt(60) * 1000;
			scheduledThreadPool.scheduleWithFixedDelay(() -> {
				check();
			}, initialDelay, asyHandler.getCheckInterval(), TimeUnit.MILLISECONDS);
		}

		@Override
		public String getName() {
			return null;
		}

		@Override
		public long getCheckInterval() {
			return 0;
		}

		@Override
		public FlowTask check() {
			Thread currentThread = Thread.currentThread();
			String originalThreadName = currentThread.getName();
			String newThreadName = originalThreadName + "-" + asyHandler.getName();
			currentThread.setName(newThreadName);
			try {
				FlowTask flowTask = asyHandler.check();
				if (null != flowTask) {
					process(flowTask);
				}
			} catch (Exception exception) {
				log.error("asynchronous check task err", exception);
			} finally {
				currentThread.setName(originalThreadName);
			}
			return null;
		}

		@Override
		public ApprovalResult process(FlowTask flowTask) {
			workerThreadPool.submit(() -> {
				Thread currentThread = Thread.currentThread();
				String originalThreadName = currentThread.getName();
				String newThreadName = originalThreadName + "-" + asyHandler.getName();
				currentThread.setName(newThreadName);
				try {
					asyHandler.process(flowTask);
				} catch (Exception exception) {
					log.error(String.format("Auto Approval flowTask[%s] of flowJob[%s] err", flowTask.getId(),flowTask.getFlowJobId()), exception);
				} finally {
					currentThread.setName(originalThreadName);
				}
			});
			return null;
		}
	}

	public static void main(String[] args) {
		Random initialDelayRandom = new Random();
		System.out.println(initialDelayRandom.nextInt(10));
		System.out.println(initialDelayRandom.nextInt(100));
		AutoApprovalHandlerManager asyHandleManager = new AutoApprovalHandlerManager(10, 10);
		asyHandleManager.register(new HandlerProxyTest());
		AutoApprovalHandler asyHandler = asyHandleManager.get(HandlerProxyTest.class);
		asyHandler.process(new FlowTask());
		asyHandleManager.shutdown();
	}
}
