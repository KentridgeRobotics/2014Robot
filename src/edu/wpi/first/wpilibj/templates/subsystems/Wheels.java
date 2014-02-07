/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.commands.DriveCommand;
import edu.wpi.first.wpilibj.templates.functions.driving.Drive;

/**
 *
 * @author Zora
 */
public class Wheels extends Subsystem
{
    private final Drive drive;
    
    public Wheels(Drive drive)
    {
        this.drive = drive;
    }

    protected void initDefaultCommand()
    {
        setDefaultCommand(new DriveCommand(this));
    }
    
    public final Drive getDrive()
    {
        return drive;
    }
    
}
