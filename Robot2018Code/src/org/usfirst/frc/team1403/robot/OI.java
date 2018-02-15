/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1403.robot;

import org.usfirst.frc.team1403.robot.commands.cgDownIntake;
import org.usfirst.frc.team1403.robot.commands.dtAllStop;
import org.usfirst.frc.team1403.robot.commands.dtDriveWithGyro;
import org.usfirst.frc.team1403.robot.commands.dtResetEncoders;
import org.usfirst.frc.team1403.robot.commands.dtSturnJoy;
import org.usfirst.frc.team1403.robot.commands.dtTestMotors;
import org.usfirst.frc.team1403.robot.commands.elMove;
import org.usfirst.frc.team1403.robot.commands.mpEject;
import org.usfirst.frc.team1403.robot.commands.mpEjectFullSpeed;
import org.usfirst.frc.team1403.robot.commands.mpIntake;
import org.usfirst.frc.team1403.robot.commands.mpIntakeFullSpeed;
import org.usfirst.frc.team1403.robot.commands.mpManipulate;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	public Joystick djoy = new Joystick(0);
	public Button aDjoy = new JoystickButton(djoy, 1);
	public Button bDjoy = new JoystickButton(djoy, 2);
	public Button xDjoy = new JoystickButton(djoy, 3);
	public Button yDjoy = new JoystickButton(djoy, 4);
	public Button rbDjoy = new JoystickButton(djoy, 5);
	public Button lbDjoy = new JoystickButton(djoy, 6);
	public Button backDjoy = new JoystickButton(djoy,7);
	public Button startDjoy =  new JoystickButton(djoy, 8);

	public Joystick ojoy = new Joystick(1);
	public Button aOjoy = new JoystickButton(ojoy, 1);
	public Button bOjoy = new JoystickButton(ojoy, 2);
	public Button xOjoy = new JoystickButton(ojoy, 3);
	public Button yOjoy = new JoystickButton(ojoy, 4);
	public Button rbOjoy = new JoystickButton(ojoy, 5);
	public Button lbOjoy = new JoystickButton(ojoy, 6);
	public Button backOjoy = new JoystickButton(ojoy,7);
	public Button startOjoy =  new JoystickButton(ojoy, 8);
	
	public Joystick tjoy = new Joystick(2);
	public Button aTjoy = new JoystickButton(tjoy, 1);
	public Button bTjoy = new JoystickButton(tjoy, 2);
	public Button xTjoy = new JoystickButton(tjoy, 3);
	public Button yTjoy = new JoystickButton(tjoy, 4);
	public Button lbTjoy = new JoystickButton(tjoy, 5);
	public Button rbTjoy = new JoystickButton(tjoy, 6);
	public Button backTjoy = new JoystickButton(tjoy,7);
	public Button startTjoy =  new JoystickButton(tjoy, 8);
	public Button leftTrigger = new JoystickButton(tjoy, 9);
	public Button rightTrigger = new JoystickButton(tjoy, 9);


	public OI() {
		
		aDjoy.whenReleased(new dtResetEncoders());
		bDjoy.whileHeld(new dtDriveWithGyro(0.5));
		lbDjoy.whileHeld(new dtSturnJoy("Left"));
		rbDjoy.whileHeld(new dtSturnJoy("Right"));
		
		//lbOjoy.whileHeld(new cgDownIntake());
		//lbOjoy.whileHeld(new mpManipulate("Intake", 0.6));
		lbOjoy.whileHeld(new mpIntake());
		//rbOjoy.whileHeld(new mpManipulate("Eject", 0.6));
		rbOjoy.whileHeld(new mpEject());
		backOjoy.whenPressed(new elMove(1));
		startOjoy.whenPressed(new elMove(2));
		aOjoy.whenPressed(new elMove(3));
		bOjoy.whileHeld(new dtAllStop());
		xOjoy.whileHeld(new mpIntakeFullSpeed());
		yOjoy.whileHeld(new mpEjectFullSpeed());
		
		//TESTING CODE (with test joystick)
		
		
		aTjoy.whileHeld(new dtTestMotors(1));
		bTjoy.whileHeld(new dtTestMotors(10));
		xTjoy.whileHeld(new dtTestMotors(4));
		yTjoy.whileHeld(new dtTestMotors(11));


	}
}
