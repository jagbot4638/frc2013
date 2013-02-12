/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.northwestrobotics.frc2013;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
<<<<<<< HEAD
=======
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
>>>>>>> origin/master

/**
 * @author AgentOrange
 * @author soggy.potato
 * @author RG3
 */
class Driver {
<<<<<<< HEAD
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
    
=======

    /**
     * Joystick responsible for the user control of driving.
     *
     * @author soggy.potato
     */
    private Joystick moveStick;
    /**
     * The object called for arcade drive
     *
     * @author soggy.potato
     */
    private RobotDrive robotDrive;
    // MOTORS
    private SpeedController frontLeftController = new Talon(RobotConstants.Drive.FRONT_LEFT_MOTOR); // TODO: Invert 
    private SpeedController backRightController = new Talon(RobotConstants.Drive.BACK_RIGHT_MOTOR);
    private SpeedController frontRightController = new Victor(RobotConstants.Drive.FRONT_RIGHT_MOTOR);
    private SpeedController backLeftController = new Victor(RobotConstants.Drive.BACK_LEFT_MOTOR);

>>>>>>> origin/master
    /**
     * @author soggy.potato
     * @author AgentOrange
     * @author RG3
     */
<<<<<<< HEAD
    public Driver(){
        moveStick = new Joystick(RobotConstants.Drive.MOVE_CONTROLLER);
        robotDrive = new RobotDrive(RobotConstants.Drive.FRONT_LEFT_MOTOR,
                RobotConstants.Drive.BACK_LEFT_MOTOR,RobotConstants.Drive.FRONT_RIGHT_MOTOR,
                RobotConstants.Drive.BACK_RIGHT_MOTOR);
    }
    /**
     * Drive the robot in response to user input.
     * @author soggy.potato
     * @author AgentOrange
     */
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
        
=======
    public Driver() {
        moveStick = new Joystick(RobotConstants.Drive.MOVE_CONTROLLER);
        //TODO: Change variable type to SpeedController
        initializeRobotDrive();
    }

    public void drive() {
        move();
    }

    /**
     * Move the robot motors in accordance with the user's input.
     *
     * @author AgentOrange
     */
    private void move() {
        robotDrive.arcadeDrive(moveStick);
    }

    public void test() {
        if (moveStick.getRawButton(RobotConstants.TestDrive.ACTIVATE_FRONT_LEFT_MOTOR_BUTTON)) {
            frontLeftController.set(1);
        } else {
            frontLeftController.set(0);
        }
        
        if (moveStick.getRawButton(RobotConstants.TestDrive.ACTIVATE_FRONT_RIGHT_MOTOR_BUTTON)) {
            frontRightController.set(1);
        } else {
            frontRightController.set(0);
        }
        
        if (moveStick.getRawButton(RobotConstants.TestDrive.ACTIVATE_BACK_LEFT_MOTOR_BUTTON)) {
            backLeftController.set(1);
        } else {
            backLeftController.set(0);
        }
        
        if (moveStick.getRawButton(RobotConstants.TestDrive.ACTIVATE_BACK_RIGHT_MOTOR_BUTTON)) {
            backRightController.set(1);
        } else {
            backRightController.set(0);
        }
    }

    private void initializeRobotDrive() {
        robotDrive = new RobotDrive(frontLeftController, backLeftController, frontRightController, backRightController);
        robotDrive.setInvertedMotor(MotorType.kRearRight, true);// original
        robotDrive.setInvertedMotor(MotorType.kFrontRight, true);
        robotDrive.setInvertedMotor(MotorType.kRearLeft, true);
        robotDrive.setInvertedMotor(MotorType.kFrontLeft, true);// original
        robotDrive.setMaxOutput(RobotConstants.Drive.MAX_MOTOR_SPEED);
>>>>>>> origin/master
    }
}
