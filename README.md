# A java program illustrating thread discussion

- This is analogous to a human group discussion where each person has a chance
to speak.
- One thread generates a message of the form
	`<thread-name>-<timestamp>`
- The other two threads print that message when they receive the message.
	`<Message>	<thread-name>-<message travel time>`
	
## Message
	This is representation of a message produced by a thread.
	It provides print(String) API to print message in required format.
## Participant
	Each thread in discussion is represented by Participant class which is a Runnable.
### Listener
	receive: Each Participant listens to messages using receive(Message) API
### Speaker
	register: Each Participant registers the listeners using register(Listener) API
	speak: Each Participant is a Speaker and exposes speak() API, this generates a message
	and calls receive method on each listener
### shut
	This API is used to send stop signal to Participants
## Driver
	This is the Java main class, used to start the discussion
	It accepts duration of discussion as command line argument(default is 100ms)
	