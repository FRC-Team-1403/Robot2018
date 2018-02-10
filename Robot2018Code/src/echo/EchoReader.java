package echo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

//basic wrapper
public class EchoReader {
	private ObjectInputStream fileReader; //import these later
	private Recording[] recordingArr;
	
	public EchoReader() {
	}
	public void initReader(FileInputStream file) {
		try {
			fileReader = new ObjectInputStream(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("ObjectInputStream obj finished init");
	}
	public Recording[] getArrayFromFile() { //return null if nonexistent
		try {
			this.recordingArr = (Recording[]) fileReader.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return this.recordingArr;
	}
	public Recording[] returnArrayFromFile() {
		return this.recordingArr;
	}
	public void destroy(){
		try {
			fileReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
