/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.northwestrobotics.frc2013;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;

/**
 * The actuator will be attached to the side of the side of the robot. There will be a flat disk screwed into the actuator.
 * The actuator will be facing down so when the button is pressed the disk will touch the ground and push the robot off the ground. 
 * There will be hooks on the top of the shooter. When the bot is pushed off the ground the hook will attach to the first level rung.
 * While the robot is hooked to the first rung the actuator will retract. Now the robot is not touching the ground and we will be awarded 10 points.
 * @author soggy.potato
 * @author AgentOrange
 */
public final class Lifter {

    private final DoubleSolenoid[] climber = {
        new DoubleSolenoid(RobotConstants.Lifter.CLIMBER1_FORWARD_CHANNEL, RobotConstants.Lifter.CLIMBER1_REVERSE_CHANNEL),
        new DoubleSolenoid(RobotConstants.Lifter.CLIMBER2_FORWARD_CHANNEL, RobotConstants.Lifter.CLIMBER2_REVERSE_CHANNEL)
    };
    private final Joystick moveController;

    public Lifter(Joystick moveController) {
        this.moveController = moveController;
    }
    
    public final void reactToUserInput() {
        if(moveController.getTrigger()){
            climber[0].set(DoubleSolenoid.Value.kForward);
            climber[1].set(DoubleSolenoid.Value.kForward);
            
            
        }
    }
}
