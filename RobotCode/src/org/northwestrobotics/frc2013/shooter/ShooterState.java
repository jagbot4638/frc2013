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
public abstract class ShooterState implements State {
    protected final Shooter shooter;
    
    public ShooterState(Shooter shooter) {
        this.shooter = shooter;
    }
    
}
