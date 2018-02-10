package echo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Recorder {
	private final int RMAX; //20 ms delay * 50 ms in 1 second * 15 seconds
	private int iteration;
	private long prevTime;
	private Recording[] sequencedReadings;
	public static boolean isRecording;
	public static boolean isStoring;
	
	public Map<Integer, String> FileSelect;
	public static EchoWriter writer;
	public static EchoReader reader;
	public static FileOutputStream writerFile;
	public static FileInputStream readerFile;
	
	public Recorder(int arrsize) {
		this.RMAX = arrsize;
		this.iteration = 0;
		this.prevTime = 0;
		Recorder.isRecording = false;
		Recorder.isStoring = false;
		this.sequencedReadings = new Recording[RMAX];
		this.sequencedReadings[iteration] = new Recording();
		this.FileSelect = new HashMap<Integer, String>();
		Recorder.writer = new EchoWriter();
		Recorder.reader = new EchoReader();
	}
	public static boolean isStoring() {
		if(Recorder.isStoring) {
			Recorder.isStoring = false;
			System.out.println("Recorder Storing Status: " + Recorder.isStoring);
			return true;
		}
		return false;
	}
	public static void startRecording() {
		Recorder.isRecording = true;
		if(!Recorder.writer.isActive()) {
			initWriter();
		}
	}
	public Recording getRecording(int index) {
		return sequencedReadings[index];
	}
	public double getReading(String key) {
		return sequencedReadings[iteration].returnReading(key);
	}
	public void addReading(String key, double value) {
		this.sequencedReadings[iteration].addIndex(iteration);
		this.sequencedReadings[iteration].addReading(key, value);
	}
	public void doneRecording() {
		this.sequencedReadings[iteration] = new Recording();
		this.sequencedReadings[iteration].addIndex(-1);
	}
	public int nextReading() {
		return ++this.iteration;
	}
	public int initNextReading() {
		this.sequencedReadings[++this.iteration] = new Recording();
		return this.iteration;
	}
	//file selection process
	public void addFileSelect(int index, String file) {
		this.FileSelect.put(index, file);
	}
	public FileOutputStream setCurrentWritefile(int index) {
		try {
			Recorder.writerFile = new FileOutputStream(this.FileSelect.get(index), false);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return writerFile;
	}
	public FileInputStream setCurrentReadfile(int index) {
		try {
			Recorder.readerFile = new FileInputStream(this.FileSelect.get(index));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return readerFile;
	}
	public void storeReadings() {
		Recorder.reader.getArrayFromFile();
		sequencedReadings = Arrays.copyOf(Recorder.reader.returnArrayFromFile(), Recorder.reader.returnArrayFromFile().length);
	}
	public void storeWritings() {
		this.doneRecording();
		for(Recording r : sequencedReadings) {
			if(r.returnIndex() == -1)
				break;
		}
		writer.serializeToFile(sequencedReadings);
	}
	public static void initWriter() {
		System.out.println("Writer Init Recorder");
		writer.initWriter(writerFile);
	}
	public static void initReader() {
		System.out.println("Reader Init Recorder");
		reader.initReader(readerFile);
	}
	public boolean hasNextLine() {
		boolean hasNextLine = getRecording(iteration+1).returnIndex() != -1;
		if(!hasNextLine) return hasNextLine;
		if(checkTime()) 
			nextReading();
		return hasNextLine;
	}
	public void resetReadings() {
		this.iteration = 0;
		this.sequencedReadings = new Recording[RMAX];
		this.sequencedReadings[0] = new Recording();
	}
	public boolean checkTime() {
		long currTime = System.currentTimeMillis();
		System.out.println(currTime);
		if(currTime - this.prevTime < 20) {
			interpolateReadings((int)(currTime - this.prevTime));
			return false;
		} else {
			this.prevTime = currTime;
			return true;
		}
	}
	public void interpolateReadings(int timediff) {
		//20 ms
		if(this.iteration < 1 || getRecording(iteration+1).returnIndex() == -1) 
			return;
		for(String key : sequencedReadings[this.iteration].returnMap().keySet()) {
			double value = sequencedReadings[this.iteration+1].returnMap().get(key) - sequencedReadings[this.iteration].returnMap().get(key);
			value *= (double)timediff/20.0;
			//System.out.println(timediff + " Iter " + iteration + " Value: " + value + "Curr: " + sequencedReadings[this.iteration].returnMap().get(key) + "Next: " + sequencedReadings[this.iteration+1].returnMap().get(key));
			sequencedReadings[this.iteration].addReading(key, sequencedReadings[this.iteration].returnMap().get(key)+value);
		}
	}
}
