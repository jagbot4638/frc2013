/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.northwestrobotics.frc2013;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.DriverStationLCD.Line;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 *
 * @author trae
 */
public class Test {

    DriverStationLCD Display;
    Joystick[] controlers;
    Encoder[] encoders;
    SpeedController[] motors;
    Solenoid[] solinoids;
    Compressor compressor;
    AxisCamera camera;
    int wait;

    public Test(int wait) {
        Display = DriverStationLCD.getInstance();
        this.wait = wait;

    }

    public Test() {
        Display = DriverStationLCD.getInstance();
        this.wait =0;;

    }
    public void addInstrement(Joystick[] controlers) {
        this.controlers = controlers;
    }

    public void addInstrement(Encoder[] encode) {
        this.encoders = encode;
    }

    public void addInstrement(SpeedController[] motors) {
        this.motors = motors;
    }

    public void addInstrement(Solenoid[] solinoids) {
        this.solinoids = solinoids;
    }

    public void addInstrement(Compressor compressor) {
        this.compressor = compressor;
    }

    public void addInstrement(AxisCamera camera) {
        this.camera = camera;
    }

    public void Update() {
        try {
            CheckMotors();
            CheckControlers();
            CheckEncoders();
            CheckSolinoids();
            Display.updateLCD();
            Thread.sleep(wait * 1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public void CheckMotors() {
        int colum = 1;
        Line[] lines = {DriverStationLCD.Line.kUser1, DriverStationLCD.Line.kUser2,
            DriverStationLCD.Line.kUser3, DriverStationLCD.Line.kUser4,
            DriverStationLCD.Line.kUser5, DriverStationLCD.Line.kUser6};
        if (motors != null) {
            for (int x = 0; x < motors.length; x++) {
                String motor = "Motor" + (String.valueOf(x)) + ": ";
                Display.println(lines[x], colum, motor.concat(String.valueOf(motors[x].get())));
            }
        }

    }

    public void CheckControlers() {
        int colum = 2;
        Line[] lines = {DriverStationLCD.Line.kUser1, DriverStationLCD.Line.kUser2,
            DriverStationLCD.Line.kUser3, DriverStationLCD.Line.kUser4,
            DriverStationLCD.Line.kUser5, DriverStationLCD.Line.kUser6};
        int line = 0;
        if (controlers != null) {
            for (int x = 0; x < controlers.length; x++) {
                String label = "Controler" + String.valueOf(x) + ": ";
                Display.println(lines[line], colum, label + String.valueOf(controlers[x].getAxis(Joystick.AxisType.kX)));
                line++;
                Display.println(lines[line], colum, label + String.valueOf(controlers[x].getAxis(Joystick.AxisType.kY)));
                line++;
            }
        }

    }

    public void CheckEncoders() {
        int colum = 3;
        Line[] lines = {DriverStationLCD.Line.kUser1, DriverStationLCD.Line.kUser2,
            DriverStationLCD.Line.kUser3, DriverStationLCD.Line.kUser4,
            DriverStationLCD.Line.kUser5, DriverStationLCD.Line.kUser6};
        if (encoders != null) {
            for (int x = 0; x < encoders.length; x++) {
                String label = "Encoder" + String.valueOf(x) + ": ";
                Display.println(lines[x], colum, label + String.valueOf(encoders[x]));
            }
        }

    }

    public void CheckSolinoids() {
        int colum = 4;
        Line[] lines = {DriverStationLCD.Line.kUser1, DriverStationLCD.Line.kUser2,
            DriverStationLCD.Line.kUser3, DriverStationLCD.Line.kUser4,
            DriverStationLCD.Line.kUser5, DriverStationLCD.Line.kUser6};
        if (solinoids != null) {
            for (int x = 0; x < solinoids.length; x++) {
                String label = "Solinoids" + String.valueOf(x) + ": ";
                Display.println(lines[x], colum, label + String.valueOf(solinoids[x].get()));
            }
        }

    }
    /*
     public void CheckCompressor() {
     int colum = 5;
     Line[] lines = {DriverStationLCD.Line.kUser1, DriverStationLCD.Line.kUser2,
     DriverStationLCD.Line.kUser3, DriverStationLCD.Line.kUser4,
     DriverStationLCD.Line.kUser5, DriverStationLCD.Line.kUser6};
     if (compressor != null) {
            
     Display.println(DriverStationLCD.Line.kUser1, colum, String.valueOf(compressor));

     }

     }

     public void CheckCamera() {
     int colum = 6;
     Line[] lines = {DriverStationLCD.Line.kUser1, DriverStationLCD.Line.kUser2,
     DriverStationLCD.Line.kUser3, DriverStationLCD.Line.kUser4,
     DriverStationLCD.Line.kUser5, DriverStationLCD.Line.kUser6};
     if (camera != null) {

     Display.println(DriverStationLCD.Line.kUser1, colum, String.valueOf(camera.g));

     }

     }

     */
}