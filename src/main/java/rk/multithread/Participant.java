package rk.multithread;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Participant implements Listener, Speaker, Runnable {

	private List<Listener> listeners;
	private BlockingQueue<Message> messageQueue;
	private Thread currentThread;

	public Participant() {
		listeners = new ArrayList<>(5);
		this.messageQueue = new LinkedBlockingQueue<>();
	}

	@Override
	public void speak() {
		Message message = new Message(Instant.now(), Thread.currentThread().getName());
		listeners.forEach(listener -> listener.receive(message));
	}


	@Override
	public void receive(Message message) {
		messageQueue.offer(message);
	}

	@Override
	public void run() {
		currentThread = Thread.currentThread();
		speak();
		while (!currentThread.isInterrupted()) {
			try {
				messageQueue.take().print(currentThread.getName());
				speak();
			} catch (InterruptedException e) {
				currentThread.interrupt();
			}
		}
		Message msg =messageQueue.poll();
		while(msg != null) {
			msg.print(currentThread.getName());
			msg = messageQueue.poll();
		}
		System.out.printf("name : %s, msgs : %d exiting%n", currentThread.getName(), messageQueue.size());
	}

	@Override
	public void register(Listener listener) {
		listeners.add(listener);
	}

	public void shut() {
		currentThread.interrupt();
	}
}
