/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.northwestrobotics.frc2013;

/**
 
 * 
 
 * @author soggy.potato
 
 */
 
public class SensorEvent {
 
    
 
    private Sensor source;
 
    private int id;
 
    private double data;
 
    
 
    public SensorEvent(Sensor source, int id, double data) {
 
        this.source = source;
 
        this.id = id;
 
        this.data = data;
 
    }
 

 
    public double getData() {
 
        return data;
 
    }
 

 
    public int getId() {
 
        return id;
 
    }
 
    
 
    public Sensor getSource() {
 
        return source;
 
    }
 
    
 
} 

