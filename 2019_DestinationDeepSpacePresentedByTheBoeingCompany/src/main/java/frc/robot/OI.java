/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVButton;
import frc.robot.commands.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  Joystick driveStick = new Joystick(0);

  Button hatchToggle = new JoystickButton(driveStick, 3);
  Button hatchInOut = new JoystickButton(driveStick, 4);
  Button rightRumble = new JoystickButton(driveStick, 9);
  Button leftRumble = new JoystickButton(driveStick, 10);

  POVButton ShiftBtn = new POVButton(driveStick, 3);
  POVButton AntiShiftBtn = new POVButton(driveStick, 7);
  Button CIn = new JoystickButton(driveStick, 1);
  // Currently no way to reverse the cargo intake, this button will be for that in the future.
  // Button COut = new JoystickButton(driveStick, 5); 

  public OI() {
    leftRumble.whenPressed(new RumbleLeft(1));
    rightRumble.whenPressed(new RumbleRight(1));

    hatchToggle.whenPressed(new LatchToggleGrab());
    hatchToggle.whenPressed(new LatchToggleLocation());

    ShiftBtn.whenPressed(new Shift());
    AntiShiftBtn.whenPressed(new AntiShift());
    CIn.toggleWhenPressed(new Yeet());
  }

  public void setRightRumble(double intensity) {
    driveStick.setRumble(RumbleType.kRightRumble, intensity);
  }

  public void setLeftRumble(double intensity) {
    driveStick.setRumble(RumbleType.kLeftRumble, intensity);
  }

  public Joystick getDrivestick() {
    return driveStick;
  }
}
