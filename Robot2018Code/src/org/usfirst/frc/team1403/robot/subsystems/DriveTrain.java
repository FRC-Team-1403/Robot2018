package org.usfirst.frc.team1403.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team1403.robot.Robot;
import org.usfirst.frc.team1403.robot.RobotMap;
import org.usfirst.frc.team1403.robot.commands.dtDriveWithJoystick;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class DriveTrain extends Subsystem 
{

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public static TalonSRX frontLeft, backLeftencR, frontRight, backRight;
	public double getRawAxisLeft;
	public double getRawAxisRight;
    public AnalogGyro gyro;
    
    public DriveTrain()
    {	
    	
    	frontLeft = new TalonSRX(RobotMap.frontLeftMotor);
    	backLeftencR = new TalonSRX(RobotMap.backLeftMotorencR);
    	frontRight = new TalonSRX(RobotMap.frontRightMotor);
    	backRight = new TalonSRX(RobotMap.backRightMotor);
    	gyro = new AnalogGyro(RobotMap.gyroSensor);
    	getRawAxisLeft = .0;
    	getRawAxisRight = .0;
    	
	}
    
    public void initDefaultCommand()
    { 
    	setDefaultCommand(new dtDriveWithJoystick()); 
    }
    
    public void setLeftRightPower(double leftPower, double rightPower)
    {
    	frontLeft.set(ControlMode.PercentOutput, leftPower);
    	backLeftencR.set(ControlMode.PercentOutput, leftPower);
    	frontRight.set(ControlMode.PercentOutput, -rightPower);
    	backRight.set(ControlMode.PercentOutput, -rightPower); 
    }

    public void drive()
    {
    	
    	
    	if(!RobotState.isAutonomous())
    	{
    		getRawAxisLeft = -Robot.m_oi.djoy.getRawAxis(1);
        	getRawAxisRight = -Robot.m_oi.djoy.getRawAxis(5);
    		
    		if(Robot.m_oi.djoy.getRawButton(5))
    		{
    			frontLeft.set(ControlMode.PercentOutput, getRawAxisLeft*0.5);
    			backLeftencR.set(ControlMode.PercentOutput, -getRawAxisLeft*0.5);
    			frontRight.set(ControlMode.PercentOutput, getRawAxisRight*0.5);
    			backRight.set(ControlMode.PercentOutput, getRawAxisRight*0.5);
    		}
    	
    		else
    		{
    			frontLeft.set(ControlMode.PercentOutput, getRawAxisLeft);
    			backLeftencR.set(ControlMode.PercentOutput, -getRawAxisLeft);
    			frontRight.set(ControlMode.PercentOutput, getRawAxisRight);
    			backRight.set(ControlMode.PercentOutput, getRawAxisRight);
    		}
    	}
    }
    
    public void stop()
    {
    	frontLeft.set(ControlMode.PercentOutput, 0.0);
    	backLeftencR.set(ControlMode.PercentOutput, 0.0);
    	frontRight.set(ControlMode.PercentOutput, 0.0);
    	backRight.set(ControlMode.PercentOutput, 0.0);
    }
   
    public void resetEncoders()
    {
    	backLeftencR.getSensorCollection().setQuadraturePosition(0, 0);
    	Robot.manip.clawLeftencL.getSensorCollection().setQuadraturePosition(0, 0);
    }
    
    public double getRightPosition() { return -backLeftencR.getSensorCollection().getQuadraturePosition(); } //* RobotMap.feetPerTick
    public double getLeftPosition() { return Robot.manip.clawLeftencL.getSensorCollection().getQuadraturePosition(); } //* RobotMap.feetPerTick-
    
    public static void setSpeed(TalonSRX talon, double speed) {
    	talon.set(ControlMode.PercentOutput, speed);
    }
}
