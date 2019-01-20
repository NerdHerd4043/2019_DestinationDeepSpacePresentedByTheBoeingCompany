/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.Robot;

/**
 * Add your docs here.
 */
public class RumbleRight extends TimedCommand {
  /**
   * Add your docs here.
   */
  public RumbleRight(double timeout) {
    super(timeout);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.rumble);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.rumble.rumbleRight();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Called once after timeout
  @Override
  protected void end() {
    Robot.rumble.stopRumble();
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      System.out.println("INTERRRUPTED");
    }
    Robot.rumble.rumbleRight();
    Robot.rumble.rumbleLeft();
    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {
      System.out.println("INTERRRUPTED");
    }
    Robot.rumble.stopRumble();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.rumble.stopRumble();
  }
}
