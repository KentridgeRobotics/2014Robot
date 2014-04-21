/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.templates.commands.ThrowBallCommand;
import edu.wpi.first.wpilibj.templates.subsystems.*;

/**
 *
 * @author Driver Person
 */
public class AutonomousCommandGroup extends CommandGroup
{
    public AutonomousCommandGroup(final Arms arms, final Wheels wheels)
    {
        addSequential(new ThrowBallCommand(arms));
        //TODO: be able to specify how many revolutions I want the wheels to be able to turn
    }
}
