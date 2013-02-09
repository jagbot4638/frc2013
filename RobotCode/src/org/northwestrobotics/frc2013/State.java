/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.northwestrobotics.frc2013;

/**
 * Represents a state of a state machine. It also is responsible for responding to being in the state.
 * @author soggy.potato
 */
public interface State {
    /**
     * This method is called when the state machine enters the state.
     * @author soggy.potato
     */
    void enter();

    /**
     * Handles any state interactions.
     * @author soggy.potato
     * @return The next state for the state machine
     */
    State handle();
}
