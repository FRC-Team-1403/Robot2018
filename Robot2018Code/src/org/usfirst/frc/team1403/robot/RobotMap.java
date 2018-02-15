/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1403.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap
{
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;
	public static double feetPerTick = 0.0010047574;
	public static double feetPerTick256 = 0.0040906154;
	public static double inchesPerTick256 = 0.0490873852;
	public static double feetPerTick1024 =  0.0010226539;
	public static double inchesPerTick1024 = 0.0122718463;
	
	//DriveTrain
	public static int frontLeftMotor = 9;
	public static int backLeftMotor = 5;
	public static int frontRightMotor = 3; //has left encoder
	public static int backRightMotor = 2; //has right encoder

	public static int gyroSensor = 0;
	
	//Elevator
	public static int opticIntake = 0;
	public static int opticSwitch = 1;
	public static int opticMax = 2;
	public static int elevatorMotor = 7;
	
	//Manipulation
	public static int limitSwitch = 1;
	
	//Front intake motors
	public static int inLeft = 1;
	public static int inRight = 11;
	
	//Back roller motors
	public static int rlLeft = 4;
	public static int rlRight = 10;
	
	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}
