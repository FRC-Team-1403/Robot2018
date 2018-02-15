package org.usfirst.frc.team1403.robot.commands;

import org.usfirst.frc.team1403.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class mpManipulate extends Command {

	private double speed;
	private String direction;
    public mpManipulate(String direction, double speed) {
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
    	
    	if(direction.toLowerCase().equals("eject"))
    	{
    		Robot.manip.elEject(speed);
    		if(Robot.elevator.opticIntake.get()) 
    		{ 
    			Robot.manip.groundEject(speed); 
    		}
    	}
    	
    	else
    	{
 /*   		while(!Robot.manip.limitSwitch.get())
    		{	
    			Robot.manip.groundIntake(speed); 
    			Robot.manip.elIntake(speed);
    		}
    		Robot.manip.groundIntake(0); 
    		Robot.manip.elIntake(0); */
    	}
  
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.manip.rlLeft.set(ControlMode.PercentOutput, 0);
    	Robot.manip.rlRight.set(ControlMode.PercentOutput, 0);

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
