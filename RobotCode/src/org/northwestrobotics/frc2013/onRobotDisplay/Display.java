/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.northwestrobotics.frc2013.onRobotDisplay;

import java.util.Hashtable;

/**
 *
 * @author Saagar
 */
public class Display {
    private int nextZone = 1;
    private Hashtable zones;
    private InsightLT display = new InsightLT(InsightLT.FOUR_ZONES);
    
    public Display() {
        display.startDisplay();
    }
    
    public void setData(String header, double number) {
        if (!zones.containsKey(header)) {
            addDecimalZone(header);
        }
        
        ((DecimalData)zones.get(header)).setData(number);
    }

    private void addDecimalZone(String header) {
        if (nextZone > 4) {
            throw
                new IndexOutOfBoundsException(
               "Cannot have more than four zones in the on-robot display."
                );
        }
        DisplayData displayer = new DecimalData(header);
        zones.put(header, displayer);
        display.registerData(displayer, nextZone++);
    }
}
