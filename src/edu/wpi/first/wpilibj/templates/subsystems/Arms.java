/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.CANJaguar.*;
import edu.wpi.first.wpilibj.config.Config;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.commands.FeedSlaveCommand;
import edu.wpi.first.wpilibj.templates.commands.OutputPositionCommand;

/**
 *
 * @author Driver Person
 */
public class Arms extends Subsystem 
{
    public Arms() throws CANTimeoutException
    {
        armMotor = new CANJaguar(Config.get().getLifterMotorChannel(), ControlMode.kPosition);
        armMotor.setPositionReference(CANJaguar.PositionReference.kQuadEncoder);
        armMotor.configEncoderCodesPerRev(360);
        //P: 600 I: 1.0 D: 0.0
        armMotor.setPID(600.0D, 1.0D, 0.0D);
        armMotor.configSoftPositionLimits(0.90, -0.80);
      
//        armMotor.enableControl(0.50);
        armMotor.enableControl(0.0);
        
        armSlaveMotor = new CANJaguar(Config.get().getLifterMotorSlaveChannel(), ControlMode.kPercentVbus);
        armSlaveMotor.configNeutralMode(NeutralMode.kCoast);
        armSlaveMotor.enableControl();
        
        grabberMotor = new CANJaguar(Config.get().getGrabberMotorChannel(), ControlMode.kPercentVbus);
        grabberMotor.configNeutralMode(NeutralMode.kBrake);
        grabberMotor.enableControl();
    }
    
    private final CANJaguar armMotor;
    private final CANJaguar armSlaveMotor;
    private final CANJaguar grabberMotor;
    
    public void incrementMotor(double positionIncrement) throws CANTimeoutException
    {
        System.out.println("Position: " + armMotor.getPosition());
        System.out.println("Setting to: " + (armMotor.getPosition() + positionIncrement));
        armMotor.setX(armMotor.getPosition() + positionIncrement);
//        feedSlave();
    }
    
    public void setToPosition(double position) throws CANTimeoutException
    {
        armMotor.setX(position);
//        feedSlave();
    }
    
    public void openArms() throws CANTimeoutException
    {
//        System.out.println("opening arms: setting x...");
        grabberMotor.setX(-1.0D);
//        System.out.println("opening arms: X set. (" + grabberMotor.getX() + ")");
    }
    
    public void closeArms() throws CANTimeoutException
    {
//        System.out.println("opening arms: setting x...");
        grabberMotor.setX(1.0D);
//        System.out.println("opening arms: X set. (" + grabberMotor.getX() + ")");
    }
    
    public double getPosition() throws CANTimeoutException
    {
        return armMotor.getPosition();
    }
    
//    public void feedSlave() throws CANTimeoutException
//    {
////        System.out.println("+--");
////        System.out.println("arm motor output voltage: " + armMotor.getOutputVoltage());
//        armSlaveMotor.setX(armMotor.getOutputVoltage());
////        System.out.println("fed voltage to slave. Slave voltage: " + armSlaveMotor.getX());
////        System.out.println("--+");
//    }
    
    public void setSlave(double value) throws CANTimeoutException
    {
        armSlaveMotor.setX(value);
    }
    
    public double getArmOutputVoltage() throws CANTimeoutException
    {
        return armMotor.getOutputVoltage();
    }
    
    public CANJaguar getGrabberMotor()
    {
        return grabberMotor;
    }

    public void initDefaultCommand() 
    {
        // Set the default command for a subsystem here.
//        setDefaultCommand(new MySpecialCommand());
        setDefaultCommand(new OutputPositionCommand(this));
//        setDefaultCommand(new FeedSlaveCommand(this));
    }
}
