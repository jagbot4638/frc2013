/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package org.northwestrobotics.frc2013;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import org.northwestrobotics.frc2013.shooter.Shooter;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Jagbot extends IterativeRobot {

    private Joystick aimingController;
    private Driver driver;
    private Shooter shooter;
    private Compressor airCompressor;
    private Solenoid feeder;
    private int magazine = 4;
    private boolean isStart = true;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        driver = new Driver();
        aimingController = new Joystick(RobotConstants.Shooting.AIMING_CONTROLLER);


        airCompressor = new Compressor(RobotConstants.Pneumatics.PRESSURE_SWITCH_VALUE,
                RobotConstants.Pneumatics.COMPRESSOR_RELAY);
        feeder = new Solenoid(RobotConstants.Pneumatics.FEEDER_CHANNEL);

        shooter = new Shooter(aimingController, airCompressor, feeder);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        if (magazine > 0) {
            if (isStart) {
                shooter.activateShootMotorForAutonomous();
                Timer.delay(2);// second
                isStart = false;
            }
            shooter.getFeeder().set(true);
            Timer.delay(.5);
            shooter.getFeeder().set(false);
            magazine--;
        } else {
            shooter.deactivateShootMotorForAutonomous();
        }
        shooter.updatePressure();
        shooter.updateAutonomous();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        // Drive the robot in response to user input
        driver.drive();

        // Adjust the shooter arm angle based on the user input
        shooter.adjustAim();

        // Check and initiate shooting based on the fire button
        shooter.updateShooting();

        shooter.updatePressure();
        SmartDashboard.putNumber("Voltage", DriverStation.kBatteryChannel);
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    }
}
