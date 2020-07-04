
import java.util.Vector;

public class BinarySemaphore {
	
	private int value;
	private Vector<Process> blockedQueue;
	
	
	public BinarySemaphore(int initialValue) {
		value = initialValue;
		blockedQueue = new Vector<Process>();
	}
	
	
	
	@SuppressWarnings("deprecation")
	public void semWait(Process p) {
		
		if (value==1)
			value = 0;
		else {
			blockedQueue.add(p);
			p.setCurrentState(ProcessState.BLOCKED);
			p.suspend();
		}
		
	}
	
	
	public Process semPost(Process p) {
		
		if (blockedQueue.isEmpty()) {
			value = 1;
			return null;
		}
		else
			return blockedQueue.remove(0);
		
	}
	
	
}
