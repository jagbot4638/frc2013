/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package org.northwestrobotics.frc2013;

import org.northwestrobotics.frc2013.shooter.Shooter;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;

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

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        driver = new Driver();
        aimingController = new Joystick(RobotConstants.Shooting.AIMING_CONTROLLER);
        shooter = new Shooter(aimingController);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
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
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        driver.test();
    }
}
