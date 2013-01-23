/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.northwestrobotics.frc2013;

/**
 *
 * @author Saagar
 */
class Driver {
    
    final double threshold = .5;
    
    double joystickX = 0.0;
    double joystickY = 0.0;
    
    public Driver(){
        
    }
    
    public void drive(){
        double previousJoystickX = joystickX;
        double previousJoystickY = joystickY;
        userInput();
        minimizeMotorDamage(previousJoystickX, previousJoystickX);
        moveMotors();
    }

    private void userInput() {
        // joystickX = ...;
        // joystickY = ...;
    }

    private void minimizeMotorDamage(double previousJoystickX, double
            previousJoystickX1) {
        // if (joystickX - previousJoystickX ...) joystickX = ...;
    }

    private void moveMotors() {
        // arcadeDrive(joystickX, joystickY);
    }
}
