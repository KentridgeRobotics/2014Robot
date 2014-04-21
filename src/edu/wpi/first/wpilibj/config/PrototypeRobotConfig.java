/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.config;

/**
 *
 * @author Driver Person
 */
public class PrototypeRobotConfig extends BaseConfig
{
    /********** START CONFIG ****************************************/
    //wheels
    private final int LEFT_MAIN_CHANNEL         = 2;
    private final int LEFT_SLAVE_CHANNEL        = -1;   //no slave
    private final int RIGHT_MAIN_CHANNEL        = 3;
    private final int RIGHT_SLAVE_CHANNEL       = -1;  //no slave
    
    //thrower
    private final int GRABBER_CHANNEL           = 10;
    private final int LIFTER_CHANNEL            = 5;
    private final int LIFTER_SLAVE_CHANNEL      = 13;
    
    //pids
    private final double LIFTER_P               = 0.5D;
    private final double LIFTER_I               = 0.0041D;
    private final double LIFTER_D               = 0.0D;
    
    //values                                    //find
    private final double CENTER_POSITION        = 0.7111053466796875;
    private final double THROW_POSITION_FOR_FROM_POSITIVE_SIDE = 0.0;
    private final double THROW_POSITION_FOR_FROM_NEGATIVE_SIDE = 0.0;
    /********** END CONFIG ******************************************/
    
    public int getConfigType()
    {
        return Config.ConfigType.PROTOTYPE_ROBOT;
    }

    public int getLeftMainMotorChannel() 
    {
        return LEFT_MAIN_CHANNEL;
    }

    public int getLeftSlaveMotorChannel() 
    {
        return LEFT_SLAVE_CHANNEL;
    }

    public int getRightMainMotorChannel() 
    {
        return RIGHT_MAIN_CHANNEL;
    }

    public int getRightSlaveMotorChannel() 
    {
        return RIGHT_SLAVE_CHANNEL;
    }

    public int getGrabberMotorChannel() 
    {
        return GRABBER_CHANNEL;
    }
    
    public int getLifterMotorChannel()
    {
        return LIFTER_CHANNEL;
    }
    
    public double getLifterP()
    {
        return LIFTER_P;
    }
    
    public double getLifterI()
    {
        return LIFTER_I;
    }
    
    public double getLifterD()
    {
        return LIFTER_D;
    }
    
    public double getCenterPosition()
    {
        return CENTER_POSITION;
    }

    public double getThrowPositionFromPositiveSide()
    {
        return THROW_POSITION_FOR_FROM_POSITIVE_SIDE;
    }

    public double getThrowPositionFromNegativeSide()
    {
        return THROW_POSITION_FOR_FROM_NEGATIVE_SIDE;
    }

    public int getLifterMotorSlaveChannel() 
    {
        return LIFTER_SLAVE_CHANNEL;
    }
}
