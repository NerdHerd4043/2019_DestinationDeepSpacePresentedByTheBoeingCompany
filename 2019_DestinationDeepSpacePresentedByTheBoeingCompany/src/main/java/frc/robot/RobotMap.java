/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;


public class RobotMap {
  public static WPI_TalonSRX motorFR = new WPI_TalonSRX(13);
  public static WPI_TalonSRX motorFL = new WPI_TalonSRX(11);
  public static WPI_TalonSRX motorBR = new WPI_TalonSRX(12);
  public static WPI_TalonSRX motorBL = new WPI_TalonSRX(15);

  public static Solenoid hatchGrab = new Solenoid(7, 5);
  public static Solenoid hatchMove = new Solenoid(7, 4);
  public static WPI_TalonSRX cargoConv = new WPI_TalonSRX(2);

  // public static Solenoid cargoPos = new Solenoid(7, 8);
  public static Solenoid shifter = new Solenoid(7, 6);
}
