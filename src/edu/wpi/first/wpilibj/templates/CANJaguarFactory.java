/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.CANJaguar.ControlMode;
import edu.wpi.first.wpilibj.CANJaguar.SpeedReference;
import edu.wpi.first.wpilibj.can.CANTimeoutException;

/**
 *
 * @author Driver Person
 */
public class CANJaguarFactory 
{
    private static CANJaguarFactory instance = null;
    
    private CANJaguarFactory()
    {
    }
    
    public static CANJaguarFactory createAt(int channel)
    {
        if(instance == null)
        {
            instance = new CANJaguarFactory();
            reset();
        }
        
        instance.setChannel(channel);
        return instance;
    }
    
    public static void reset()
    {
        if(instance == null)
        {
            System.out.println("instance is null!");
        }
        instance.setChannel(0);
        instance.setControlMode(ControlMode.kPercentVbus);
        instance.setP(0.008);
        instance.setI(0);
        instance.setD(0);
        instance.setEncoderCodesPerRev(0);
        instance.setUsingPID(false);
    }
    
    private int channel;
    private ControlMode controlMode;
    private SpeedReference speedReference;
    private double p;
    private double i;
    private double d;
    private int encoderCodesPerRev;
    
    private boolean usingPID = false;
    
    public CANJaguar construct()
    {
        CANJaguar jag = null;
        try
        {
            jag = new CANJaguar(channel, controlMode);
//            System.out.println("-----------CREATING JAGUAR AT CHANNEL " + channel + "----------------");
//            System.out.println("Control Mode:\t\t" + controlMode);
            
            if(usingPID)
            {
//                System.out.println("Speed Reference:\t\t" + speedReference);
                jag.setSpeedReference(speedReference);
//                System.out.println("Encoder Codes Per Rev:\t\t" + encoderCodesPerRev);
                jag.configEncoderCodesPerRev(encoderCodesPerRev);
//                System.out.println("P:\t\t" + p);
//                System.out.println("I:\t\t" + i);
//                System.out.println("D:\t\t" + d);
//                System.out.println("Verifying PID:");
//                System.out.println("P:\t\t" + jag.getP());
//                System.out.println("I:\t\t" + jag.getI());
//                System.out.println("D:\t\t" + jag.getD());
                jag.setPID(p, i, d);
                jag.enableControl();
                
                if(p == 0)
                {
                    System.out.println("[WARNING] Created a CANJaguar with P=0!");
                }
                
                if(encoderCodesPerRev == 0)
                {
                    System.out.println("[WARNING] Created a CANJaguar with encoder codes per revolution=0!");
                }
            }
            
//            System.out.println("-----------Motor Create Complete----------");
        }
        catch (CANTimeoutException e)
        {
            e.printStackTrace();
        }
        
        return jag;
    }
    
    private void setChannel(int channel)
    {
        this.channel = channel;
    }
    
    public CANJaguarFactory setControlMode(ControlMode mode)
    {
        this.controlMode = mode;
        return this;
    }
    
    public CANJaguarFactory setP(double p)
    {
        this.p = p;
        usingPID = true;
        return this;
    }
    
    public CANJaguarFactory setI(double i)
    {
        this.i = i;
        usingPID = true;
        return this;
    }
    
    public CANJaguarFactory setD(double d)
    {
        this.d = d;
        usingPID = true;
        return this;
    }
    
    public CANJaguarFactory resetPID()
    {
        this.p = 0;
        this.i = 0;
        this.d = 0;
        usingPID = false;
        return this;
    }
    
    public CANJaguarFactory setEncoderCodesPerRev(int encoderCodesPerRev)
    {
        this.encoderCodesPerRev = encoderCodesPerRev;
        usingPID = true;
        return this;
    }
    
    public CANJaguarFactory setUsingPID(boolean usingPID)
    {
        this.usingPID = usingPID;
        return this;
    }
    
    public CANJaguarFactory setSpeedReference(SpeedReference speedReference)
    {
        this.speedReference = speedReference;
        return this;
    }
}
