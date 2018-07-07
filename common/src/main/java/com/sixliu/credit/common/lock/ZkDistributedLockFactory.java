package com.sixliu.credit.common.lock;

import java.util.Objects;


import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.sixliu.credit.common.exception.SystemAppException;

/**
 * @author:MG01867
 * @date:2018年7月7日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe //TODO
 */
public class ZkDistributedLockFactory implements DistributedLockFactory {

	protected static final Logger log = LoggerFactory.getLogger(ZkDistributedLockFactory.class);

	private CuratorFramework curatorFramework;

	public ZkDistributedLockFactory(CuratorFramework curatorFramework) {
		Objects.requireNonNull(curatorFramework);
		this.curatorFramework=curatorFramework;
	}

	@Override
	public DistributedLock newInstance(String stamp) {
		if (null != curatorFramework) {
			throw new SystemAppException("default ZkDistributedLockFactory no support");
		}
		InterProcessReadWriteLock interProcessReadWriteLock = new InterProcessReadWriteLock(curatorFramework, stamp);
		return new ZkDistributedLock(interProcessReadWriteLock);
	}

	private class ZkDistributedLock implements DistributedLock {

		InterProcessReadWriteLock interProcessReadWriteLock;

		ZkDistributedLock(InterProcessReadWriteLock interProcessReadWriteLock) {
			this.interProcessReadWriteLock = interProcessReadWriteLock;
		}

		@Override
		public void readLock() throws Exception {
			interProcessReadWriteLock.readLock().acquire();
		}

		@Override
		public void unreadlock() throws Exception {
			interProcessReadWriteLock.readLock().release();
		}

		@Override
		public void writeLock() throws Exception {
			interProcessReadWriteLock.writeLock().acquire();
		}

		@Override
		public void unwriteLock() throws Exception {
			interProcessReadWriteLock.writeLock().release();
		}
	}

}
