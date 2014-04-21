/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.config;

/**
 *
 * @author Driver Person
 */
public class Config 
{
    private Config()
    {
    }
    
    private static BaseConfig instance;
    
    public static void init(BaseConfig config)
    {
        instance = config;
    }
    
    public static BaseConfig get()
    {
        return instance;
    }
    
    public class ConfigType
    {
        public static final int PROTOTYPE_ROBOT = 1;
    }
}
