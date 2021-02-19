package rk.multithread;

import java.time.Instant;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MessagingService {

	Lock lock = new ReentrantLock();
	Message message;
	Semaphore readPermit = new Semaphore(0);
	Semaphore writePermit = new Semaphore(2);

	public void createOrRecieveMessage() {

		if (writePermit.tryAcquire(2)) {
			message = new Message(Instant.now(), Thread.currentThread().getName());
			readPermit.release(2);
			return;
		}
		try {
			readPermit.acquire();
			message.print(Thread.currentThread().getName());
			writePermit.release(1);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}
