/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.config.Config;
import edu.wpi.first.wpilibj.templates.subsystems.Arms;

/**
 *
 * @author Driver Person
 */
public class ThrowBallCommand extends FailableCommand
{
    //TODO: test
    
    public ThrowBallCommand(final Arms arms)
    {
        requires(arms);
        this.arms = arms;
        
        positiveSideThrowPoint = Config.get().getThrowPositionFromPositiveSide();
        negativeSideThrowPoint = Config.get().getThrowPositionFromNegativeSide();
    }
    
    private final Arms arms;
    private final double positiveSideThrowPoint;
    private final double negativeSideThrowPoint;
    
    protected void tryExecute() throws Exception 
    {
        double throwPosition = findClosestThrowSide();
        arms.setToPosition(throwPosition);
        arms.openArms();
    }
    
    private double findClosestThrowSide()throws CANTimeoutException
    {
        double currentPosition = arms.getPosition();
        
        double distanceToPositive = Math.abs(currentPosition - positiveSideThrowPoint);
        double distanceToNegative = Math.abs(currentPosition - negativeSideThrowPoint);
        
        if(distanceToPositive < distanceToNegative)
        {
            return distanceToPositive;
        }
        else
        {
            return distanceToNegative;
    }
    }
}
