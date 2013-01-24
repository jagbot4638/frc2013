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
    double joystickX = 0.0;
    double joystickY = 0.0;
    //edited SilverX 1/23
    boolean joystickButton1 = false;//creates and indicator for the pressing of button 1
    boolean joystickButton2 = false;//creates and indicator for the pressing of button 2
    boolean joystickButton3 = false;//creates and indicator for the pressing of button 3
    //end edit
    Joystick moveStick;//This joystick is for the driving
    //edited SilverX 1/23
    Joystick moveStick2;//This joystick is for the shooter and pick-up-er
    //end edit
    RobotDrive robotDrive;
    
    
    public Driver(){
        moveStick = new Joystick(RobotConstants.Drive.JOYSTICK); 
        moveStick2 = new Joystick(RobotConstants.Drive.JOYSTICK2);
        robotDrive = new RobotDrive(RobotConstants.Drive.LEFT_MOTOR,
                RobotConstants.Drive.RIGHT_MOTOR);
        //edited SilverX 1/23
   Talon shooterMotor = new Talon(3);//opens the motor for the shooter
    shooterMotor.set(1);//sets thee shooter motor to always run
    //end edit
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
        
        joystickY = moveStick.getAxis(Joystick.AxisType.kY)/2;
        joystickX = moveStick.getAxis(Joystick.AxisType.kX)/2;
        //edited SilverX 1/23
        joystickButton1 = moveStick2.getRawButton(1);//reads in the pressing or release of button1
        joystickButton2 = moveStick2.getRawButton(2);//reads in the pressing or release of button2
        joystickButton3 = moveStick2.getRawButton(3);//reads in the pressing or release of button3
        //end edit
    }

    private void minimizeMotorDamage(double previousJoystickX, double previousJoystickY) {
       //keeps joystick values within certain threshold
        
        joystickX = keepWithin(joystickX, previousJoystickX, RobotConstants.Drive.THRESHOLD);
        joystickY = keepWithin(joystickY, previousJoystickY, RobotConstants.Drive.THRESHOLD);
    }

    private void move() {
        robotDrive.arcadeDrive(-joystickX, joystickY); //takes in parameters for one-stick drive
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
