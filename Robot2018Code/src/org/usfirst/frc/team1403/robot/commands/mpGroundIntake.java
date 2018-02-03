package org.usfirst.frc.team1403.robot.commands;

import org.usfirst.frc.team1403.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class mpGroundIntake extends Command {

	private double speed;
	String direction;
    public mpGroundIntake(String direction, double speed) {
        // Use requires() here to declare subsystem dependencies
         requires(Robot.manip);
         this.direction = direction;
         this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(direction.equals("Intake") || direction.equals("In")) {
    		Robot.manip.intake(speed); 
    		Robot.manip.intakeRollers(speed);
    		}
    	else {
    		Robot.manip.intake(0); 
    		Robot.manip.intakeRollers(0);
    		
    	}
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
