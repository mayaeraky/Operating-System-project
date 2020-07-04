import java.io.*;
import java.util.*;

public class Kernel {
	
	static int nextProcessID = 1;
	
	private Vector<Process> processTable;
	private Vector<Process> readyQueue;
	private Dispatcher dispatcher;
	private Vector<BinarySemaphore> semaphores;
	
	
	public Kernel() {
		processTable = new Vector<Process>();
		readyQueue = new Vector<Process>();
		semaphores = new Vector<BinarySemaphore>();
		for (int i=0 ; i<4 ; i++) {
			semaphores.add(new BinarySemaphore(1));
		}
		dispatcher = new Dispatcher(readyQueue);
	}
	
	
	public Vector<Process> getProcessTable() {
		return processTable;
	}
	
	
	
	//system calls:
	
	// 1- Read from File
	public String readFile(String fileName) {
		String data = "";
		try {
			FileReader fr = new FileReader(fileName);
	        BufferedReader br = new BufferedReader(fr);
	        String line = br.readLine();
	        
	        while (line != null) {
	        	data += line+"\n";
	        	line = br.readLine();
	        }
	        br.close();
	        fr.close();
		}
        catch (FileNotFoundException e) {
        	System.out.println("File not found.");
        }
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		return data;
	}
	
	
	// 2- Write into file
	public void writeFile(String fileName, String data) {
		try {
			File myObj = new File(fileName);
			
			//if file does not exist create a new file
			if (!myObj.createNewFile())
				System.out.println("File already exists.");
			else
				System.out.println("File created successfully.");
			
			//override the old data in the file
			FileWriter writer = new FileWriter(fileName, false);
			System.out.println("File edited successfully.");
			writer.write(data);
			writer.close();
		}
		catch (IOException e) {
			System.out.println("Could not write to file.");
		}
	}
	
	
	// 3- print to console
	public void print(String s) {
		System.out.println(s);
	}
	
	
	// 4- take input
	public String takeInput() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		return s;
	}
	
	
	
	//methods
	
	//create a new process based on its type
	public void createProcess(String processType) {
		
		Process newProcess = null;
		switch (processType) {
		
		case "Process 1":
			newProcess = new Process1(this, nextProcessID++);
			break;
				
		case "Process 2":
			newProcess = new Process2(this, nextProcessID++);
			break;
				
		case "Process 3":
			newProcess = new Process3(this, nextProcessID++);
			break;
				
		case "Process 4":
			newProcess = new Process4(this, nextProcessID++);
			break;
				
		case "Process 5":
			newProcess = new Process5(this, nextProcessID++);
			break;
		}
		
		processTable.add(newProcess);
		acceptProcess(newProcess);
		
	}
	
	
	//take a new process and put it in the ready queue to be picked to start executing later by the dispatcher 
	public void acceptProcess(Process newProcess) {
		newProcess.setCurrentState(ProcessState.READY);
		readyQueue.add(newProcess);
	}
	
	
	//takes the name of the semaphore and calls semWait on it
	public void semWait(Process p, String semaphore) {
		
		BinarySemaphore s;
		switch (semaphore) {
		
		case "readFile":
			s = semaphores.get(0);
			s.semWait(p);
			break;
		
		case "writeFile":
			s = semaphores.get(1);
			s.semWait(p);
			break;
		
		case "print":
			s = semaphores.get(2);
			s.semWait(p);
			break;
			
		case "takeInput":
			s = semaphores.get(3);
			s.semWait(p);
			break;
		}
		
	}
	
	
	//takes the name of the semaphore and calls semPost on it
	public void semPost(Process p, String semaphore) {
		
		BinarySemaphore s;
		Process dequeued = null;
		switch (semaphore) {
		
		case "readFile":
			s = semaphores.get(0);
			dequeued = s.semPost(p);
			break;
		
		case "writeFile":
			s = semaphores.get(1);
			dequeued = s.semPost(p);
			break;
		
		case "print":
			s = semaphores.get(2);
			dequeued = s.semPost(p);
			break;
			
		case "takeInput":
			s = semaphores.get(3);
			dequeued = s.semPost(p);
			break;
		}
		
		if (dequeued != null) {
			dequeued.setCurrentState(ProcessState.READY);
			readyQueue.add(dequeued);
		}
	}
	
	
	public void schedule() {
		dispatcher.schedule();
	}
	
	
}
