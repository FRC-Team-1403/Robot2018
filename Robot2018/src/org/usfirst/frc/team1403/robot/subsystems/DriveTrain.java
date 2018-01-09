package org.usfirst.frc.team1403.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team1403.robot.Robot;

import com.ctre.CANTalon;
/**
 *
 */
public class DriveTrain extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public CANTalon m1; 
	public CANTalon m2;
	public CANTalon m3; 
	public CANTalon m4; 
	
	public DriveTrain()
	{
		m1 = new CANTalon(0);
		m2 = new CANTalon(1);
		m3 = new CANTalon(2);
		m4 = new CANTalon(3);
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void drive()
    {
    	m1.set(Robot.m_oi.stick.getRawAxis(1));
    	m2.set(Robot.m_oi.stick.getRawAxis(1));
    	m3.set(Robot.m_oi.stick.getRawAxis(5));
    	m4.set(Robot.m_oi.stick.getRawAxis(5));
    }
}

