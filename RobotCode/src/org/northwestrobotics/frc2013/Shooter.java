/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.northwestrobotics.frc2013;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;

/**
 * @author soggy.potato
 * @author SilverX
 * @author AgentOrange
 */
public class Shooter {
    Joystick aimingStick;
    
    
    // Joystick buttons
    boolean shootButton = false;//creates and indicator for the pressing of button
    
    // Motors
    /**
     * The motor used to adjust the shooter's vertical aiming.
     * @author soggy.potato
     * @author SilverX
     */
    Talon pitchMotor;
    
    /**
     * The motor used to launch a frisbee at targets
     * @author soggy.potato
     * @author SilverX
     */
    Talon shootMotor;
    
    
    public Shooter(){
        aimingStick = new Joystick(RobotConstants.Shooting.AIMING_STICK);
        initializeMotors();
    }
    
    public void respondToUserInput() {
        // User uses controller to aim. Read in this user input.
        double pitchAdjustment = readUserInput();
        
        pitchMotor.
        
        // Start the angle screw motor in the direction specified by the user input amount
        // Stop the motor once the encoder detects that the motor has reached
        // the angle the user wants it to reach
        
        
        
    }
    
    /**
     * Determines whether the drive time has commanded the robot to shoot a frisbee.
     * @author soggy.potato
     */
    public boolean shootButtonPressed() {
        return shootButton;
    }
    /**
     * Fires a frisbee.
     */
    public void shoot() {
        // Start the shooter motor
        // Keep the shooter motor on for T seconds, where T is the amount of time
        // to spin the motor to launch one frisbee.
        // The T constants value will be determined through testing.
    }
    /**
     * Reads in the user's commands for the robot.
     * @return The pitch adjustment from the aiming joystick.
     */
    private double readUserInput() {
        shootButton = aimingStick.getRawButton(
                RobotConstants.Shooting.SHOOT_BUTTON); // Determine whether the shoot button is pressed or not
        return aimingStick.getY(); // Get the input for adjustment to the pitch, which is the only thing we can control.
    }

    private void initializeMotors() {
        pitchMotor = new Talon(RobotConstants.Shooting.PITCH_MOTOR);
        shootMotor = new Talon(RobotConstants.Shooting.SHOOT_MOTOR);
    }
}
