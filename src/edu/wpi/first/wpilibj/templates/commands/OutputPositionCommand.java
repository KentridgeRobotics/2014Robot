/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.subsystems.Arms;

/**
 *
 * @author Driver Person
 */
public class OutputPositionCommand extends Command
{
    public OutputPositionCommand(final Arms arms)
    {
        requires(arms);
        this.arms = arms;
    }
    
    private final Arms arms;
    
    protected void initialize() {
    }

    protected void execute() {
//        try {
//            System.out.println("[Position] " + arms.getPosition());
//        } catch (CANTimeoutException ex) {
//            ex.printStackTrace();
//        }
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
