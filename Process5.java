
public class Process5 extends Process {
	
	static int filenumber = 1;
	
	public Process5(Kernel kernel, int processID) {
		super(kernel, processID);
	}
	
	
	public void run() {
		
		semWait("print");
		semWait("takeInput");
			print("please enter the lower bound:");
			String lowerBound = takeInput();
			print("please enter the upper bound:");
			String upperBound = takeInput();
		semPost("print");
		semPost("takeInput");
		
		int x,y;
		try {
			x = Integer.parseInt(lowerBound);
			y = Integer.parseInt(upperBound);
		}
		catch (NumberFormatException e) {
			semWait("print");
				print("Invalid input.");
			semPost("print");
			setCurrentState(ProcessState.FINISHED);
			return;
		}
		
		String data = "";
		for(int i=x ; i<=y ;i++)
              data += i+" ";
		
		semWait("writeFile");
			writeFile("newfile_" + filenumber++ + ".txt", data);
		semPost("writeFile");
		
		
		setCurrentState(ProcessState.FINISHED);
	}
		
}
