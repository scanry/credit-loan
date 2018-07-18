package com.sixliu.credit.common.thread;

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

/**
 * @author:MG01867
 * @date:2018年7月18日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe 异步handler管理
 */
public class AsyHandleManager {

	private final static Logger log = LoggerFactory.getLogger(AsyHandleManager.class);

	/**工作线程名字索引**/
	private final static AtomicInteger WORKER_THREAD_INDEX = new AtomicInteger(0);
	/**工作线程名字前缀**/
	private final static String WORKER_THREAD_NAME_PRE = "";

	/**定时检查线程名字索引**/
	private final static AtomicInteger SCHEDULED_THREAD_INDEX = new AtomicInteger(0);
	/**定时检查线程名字前缀**/
	private final static String SCHEDULED_THREAD_NAME_PRE = "";

	/** 初始化延迟随机器 **/
	private Random initialDelayRandom;
	/** 异步handler缓存 **/
	private Map<Class<? extends AsyHandler>, AsyHandler> cache;
	/** 工作线程池 **/
	private ExecutorService workerThreadPool;
	/** 定时检查线程池 **/
	private ScheduledExecutorService scheduledThreadPool;

	public AsyHandleManager(int workerThreads, int scheduledThreads) {
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

	public <A extends AsyHandler> void register(A asyHandler) {
		Objects.requireNonNull(asyHandler);
		cache.put(asyHandler.getClass(), new AsyHandlerProxy(asyHandler));
	}

	public <A extends AsyHandler> AsyHandler get(Class<A> clz) {
		Objects.requireNonNull(clz);
		return cache.get(clz);
	}

	public void shutdown() {
		if (null != scheduledThreadPool) {
			scheduledThreadPool.shutdown();
		}
		if (null != workerThreadPool) {
			workerThreadPool.shutdown();
		}
	}

	class AsyHandlerProxy implements AsyHandler {

		private AsyHandler asyHandler;

		private AsyHandlerProxy(AsyHandler asyHandler) {
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
		public Object check() {
			Thread currentThread = Thread.currentThread();
			String originalThreadName = currentThread.getName();
			String newThreadName = originalThreadName + "-" + asyHandler.getName();
			currentThread.setName(newThreadName);
			try {
				Object result = asyHandler.check();
				if (null != result) {
					invoke(result);
				}
			} catch (Exception exception) {
				log.error("asynchronous check task err", exception);
			} finally {
				currentThread.setName(originalThreadName);
			}
			return null;
		}

		@Override
		public void invoke(Object arg) {
			workerThreadPool.submit(() -> {
				Thread currentThread = Thread.currentThread();
				String originalThreadName = currentThread.getName();
				String newThreadName = originalThreadName + "-" + asyHandler.getName();
				currentThread.setName(newThreadName);
				try {
					asyHandler.invoke(arg);
				} catch (Exception exception) {
					log.error(String.format("asynchronous invoke task err by arg[%s]", arg.toString()), exception);
				} finally {
					currentThread.setName(originalThreadName);
				}
			});
		}
	}

	public static void main(String[] args) {
		Random initialDelayRandom = new Random();
		System.out.println(initialDelayRandom.nextInt(10));
		System.out.println(initialDelayRandom.nextInt(100));
		AsyHandleManager asyHandleManager = new AsyHandleManager(10, 10);
		asyHandleManager.register(new HandlerProxyTest());
		AsyHandler asyHandler = asyHandleManager.get(HandlerProxyTest.class);
		asyHandler.invoke("notify");
		asyHandleManager.shutdown();
	}
}
