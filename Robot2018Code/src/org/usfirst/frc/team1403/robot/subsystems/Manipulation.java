package org.usfirst.frc.team1403.robot.subsystems;

import org.usfirst.frc.team1403.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Manipulation extends Subsystem {

    public TalonSRX inLeft;
    public TalonSRX inRight;
    public TalonSRX rlRight;
    public TalonSRX rlLeft;

	public Manipulation() {
		inLeft = new TalonSRX(RobotMap.inLeft);
		inRight = new TalonSRX(RobotMap.inRight);
		rlRight = new TalonSRX(RobotMap.rlRight);
		rlLeft = new TalonSRX(RobotMap.rlLeft);
	}
	
	public void intake(double speed) {
		inLeft.set(ControlMode.PercentOutput, speed);
		inRight.set(ControlMode.PercentOutput, -speed);
	}
	
	public void eject(double speed) {
		inLeft.set(ControlMode.PercentOutput, -speed);
		inRight.set(ControlMode.PercentOutput, speed);
	}
	
	public void intakeRollers(double speed) {
		rlRight.set(ControlMode.PercentOutput, speed);
		rlLeft.set(ControlMode.PercentOutput, -speed);
	}
	
	public void ejectRollers(double speed) {
		rlRight.set(ControlMode.PercentOutput, -speed);
		rlLeft.set(ControlMode.PercentOutput, speed);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

