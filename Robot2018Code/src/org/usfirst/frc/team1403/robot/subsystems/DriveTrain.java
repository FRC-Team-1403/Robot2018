package org.usfirst.frc.team1403.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team1403.robot.Robot;
import org.usfirst.frc.team1403.robot.RobotMap;
import org.usfirst.frc.team1403.robot.commands.dtJoyDrive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class DriveTrain extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	//public TalonSRX motor4;
    //public TalonSRX motor5;
    public TalonSRX left1, left2, right1, right2;
    //public TalonSRX motor0, motor1;
    public AnalogGyro gyro;
    
    public DriveTrain()
    {	
    	left1 = new TalonSRX(RobotMap.frontLeftMotor); //left1
    	left2 = new TalonSRX(RobotMap.backLeftMotor); //left1
    	right1 = new TalonSRX(RobotMap.frontRightMotor); //left1
    	right2 = new TalonSRX(RobotMap.backRightMotor); //left1
    	gyro = new AnalogGyro(RobotMap.gyroSensor);
	}
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new dtJoyDrive());
    }
    
    public void setLeftRightPower(double leftPower, double rightPower)
    {
    	left1.set(ControlMode.PercentOutput, -leftPower);
    	left1.set(ControlMode.PercentOutput, -leftPower);
    	right1.set(ControlMode.PercentOutput, rightPower);
    	right1.set(ControlMode.PercentOutput, rightPower); 
    }

    public void resetEncoders()
    {
    	left1.getSensorCollection().setQuadraturePosition(0, 0);
    	right1.getSensorCollection().setQuadraturePosition(0, 0);
    }
    
    public void drive()
    {
    	if(Robot.m_oi.djoy.getRawButton(6)) {
    		left1.set(ControlMode.PercentOutput, Robot.m_oi.djoy.getRawAxis(1)*-0.5);
    		left2.set(ControlMode.PercentOutput, Robot.m_oi.djoy.getRawAxis(1)*-0.5);
    		right1.set(ControlMode.PercentOutput, Robot.m_oi.djoy.getRawAxis(5)*0.5);
    		right2.set(ControlMode.PercentOutput, Robot.m_oi.djoy.getRawAxis(5)*0.5);
    	}
    	
    	else {
    		left1.set(ControlMode.PercentOutput, -Robot.m_oi.djoy.getRawAxis(1));
    		left2.set(ControlMode.PercentOutput, -Robot.m_oi.djoy.getRawAxis(1));
    		right1.set(ControlMode.PercentOutput, Robot.m_oi.djoy.getRawAxis(5));
    		right2.set(ControlMode.PercentOutput, Robot.m_oi.djoy.getRawAxis(5));
    	}
    }
    
    public void stop()
    {
    	left1.set(ControlMode.PercentOutput, 0.0);
		left2.set(ControlMode.PercentOutput, 0.0);
    	right1.set(ControlMode.PercentOutput, 0.0);
    	right2.set(ControlMode.PercentOutput, 0.0);
    }
   
    public double getLeftPosition() { return left1.getSensorCollection().getQuadraturePosition() * RobotMap.feetPerTick; }
    public double getRightPosition() { return right1.getSensorCollection().getQuadraturePosition() * RobotMap.feetPerTick; }


}

