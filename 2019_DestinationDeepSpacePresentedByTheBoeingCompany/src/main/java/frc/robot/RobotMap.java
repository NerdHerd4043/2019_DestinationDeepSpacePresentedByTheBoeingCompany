/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;


<<<<<<< HEAD
=======
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
>>>>>>> Nick
public class RobotMap {
  public static WPI_TalonSRX motorFR = new WPI_TalonSRX(0);
  public static WPI_TalonSRX motorFL = new WPI_TalonSRX(1);
  public static WPI_TalonSRX motorBR = new WPI_TalonSRX(2);
  public static WPI_TalonSRX motorBL = new WPI_TalonSRX(3);
<<<<<<< HEAD

  public static Solenoid hatchGrab = new Solenoid(0, 0);
  public static Solenoid hatchMove = new Solenoid(0, 1);
=======
  public static WPI_TalonSRX cargoConv = new WPI_TalonSRX(4);

  public static Solenoid CargoPos = new Solenoid(0, 8);
>>>>>>> Nick
}
