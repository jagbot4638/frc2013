/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.northwestrobotics.frc2013;

/**
 *
 * @author Saagar
 */
public interface State {

    void enter();

    /**
     *
     * @param shooter
     * @return The next state
     */
    State handle();
}
