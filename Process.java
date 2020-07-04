
public abstract class Process extends Thread {
	
	private int processID;
	private ProcessState currentState;
	private Kernel kernel;
	
	
	public Process(Kernel kernel, int processID) {
		this.processID = processID;
		currentState = ProcessState.NEW;
		this.kernel = kernel;
	}

	
	public int getProcessId() {
		return processID;
	}
	
	public void setProcessId(int processId) {
		this.processID = processId;
	}
	
	public ProcessState getCurrentState() {
		return currentState;
	}
	
	public void setCurrentState(ProcessState currentState) {
		this.currentState = currentState;
		
//		//should we remove process from process table?
//		if (currentState == ProcessState.FINISHED) {
//			kernel.getProcessTable().remove(this);
//		}
		
	}
	
	
	
	public void semWait(String semaphore) {
		kernel.semWait(this, semaphore);
	}
	
	
	public void semPost(String semaphore) {
		kernel.semPost(this, semaphore);
	}
	
	
	public String readFile(String fileName) {
		return kernel.readFile(fileName);
	}
	
	
	public void writeFile(String fileName, String data) {
		kernel.writeFile(fileName, data);
	}
	
	
	public void print(String s) {
		kernel.print(s);
	}
	
	
	public String takeInput() {
		return kernel.takeInput();
	}
	
	
}
