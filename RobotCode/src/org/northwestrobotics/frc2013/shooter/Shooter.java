/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.northwestrobotics.frc2013.shooter;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.MotorSafetyHelper;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SolenoidBase;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import org.northwestrobotics.frc2013.RobotConstants;
import org.northwestrobotics.frc2013.State;
import org.northwestrobotics.frc2013.StateMachine;

/**
 * This class manages the shooting system.
 *
 * @author soggy.potato
 * @author SilverX
 * @author AgentOrange
 */
public final class Shooter {
    // Aiming

    /**
     * The joystick for obtaining aiming and shooting input.
     */
    private final Joystick aimingStick;
    /**
     * The motor used to adjust the shooter's vertical aiming.
     *
     * @author soggy.potato
     * @author SilverX
     */
    private final SpeedController pitchMotor = new Talon(RobotConstants.Shooting.PITCH_MOTOR);
    // Shooting
    /**
     * The motor used to launch a frisbee at targets.
     *
     * @author soggy.potato
     * @author SilverX
     * @author AgentOrange
     */
    private final SpeedController shootMotor = new Talon(RobotConstants.Shooting.SHOOT_MOTOR);
    /**
     * Pneumatic arm to push frisbees into shooter
     *
     * @author AgentOrange
     */
    private final Solenoid feeder = new Solenoid(RobotConstants.Shooting.FEEDER_CHANNEL);
    // STATE MACHINE
    // States
    private final State awaitingUserInputState = new AwaitingUserInputState(this);
    private final State shootingState = new ShootingState(this);
    // Machine
    private final StateMachine shootingStateMachine = new StateMachine(awaitingUserInputState);
    private final Compressor airCompressor = new Compressor(RobotConstants.Shooting.PRESSURE_SWITCH_VALUE,
            RobotConstants.Shooting.COMPRESSOR_RELAY);

    
    public Potentiometer angleReader
            = new Potentiometer(RobotConstants.Shooting.ANGLE_READER_CHANNEL);
    public Shooter(Joystick aimingStick) {
        this.aimingStick = aimingStick;
    }

    State getAwaitingUserInputState() {
        return awaitingUserInputState;
    }

    State getShootingState() {
        return shootingState;
    }

    /**
     * @author AgentOrange
     * @author soggy.potato Adjusts vertical aiming in accordance to user input.
     */
    public void adjustAim() {
        // User uses controller to aim. Read in this user input.
        // If the y is zero pitch arm stops moving
        /*
         * TODO: Find out the sign for the Y value, and adjust the pitch factor
         * from testing. Perform a range check before calling the set method.
         * May have to change sign.
         */
        final double pitchAdjustment = aimingStick.getY() * RobotConstants.Shooting.PITCH_FACTOR;
        pitchMotor.set(pitchAdjustment);

    }

    /**
     * Determines whether the driver has commanded the robot to shoot a frisbee.
     *
     * @author soggy.potato
     */
    boolean isShootButtonPressed() {
        // Determine whether the shoot button is pressed or not.
        return aimingStick.getRawButton(RobotConstants.Shooting.SHOOT_BUTTON);
    }

    public void updateShooting() {
        shootingStateMachine.update();
        if (isActivateShootMotorButtonPressed()) {
            shootMotor.set(getShootMotorSpeed());
        } else {
            shootMotor.set(0);
        }
        DriverStationLCD lcd = DriverStationLCD.getInstance();
        lcd.println(DriverStationLCD.Line.kUser3, 0, new Double(angleReader.getAngle()).toString());
        lcd.updateLCD();
    }

    private void toggleShootMotor() {


        //if (shootMotor.get() == 0) {
        shootMotor.set(Math.abs(aimingStick.getAxis(Joystick.AxisType.kZ)));
        //   print("Shooting motor is activated");
        //} else {
        //shootMotor.set(0);
        // print("Shooting motor is deactivated");
        //}

    }

    // Util
    SpeedController getShootMotor() {
        return shootMotor;
    }

    Solenoid getFeeder() {
        return feeder;
    }
    private boolean previousButtonState = false;

    private boolean shouldToggleShooterMotor() {
        boolean currentButtonState = isActivateShootMotorButtonPressed();
        boolean result = previousButtonState && !currentButtonState;
        previousButtonState = currentButtonState;
        return result;
    }

    public void updatePressure() {
        if (airCompressor.getPressureSwitchValue() == RobotConstants.Shooting.MAX_PRESSURE) {
            airCompressor.stop();
        } else {
            airCompressor.start();
        }


    }

    private boolean isActivateShootMotorButtonPressed() {
        return aimingStick.getRawButton(RobotConstants.Shooting.TOGGLE_SHOOT_MOTOR_BUTTON);
    }

    private double getShootMotorSpeed() {
        return Math.abs(aimingStick.getAxis(Joystick.AxisType.kZ));
    }
}
