/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.config;

/**
 *
 * @author Driver Person
 */
public abstract class BaseConfig 
{
    /********** START CONFIG ****************************************/
    private final int CONTROLLER_SLOT   = 1;
    
    private final int CIMPLEBOX_ENCODER_CODES_PER_REV = 360;
    
    private final double GRAB_SPEED = 0.5;
    private final double ARM_THROW_SPEED = 400;
    private final double ARM_MOVE_SPEED  = 0.05D;
    
    private final double ARM_POSITION_THRESHOLD = 0.05;
    
    private final double CIM_MOTOR_MAX_RPMS = 400.0;
    /********** END CONFIG ******************************************/
    
    public abstract int getConfigType();
    
    public abstract int getLeftMainMotorChannel();
    public abstract int getLeftSlaveMotorChannel();
    public abstract int getRightMainMotorChannel();
    public abstract int getRightSlaveMotorChannel();
    public abstract int getGrabberMotorChannel();
    public abstract int getLifterMotorChannel();
    public abstract int getLifterMotorSlaveChannel();
    
    public abstract double getLifterP();
    public abstract double getLifterI();
    public abstract double getLifterD();
    
    public abstract double getCenterPosition();
    public abstract double getThrowPositionFromPositiveSide();
    public abstract double getThrowPositionFromNegativeSide();
    
    public String toString()
    {
        switch (getConfigType())
        {
            case Config.ConfigType.PROTOTYPE_ROBOT:
                return "Prototype Robot Config";
            default:
                return "Unknown Robot Config";
        }
    }
    
    public final int getControllerSlot()
    {
        return CONTROLLER_SLOT;
    }
    
    public final int getCimpleboxEncoderCodesPerRev()
    {
        return CIMPLEBOX_ENCODER_CODES_PER_REV;
    }
    
    public final double getGrabSpeed()
    {
        return GRAB_SPEED;
    }
    
    public final double getArmThrowSpeed()
    {
        return ARM_THROW_SPEED;
    }
    
    public final double getArmMoveSpeed()
    {
        return ARM_MOVE_SPEED;
    }
    
    public final double getArmPositionThreshold()
    {
        return ARM_POSITION_THRESHOLD;
    }
    
    public final double getCimMotorMaxRpms()
    {
        return CIM_MOTOR_MAX_RPMS;
    }
}
