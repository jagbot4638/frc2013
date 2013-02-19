/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.northwestrobotics.frc2013.shooter;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
        SmartDashboard.putString("Shooting State Machine ", "Shooting State");
        shouldActivateFeeder = true;
        motorStopTimer.reset(); // Reset the timer


        SmartDashboard.putBoolean("Arm Fired", true);

    }


    public State handle() {

        SmartDashboard.putBoolean("Arm Fired", false);   

        // Activate pneumatic arm. Activation has been moved
        // to this function, so that there is a small delay between
        // starting the motor in the enter() method and feeding
        // the frisbee


        // Retract the pneumatic arm to the original position 
        if (shooter.getFeeder().get()) {
            // TODO: may not have to do this as it is autoretract.
            shooter.getFeeder().set(true); // Retract the pneumatic arm
           
            shooter.getFeeder().set(true);
            return shooter.getAwaitingUserInputState();

        }

        return this; // Stay on this state because it is not time to stop the shoot motor
    }
}
