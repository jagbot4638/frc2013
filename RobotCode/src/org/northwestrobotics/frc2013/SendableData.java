/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.northwestrobotics.frc2013;

import edu.wpi.first.wpilibj.NamedSendable;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 *
 * @author trae
 */
public class SendableData implements NamedSendable {
         NetworkTable data;
         String name;
         public SendableData(NetworkTable x,String name){
         data=x;
         this.name=name;
         }
         public String getName() {
                return name;
            }

            public void initTable(ITable itable) {
            }

            public ITable getTable() {
                return data;
            }

            public String getSmartDashboardType() {
               return name;
                
            }
}