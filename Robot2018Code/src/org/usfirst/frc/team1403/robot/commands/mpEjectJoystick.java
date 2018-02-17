package org.usfirst.frc.team1403.robot.commands;

import org.usfirst.frc.team1403.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class mpEjectJoystick extends Command {

    public mpEjectJoystick() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.manip);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.manip.clawLeftencL.set(ControlMode.PercentOutput, -Robot.m_oi.ojoy.getRawAxis(5));
    	Robot.manip.clawRight.set(ControlMode.PercentOutput, -Robot.m_oi.ojoy.getRawAxis(5));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
