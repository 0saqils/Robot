// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

// import java.io.ObjectInputStream.GetField;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.xrp.XRPGyro;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  // private XboxController Xbox = new XboxController(0);
  // private XRPGyro Gyro = new XRPGyro();
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  private final XRPDrivetrain m_drivetrain = new XRPDrivetrain();

  private final Timer m_timer = new Timer();
  private double lCorrect=0;
  private double rCorrect=0;
  

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {}

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);

    m_drivetrain.resetEncoders();
    m_timer.reset();
    m_timer.start();
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        if (m_drivetrain.getLeftDistanceInch() < m_drivetrain.getRightDistanceInch()) {
          rCorrect += .005;
          lCorrect -= .005;
        }
        if (m_drivetrain.getLeftDistanceInch() > m_drivetrain.getRightDistanceInch()) {
          lCorrect += .005;
          rCorrect -= .005;
        }
        m_drivetrain.tankDrive(1-lCorrect, 1-rCorrect);
        // Put custom auto code here

        // if (m_timer.get() < 5) {
        //   m_drivetrain.arcadeDrive(1.0, 0);
        // }
        // else {
        //   if (m_timer.get() > 5 & m_timer.get() < 10) {
        //   m_drivetrain.arcadeDrive(-1.0, 0);
        //   }
        //   else {
        //     m_drivetrain.arcadeDrive(0, 0);
        //   }
        // }
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {}

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    // lCorrect = m_drivetrain.getLeftDistanceInch();
    // rCorrect = m_drivetrain.getRightDistanceInch();
    // double red = m_drivetrain.getLeftDistanceInch();
    // double blue = m_drivetrain.getRightDistanceInch();
    // m_drivetrain.tankDrive(-Xbox.getLeftY()-lCorrect, -Xbox.getLeftY()-rCorrect);
    // m_drivetrain.arcadeDrive(0,-Xbox.getRightX());
    // if (Xbox.getAButton() == true ) {
    //   if (m_drivetrain.getLeftDistanceInch() > m_drivetrain.getRightDistanceInch()) {

    //     m_drivetrain.tankDrive(-Xbox.getLeftY()-leftslow, -Xbox.getLeftY());
    //   }
    //   else if (m_drivetrain.getLeftDistanceInch() < m_drivetrain.getRightDistanceInch()) {

    //     m_drivetrain.tankDrive(-Xbox.getLeftY(), -Xbox.getLeftY()-rightslow);
    //   }

    // }
    // else {
    //   Gyro.reset();
    //   m_drivetrain.arcadeDrive(-Xbox.getLeftY(),-Xbox.getRightX());
    // } 
  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}
