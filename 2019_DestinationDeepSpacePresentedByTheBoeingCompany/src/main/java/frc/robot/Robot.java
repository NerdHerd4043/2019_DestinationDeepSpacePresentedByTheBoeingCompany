/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;

// import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;   

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.TimedRobot;
// import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

import frc.robot.subsystems.*;
import frc.robot.commands.SafeMode;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static RumbleRumble rumble;
  public static Drivetrain drivetrain;
  public static HatchLatch hatchLatch;
  public static CargoIntake cargoIntake;
  public static Climber climber;

  public static DigitalInput cLimit;

  public static AHRS ahrs;

  public static NetworkTableEntry collisionDetection;
  public static NetworkTableEntry arcadeDrive;
  public static NetworkTableEntry hatchExtend;
  public static NetworkTableEntry hatchOpen;

  public static CameraServer inst;
  public static MjpegServer server;
  public static UsbCamera usobo1;
  public static UsbCamera usobo2;

  public static double currAccelX;
  public static double lastAccelX;
  public static double currentJerkX;

  public static double currAccelY;
  public static double lastAccelY;
  public static double currentJerkY;

  public static boolean collisionDetected;
  public static double jerkThreshold;

  //ALWAYS INITIALIZE YOUR OI AFTER ALL THE OTHER SUBSYSTEMS
  public static OI m_oi;

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    ahrs = new AHRS(SPI.Port.kMXP);

    rumble = new RumbleRumble();
    drivetrain = new Drivetrain();
    hatchLatch = new HatchLatch();
    
    cargoIntake = new CargoIntake();

    climber = new Climber();

    cLimit = new DigitalInput(9);

    RobotMap.hatchGrab.set(false);
    RobotMap.hatchMove.set(false);

    ShuffleboardTab shuffTab = Shuffleboard.getTab("Drive");

    collisionDetection = shuffTab
      .add("Collision Detection", false)
      .withWidget(BuiltInWidgets.kToggleButton)
      .withPosition(0, 3)
      .getEntry();  

    arcadeDrive = shuffTab
      .add("Arcade Drive", true) 
      .withWidget(BuiltInWidgets.kToggleButton)
      .withPosition(0, 2)
      .getEntry();

    hatchOpen = shuffTab
      .add("HatchOpen", false) 
      .withWidget(BuiltInWidgets.kBooleanBox)
      .withPosition(0, 1)
      .getEntry();

    hatchExtend = shuffTab
      .add("HatchExtend", false) 
      .withWidget(BuiltInWidgets.kBooleanBox)
      .withPosition(0, 0)
      .getEntry();  

    CameraServer inst = CameraServer.getInstance();

    usobo1 = new UsbCamera("Forward Cam", 0);
    inst.addCamera(usobo1);
    usobo2 = new UsbCamera("Other Cam", 1);
    inst.addCamera(usobo2);

    server = inst.addServer("serve_USB Camera 0");
    server.setSource(usobo1);
    server.getProperty("compression").set(-1);

    shuffTab
      .add("Forward Cam", usobo1)
      .withWidget(BuiltInWidgets.kCameraStream)
      .withPosition(1, 0)
      .withSize(5, 4);  

    // shuffTab
    //   .add("Other Cam", usobo2)
    //   .withWidget(BuiltInWidgets.kCameraStream)
    //   .withPosition(6, 0)
    //   .withSize(4, 4);

    // CameraServer.getInstance().startAutomaticCapture("Forward Cam", 0);
    // CameraServer.getInstance().startAutomaticCapture("Other Cam", 1);

    m_oi = new OI();

  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    if (collisionDetection.getBoolean(true)) {
      getJerk();
      if (collisionDetected) {
        new SafeMode();
      }
    }
  }

  public void getJerk() {
    collisionDetected = false;

    currAccelX = ahrs.getWorldLinearAccelX();
    currentJerkX = currAccelX - lastAccelX;
    lastAccelX = currAccelX;

    currAccelY = ahrs.getWorldLinearAccelY();
    currentJerkY = currAccelY - lastAccelY;
    lastAccelY = currAccelY;
    // System.out.println(currentJerkX);
    // System.out.println(currentJerkY);
    
    if ( ( Math.abs(currentJerkX) > jerkThreshold ) ||
          ( Math.abs(currentJerkY) > jerkThreshold) ) {
        collisionDetected = true;
        // System.out.println(collisionDetected);
    }
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    hatchLatch.reset();


    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    // Scheduler.getInstance().run();
    teleopPeriodic();
  }

  @Override
  public void teleopInit() {
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
