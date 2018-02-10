package org.usfirst.frc.team1403.robot.subsystems;

import org.usfirst.frc.team1403.robot.Robot;
import org.usfirst.frc.team1403.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {

    public DigitalInput  opticSwitch, opticIntake, opticMax;
    public AnalogInput infrared;
    public TalonSRX elMotor;

    
    public Elevator()
    {
    	opticSwitch = new DigitalInput(RobotMap.opticSwitch);
    	opticIntake = new DigitalInput(RobotMap.opticIntake);
    	opticMax = new DigitalInput(RobotMap.opticMax);
    	elMotor = new TalonSRX(RobotMap.elevatorMotor);
    }

    public void Move(boolean direction) // false = down; true = up
    {
    	if (direction) { elMotor.set(ControlMode.PercentOutput, 0.5); }
    	else { elMotor.set(ControlMode.PercentOutput, -0.5); }
    }
    
    public void move(double position) { elMotor.set(ControlMode.PercentOutput, 0.5*position); }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

