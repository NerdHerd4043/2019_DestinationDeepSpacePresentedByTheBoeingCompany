/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.SetYeet;


/**
 * Add your docs here.
 */
public class CargoIntake extends Subsystem {
  private boolean cargoUp = false;


  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public void up() {
    RobotMap.CargoPos.set(true);
  }

  public void down() {
    RobotMap.CargoPos.set(false);
  }

  public void reverseYeet() {
    cargoUp = false;
    RobotMap.cargoConv.set(-0.5);
  }

  public void stopYeet() {
    RobotMap.cargoConv.set(0);
  }

  public void switchYeet() {
    cargoUp = !cargoUp;
  }

  public void setYeet() {
    if (cargoUp) {
      RobotMap.cargoConv.set(0.5);  
    } else {
      RobotMap.cargoConv.set(0);  
    }
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new SetYeet());
  }
}