/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.northwestrobotics.frc2013.shooter;

import org.northwestrobotics.frc2013.State;

/**
 *
 * @author Saagar
 */
abstract class BaseShooterState implements State {
    protected final Shooter shooter;
    
    public BaseShooterState(Shooter shooter) {
        this.shooter = shooter;
    }
    
}
