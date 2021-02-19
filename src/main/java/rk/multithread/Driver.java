package rk.multithread;

public class Driver {

	public static void main(String[] args) throws InterruptedException {
		Participant[] parts = formGroup();
		formParticipantRelation(parts);
		initiateDiscussion(parts);
		waitForDurationOfDiscussion(args);
		askParticipantToCloseDiscussion(parts);
	}

	private static void askParticipantToCloseDiscussion(Participant[] parts) {
		for (Participant part : parts) {
			part.shut();
		}
	}

	private static void waitForDurationOfDiscussion(String[] args) throws InterruptedException {
		long sleepDuration = 100l;
		if (args.length > 0)
			sleepDuration = Long.parseLong(args[0]);
		Thread.sleep(sleepDuration);
	}

	private static Participant[] formGroup() {
		return new Participant[] { new Participant(), new Participant(), new Participant() };
	}

	private static void initiateDiscussion(Participant[] parts) {
		Thread[] ths = new Thread[parts.length];
		for (int i = 0; i < parts.length; i++) {
			ths[i] = new Thread(parts[i]);
		}
		for (int i = 0; i < parts.length; i++) {
			ths[i].start();
		}
	}

	private static void formParticipantRelation(Participant[] parts) {
		for (Speaker speaker : parts) {
			for (Listener listner : parts) {
				if (speaker != listner)
					speaker.register(listner);
			}
		}
	}

}
