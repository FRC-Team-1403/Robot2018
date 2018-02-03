package org.usfirst.frc.team1403.robot.subsystems;

import org.usfirst.frc.team1403.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Manipulation extends Subsystem {

    public TalonSRX leftIntake;
    public TalonSRX rightIntake;
    public TalonSRX rollerMotorRight;
    public TalonSRX rollerMotorLeft;

	public Manipulation() {
		leftIntake = new TalonSRX(RobotMap.intakeLeft);
		rightIntake = new TalonSRX(RobotMap.intakeRight);
		rollerMotorRight = new TalonSRX(RobotMap.rollerRight);
		rollerMotorLeft = new TalonSRX(RobotMap.rollerLeft);
	}
	
	public void intake(double speed) {
		leftIntake.set(ControlMode.PercentOutput, speed);
		rightIntake.set(ControlMode.PercentOutput, -speed);
	}
	public void eject(double speed) {
		leftIntake.set(ControlMode.PercentOutput, -speed);
		rightIntake.set(ControlMode.PercentOutput, speed);
	}
	public void intakeRollers(double speed) {
		rollerMotorRight.set(ControlMode.PercentOutput, speed);
		rollerMotorLeft.set(ControlMode.PercentOutput, -speed);
	}
	public void ejectRollers(double speed) {
		rollerMotorRight.set(ControlMode.PercentOutput, -speed);
		rollerMotorLeft.set(ControlMode.PercentOutput, speed);
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

