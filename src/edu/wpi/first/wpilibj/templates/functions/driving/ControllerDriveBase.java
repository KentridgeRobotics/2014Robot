package edu.wpi.first.wpilibj.templates.functions.driving;
import edu.wpi.first.wpilibj.Joystick;
/**
 *
 * @author Matt & Steve Sessa(with Sam Umporowicz's input)
 */
public abstract class ControllerDriveBase implements Drive 
{
    public ControllerDriveBase(Joystick control)
    {
        controller = control;
    }

    public void InvertFowardBackwards(boolean value) {
        inverted = value;
    }
    
    private final Joystick controller;
    
    private boolean inverted = false;
    private double reduceSpeedBy = 8;
    
    public void move()
    {    
        double turn = (-controller.getX());
        double speed = inverted ? controller.getZ() : -controller.getZ();
        
        if(Math.abs(speed) < .05)
            speed = 0;
        
        if(Math.abs(turn) < .15)
            turn = 0;
        
        boolean slowDownRequested = controller.getRawButton(1);
        double speedReductionFactor = calculateSpeedReduction(speed, turn);
        
        if (speed != 0) // we are moving
        {
            if (turn >= -0.05 && turn <= 0.05) // go straight
            {
                if (!slowDownRequested)
                {
                    driveLeftSide(speed);
                    driveRightSide(speed);
                }
                else
                {
                    driveLeftSide(speed / reduceSpeedBy);
                    driveRightSide(speed / reduceSpeedBy);
                }
            }
            else if (turn < -0.05) // arc left
            {
                if (!slowDownRequested)
                {
                    driveLeftSide(speedReductionFactor);
                    driveRightSide(speed);
                }
                else
                {
                    driveLeftSide(speedReductionFactor / reduceSpeedBy);
                    driveRightSide(speed / reduceSpeedBy);
                }
            }
            else if (turn > 0.05) //arc right
            {
                if (slowDownRequested == false)
                {
                    driveLeftSide(speed);
                    driveRightSide(speedReductionFactor);
                }
                else
                {
                    driveLeftSide(speed / reduceSpeedBy);
                    driveRightSide(speedReductionFactor / reduceSpeedBy);
                }
            }
        }
        else // pivots
        {
            turn *= .5;    
            turn = -turn;
            reduceSpeedBy = 2;
            if (turn < 0.05) // pivot left
            {
                if (slowDownRequested == false)
                {
                    driveLeftSide(speed + turn);
                    driveRightSide(speed - turn);
                }
                else
                {
                    driveLeftSide((speed + turn) / reduceSpeedBy);
                    driveRightSide((speed - turn) / reduceSpeedBy);
                }
            }
            else if (turn > 0.05) // pivot right
            {
                if (slowDownRequested == false)
                {
                    driveLeftSide(speed + turn);
                    driveRightSide(speed - turn);
                }
                else
                {
                    driveLeftSide((speed + turn) / reduceSpeedBy);
                    driveRightSide((speed -turn) / reduceSpeedBy);
                }
            }
        }
    }

    private double calculateSpeedReduction(double speed, double turn)
    {
        boolean goingInReverse = (speed < 0);

        speed = Math.abs(speed);

        turn = Math.abs(turn);

        double turnRate = ((1 - turn) * speed);

        if (turnRate <= 0)
        {
            turnRate = 0;
        }

        if (goingInReverse)
        {
            turnRate = -turnRate;
        }
        return turnRate;
    }
}