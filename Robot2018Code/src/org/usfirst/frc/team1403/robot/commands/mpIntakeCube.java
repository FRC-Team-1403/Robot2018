package org.usfirst.frc.team1403.robot.commands;

import org.usfirst.frc.team1403.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class mpIntakeCube extends Command {

    public mpIntakeCube() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.manip);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.manip.limitSwitch.get())
    	{
    		Robot.manip.clawLeft.set(ControlMode.PercentOutput, 0);
        	Robot.manip.clawRight.set(ControlMode.PercentOutput, 0);
        	Robot.manip.intakeRight.set(ControlMode.PercentOutput, 0);
        	Robot.manip.intakeLeft.set(ControlMode.PercentOutput, 0);
    	}
    	else
    	{
    		Robot.manip.clawIntake();
        	Robot.manip.groundIntake();
    	}
   }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.manip.clawLeft.set(ControlMode.PercentOutput, 0);
    	Robot.manip.clawRight.set(ControlMode.PercentOutput, 0);
    	Robot.manip.intakeRight.set(ControlMode.PercentOutput, 0);
    	Robot.manip.intakeLeft.set(ControlMode.PercentOutput, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.manip.clawLeft.set(ControlMode.PercentOutput, 0);
    	Robot.manip.clawRight.set(ControlMode.PercentOutput, 0);
    	Robot.manip.intakeRight.set(ControlMode.PercentOutput, 0);
    	Robot.manip.intakeLeft.set(ControlMode.PercentOutput, 0);
    }
}
