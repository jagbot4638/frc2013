/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.northwestrobotics.frc2013;

import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;

import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author AgentOrange
 * @author soggy.potato
 * @author RG3
 */
class Driver {

    /**
     * Joystick responsible for the user control of driving.
     *
     * @author soggy.potato
     */
    private Joystick moveStick = new Joystick(RobotConstants.Drive.MOVE_CONTROLLER);
    /**
     * The object called for arcade drive
     *
     * @author soggy.potato
     */
    private RobotDrive robotDrive;
    // MOTORS
    private SpeedController frontLeftController = new Talon(RobotConstants.Drive.FRONT_LEFT_MOTOR); // TODO: Invert 
    private SpeedController backRightController = new Talon(RobotConstants.Drive.BACK_RIGHT_MOTOR);
    private SpeedController frontRightController = new Victor(RobotConstants.Drive.FRONT_RIGHT_MOTOR);
    private SpeedController backLeftController = new Victor(RobotConstants.Drive.BACK_LEFT_MOTOR);

    public Driver() {
        initializeRobotDrive();
    }

    public void drive() {
        move();
    }

    /**
     * Move the robot motors in accordance with the user's input.
     *
     * @author AgentOrange
     */
    private void move() {
        robotDrive.arcadeDrive(moveStick);



    }


    private void initializeRobotDrive() {
        robotDrive = new RobotDrive(frontLeftController, backLeftController, frontRightController, backRightController);
        robotDrive.setInvertedMotor(MotorType.kRearRight, true);// original
        robotDrive.setInvertedMotor(MotorType.kFrontRight, true);
        robotDrive.setInvertedMotor(MotorType.kRearLeft, true);
        robotDrive.setInvertedMotor(MotorType.kFrontLeft, true);// original
        robotDrive.setMaxOutput(RobotConstants.Drive.MAX_MOTOR_SPEED);

    }


    public void getController() {

        //moveeStick
        NetworkTable data = NetworkTable.getTable("Move Stick");
        data.putNumber("X Axis", moveStick.getAxis(Joystick.AxisType.kX));
        data.putNumber("Y Axis", moveStick.getAxis(Joystick.AxisType.kY));
        SendableData send = new SendableData(data, "Move Stick");
        SmartDashboard.putData(send);




    }
}
