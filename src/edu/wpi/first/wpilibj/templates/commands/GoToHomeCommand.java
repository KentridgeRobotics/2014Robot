/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.templates.subsystems.Arms;

/**
 *
 * @author Driver Person
 */
public class GoToHomeCommand extends FailableCommand
{
    public GoToHomeCommand(Arms arms)
    {
        this.arms = arms;
    }
    
    private final Arms arms;
    
    protected void tryExecute() throws Exception 
    {
        arms.setToPosition(0.0D);
        setFinished(true);
    }
    
}
