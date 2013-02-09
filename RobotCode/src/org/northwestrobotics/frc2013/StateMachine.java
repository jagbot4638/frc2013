/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.northwestrobotics.frc2013;

/**
 *
 * @author Saagar
 */
public class StateMachine {
    private State current;
    
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
