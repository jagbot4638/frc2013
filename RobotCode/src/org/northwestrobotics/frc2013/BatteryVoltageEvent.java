/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.northwestrobotics.frc2013;

/**
 
 *
 
 * @author ajc
 
 */
 
public class BatteryVoltageEvent {
 
    private final BatterySensor sensor;
 
    private final double voltage;
 

 
    public BatteryVoltageEvent(BatterySensor sensor, double voltage){
 
        this.sensor = sensor;
 
        this.voltage = voltage;
 
    }
 

 
    public BatterySensor getSource(){
 
        return sensor;
 
    }
 

 
    public double getVoltage(){
 
        return voltage;
 
    }
 

 
}
 

