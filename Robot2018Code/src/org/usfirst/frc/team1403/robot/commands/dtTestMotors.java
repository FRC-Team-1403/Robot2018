package org.usfirst.frc.team1403.robot.commands;

import org.usfirst.frc.team1403.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class dtTestMotors extends Command {

	int motor;
	
    public dtTestMotors(int motor) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivetrain);
        this.motor = motor;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute()
    {
    	switch(motor)
    	{
    	case 9: { Robot.drivetrain.frontLeft.set(ControlMode.PercentOutput, -0.25); break; }
    	case 5: { Robot.drivetrain.backLeft.set(ControlMode.PercentOutput, -0.25); break; }
    	case 3: { Robot.drivetrain.frontRightencR.set(ControlMode.PercentOutput, 0.25); break; }
    	case 2: { Robot.drivetrain.backRightencL.set(ControlMode.PercentOutput, 0.25); break; }
    	
    	case 1: { Robot.manip.intakeLeft.set(ControlMode.PercentOutput, -0.25); break; }
    	case 10: { Robot.manip.intakeRight.set(ControlMode.PercentOutput, -0.25); break; }
    	case 4: { Robot.manip.clawLeft.set(ControlMode.PercentOutput, 0.25); break; }
    	case 11: { Robot.manip.clawRight.set(ControlMode.PercentOutput, 0.25); break; }
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.frontLeft.set(ControlMode.PercentOutput, 0);
    	Robot.drivetrain.backLeft.set(ControlMode.PercentOutput, 0);
    	Robot.drivetrain.frontRightencR.set(ControlMode.PercentOutput, 0);
    	Robot.drivetrain.backRightencL.set(ControlMode.PercentOutput, 0);
    	Robot.manip.intakeLeft.set(ControlMode.PercentOutput, 0);
    	Robot.manip.intakeRight.set(ControlMode.PercentOutput, 0);
    	Robot.manip.clawLeft.set(ControlMode.PercentOutput, 0);
    	Robot.manip.clawRight.set(ControlMode.PercentOutput, 0);

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drivetrain.frontLeft.set(ControlMode.PercentOutput, 0);
    	Robot.drivetrain.backLeft.set(ControlMode.PercentOutput, 0);
    	Robot.drivetrain.frontRightencR.set(ControlMode.PercentOutput, 0);
    	Robot.drivetrain.backRightencL.set(ControlMode.PercentOutput, 0);
    	Robot.manip.intakeLeft.set(ControlMode.PercentOutput, 0);
    	Robot.manip.intakeRight.set(ControlMode.PercentOutput, 0);
    	Robot.manip.clawLeft.set(ControlMode.PercentOutput, 0);
    	Robot.manip.clawRight.set(ControlMode.PercentOutput, 0);
    }
}
