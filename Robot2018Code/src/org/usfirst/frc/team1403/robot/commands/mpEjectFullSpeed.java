package org.usfirst.frc.team1403.robot.commands;

import org.usfirst.frc.team1403.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class mpEjectFullSpeed extends Command {

    public mpEjectFullSpeed() {
        // Use requires() here to declare subsystem dependencies
       requires(Robot.manip);
    }

    // Called just before this Command runs the first time
    protected void execute() {
    	Robot.manip.elEject(1);
    	Robot.manip.groundEject(1);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.manip.rlLeft.set(ControlMode.PercentOutput, 0);
    	Robot.manip.rlRight.set(ControlMode.PercentOutput, 0);
    	Robot.manip.inRight.set(ControlMode.PercentOutput, 0);
    	Robot.manip.inLeft.set(ControlMode.PercentOutput, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.manip.rlLeft.set(ControlMode.PercentOutput, 0);
    	Robot.manip.rlRight.set(ControlMode.PercentOutput, 0);
    	Robot.manip.inRight.set(ControlMode.PercentOutput, 0);
    	Robot.manip.inLeft.set(ControlMode.PercentOutput, 0);
    }
}
