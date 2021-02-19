package rk.multithread;

import java.time.Duration;
import java.time.Instant;

class Message {
	Instant birthTime;
	String writer;
	Message(Instant birthTime, String writer) {
		this.birthTime = birthTime;
		this.writer = writer;
	}

	public void print(String reader) {
		System.out.println(String.format("%s-%s\t%s-%s",writer, birthTime, reader, Duration.between(Instant.now(), birthTime)));
	}
}