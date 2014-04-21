/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.templates.subsystems.Arms;

/**
 *
 * @author Driver Person
 */
public class RoughThrowCommand extends FailableCommand
{
    public RoughThrowCommand(Arms arms)
    {
        this.arms = arms;
    }
    
    public void initialize()
    {
        System.out.println("throw command init");
    }
    
    private final Arms arms;
    
    protected void tryExecute() throws Exception 
    {
//        System.out.println("throwing.");
        
//        arms.setToPosition(0.2);
//        while(arms.getPosition() > 0.2)
        arms.setToPosition(.2);
        while(arms.getPosition() > .2)
        {
            arms.setSlave(arms.getArmOutputVoltage() > 0 ? 1.0 : -1.0);
        }
//        System.out.println("opening.");
        arms.openArms();
        arms.setSlave(0.0);
        setFinished(true);
    }
}
