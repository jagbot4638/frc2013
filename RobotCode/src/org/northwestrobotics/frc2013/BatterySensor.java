/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.northwestrobotics.frc2013;

import edu.wpi.first.wpilibj.DriverStation;
import java.util.Vector;

/**
 *
 * A battery sensor that retrieves main battery voltage from the analog sidecar
 * @author soggy.potato
 */
public class BatterySensor extends PollingSensor {

    public static final int BATTERY_VOLTAGE = 0;
    private final DriverStation ds;
    private final Vector listeners;

    public BatterySensor(int pollTime, String name) {

        super(name, pollTime, 1);

        ds = DriverStation.getInstance();

        listeners = new Vector();

        start();

    }

    protected void poll() {

        setState(BATTERY_VOLTAGE, ds.getBatteryVoltage());

    }

    protected void notifyListeners(int id, double oldDatum, double newDatum) {



        BatteryVoltageEvent e = new BatteryVoltageEvent(this, newDatum);



        for (int i = 0; i < listeners.size(); i++) {

            ((BatteryVoltageListener) listeners.elementAt(i)).batteryVoltageChanged(e);

        }

    }

    public void addBatteryVoltageListener(BatteryVoltageListener l) {

        listeners.addElement(l);

    }

    public void removeBatteryVoltageListener(BatteryVoltageListener l) {

        listeners.removeElement(l);

    }
}
