/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.northwestrobotics.frc2013.shooter;

import edu.wpi.first.wpilibj.AnalogChannel;
import org.northwestrobotics.frc2013.RobotConstants;

/**
 *
 * @author Developer
 */
public class Potentiometer extends AnalogChannel{
    
    
    public Potentiometer(int channel) {
        super(channel);
    }
    
    public double getAngle() {
        
        return getAverageVoltage();
    }
    
    
}
