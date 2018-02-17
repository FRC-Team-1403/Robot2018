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
	int chooserint;
	
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
		init();
		manip = new Manipulation();
		elevator = new Elevator();
		m_oi = new OI();
		chooser.addDefault("Default Auto", new ExampleCommand());
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", chooser);
		recorder.setCurrentWritefile(1);
		recorder.setCurrentReadfile(0);
		Recorder.initWriter();
		Recorder.initReader();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
@Override
	public void disabledInit() { chooserint = 0; }

	@Override
	public void disabledPeriodic() 
	{
		
		if (Robot.m_oi.ojoy.getRawButtonReleased(1)) 
		{ 
			chooserint++; SmartDashboard.putNumber("chooserint", chooserint%7); 
		}
	
		switch(chooserint%2) 
		{
		case 0: SmartDashboard.putString("Auto Selector", "Straight");break;
		case 1: SmartDashboard.putString("Auto Selector", "Switch");break; 
		}
		Scheduler.getInstance().run();

}
	@Override
	public void autonomousInit() 
	{
		recorder.resetReadings();
		recorder.storeReadings();
		autonomousCommand = chooser.getSelected();
		autonomousCommand = chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		String gameData = DriverStation.getInstance().getGameSpecificMessage();

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
		}
		
		Scheduler.getInstance().run();
		
		
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		while(!recorder.checkTime()) {Timer.delay(0.001);}
		if(recorder.hasNextLine()) {
			DriveTrain.setSpeed(DriveTrain.frontLeft, recorder.getReading("DriveTrain L"));
			DriveTrain.setSpeed(DriveTrain.frontRightencR, recorder.getReading("DriveTrain R"));
			Elevator.setSpeed(Elevator.elMotor, recorder.getReading("Elevator"));
			Manipulation.setSpeed(Manipulation.intakeLeft,recorder.getReading("Intake Left Motor"));
			Manipulation.setSpeed(Manipulation.intakeRight, recorder.getReading("Intake Right Motor"));
			Manipulation.setSpeed(Manipulation.clawLeft, recorder.getReading("Intake Roller Left Motor"));
			Manipulation.setSpeed(Manipulation.clawRight, recorder.getReading("Intaker Roller Right Motor"));
			Manipulation.setSpeed(Manipulation.clawLeft,recorder.getReading("Eject Left Motor"));
			Manipulation.setSpeed(Manipulation.clawRight, recorder.getReading("Eject Right Motor"));
			Manipulation.setSpeed(Manipulation.clawLeft, recorder.getReading("Eject Roller Left Motor"));
			Manipulation.setSpeed(Manipulation.clawRight, recorder.getReading("Eject Roller Right Motor"));
			recorder.nextReading();
		} else {
			DriveTrain.setSpeed(DriveTrain.frontLeft, 0);
			DriveTrain.setSpeed(DriveTrain.frontRightencR, 0);
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
		
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic()
	{
		Scheduler.getInstance().run();
		
		SmartDashboard.putBoolean("Top Optical Gate", Robot.elevator.opticSwitch.get());
		SmartDashboard.putBoolean("Bottom Optical Gate", Robot.elevator.opticIntake.get());
		SmartDashboard.putBoolean("Intake Limit Switch", Robot.manip.limitSwitch.get());
		SmartDashboard.putNumber("Encoder Left", Robot.drivetrain.getLeftPosition());
		SmartDashboard.putNumber("Encoder Right", Robot.drivetrain.getRightPosition());
		
		if(Recorder.isRecording) {
			recorder.addReading("DriveTrain L", drivetrain.getRawAxisLeft);
			recorder.addReading("DriveTrain R", drivetrain.getRawAxisRight);
			recorder.addReading("Elevator", elevator.getRawAxisRight);
			recorder.addReading("Intake Left Motor", manip.inLeftSpeedi);
			recorder.addReading("Intake Right Motor", manip.inRightSpeedi);
			recorder.addReading("Eject Left Motor", manip.inLeftSpeede);
			recorder.addReading("Eject Right Motor", manip.inLeftSpeede);
			recorder.addReading("Intake Roller Left Motor", manip.rlLeftSpeedi);
			recorder.addReading("Intake Roller Right Motor", manip.rlRightSpeedi);
			recorder.addReading("Eject Roller Left Motor", manip.rlLeftSpeede);
			recorder.addReading("Eject Roller Right Motor", manip.rlLeftSpeede);
			recorder.initNextReading();
		} else if (Recorder.isStoring()) {
			recorder.storeWritings();
		}
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
	public void init() {
		//File Select Menu
		path = new String("/home/lvuser/0.txt");
		recorder.addFileSelect(numpaths, path);
		SmartDashboard.putString(Integer.toString(numpaths), path);
		++numpaths;
		path = new String("/home/lvuser/10.txt");
		recorder.addFileSelect(numpaths, path);
		SmartDashboard.putString(Integer.toString(numpaths), path);
		++numpaths;
	}
}
