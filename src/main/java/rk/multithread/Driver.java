package rk.multithread;

public class Driver {

	public static void main(String[] args) throws InterruptedException{
		Participant[] parts = new Participant[] {new Participant(), new Participant(), new Participant()};
		parts[0].register(parts[1]);
		parts[0].register(parts[2]);
		parts[1].register(parts[0]);
		parts[1].register(parts[2]);
		parts[2].register(parts[0]);
		parts[2].register(parts[1]);
		Thread th1 = new Thread(parts[0]);
		Thread th2 = new Thread(parts[1]);
		Thread th3 = new Thread(parts[2]);
		th1.start();
		th2.start();
		th3.start();
		long sleepDuration = 100l;
		if(args.length > 0)
			sleepDuration = Long.parseLong(args[0]);
		Thread.sleep(sleepDuration);
		parts[0].shut();
		parts[1].shut();
		parts[2].shut();
	}

}
