/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.functions.driving;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.can.CANTimeoutException;

/**
 *
 * @author Driver Person
 */
public class TankDrive implements Drive
{
    public TankDrive(Joystick leftStick, Joystick rightStick,
            int leftMainChannel,
            int rightMainChannel)
            throws CANTimeoutException
    {
//        this.leftMain = leftMain;
//        this.leftSlave = leftSlave;
//        this.rightMain = rightMain;
//        this.rightSlave = rightSlave;
        
        this.leftMain = new CANJaguar(leftMainChannel, CANJaguar.ControlMode.kPercentVbus);
        this.leftMain.enableControl();
        
        this.rightMain = new CANJaguar(rightMainChannel, CANJaguar.ControlMode.kPercentVbus);
        this.rightMain.enableControl();
        
        this.leftStick = leftStick;
        this.rightStick = rightStick;
//        this.leftStick = new Joystick(1);
//        this.rightStick = new Joystick(2);
    }
    
    private final CANJaguar leftMain;
    private final CANJaguar rightMain;
    
    private final Joystick leftStick;
    private final Joystick rightStick;
    
    public void driveLeftSide(double speed)
    {
        try {
            leftMain.setX(speed);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }

    public void driveRightSide(double speed)
    {
        try {
            rightMain.setX(speed);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }

    public void move()
    {
        double leftSpeed = leftStick.getY();
        double rightSpeed = rightStick.getY();
        
//        System.out.println("Left joystick y: " + leftSpeed);
//        System.out.println("Right joystick y: " + rightSpeed);
        
        driveLeftSide(leftSpeed);
        driveRightSide(rightSpeed);
    }
}
