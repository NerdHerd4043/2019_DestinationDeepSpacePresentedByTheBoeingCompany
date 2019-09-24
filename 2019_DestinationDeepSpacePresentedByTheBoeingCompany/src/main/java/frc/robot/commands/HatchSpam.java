/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class HatchSpam extends Command {
  private double lastSwitch = 0;

  public HatchSpam() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.hatchLatch);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    System.out.println("Spam Started");
    lastSwitch = Timer.getFPGATimestamp();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(Timer.getFPGATimestamp() >= lastSwitch + 0.2    ) {
      lastSwitch = Timer.getFPGATimestamp();
      Robot.hatchLatch.toggleState();
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.hatchLatch.reset();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.hatchLatch.reset();
  }
}
