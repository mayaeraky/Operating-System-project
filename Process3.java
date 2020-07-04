
public class Process3 extends Process {
	
	public Process3(Kernel kernel, int processID) {
		super(kernel, processID);
	}
	
	
	public void run() {
		
		semWait("print");
		for(int i=0 ; i<=300 ; i++)
			print(i+"");
		semPost("print");
		
		
		setCurrentState(ProcessState.FINISHED);
	}
	
}
