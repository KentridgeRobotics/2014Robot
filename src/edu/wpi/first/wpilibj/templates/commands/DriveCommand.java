/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.templates.subsystems.Wheels;

/**
 *
 * @author Zora
 */
public class DriveCommand extends Command
{   
    public DriveCommand(Wheels wheels)
    {
        requires(wheels);
        
        this.wheels = wheels;
    }
    
    private final Wheels wheels;
    
    protected void initialize()
    {
    }

    protected void execute()
    {
        wheels.getDrive().move();
    }

    protected boolean isFinished()
    {
        //We are never done driving
        return false;
    }

    protected void end()
    {
        wheels.getDrive().driveLeftSide(0.0);
        wheels.getDrive().driveRightSide(0.0);
    }

    protected void interrupted()
    {
        end();
    }
}
