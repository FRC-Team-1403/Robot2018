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

    public DigitalInput opticHigh, opticMax, opticMid, opticLow;
    public AnalogInput infrared;
    public TalonSRX elMotor;

    
    public Elevator() {
    	opticHigh = new DigitalInput(RobotMap.OpticHigh);
    	opticMid = new DigitalInput(RobotMap.OpticMid);
    	opticLow = new DigitalInput(RobotMap.OpticLow);
    	opticMax = new DigitalInput(RobotMap.OpticMax);
    	elMotor = new TalonSRX(RobotMap.elevatorMotor);
    }

    public void Move(boolean direction) {
    	if (direction) {
    		elMotor.set(ControlMode.PercentOutput, 0.5);
    		}
    	else {
    		elMotor.set(ControlMode.PercentOutput, -0.5);
    		}
    }
    public void move(double position) {
    	elMotor.set(ControlMode.PercentOutput, 0.5*position);
    }
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

