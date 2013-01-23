/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.northwestrobotics.frc2013;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Timer;
/**
 *
 * @author Saagar
 */
class Driver {
    
    final static double threshold = .5;
    
    double joystickX = 0.0;
    double joystickY = 0.0;
    
    Joystick stick1;
    RobotDrive drive1;
    
    public Driver(){
      
    }
    
    public void drive(){
        double previousJoystickX = joystickX;
        double previousJoystickY = joystickY;
        userInput();
        minimizeMotorDamage(previousJoystickX, previousJoystickY);
        moveMotors();
    }

    private void userInput() {
        
        joystickY = stick1.getAxis(Joystick.AxisType.kY);
        joystickX = stick1.getAxis(Joystick.AxisType.kX);
        
    }

    private void minimizeMotorDamage(double previousJoystickX, double
            previousJoystickY) {
        // if (joystickX - previousJoystickX ...) joystickX = ...;
    }

    private void moveMotors() {
        drive1.arcadeDrive(joystickX, joystickY);
    }
}
