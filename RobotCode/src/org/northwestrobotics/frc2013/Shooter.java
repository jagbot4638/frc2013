/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.northwestrobotics.frc2013;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.MotorSafetyHelper;
import edu.wpi.first.wpilibj.Talon;



/**
 * This class manages the shooting system.
 * @author soggy.potato
 * @author SilverX
 * @author AgentOrange
 */
public class Shooter {
    private Joystick aimingStick;
    
    
    // Joystick buttons
    
    /**
     * The state of the button, which is pressed to active launching.
     */
    private boolean shootButton = false;//creates and indicator for the pressing of button
    
    // Motors
    /**
     * The motor used to adjust the shooter's vertical aiming.
     * @author soggy.potato
     * @author SilverX
     */
    private Talon pitchMotor;
    
    /**
     * The motor used to launch a frisbee at targets
     * @author soggy.potato
     * @author SilverX
     * @AgentOrange
     */
    private Talon shootMotor;
    
    
    /*
     * Used to adjust pitchMotor (vertical aiming)
     * @author AgentOrange
     */
    private Encoder pitchChanger; // yet to initialize
    
    
    
   
    
    
    public Shooter(Joystick aimingStick){
        this.aimingStick = aimingStick;
        initializeMotors();
    }
    
    /*
     * @author AgentOrange
     * adjusts vertical aiming
     */
    
    public void respondToUserInput() {
        // User uses controller to aim. Read in this user input.
        double pitchAdjustment = readUserInput();
        
        pitchChanger.start();
        
        pitchChanger.setDistancePerPulse(1); //units: degrees
        
        
        
        
        
        
        
        // Start the angle screw motor in the direction specified by the user input amount
        // Stop the motor once the encoder detects that the motor has reached
        // the angle the user wants it to reach
        
        
        if(pitchChanger.getDistance() == pitchAdjustment)
             pitchChanger.stop();
        }
        
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
        
        double time = 10; //change time later
        double speed = 0.5;
        
        shootMotor.setExpiration(time);
        shootMotor.set(speed); // change speed later
        
        
        
        
        
        
        
        
      
        
        
        shootMotor.Feed();
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
    
    /**
     * Sets up the motors for use.
     */
    private void initializeMotors() {
        pitchMotor = new Talon(RobotConstants.Shooting.PITCH_MOTOR);
        shootMotor = new Talon(RobotConstants.Shooting.SHOOT_MOTOR);
    }
}
