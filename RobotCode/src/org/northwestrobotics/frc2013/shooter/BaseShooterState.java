/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.northwestrobotics.frc2013.shooter;

import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.DriverStationLCD.Line;
import org.northwestrobotics.frc2013.State;

/**
 *
 * @author Saagar
 */
abstract class BaseShooterState implements State {

    protected final Shooter shooter;
    public DriverStationLCD drive;

    public BaseShooterState(Shooter shooter) {
        this.shooter = shooter;
        drive = DriverStationLCD.getInstance();
    }

    public void print(String state, Line line) {
        drive.println(line, 1, state);
        drive.updateLCD();
    }
}
