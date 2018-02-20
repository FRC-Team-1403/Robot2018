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
	public double getRawAxisLeftoJoy;

    
    public Elevator()
    {
    	opticSwitch = new DigitalInput(RobotMap.opticSwitch);
    	opticIntake = new DigitalInput(RobotMap.opticIntake);

    	getRawAxisLeftoJoy = 0;
    	//infared = new AnalogInput(0);
    	elMotor = new TalonSRX(RobotMap.elevatorMotor);
    }

    public void Move(boolean direction) // false = down; true = up
    {
    	if(!RobotState.isAutonomous()) {
    		if (direction) { elMotor.set(ControlMode.PercentOutput, -0.75); }
    		else { elMotor.set(ControlMode.PercentOutput, 0.75); }
    	}
    }
    
    public void move()
    { 
    	if(!RobotState.isAutonomous()) {
    		getRawAxisLeftoJoy = -Robot.m_oi.ojoy.getRawAxis(1);
    		Elevator.elMotor.set(ControlMode.PercentOutput, -getRawAxisLeftoJoy); 
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

