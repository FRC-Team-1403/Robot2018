package org.usfirst.frc.team1403.robot.subsystems;

import org.usfirst.frc.team1403.robot.Robot;
import org.usfirst.frc.team1403.robot.RobotMap;
import org.usfirst.frc.team1403.robot.commands.elMoveJoystick;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {

    public DigitalInput opticSwitch, opticIntake;
    public AnalogInput infrared;
    public static TalonSRX elMotor;
	public double getRawAxisRight;

    
    public Elevator()
    {
    	opticSwitch = new DigitalInput(RobotMap.opticSwitch);
    	opticIntake = new DigitalInput(RobotMap.opticIntake);

    	getRawAxisRight = 0;
    	//infared = new AnalogInput(0);
    	elMotor = new TalonSRX(RobotMap.elevatorMotor);
    }

    public void Move(boolean direction) // false = down; true = up
    {
    	if (direction) { elMotor.set(ControlMode.PercentOutput, -0.1); }
    	else { elMotor.set(ControlMode.PercentOutput, 0.1); }
    }
    
    public void move()
    { 
    	if(!RobotState.isAutonomous()) {
        	getRawAxisRight = -Robot.m_oi.ojoy.getRawAxis(5);
    		Elevator.elMotor.set(ControlMode.PercentOutput, -getRawAxisRight); 
    	}
    }
    public static void setSpeed(TalonSRX talon, double speed)
    {
    	talon.set(ControlMode.PercentOutput, speed);
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new elMoveJoystick());
    }
}

