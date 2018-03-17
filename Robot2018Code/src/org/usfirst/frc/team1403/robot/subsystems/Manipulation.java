package org.usfirst.frc.team1403.robot.subsystems;

import org.usfirst.frc.team1403.robot.Robot;
import org.usfirst.frc.team1403.robot.RobotMap;
import org.usfirst.frc.team1403.robot.commands.mpEjectJoystick;
import org.usfirst.frc.team1403.robot.commands.mpTestManip;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Manipulation extends Subsystem {

    public static TalonSRX intakeLeft;
	public static TalonSRX intakeRight;
	public static TalonSRX clawRight;
	public static TalonSRX clawLeftencL;
    public DigitalInput limitSwitch;
    public DigitalOutput LED;
    public double inLeftSpeede, inRightSpeede, rlRightSpeede, rlLeftSpeede, inLeftSpeedi, inRightSpeedi, rlRightSpeedi, rlLeftSpeedi;
    public static double getRawAxisRightoJoy;
	public Manipulation()
	{
		intakeLeft = new TalonSRX(RobotMap.intakeLeft);
		intakeRight = new TalonSRX(RobotMap.intakeRight);
		clawRight = new TalonSRX(RobotMap.clawRight);
		clawLeftencL = new TalonSRX(RobotMap.clawLeftencL);
		limitSwitch = new DigitalInput (8);
		LED = new DigitalOutput(9);
		getRawAxisRightoJoy = 0;
		
		inLeftSpeede = .75;
		inRightSpeede = .75;
		rlRightSpeede = .75;
		rlLeftSpeede = .75;
		inLeftSpeedi= .6;
		inRightSpeedi = .6;
		rlRightSpeedi = .6;
		rlLeftSpeedi = .6;
		
	
	}
	
	public void ejectWithJoystick()
	{
		if(!RobotState.isAutonomous()) {
			getRawAxisRightoJoy = Robot.m_oi.ojoy.getRawAxis(5);
			Robot.manip.clawLeftencL.set(ControlMode.PercentOutput, -Robot.m_oi.ojoy.getRawAxis(5));
			Robot.manip.clawRight.set(ControlMode.PercentOutput, -Robot.m_oi.ojoy.getRawAxis(5));
		}
	}
	
	public void clawIntake() //for roller claw on elevator
	{
		if(!RobotState.isAutonomous()) {
			clawLeftencL.set(ControlMode.PercentOutput, rlLeftSpeedi);
			clawRight.set(ControlMode.PercentOutput, rlRightSpeedi);
		}
	}
	
	public void clawEject() //for roller claw on elevator
	{
		if(!RobotState.isAutonomous()) {
			clawLeftencL.set(ControlMode.PercentOutput, -rlLeftSpeede);
			clawRight.set(ControlMode.PercentOutput, -rlRightSpeede);
		}
	}
	
	public void groundIntake() //for ground intake
	{
		if(!RobotState.isAutonomous()) {
			intakeLeft.set(ControlMode.PercentOutput, -inLeftSpeedi);
			intakeRight.set(ControlMode.PercentOutput, inRightSpeedi);
		}
	}
		
	
	public void groundEject() //for ground intake
	{
		if(!RobotState.isAutonomous()) {
			intakeLeft.set(ControlMode.PercentOutput, inLeftSpeede);
			intakeRight.set(ControlMode.PercentOutput, -inRightSpeede);
		}
	}
	public static void setSpeed(TalonSRX talon, double speed)
    {
    	talon.set(ControlMode.PercentOutput, speed);
    }
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new mpEjectJoystick());
    }
    
}

