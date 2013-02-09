package org.northwestrobotics.frc2013;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import org.northwestrobotics.frc2013.RobotConstants;

/*Notes Links:s
 *  http://www.chiefdelphi.com/forums/showthread.php?t=80790
 */
public class Loader {

    private Solenoid armRetractor, suctionCup;
    private Compressor airCompressor;//defines Axis Camera
    private Talon arm;
    private Encoder counter;
    private Joystick controller;
    private double seconds;

    /**
     * @param controller
     *
     *
     */
    public Loader(Joystick controller) {
        airCompressor = new Compressor(1, 1);  //Digtial I/O,Relay (Creates air compressor referance)
        airCompressor.start();                        // Start the air compressor
        this.controller = controller;           //creates local controller referance
        armRetractor = new Solenoid(1);                        // Solenoid port (Creates solenoid referances and motor referances)
        suctionCup = new Solenoid(2);
        arm = new Talon(5);
        counter = new Encoder(32, 5);// creates bad encoder referance 
    }

    /**
     * Checks pressure in system and makes sure it is constant.
     * and turns of compressor when not needed
     * @author RG3
     */
    public void checkpressure() {
        //if air pressure is at max it turns off comprssor else it turns it on
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
        counter.start();
        while (counter.get() < 86) {
            arm.set(.5);
        }
        arm.set(0);
        counter.reset();
        armRetractor.set(false);
        Timer.delay(seconds);
        suctionCup.set(true);
        armRetractor.set(true);
        Timer.delay(seconds);
        while (counter.get() < 5656) {
            arm.set(-.5);
        }
        arm.set(0);
        counter.reset();
        suctionCup.set(false);
        counter.stop();
    }
}
