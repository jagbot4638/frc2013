/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.northwestrobotics.frc2013;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Talon;
/**
 *
 * @author Saagar
 */
class Driver {
    
    final static double threshold = .5;
    
    double joystickX = 0.0;
    double joystickY = 0.0;
    
    Joystick moveStick;
    RobotDrive robotDrive;
    
    public Driver(){
        moveStick = new Joystick(RobotConstants.Drive.joystick); 
        
        robotDrive = new RobotDrive(RobotConstants.Drive.leftMotor,
                RobotConstants.Drive.rightMotor);
    }
    
    public void drive(){
        
        //recoreded previous joystick values
        double previousJoystickX = joystickX; 
        double previousJoystickY = joystickY;
        
        
        userInput();
        minimizeMotorDamage(previousJoystickX, previousJoystickY);
        move();
    }

    private void userInput() {
        // takes in user input for x and y axis of joystick
        
        joystickY = moveStick.getAxis(Joystick.AxisType.kY);
        joystickX = moveStick.getAxis(Joystick.AxisType.kX);
        
    }

    private void minimizeMotorDamage(double previousJoystickX, double previousJoystickY) {
       //keeps joystick values within certain threshold
        
        joystickX = keepWithin(joystickX, previousJoystickX, RobotConstants.Drive.threshold);
        joystickY = keepWithin(joystickY, previousJoystickY, RobotConstants.Drive.threshold);
    }

    private void move() {
        robotDrive.arcadeDrive(joystickX, joystickY); //takes in parameters for one-stick drive
    }

    private double keepWithin(double velocity, double initialVelocity, double threshold) {
        final double deltaVelocity = velocity - initialVelocity;
        if (Math.abs(deltaVelocity) <= threshold) //checks whether the change in velocity is less than the threshold velocity
        {
            return velocity; //the final velocity is returned
        }
        else if (deltaVelocity > 0) //checks whether the change in velocity is greater than 0
        {
            return initialVelocity + threshold; //the sum of the initial velocity and the threshold velocity
        }
        else{
            return initialVelocity - threshold; //the difference between the initial velocity and the threshold velocity
        }
        
    }
}
