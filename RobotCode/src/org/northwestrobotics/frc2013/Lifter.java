/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.northwestrobotics.frc2013;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;

/**
 * Manages the state for the lifting subsystem, and activates the climber on
 * user command.
 *
 * @author soggy.potato
 * @author AgentOrange
 */
public final class Lifter {

    /*
     * The actuator will be attached to the side of the side of the robot. There will be a flat disk screwed into the actuator.
     * The actuator will be facing down so when the button is pressed the disk will touch the ground and push the robot off the ground. 
     * There will be hooks on the top of the shooter. When the bot is pushed off the ground the hook will attach to the first level rung.
     * While the robot is hooked to the first rung the actuator will retract. Now the robot is not touching the ground and we will be awarded 10 points.
     */
    /**
     * The solenoids used for to allow the robot to perform the movement
     * necessary for climbing.
     *
     * @author AgentOrange
     * @author RG3
     */
    private final DoubleSolenoid climber =
        new DoubleSolenoid(RobotConstants.Lifter.CLIMBER_FORWARD_CHANNEL,
            RobotConstants.Lifter.CLIMBER_REVERSE_CHANNEL);
    
    /**
     * The joystick used to activate the climbing function.
     *
     * @author soggy.potato
     */
    private final Joystick moveController;
    private boolean previousButtonState;

    /**
     * Initializes the lifter. Specifically, gives the lifter access to the
     * joystick used for user input.
     *
     * @param moveController The joystick used to activate the climbing
     * function.
     */
    public Lifter(Joystick moveController) {
        this.moveController = moveController;
    }

    public final void reactToUserInput() {
        if (isActivateButtonPressed()) { // determine whether or not the user has commanded the robot to climb
            climber.set(DoubleSolenoid.Value.kForward); // activate both double solenoids double solenoid
        } else if (isDeactivateButtonPressed()) {
            climber.set(DoubleSolenoid.Value.kReverse);
        }
    }

    private boolean isActivateButtonPressed() {
        return moveController.getRawButton(10);
    }

    private boolean isDeactivateButtonPressed() {
        return moveController.getRawButton(11);
    }
}
