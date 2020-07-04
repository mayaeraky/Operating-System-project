import java.util.Vector;

public class Dispatcher {
	
	private Vector<Process> readyQueue;
	private Process currentlyRunning;
	
	
	public Dispatcher(Vector<Process> readyQueue) {
		this.readyQueue = readyQueue;
	}
	
	
	
	//FCFS algorithm
	@SuppressWarnings("deprecation")
	public void schedule() {
		
		while (!readyQueue.isEmpty() || currentlyRunning!=null) {
			
			if (currentlyRunning!=null) {
				if (currentlyRunning.getCurrentState()==ProcessState.BLOCKED || currentlyRunning.getCurrentState()==ProcessState.FINISHED) {
					System.out.println("Process " + currentlyRunning.getProcessId() + " is " + currentlyRunning.getCurrentState());
					currentlyRunning = null;
				}
			}
			
			
			if (!readyQueue.isEmpty() && currentlyRunning==null) {
				currentlyRunning = readyQueue.remove(0);
				currentlyRunning.setCurrentState(ProcessState.RUNNING);
				System.out.println("Process " + currentlyRunning.getProcessId() + " is " + currentlyRunning.getCurrentState());
				if (currentlyRunning.isAlive())
					currentlyRunning.resume();
				else
					currentlyRunning.start();
			}
		}
		
		
	}
	
}