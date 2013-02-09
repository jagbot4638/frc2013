/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.northwestrobotics.frc2013.shooter;

import org.northwestrobotics.frc2013.State;

/**
 * This is the initial state for the Shooter. This represents the shooter
 * waiting for the user to command the robot to shoot.
 *
 * @author soggy.potato
 */
public final class AwaitingUserInputState extends BaseShooterState {

    public AwaitingUserInputState(Shooter shooter) {
        super(shooter);
    }

    public void enter() {
    }

    public State handle() {
        if (shooter.isShootButtonPressed()) {
            return shooter.getShootingState();
        }
        return this;
    }
}
