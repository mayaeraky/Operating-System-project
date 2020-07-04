
public class Process1 extends Process {
	
	public Process1(Kernel kernel, int processID) {
		super(kernel, processID);
	}
	
	
	public void run() {
		
		semWait("print");
		semWait("takeInput");
			print("P1: Enter the filename:");
			String filename = takeInput();
		semPost("print");
		semPost("takeInput");
		
		semWait("readFile");
			String data = readFile(filename);
		semPost("readFile");
		
		semWait("print");
			print("P1: " + filename + ":");
			print(data);
		semPost("print");
		
		
		setCurrentState(ProcessState.FINISHED);
	}
	
}
