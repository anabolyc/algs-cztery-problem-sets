

public class CalcThread
implements Runnable		//(содержащее метод run())
{
	public Percolation instance;
	public boolean finished = false;
	
	public CalcThread(int GridSize) {
		instance = new Percolation(GridSize);
	}
	
	public static int Random(int min, int max) {
		return (min + (int)(Math.random() * ((max - min) + 1)));
	} 
	
	public void run()
    {
		int N = instance.GRID_SIZE;
		while (!instance.percolates()) {
        	instance.open(Random(1, N), Random(1, N));
        }
		System.out.format("Open Count: %d; Total Count: %d; %% = %f", instance.openCount, N * N, instance.openCount *100. / N / N);
		System.out.println();
		this.finished = true; 
    }
}