/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.northwestrobotics.frc2013.shooter;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import org.northwestrobotics.frc2013.RobotConstants;
import org.northwestrobotics.frc2013.State;


public class ShootingState extends ShooterState {
    Timer motorStopTimer = new Timer();
    public ShootingState(Shooter shooter) {
        super(shooter);
    }
    public void enter() {
        motorStopTimer.reset(); // reset the timer
        // 1. Start shoot motor
        shooter.getShootMotor().set(RobotConstants.Shooting.SHOOT_MOTOR_SPEED);
        
        // 2. Trigger pneumatic arm
        shooter.getFeeder().set(true);
        
    }

    public State handle() {
        if (motorStopTimer.get() >= RobotConstants.Shooting.PNEUMATIC_ARM_DEACTIVATION_TIME) {
            shooter.getFeeder().set(false); // retract the pneumatic arm
        }
        
        // Check whether the timer has expired
        if (motorStopTimer.get() >= RobotConstants.Shooting.SHOOT_MOTOR_DEACTIVATION_TIME) {
            shooter.getShootMotor().set(0); // stop the shoot motor
            return shooter.getBaseState(); // switch back to awaiting user input
        }   
        return this; // Stay on this state because it is not time to stop the shoot motor
    }
    
}
