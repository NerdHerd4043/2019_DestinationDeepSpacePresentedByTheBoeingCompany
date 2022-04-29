/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;

/**
 * Add your docs here.
 */
public class RumbleRumble extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  double intensity = .75;

  public void rumbleRight() {
    Robot.m_oi.setRightRumble(intensity);
  }

  public void rumbleLeft() {
    Robot.m_oi.setLeftRumble(intensity);
  }

  public void stopRumble() {
    Robot.m_oi.setLeftRumble(0);
    Robot.m_oi.setRightRumble(0);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
