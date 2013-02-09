/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.northwestrobotics.frc2013.shooter;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.MotorSafetyHelper;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import org.northwestrobotics.frc2013.RobotConstants;
import org.northwestrobotics.frc2013.State;
import org.northwestrobotics.frc2013.StateMachine;

/**
 * This class manages the shooting system.
 *
 * @author soggy.potato
 * @author SilverX
 * @author AgentOrange
 */
public final class Shooter {
    // Aiming
    private Joystick aimingStick;
    // Joystick buttons
    /**
     * The state of the button, which is pressed to active launching.
     */
    private boolean shootButton = false;//creates and indicator for the pressing of button
    
    
    /**
     * The motor used to adjust the shooter's vertical aiming.
     *
     * @author soggy.potato
     * @author SilverX
     */
    private Talon pitchMotor = new Talon(RobotConstants.Shooting.PITCH_MOTOR);
    
    
    // Shooting
    
    
    
    /**
     * The motor used to launch a frisbee at targets.
     * @author soggy.potato
     * @author SilverX
     * @author AgentOrange
     */
    private Talon shootMotor = new Talon(RobotConstants.Shooting.SHOOT_MOTOR);
    

    /**
     * Pneumatic arm to push frisbees into shooter
     * @author AgentOrange
     */
    private Solenoid feeder = new Solenoid(RobotConstants.Shooting.FEEDER_CHANNEL);
    
    // STATE MACHINE
    
    // States
    private State awaitingUserInputState = new AwaitingUserInputState(this);
    private State shootingState = new ShootingState(this);
    
    // Machine
    private StateMachine shootingStateMachine = new StateMachine(awaitingUserInputState);
   
    public Shooter(Joystick aimingStick) {
        this.aimingStick = aimingStick;
    }
    
    State getAwaitingUserInputState() {
        return awaitingUserInputState;
    }
    State getShootingState() {
        return shootingState;
    }
    
    /**
     * @author AgentOrange
     * @author soggy.potato
     * Adjusts vertical aiming in accordance to user input.
     */
    public void adjustAim() {
        // User uses controller to aim. Read in this user input.
        // If the y is zero pitch arm stops moving
        /*
         * TODO: Find out the sign for the Y value, and adjust the pitch factor
         * from testing. Perform a range check before calling the set method.
         * May have to change sign.
         */
        double pitchAdjustment = aimingStick.getY() * RobotConstants.Shooting.PITCH_FACTOR;
        pitchMotor.set(pitchAdjustment);
        
    }

    /**
     * Determines whether the driver has commanded the robot to shoot a
     * frisbee.
     *
     * @author soggy.potato
     */
    boolean isShootButtonPressed() {
        // Determine whether the shoot button is pressed or not.
        return aimingStick.getRawButton(RobotConstants.Shooting.SHOOT_BUTTON);
    }
    
    public void updateShooting() {
        shootingStateMachine.update();
    }
    
    // Util
    Talon getShootMotor() {
        return shootMotor;
    }

    Solenoid getFeeder() {
        return feeder;
    }
    
}
