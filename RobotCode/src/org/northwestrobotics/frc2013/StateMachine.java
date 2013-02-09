/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.northwestrobotics.frc2013;

/**
 *
 * @author soggy.potato
 */
public class StateMachine {
    private State current;
    /**
     * Creates a state machine.
     * @param initial The state the state machine starts at.
     * @author soggy.potato
     */
    public StateMachine(State initial) {
        current = initial;
    }
    
    public void update(){
        State previous = current; 
        current = current.handle();
        if (previous != current) {
            current.enter();
	}
    }
}
