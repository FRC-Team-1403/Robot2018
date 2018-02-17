package org.usfirst.frc.team1403.robot.commands;

import org.usfirst.frc.team1403.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class elMove extends Command {
	
	int spot;
	boolean direction = true;
	DigitalInput gate = Robot.elevator.opticIntake;
	
    public elMove(int spot) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.elevator);
        this.spot = spot;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	switch(spot)
    	{
    	case 2:
    		direction = true; 
    		gate = Robot.elevator.opticSwitch; 
    		break;
    	case 1:
    		direction = false; 
    		gate = Robot.elevator.opticIntake;
    		break; 
    	}
    	
    	
    	while (!gate.get()) 
    	{ 
    		Robot.elevator.Move(direction); 
    	}
    	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return gate.get();
    }

    // Called once after isFinished returns true
    protected void end() { 
    	Robot.elevator.elMotor.set(ControlMode.PercentOutput, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.elevator.elMotor.set(ControlMode.PercentOutput, 0);
    }
}
