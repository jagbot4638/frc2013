/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.northwestrobotics.frc2013;

/**
 *
 * @author Saagar
 */
public class RobotConstants {
    public static final class Drive {
        private Drive(){
        }
        public static final double THRESHOLD = .05;
        
        public static final int MOVE_STICK = 1;
        
        public static final int LEFT_MOTOR = 1;
        public static final int RIGHT_MOTOR = 2;
    }
    
    public static final class Shooting {
        private Shooting(){
        }
        
        public static final int AIMING_STICK = 2;
        
        
        public static final int SHOOT_BUTTON = 1;
        public static final int AIM_DOWN_BUTTON = 2;
        public static final int AIM_UP_BUTTON = 3;
        
        // Motors
        public static final int PITCH_MOTOR = 3;
        public static final int SHOOT_MOTOR = 4;
    }
}
