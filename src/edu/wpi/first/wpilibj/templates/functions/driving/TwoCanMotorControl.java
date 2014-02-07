/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.functions.driving;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.templates.CANJaguarFactory;

/**
 *
 * @author Driver Person
 */
public class TwoCanMotorControl extends ControllerDriveBase
{
    public TwoCanMotorControl(Joystick controller, int leftMotorChannel, int rightMotorChannel) throws Exception
    {
        super(controller);
        
        leftMotor = CANJaguarFactory.createAt(leftMotorChannel).construct();
        rightMotor = CANJaguarFactory.createAt(rightMotorChannel).construct();
    }
    
    private final CANJaguar leftMotor;
    private final CANJaguar rightMotor;

    public void driveLeftSide(double speed) 
    {
        try {
            leftMotor.setX(speed);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }

    public void driveRightSide(double speed) 
    {
        try {
            rightMotor.setX(speed);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }
    
}
