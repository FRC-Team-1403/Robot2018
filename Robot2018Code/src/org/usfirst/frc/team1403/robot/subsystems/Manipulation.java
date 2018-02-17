package org.usfirst.frc.team1403.robot.subsystems;

import org.usfirst.frc.team1403.robot.RobotMap;
import org.usfirst.frc.team1403.robot.commands.mpEjectJoystick;
import org.usfirst.frc.team1403.robot.commands.mpTestManip;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
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
    public double inLeftSpeede, inRightSpeede, rlRightSpeede, rlLeftSpeede, inLeftSpeedi, inRightSpeedi, rlRightSpeedi, rlLeftSpeedi;
	public Manipulation()
	{
		intakeLeft = new TalonSRX(RobotMap.intakeLeft);
		intakeRight = new TalonSRX(RobotMap.intakeRight);
		clawRight = new TalonSRX(RobotMap.clawRight);
		clawLeftencL = new TalonSRX(RobotMap.clawLeftencL);
		limitSwitch = new DigitalInput (2);
		
		inLeftSpeede = .75;
		inRightSpeede = .75;
		rlRightSpeede = .75;
		rlLeftSpeede = .75;
		inLeftSpeedi= .6;
		inRightSpeedi = .6;
		rlRightSpeedi = .6;
		rlLeftSpeedi = .6;
		
	
	}
	
	public void clawIntake() //for roller claw on elevator
	{
		clawLeftencL.set(ControlMode.PercentOutput, rlLeftSpeedi);
		clawRight.set(ControlMode.PercentOutput, rlRightSpeedi);
	}
	
	public void clawEject() //for roller claw on elevator
	{
		clawLeftencL.set(ControlMode.PercentOutput, -rlLeftSpeede);
		clawRight.set(ControlMode.PercentOutput, -rlRightSpeede);
	}
	
	public void groundIntake() //for ground intake
	{
		intakeLeft.set(ControlMode.PercentOutput, -inLeftSpeedi);
		intakeRight.set(ControlMode.PercentOutput, inRightSpeedi);
	}
	
	public void groundEject() //for ground intake
	{
		intakeLeft.set(ControlMode.PercentOutput, inLeftSpeede);
		intakeRight.set(ControlMode.PercentOutput, -inRightSpeede);
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

