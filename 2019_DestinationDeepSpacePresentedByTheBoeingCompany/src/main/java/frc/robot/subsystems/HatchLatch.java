/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * Iain was here!
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

  public void reset() {
    latchLocation = false;
    latchState = false; 

    updateShuffleBoard();
  }

  void updateShuffleBoard() {
    Robot.hatchOpen.setBoolean(latchState);
    Robot.hatchExtend.setBoolean(latchLocation);
  }

  public void setLatch() { 
    RobotMap.hatchGrab.set(latchState);
    RobotMap.hatchMove.set(latchLocation);

    updateShuffleBoard();
  }

  public void safeMode() {
    latchState = false;
    latchLocation = false;

    updateShuffleBoard();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    // setDefaultCommand(new SetHatchLatch());
  }
}
