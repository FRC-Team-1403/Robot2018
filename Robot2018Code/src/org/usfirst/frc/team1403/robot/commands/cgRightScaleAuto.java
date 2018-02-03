package org.usfirst.frc.team1403.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class cgRightScaleAuto extends CommandGroup {

    public cgRightScaleAuto() {
        // Add Commands here:
    	addSequential(new dtDriveTimeGyro(5, 0.5));
        addSequential(new dtSturn90("Left"));
        addSequential(new elMove(3));
        addSequential(new mpRollerClaw(), 1);
        addSequential(new elMove(1));

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
