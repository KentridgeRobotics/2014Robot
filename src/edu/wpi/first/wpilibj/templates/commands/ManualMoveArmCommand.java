/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.config.Config;
import edu.wpi.first.wpilibj.templates.subsystems.Arms;

/**
 *
 * @author Driver Person
 */
public class ManualMoveArmCommand extends FailableCommand
{
    public ManualMoveArmCommand(final Arms arms, final boolean forwards)
    {
//        requires(arms);
        
        this.arms = arms;
        
        final double armMoveSpeed = 0.03D;//Config.get().getArmMoveSpeed();
        
        if(forwards)
            increment = armMoveSpeed;
        else
            increment = -armMoveSpeed;
    }
    
    private final double increment;
    private final Arms arms;
    
    protected void tryExecute() throws Exception
    {
        arms.incrementMotor(increment);
    }
}
