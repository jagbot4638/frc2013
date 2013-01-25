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
 * @author AgentOrange
 * @author soggy.potato
 */
class Driver {
    private double joystickX = 0.0;
    private double joystickY = 0.0;
    /**
     * Joystick responsible for the user control of driving.
     * @author soggy.potato
     */
    private Joystick moveStick;
    
    /**
     * The object called for arcade drive
     * @author soggy.potato
     */
    private RobotDrive robotDrive;
    
    
    public Driver(){
        moveStick = new Joystick(RobotConstants.Drive.MOVE_STICK);
        robotDrive = new RobotDrive(RobotConstants.Drive.LEFT_MOTOR,
                RobotConstants.Drive.RIGHT_MOTOR);
    }
    
    public void drive(){
        
        //recorded previous joystick values
        double previousJoystickX = joystickX; 
        double previousJoystickY = joystickY;
        
        
        userInput();
        minimizeMotorDamage(previousJoystickX, previousJoystickY);
        move();
    }
    /**
     * Read the user input for motion.
     * @author AgentOrange
     */
    private void userInput() {
        // takes in user input for x and y axis of joystick
        
        joystickY = moveStick.getAxis(Joystick.AxisType.kY)/2;
        joystickX = moveStick.getAxis(Joystick.AxisType.kX)/2;
        
    }

    /**
     * Make the motion of the robot smoother by eliminating jerkiness caused by
     * sudden changes in velocity.
     * @param previousJoystickX
     * @param previousJoystickY 
     */
    private void minimizeMotorDamage(double previousJoystickX, double
            previousJoystickY) {
       //keeps joystick values within certain threshold
        
        joystickX = keepWithin(joystickX, previousJoystickX,
                RobotConstants.Drive.THRESHOLD);
        joystickY = keepWithin(joystickY, previousJoystickY, RobotConstants.Drive.THRESHOLD);
    }
    
    /**
     * Move the robot motors in accordance with the user's input.
     * @author AgentOrange
     */
    private void move() {
        robotDrive.arcadeDrive(-joystickX, joystickY); //takes in parameters for one-stick drive
    }
    
    /**
     * 
     * @param velocity
     * @param initialVelocity
     * @param threshold
     * @return The new velocity, which maintains the change of velocity within the threshold.
     */
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
