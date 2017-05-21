public class Program {
    
	private static final int GRID_SIZE = 200;
	private static final int RUNS_COUNT = 100;
	private static final int CORES_COUNT = Runtime.getRuntime().availableProcessors();
	
	private static final byte INIT = 0;
	private static final byte RUNNING = 1;
	private static final byte FINISHED = 2;
	
	public static void main(String[] args) {
		long avgResult = 0;
		int runsStarted = 0;
		int runsFinished = 0;
		
		Thread[] pipeLine = new Thread[CORES_COUNT];
		byte[] threadState = new byte[CORES_COUNT];
		CalcThread[] calcThreads = new CalcThread[CORES_COUNT]; 
		
		while (true) {
			for( int i = 0; i < CORES_COUNT; i++) {
				if (threadState[i] == RUNNING) {
					if (!pipeLine[i].isAlive()) {
						avgResult += calcThreads[i].instance.openCount; 
						runsFinished++;		
						threadState[i] = FINISHED;
					}
				}
				if ( threadState[i] == INIT || threadState[i] == FINISHED ) {
					if (runsStarted < RUNS_COUNT) {
						calcThreads[i] = new CalcThread(GRID_SIZE);
						pipeLine[i] = new Thread(calcThreads[i]);
						pipeLine[i].start();
						threadState[i] = RUNNING;
						runsStarted++;
					}		
				}
			}
			
			try {
			    Thread.sleep(20);
			} catch (InterruptedException e) {
			    e.printStackTrace();
			}
			
			if (runsFinished >= RUNS_COUNT) {
				break;
			}
			
			
		}
		System.out.format("Final average, %%: %f", avgResult * 100. / RUNS_COUNT / GRID_SIZE / GRID_SIZE); 
    }
}
