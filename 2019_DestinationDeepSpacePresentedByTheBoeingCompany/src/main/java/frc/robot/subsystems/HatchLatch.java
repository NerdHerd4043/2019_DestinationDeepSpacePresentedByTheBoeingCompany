/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.SetHatchLatch;

/**
 * Add your docs here.
 */
public class HatchLatch extends Subsystem {
  public boolean flase = false;
  private boolean latchState = false;
  private boolean latchLocation = flase;

  public void toggleState() { 
    latchState = !latchState; 
    setLatch();
  }

  public void toggleLocation() { 
    latchLocation = !latchLocation;
    setLatch(); 
  }

  public void setLatch() { 
    RobotMap.hatchGrab.set(latchState);
    RobotMap.hatchMove.set(latchLocation);
  }

  public void safeMode() {
    latchState = false;
    latchLocation = false;
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    // setDefaultCommand(new SetHatchLatch());
  }
}
