package echo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Recording implements Serializable{
	
	private int index;
	private long timestamp;
	private Map<String, Double> Data;
	
	public Recording() {
		this.Data = new HashMap<String, Double>();
		this.index = -1;
	}
	public void addIndex(int index) {
		this.index = index;
	}
	public int returnIndex() {
		return this.index;
	}
	public void addReading(String key, double value) {
		this.Data.put(key, ReferenceToPrimitive(value));
	}
	public double returnReading(String key) {
		return this.Data.get(key);
	}
	public HashMap<String, Double> returnMap() {
		return (HashMap<String, Double>)Data;	
	}
	public double ReferenceToPrimitive(double value) {
		Double d = new Double(value);
		return (double)d;
	}

	
	/*TODO: Add Key Mapping*/
	//DriveTrainR    -->     DriveTrain Right Motor Reading
	//DriveTrainL    -->     DriveTrain Left Motor Reading
	//GyroY          -->     Gyro Yaw Reading
	//GyroP          -->     Gyro Pitch Reading
	//GyroR          -->     Gyro Row Reading
}
