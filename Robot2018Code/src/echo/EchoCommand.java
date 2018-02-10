package echo;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class EchoCommand extends Command {
	
	protected double value;
	
    public EchoCommand(double value) {
    	this.value = value;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }
    
    protected void setValue(double value) {
    	
    }
    
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
