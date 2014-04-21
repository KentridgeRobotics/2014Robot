/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author Driver Person
 */
public abstract class FailableCommand extends Command
{
    private boolean hasFailed = false;
    private boolean finished = false;
    
    public boolean isFinished()
    {
        if (finished || hasFailed)
            return true;
        else
            return isDerivedFinished();
    }
    
    protected void initialize() { }
    protected void end() { }
    protected void interrupted() { }
    
    protected abstract void tryExecute() throws Exception;
    
    protected final void execute()
    {
        try
        {
            tryExecute();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            setFailed(true);
        }
    }
    
    protected boolean isDerivedFinished()
    {
        return false;
    }
    
    protected final void setFailed(boolean hasFailed)
    {
        this.hasFailed = hasFailed;
    }
    
    public final boolean hasFailed()
    {
        return hasFailed;
    }
    
    protected boolean getFinished()
    {
        return finished;
    }
    
    protected void setFinished(boolean finished)
    {
        this.finished = finished;
    }
}
