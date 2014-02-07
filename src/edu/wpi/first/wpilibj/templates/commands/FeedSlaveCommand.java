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
public class FeedSlaveCommand extends FailableCommand
{
    public FeedSlaveCommand(Arms arms)
    {
        this.arms = arms;
        
        requires(arms);
    }
    
    private final Arms arms;
    
    protected void tryExecute() throws Exception 
    {
        System.out.println("nothing done!");
//        arms.feedSlave();
//        System.out.println("[Position] " + arms.getPosition());
    }
}
