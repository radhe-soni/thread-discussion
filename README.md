# A java program illustrating thread discussion

- This is analogous to a human group discussion where each person has a chance
to speak.
- One thread generates a message of the form
	`<thread-name>-<timestamp>`
- The other two threads print that message when they receive the message.
	`<Message>	<thread-name>-<message travel time>`
	
## [Message](src/main/java/rk/multithread/Message.java)
	This is representation of a message produced by a thread.
	It provides print(String) API to print message in required format.
## [Participant](src/main/java/rk/multithread/Participant.java)
	Each thread in discussion is represented by Participant class which is a Runnable.
	shut: This API is used to send stop signal to Participants
### [Listener](src/main/java/rk/multithread/Listener.java)
	receive: Each Participant listens to messages using receive(Message) API
### [Speaker](src/main/java/rk/multithread/Speaker.java)
	register: Each Participant registers the listeners using register(Listener) API
	speak: Each Participant is a Speaker and exposes speak() API, this generates a message
	and calls receive method on each listener
## [Driver](src/main/java/rk/multithread/Driver.java)
	This is the Java main class, used to start the discussion
	It accepts duration of discussion as command line argument(default is 100ms)
	