package org.usfirst.frc.team1403.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team1403.robot.Robot;
import org.usfirst.frc.team1403.robot.RobotMap;
import org.usfirst.frc.team1403.robot.commands.dtJoyDrive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class DriveTrain extends Subsystem 
{

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public  TalonSRX frontLeft, backLeft, frontRightencR, backRightencL;
	public double getRawAxisLeft;
	public double getRawAxisRight;
    public AnalogGyro gyro;
    
    public DriveTrain()
    {	
    	
    	frontLeft = new TalonSRX(RobotMap.frontLeftMotor);
    	backLeft = new TalonSRX(RobotMap.backLeftMotor);
    	frontRightencR = new TalonSRX(RobotMap.frontRightMotor);
    	backRightencL = new TalonSRX(RobotMap.backRightMotor);
    	gyro = new AnalogGyro(RobotMap.gyroSensor);
    	getRawAxisLeft = 0;
		getRawAxisRight = 0;
	}
    
    public void initDefaultCommand() 
    { 
    	setDefaultCommand(new dtJoyDrive()); 
    }
    
    public void setLeftRightPower(double leftPower, double rightPower)
    {
    	frontLeft.set(ControlMode.PercentOutput, leftPower);
    	backLeft.set(ControlMode.PercentOutput, leftPower);
    	frontRightencR.set(ControlMode.PercentOutput, -rightPower);
    	backRightencL.set(ControlMode.PercentOutput, -rightPower); 
    }

    public void drive()
    {
    	getRawAxisLeft = Robot.m_oi.djoy.getRawAxis(1);
    	getRawAxisRight = Robot.m_oi.djoy.getRawAxis(5);
    	
    	if(Robot.m_oi.djoy.getRawButton(6))
    	{
    		frontLeft.set(ControlMode.PercentOutput, getRawAxisLeft*0.5);
    		backLeft.set(ControlMode.PercentOutput, getRawAxisLeft*0.5);
    		frontRightencR.set(ControlMode.PercentOutput, -getRawAxisRight*0.5);
    		backRightencL.set(ControlMode.PercentOutput, -getRawAxisRight*0.5);
    	}
    	
    	else
    	{
    		frontLeft.set(ControlMode.PercentOutput,getRawAxisLeft);
    		backLeft.set(ControlMode.PercentOutput, getRawAxisLeft);
    		frontRightencR.set(ControlMode.PercentOutput, -getRawAxisRight);
    		backRightencL.set(ControlMode.PercentOutput, -getRawAxisRight);
    	}
    }
    
    public void stop()
    {
    	frontLeft.set(ControlMode.PercentOutput, 0.0);
    	backLeft.set(ControlMode.PercentOutput, 0.0);
    	frontRightencR.set(ControlMode.PercentOutput, 0.0);
    	backRightencL.set(ControlMode.PercentOutput, 0.0);
    }
   
    public void resetEncoders()
    {
    	frontRightencR.getSensorCollection().setQuadraturePosition(0, 0);
    	backRightencL.getSensorCollection().setQuadraturePosition(0, 0);
    }
    
    public double getLeftPosition() { return -frontRightencR.getSensorCollection().getQuadraturePosition(); } //* RobotMap.feetPerTick
    public double getRightPosition() { return backRightencL.getSensorCollection().getQuadraturePosition(); } //* RobotMap.feetPerTick-
    
    public static void setSpeed(TalonSRX talon, double speed) {
    	talon.set(ControlMode.PercentOutput, speed);
    }
}
