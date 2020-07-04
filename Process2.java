
public class Process2 extends Process {
	
	public Process2(Kernel kernel, int processID) {
		super(kernel, processID);
	}
	
	
	public void run() {
		
		semWait("print");
		semWait("takeInput");
			print("P2: Enter the filename:");
			String filename = takeInput();
			print("P2: Enter the data to be saved into the file:");
			String data = takeInput();
		semPost("print");
		semPost("takeInput");
		
		semWait("writeFile");
			writeFile(filename, data);
		semPost("writeFile");
		
		
		setCurrentState(ProcessState.FINISHED);
	}
	
}
