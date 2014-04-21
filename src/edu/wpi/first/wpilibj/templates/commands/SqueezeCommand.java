/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.subsystems.Arms;

/**
 *
 * @author Driver Person
 */
public class SqueezeCommand extends FailableCommand
{
    public SqueezeCommand(final Arms arms, final boolean squeeze)
    {
//        requires(arms);
        
        this.squeeze = squeeze;
        this.arms = arms;
    }
    
    private final boolean squeeze;
    private final Arms arms;

    protected void tryExecute() throws Exception 
    {
        if(squeeze)
        {
            arms.closeArms();
        }
        else
        {
            arms.openArms();
            
        }
    }
    
    protected void end()
    {
//        try
//        {
//            arms.stopSqueezing();
//            System.out.println("Stopping squeezing.");
//        }
//        catch (CANTimeoutException e)
//        {
//            System.out.println("Failed to stop arms from squeezing!");
//        }
    }
}
