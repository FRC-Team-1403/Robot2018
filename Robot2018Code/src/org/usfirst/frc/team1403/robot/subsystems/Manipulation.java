package org.usfirst.frc.team1403.robot.subsystems;

import org.usfirst.frc.team1403.robot.RobotMap;
import org.usfirst.frc.team1403.robot.commands.mpTestManip;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Manipulation extends Subsystem {

    public TalonSRX inLeft, inRight, rlRight, rlLeft;
  //  public DigitalInput limitSwitch;
    public double inLeftSpeede, inRightSpeede, rlRightSpeede, rlLeftSpeede, inLeftSpeedi, inRightSpeedi, rlRightSpeedi, rlLeftSpeedi;
	public Manipulation()
	{
		inLeft = new TalonSRX(RobotMap.inLeft);
		inRight = new TalonSRX(RobotMap.inRight);
		rlRight = new TalonSRX(RobotMap.rlRight);
		rlLeft = new TalonSRX(RobotMap.rlLeft);
		inLeftSpeede = .75;
		inRightSpeede = .75;
		rlRightSpeede = .75;
		rlLeftSpeede = .75;
		inLeftSpeedi= .6;
		inRightSpeedi = .6;
		rlRightSpeedi = .6;
		rlLeftSpeedi = .6;
		
	
	}
	
	public void elIntake() //for roller claw on elevator
	{
		rlLeft.set(ControlMode.PercentOutput, rlLeftSpeedi);
		rlRight.set(ControlMode.PercentOutput, -rlRightSpeedi);
	}
	
	public void elEject() //for roller claw on elevator
	{
		rlLeft.set(ControlMode.PercentOutput, -rlLeftSpeede);
		rlRight.set(ControlMode.PercentOutput, rlRightSpeede);
	}
	
	public void groundIntake() //for ground intake
	{
		inRight.set(ControlMode.PercentOutput, inRightSpeedi);
		inLeft.set(ControlMode.PercentOutput, -inLeftSpeedi);
	}
	
	public void groundEject() //for ground intake
	{
		inRight.set(ControlMode.PercentOutput, -inRightSpeedi);
		inLeft.set(ControlMode.PercentOutput, inLeftSpeede);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new mpTestManip());
    }
}

