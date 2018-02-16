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

    public DigitalInput  opticSwitch, opticIntake, opticMax;
    public AnalogInput infrared;
    public static TalonSRX elMotor;
	public double getRawAxisRight;

    
    public Elevator()
    {
    	opticSwitch = new DigitalInput(RobotMap.opticSwitch);
    	opticIntake = new DigitalInput(RobotMap.opticIntake);
    	opticMax = new DigitalInput(RobotMap.opticMax);
    	getRawAxisRight = 0;
    	//infared = new AnalogInput(0);
    	elMotor = new TalonSRX(RobotMap.elevatorMotor);
    }

    public void Move(boolean direction) // false = down; true = up
    {
    	if (direction) { elMotor.set(ControlMode.PercentOutput, 0.5); }
    	else { elMotor.set(ControlMode.PercentOutput, -0.5); }
    }
    
    public void move() 
    { 
    	if(!RobotState.isAutonomous()) {
        	getRawAxisRight = -Robot.m_oi.ojoy.getRawAxis(5);
    		Elevator.elMotor.set(ControlMode.PercentOutput, -getRawAxisRight); //If you want equal make it .69* the joy value
    	}
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new elMoveJoystick());
    }
}

