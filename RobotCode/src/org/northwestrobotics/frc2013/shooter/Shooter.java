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
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.northwestrobotics.frc2013.RobotConstants;
import org.northwestrobotics.frc2013.State;
import org.northwestrobotics.frc2013.StateMachine;

/**
 * This class manages the shooting system.
 *
 * @author soggy.potato
 * @author SilverX
 * @author AgentOrange
 * @author Deven_Gosalia
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
    // PNEUMATICS
    /**
     * Pneumatic arm to push frisbees into shooter
     *
     * @author AgentOrange
     */
    private final Solenoid feeder = new Solenoid(RobotConstants.Pneumatics.FEEDER_CHANNEL);
    private final Compressor airCompressor = new Compressor(RobotConstants.Pneumatics.PRESSURE_SWITCH_VALUE,
            RobotConstants.Pneumatics.COMPRESSOR_RELAY);
    // STATE MACHINE
    // States
    private final State awaitingUserInputState = new AwaitingUserInputState(this);
    private final State shootingState = new ShootingState(this);
    // Machine
    private final StateMachine shootingStateMachine = new StateMachine(awaitingUserInputState);
    // Aiming Encoder
    private final Encoder angleGetter = new Encoder(RobotConstants.Shooting.AIMING_ENCODER_CHANNEL_A,
            RobotConstants.Shooting.AIMING_ENCODER_CHANNEL_B);

    public Shooter(Joystick aimingStick) {
        this.aimingStick = aimingStick;
        this.targetAngle = getAngle();
        angleGetter.start();
    }

    State getAwaitingUserInputState() {
        return awaitingUserInputState;
    }

    State getShootingState() {
        return shootingState;
    }

    /**
     * Adjusts vertical aiming in accordance to user input.
     *
     * @author AgentOrange
     * @author soggy.potato
     */
    public void adjustAim() {
        // User uses controller to aim. Read in this user input.
        // If the y is zero pitch arm stops moving
        /*
         * TODO: Find out the sign for the Y value, and adjust the pitch factor
         * from testing. Perform a range check before calling the set method.
         * May have to change sign.
         */

        if (isSettingAngle) {
            if((int)getAngle() == (int)targetAngle){
                isSettingAngle = false;
                pitchMotor.set(0);
            } else {
                isSettingAngle = true;
                double changeAngle = targetAngle - getAngle();
                pitchMotor.set(0.1);
                
            }
                
                
        } else {
            double pitchAdjustment =-aimingStick.getY() * RobotConstants.Shooting.PITCH_FACTOR;
        if (pitchAdjustment < 0) {
            pitchAdjustment *= 3;
        }
        
            if (getAngle() > RobotConstants.Shooting.MAX_ANGLE && pitchAdjustment > 0) {
                pitchMotor.set(0);
            } else if (RobotConstants.Shooting.MIN_ANGLE > getAngle() && pitchAdjustment < 0) {
                pitchMotor.set(0);
            } else {
                pitchMotor.set(pitchAdjustment);
            }
            targetAngle = getAngle();
            pitchMotor.set(pitchAdjustment);
        }

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
            shootMotor.set(-getShootMotorSpeed());
        } else {
            shootMotor.set(0);
        }
    }

    public void updatePressure() {
        if (airCompressor.getPressureSwitchValue() == RobotConstants.Pneumatics.MAX_PRESSURE) {
            airCompressor.stop();
        } else {
            airCompressor.start();
        }


    }

    // Util
    SpeedController getShootMotor() {
        return shootMotor;
    }

   public Solenoid getFeeder() {
        return feeder;
    }

    private double getShootMotorSpeed() {
        return Math.abs(aimingStick.getAxis(Joystick.AxisType.kZ));
    }

    private boolean isActivateShootMotorButtonPressed() {
        return aimingStick.getRawButton(RobotConstants.Shooting.TOGGLE_SHOOT_MOTOR_BUTTON);
    }
    //ANGLING STUFF
    private Potentiometer angle = new Potentiometer(RobotConstants.Shooting.POTENTIOMETER);
    private double targetAngle;
    private boolean isSettingAngle = false;

    /**
     * Adjusts the shooter so that it is aiming at the given angle.
     *
     * @param angle The angle to put the shooter at in degrees
     * @author soggy.potato
     * @author AgentOrange
     */
    public void setAngle(double angle) {
        if (angle != getAngle()) {
            targetAngle = angle;
            isSettingAngle = true;
        }
        
        
        
        
        
        
    }

    /**
     * Gets the shooter's angle from the ground.
     * @return The shooter's angle from the ground
     */
    private double getAngle() {
        // TODO
        int count = angleGetter.get();
        SmartDashboard.putNumber("Encoder", angleGetter.get());
        double angleInDegrees = count*RobotConstants.Shooting.DEGREES_PER_PULSE;
        angleInDegrees %= 360;
        return angleInDegrees;

    }

    private double convertToMotorOutput(double deltaAngle) {
        // TODO
        return 0.0;
    }
}
