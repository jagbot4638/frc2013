/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.northwestrobotics.frc2013.shooter;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import org.northwestrobotics.frc2013.RobotConstants;
import org.northwestrobotics.frc2013.State;


/**
 *
 * @author soggy.potato
 */
public final class ShootingState extends BaseShooterState {
    Timer motorStopTimer = new Timer();
    boolean shouldActivateFeeder = true;
    
    public ShootingState(Shooter shooter) {
        super(shooter);
    }
    public void enter() {
        shouldActivateFeeder = true;
        motorStopTimer.reset(); // Reset the timer
        // Start shoot motor
        shooter.getShootMotor().set(RobotConstants.Shooting.SHOOT_MOTOR_SPEED);
        
        
        
    }

    public State handle() {
        // Activate pneumatic arm. Activation has been moved
        // to this function, so that there is a small delay between
        // starting the motor in the enter() method and feeding
        // the frisbee
        if (!shooter.getFeeder().get() && shouldActivateFeeder) {
            shooter.getFeeder().set(true); // Trigger pneumatic arm
            shouldActivateFeeder = false;
        }
        
        // Retract the pneumatic arm to the original position 
        if (motorStopTimer.get() >= RobotConstants.Shooting.FEEDER_WAIT_TIME
                && shooter.getFeeder().get()) {
            shooter.getFeeder().set(false); // Retract the pneumatic arm
        }
        
        // Stop the motor when motor deactivation time has been reached
        if (motorStopTimer.get() >= RobotConstants.Shooting.SHOOT_MOTOR_DEACTIVATION_TIME) {
            shooter.getShootMotor().set(0); // Stop the shoot motor
            motorStopTimer.stop();
            return shooter.getAwaitingUserInputState(); // Switch back to awaiting user input
        }   
        return this; // Stay on this state because it is not time to stop the shoot motor
    }
    
}
