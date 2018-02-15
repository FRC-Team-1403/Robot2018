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

	public Manipulation()
	{
		inLeft = new TalonSRX(RobotMap.inLeft);
		inRight = new TalonSRX(RobotMap.inRight);
		rlRight = new TalonSRX(RobotMap.rlRight);
		rlLeft = new TalonSRX(RobotMap.rlLeft);
	
	}
	
	public void elIntake(double speed) //for roller claw on elevator
	{
		rlLeft.set(ControlMode.PercentOutput, speed);
		rlRight.set(ControlMode.PercentOutput, -speed);
	}
	
	public void elEject(double speed) //for roller claw on elevator
	{
		rlLeft.set(ControlMode.PercentOutput, -speed);
		rlRight.set(ControlMode.PercentOutput, speed);
	}
	
	public void groundIntake(double speed) //for ground intake
	{
		inRight.set(ControlMode.PercentOutput, speed);
		inLeft.set(ControlMode.PercentOutput, -speed);
	}
	
	public void groundEject(double speed) //for ground intake
	{
		inRight.set(ControlMode.PercentOutput, -speed);
		inLeft.set(ControlMode.PercentOutput, speed);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new mpTestManip());
    }
}

