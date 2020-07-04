
public class Process4 extends Process {
	
	public Process4(Kernel kernel, int processID) {
		super(kernel, processID);
	}
	
	
	public void run() {
		
		semWait("print");
	    for(int i=500 ; i<=1000 ; i++)
	    	print(i+"");
	    semPost("print");
	    
	    
	    setCurrentState(ProcessState.FINISHED);
	}
}
