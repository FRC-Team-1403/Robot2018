package org.usfirst.frc.team1403.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team1403.robot.commands.dtDriveTimeGyro;
import org.usfirst.frc.team1403.robot.commands.ExampleCommand;
import org.usfirst.frc.team1403.robot.commands.cgLeftSwitchAuto;
import org.usfirst.frc.team1403.robot.commands.cgRightSwitchAuto;
import org.usfirst.frc.team1403.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1403.robot.subsystems.Elevator;
import org.usfirst.frc.team1403.robot.subsystems.ExampleSubsystem;
import org.usfirst.frc.team1403.robot.subsystems.Manipulation;

import echo.Recorder;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	private int numpaths;
	private String path;
	public static DriveTrain drivetrain;
	public static Recorder recorder;
	public static boolean record;
	public static boolean store;	
	
	public static Manipulation manip;
	public static Elevator elevator;
	int delay;
	int autoint;
	
	public static OI m_oi;
	

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();
	String pathChooser;
	int writerChooser;
	int readerChooser;
	boolean executeChanges;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit()
	{
		
		CameraServer.getInstance().startAutomaticCapture();
		recorder = new Recorder(10000); //10000 = max arr size
		drivetrain = new DriveTrain();
		//SmartDashboard.putBoolean("Limit Switch", false);
		numpaths = 0;
		delay = 0;
		autoint = 0;
		manip = new Manipulation();
		elevator = new Elevator();
		m_oi = new OI();
		chooser.addDefault("Default Auto", new ExampleCommand());
		// chooser.addObject("My Auto", new MyAutoCommand());
	SmartDashboard.putData("Auto mode", chooser);
	
	/*
	Robot.drivetrain.backLeftencR.configVoltageCompSaturation(11.0, 10);
	Robot.drivetrain.backLeftencR.enableVoltageCompensation(true);
	Robot.drivetrain.backLeftencR.configVoltageMeasurementFilter(32, 10);
	
	Robot.drivetrain.backRight.configVoltageCompSaturation(11.0, 10);
	Robot.drivetrain.backRight.enableVoltageCompensation(true);
	Robot.drivetrain.backRight.configVoltageMeasurementFilter(32, 10);
	
	Robot.drivetrain.frontLeft.configVoltageCompSaturation(11.0, 10);
	Robot.drivetrain.frontLeft.enableVoltageCompensation(true);
	Robot.drivetrain.frontLeft.configVoltageMeasurementFilter(32, 10);
	
	Robot.drivetrain.frontRight.configVoltageCompSaturation(11.0, 10);
	Robot.drivetrain.frontRight.enableVoltageCompensation(true);
	Robot.drivetrain.frontRight.configVoltageMeasurementFilter(32, 10);
	*/
	
	//ONLY FOR RECORDING; COMMENT DURING MATCHES; DO NOT COMMENT DURING RECORDING
	
	/*
	init();
	recorder.setCurrentWritefile(1);
	recorder.setCurrentReadfile(0);
	Recorder.initWriter();
	Recorder.initReader();
	recorder.resetReadings();
	recorder.storeReadings();
	*/
		
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
@Override
	public void disabledInit() {	}

	@Override
	public void disabledPeriodic() 
	{
		
		if (Robot.m_oi.ojoy.getRawButtonReleased(1)) { autoint++; }
		SmartDashboard.putNumber("autoint", autoint%4);
		
	//	if (Robot.m_oi.ojoy.getRawButtonReleased(2)) { delay++; }
	//	SmartDashboard.putNumber("delay", delay%5);
		
		if (autoint%4 == 0) { SmartDashboard.putString("Auto Position", "Left"); }
		if (autoint%4 == 1) { SmartDashboard.putString("Auto Position", "Right"); }
		if (autoint%4 == 2) { SmartDashboard.putString("Auto Position", "Middle"); }
		if (autoint%4 == 3) { SmartDashboard.putString("Auto Position", "STRAIGHT"); }
		
		Scheduler.getInstance().run();

}
	@Override
	public void autonomousInit() 
	{
		Robot.drivetrain.backLeftencR.configVoltageCompSaturation(11.0, 10);
    	Robot.drivetrain.backLeftencR.enableVoltageCompensation(true);
    	Robot.drivetrain.backLeftencR.configVoltageMeasurementFilter(32, 10);
    	
    	Robot.drivetrain.backRight.configVoltageCompSaturation(11.0, 10);
    	Robot.drivetrain.backRight.enableVoltageCompensation(true);
    	Robot.drivetrain.backRight.configVoltageMeasurementFilter(32, 10);
    	
    	Robot.drivetrain.frontLeft.configVoltageCompSaturation(11.0, 10);
    	Robot.drivetrain.frontLeft.enableVoltageCompensation(true);
    	Robot.drivetrain.frontLeft.configVoltageMeasurementFilter(32, 10);
    	
    	Robot.drivetrain.frontRight.configVoltageCompSaturation(11.0, 10);
    	Robot.drivetrain.frontRight.enableVoltageCompensation(true);
    	Robot.drivetrain.frontRight.configVoltageMeasurementFilter(32, 10);
    	
    	
    	//ONLY FOR RUNNING; COMMENT FOR RECORDING; DO NOT COMMENT DURING MATCHES
    	init();
    	recorder.setCurrentWritefile(1);
		recorder.setCurrentReadfile(0);
		Recorder.initWriter();
		Recorder.initReader();
		recorder.resetReadings();
		recorder.storeReadings();
		
		
		autonomousCommand = chooser.getSelected();
		
		
		//autonomousCommand = chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
/*		String gameData = DriverStation.getInstance().getGameSpecificMessage();

		autonomousCommand = new dtDriveTimeGyro(4, 0.6);
		
		switch(chooserint%2)
		{
			case 0: autonomousCommand = new dtDriveTimeGyro(4, 0.6); break;
			case 1:
			{
			if(gameData.charAt(0) == 'L') 
			{ 
				autonomousCommand = new cgLeftSwitchAuto(); 
			}
			else 
			{ 
				autonomousCommand = new cgRightSwitchAuto(); 
			}
			}
		} */
		
		Scheduler.getInstance().run();
		
		
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		//while(!recorder.checkTime()) {Timer.delay(0.001);}
	//	Timer.delay(delay%5);
		if(recorder.hasNextLine())
		{
			System.out.println(recorder.getReading("DriveTrain Back Left"));
			DriveTrain.setSpeed(DriveTrain.frontLeft, recorder.getReading("DriveTrain Front Left"));
			DriveTrain.setSpeed(DriveTrain.backLeftencR, recorder.getReading("DriveTrain Back Left"));
			DriveTrain.setSpeed(DriveTrain.frontRight, recorder.getReading("DriveTrain Front Right"));
			DriveTrain.setSpeed(DriveTrain.backRight, recorder.getReading("DriveTrain Back Right"));
			Elevator.setSpeed(Elevator.elMotor, recorder.getReading("Elevator"));
			Manipulation.setSpeed(Manipulation.clawLeftencL, recorder.getReading("Eject Roller Left Motor"));
			Manipulation.setSpeed(Manipulation.clawRight, recorder.getReading("Eject Roller Right Motor"));
			Timer.delay(0.001);
		}
		
		else
		{
			DriveTrain.setSpeed(DriveTrain.frontLeft, 0);
			DriveTrain.setSpeed(DriveTrain.frontRight, 0);
			DriveTrain.setSpeed(DriveTrain.backLeftencR, 0);
			DriveTrain.setSpeed(DriveTrain.backRight, 0);
			Elevator.setSpeed(Elevator.elMotor, 0);
			Manipulation.setSpeed(Manipulation.clawLeftencL, 0);
			Manipulation.setSpeed(Manipulation.clawRight, 0);
		}
		
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		
	/*	Robot.drivetrain.backLeftencR.enableVoltageCompensation(false);
		Robot.drivetrain.backRight.enableVoltageCompensation(false);
		Robot.drivetrain.frontLeft.enableVoltageCompensation(false);
		Robot.drivetrain.frontRight.enableVoltageCompensation(false);
		Robot.manip.LED.set(false);
		*/
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic()
	{
		Scheduler.getInstance().run();
		
		SmartDashboard.putBoolean("Top Optical Gate", !Robot.elevator.opticSwitch.get());
		SmartDashboard.putBoolean("Bottom Optical Gate", !Robot.elevator.opticIntake.get());
		//SmartDashboard.putBoolean("Intake Limit Switch", Robot.manip.limitSwitch.get());
		SmartDashboard.putNumber("Encoder Claw Left", Robot.drivetrain.getLeftPosition());
		SmartDashboard.putNumber("Encoder Drive Right", Robot.drivetrain.getRightPosition());
		SmartDashboard.putBoolean("LED Cube", Robot.manip.limitSwitch.get());
		
		if(Recorder.isRecording)
		{
			recorder.addReading("DriveTrain Back Left", -drivetrain.getRawAxisLeft);
			recorder.addReading("DriveTrain Back Right", drivetrain.getRawAxisRight);
			recorder.addReading("DriveTrain Front Left", drivetrain.getRawAxisLeft);
			recorder.addReading("DriveTrain Front Right", drivetrain.getRawAxisRight);
			System.out.println(recorder.getReading("DriveTrain Back Left"));
			System.out.println(recorder.getReading("DriveTrain Back Right"));
			recorder.addReading("Elevator", -elevator.getRawAxisLeftoJoy);
			recorder.addReading("Eject Roller Left Motor", -manip.getRawAxisRightoJoy);
			recorder.addReading("Eject Roller Right Motor", -manip.getRawAxisRightoJoy);
			System.out.println(recorder.initNextReading());
		}
		
		else if (Recorder.isStoring()) {
			recorder.storeWritings();
		}
		
		if(Robot.manip.limitSwitch.get())
    	{
    		Robot.manip.LED.set(true);
    	}
		
		else {
			Robot.manip.LED.set(false);
		}
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
	
	public void init() {
		
		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		
		//Left
		if (autoint%4 == 0)
		{
			if(gameData.charAt(0) == 'L') { path = new String("/home/lvuser/LeftSwitchFromLeft.txt"); }	//Left switch from middle
			else { path = new String("/home/lvuser/Straight.txt"); }									//Right switch from middle
		}
		
		//Right
		if (autoint%4 == 1)
		{
			if(gameData.charAt(0) == 'L') { path = new String("/home/lvuser/Straight.txt"); }	//Left switch from right
			else { path = new String("/home/lvuser/RightSwitchFromRight.txt"); }				//Right switch from right
		}
		
		//Middle
		if (autoint%4 == 2)
		{
			if(gameData.charAt(0) == 'L') { path = new String("/home/lvuser/LeftSwitchFromMiddle.txt"); }	//Left switch from left
			else { path = new String("/home/lvuser/RightSwitchFromMiddle.txt"); }							//Right switch from left
		}
		
		
		//Straight
		if (autoint%4 == 3)
		{
			path = new String("/home/lvuser/Straight.txt");	//Straight
		}
	
		
		//File Select Menu
		/*+
		 * Different commands to call
		 * "/home/lvuser/LeftSwitchFromMiddle.txt"		COMPLETED
		 * "/home/lvuser/RightSwitchFromMiddle.txt"		COMPLETED
		 * "/home/lvuser/LeftSwitchFromRight.txt"		
		 * "/home/lvuser/RightSwitchFromRight.txt" 		COMPLETED
		 * "/home/lvuser/LeftSwitchFromLeft.txt"		COMPLETED
		 * "/home/lvuser/RightSwitchFromLeft.txt"				
		 * "/home/lvuser/Straight.txt"					COMPLETED
		 * EXTRAS 					
		 * "/home/lvuser/LeftSwitchFromMiddleREDO.txt"
		 * "/home/lvuser/RightSwitchFromMiddleREDO.txt"
		 * "/home/lvuser/LeftSwitchFromRightElite.txt"
		 * "/home/lvuser/RightSwitchFromLeftElite.txt"
		 * "/home/lvuser/TwoCubeMiddleRight.txt"
		 * 
		 */
		//path = new String("/home/lvuser/RightSwitchFromLeft.txt"); //Reads from this one
		recorder.addFileSelect(numpaths, path);
		SmartDashboard.putString(Integer.toString(numpaths), path);
		++numpaths;
		path = new String("/home/lvuser/RightSwitchFromLeft.txt");
		recorder.addFileSelect(numpaths, path);
		SmartDashboard.putString(Integer.toString(numpaths), path);
		++numpaths;
	}
}
	
//Autos to record: Center->Left, Center->Right, Left->Left, Left->Right, Right->Left, Right->Right
