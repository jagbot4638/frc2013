package org.northwestrobotics.frc2013;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import org.northwestrobotics.frc2013.RobotConstants;

/*Notes Links:s
 *  http://www.chiefdelphi.com/forums/showthread.php?t=80790
 */
public class Loader {
    /*s1=arm
     * s2=sucktioncup
     * s3=shooter
     * 
     * */

    private Solenoid armRetractor, suctionCup;
    private Compressor airCompressor;//defines Axis Camera
    private Talon arm;
    private Encoder counter;
    private Joystick controller;

    /**
     * @param controller
     *
     *
     */
    public Loader(Joystick controller) {
        Compressor airCompressor = new Compressor(1,1);  //Digtial I/O,Relay
        airCompressor.start();                        // Start the air compressor
        this.controller = controller;
        armRetractor = new Solenoid(1);                        // Solenoid port
        suctionCup = new Solenoid(2);
        arm = new Talon(5);
        counter = new Encoder(32, 5);
    }

    /**
     * Checks pressure in system and makes sure it is constant.
     *
     * @author RG3
     */
    public void checkpressure() {
        if (airCompressor.getPressureSwitchValue() == RobotConstants.Loader.MAX_PRESSURE) {
            airCompressor.stop();
        } else if (airCompressor.enabled() == false) {
            airCompressor.start();
        }


    }

    /**
     * Picks up the frisbee
     *
     * @author RG3
     */
    public void pickup() {
        while (counter.get() < 86) {
            arm.set(.5);
        }
        arm.set(0);
        counter.reset();
        armRetractor.set(false);
        try {
            Thread.sleep(32);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        suctionCup.set(true);
        armRetractor.set(true);
        while (counter.get() < 5656) {
            arm.set(-.5);
        }
        arm.set(0);
        counter.reset();
        suctionCup.set(false);

    }
}
