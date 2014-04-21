/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.config.Config;

/**
 *
 * @author Driver Person
 */
public class Controller 
{
    private Controller() 
    {
    }
    
    private static final Joystick[] instances = new Joystick[2];
    
    public static Joystick get(int slot)
    {
        //slots are one-based
        int index = slot - 1;
        
        if(instances[index] == null)
        {
            instances[index] = new Joystick(slot);
        }
        
        return instances[index];
    }
}
