/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.config.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.autonomous.AutonomousCommandGroup;
import edu.wpi.first.wpilibj.templates.commands.*;
import edu.wpi.first.wpilibj.templates.functions.driving.Drive;
import edu.wpi.first.wpilibj.templates.functions.driving.TankDrive;
import edu.wpi.first.wpilibj.templates.functions.driving.TwoCanMotorControl;
import edu.wpi.first.wpilibj.templates.subsystems.Arms;
import edu.wpi.first.wpilibj.templates.subsystems.Wheels;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotMain extends IterativeRobot 
{
    private Arms arms;
    private Command autonomousThrowCommand;
    
    private boolean failed;
    
    private double alternator;
    
    private byte[] history;
    private int historyIndex;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() 
    {
        history = new byte[1000];
        historyIndex = 0;
        
        for(int ii = 0; ii < history.length; ii++)
        {
            history[ii] = 10;
        }
        
        failed = false;
        System.out.println("Initializing 2014 Position Based Robot...");
        
        BaseConfig config = new PrototypeRobotConfig();
        Config.init(config);
        System.out.println("  Config initialized: " + config.toString());
        
        System.out.println("  Initializing Wheels...");
        Drive drive = initializeTankDrive();
        Wheels wheels;
        if(drive != null)
        {
            System.out.println("  Wheels successfully initialized.");
            wheels = new Wheels(drive);
        }
        else
        {
            System.out.println("  !!Failed to initialize the wheels!!");
            failed = true;
            return;
        }
        
        System.out.println("  Initializing arms...");
        arms = null;
        try
        {
            arms = new Arms();
            System.out.println("  Arms successfully initialized.");
        }
        catch (CANTimeoutException e)
        {
            e.printStackTrace();
            System.out.println("  !!Failed to initialize arms!!");
            failed = true;
            return;
        }
        
        System.out.println("  Initializing Autonomous command group..");
        autonomousThrowCommand = new AutonomousCommandGroup(arms, wheels);
        System.out.println("  Autonomous command group initialized.");
        
        System.out.println("  Initializing commands...");
        final ManualMoveArmCommand moveArmsForwardsCommand = new ManualMoveArmCommand(arms, true);
        final ManualMoveArmCommand moveArmsBackwardsCommand = new ManualMoveArmCommand(arms, false);
        final SqueezeCommand squeezeCommand = new SqueezeCommand(arms, true);
        final SqueezeCommand unsqueezeCommand = new SqueezeCommand(arms, false);
        final RoughThrowCommand roughThrowCommand = new RoughThrowCommand(arms);
        final GoToHomeCommand goToHomeCommand = new GoToHomeCommand(arms);
        System.out.println("  Command initialized.");
        
        Joystick leftStick = Controller.get(1);
        Joystick rightStick = Controller.get(2);
        
        System.out.println("  Binding commands to buttons...");
        new JoystickButton(leftStick, 3)
                .whileHeld(moveArmsForwardsCommand);
        new JoystickButton(rightStick, 3)
                .whileHeld(moveArmsForwardsCommand);
                       
        new JoystickButton(leftStick, 2)
                .whileHeld(moveArmsBackwardsCommand);
        new JoystickButton(rightStick, 2)
                .whileHeld(moveArmsBackwardsCommand);
        
        new JoystickButton(leftStick, 4)
                .whileHeld(squeezeCommand);
        new JoystickButton(rightStick, 5)
                .whileHeld(squeezeCommand);
        
        new JoystickButton(leftStick, 5)
                .whileHeld(unsqueezeCommand);
        new JoystickButton(rightStick, 4)
                .whileHeld(unsqueezeCommand);
        
        new JoystickButton(leftStick, 1)
                .whenPressed(roughThrowCommand);
        new JoystickButton(rightStick, 1)
                .whenPressed(roughThrowCommand);
        
        new JoystickButton(leftStick, 8)
                .whenPressed(goToHomeCommand);
        new JoystickButton(leftStick, 9)
                .whenPressed(goToHomeCommand);
        new JoystickButton(rightStick, 8)
                .whenPressed(goToHomeCommand);
        new JoystickButton(rightStick, 9)
                .whenPressed(goToHomeCommand);
        
        System.out.println("  Commands bound to buttons.");
        System.out.println("2014 Position Based Robot Initialized.");
    }

    public void autonomousInit() 
    {
        if(arms != null)
        {
            try
            {
                arms.closeArms();
                autonomousThrowCommand.start();
            }
            catch (CANTimeoutException ex)
            {
                ex.printStackTrace();
                System.out.println("It looks like the CAN bus is not working...");
            }
        }
        else
        {
            System.out.println("[WARNING] coudln't run autonomous command because the arms are null!");
        }
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() 
    {
        Scheduler.getInstance().run();
    }

    public void teleopInit() 
    {
        alternator = .001;
        if(autonomousThrowCommand != null && autonomousThrowCommand.isRunning())
        {
            autonomousThrowCommand.cancel();
        }
    }
    
//    public static boolean equalToZero(double value)
//    {
//        return Math.abs(value) < 0.01;
//    }
    

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() 
    {
        Scheduler.getInstance().run();
        
        alternator *= -1;
        
        try {
            SmartDashboard.putNumber("Position", arms.getPosition() + alternator);
            if (arms.getGrabberMotor().getX() > .5 && !arms.getGrabberMotor().getForwardLimitOK())
            {
                System.out.println("Putting 5");
//                SmartDashboard.putNumber("OpenClose", 5 + alternator);
                history[historyIndex] = 5;
                incrementHistoryIndex();
            }
            else if(arms.getGrabberMotor().getX() < -1 && !arms.getGrabberMotor().getReverseLimitOK())
            {
                System.out.println("Putting -5");
//                SmartDashboard.putNumber("OpenClose", -5 + alternator);
                history[historyIndex] = -5;
                incrementHistoryIndex();
            }
            else
            {
                System.out.println("Putting 0");
//                SmartDashboard.putNumber("OpenClose", 0 + alternator);
                history[historyIndex] = 5;
                incrementHistoryIndex();
            }
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }
    
    private void outputHistory()
    {
        double alt = 0.001;
        
        for(int ii = 0; ii < history.length; ii++)
        {
            if(history[ii] == 10)
            {
                System.out.println("Reached end of written array. Breaking out of output loop.");
                break;
            }
            
            SmartDashboard.putNumber("OpenClose", history[ii] + alt);
            
            alt = -alt;
        }
    }
    
    private void incrementHistoryIndex()
    {
        historyIndex++;
        if(historyIndex == history.length)
        {
            historyIndex = 0;
        }
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() 
    {
        LiveWindow.run();
    }
    
    private Drive initializeTankDrive()
    {
        Drive drive = null;
        try
        {
            drive = new TankDrive(
                            Controller.get(1), Controller.get(2),
                            Config.get().getLeftMainMotorChannel(),
                            Config.get().getRightMainMotorChannel()
                    );
        }
        catch (CANTimeoutException e)
        {
            e.printStackTrace();
        }
        
        return drive;
    }
    
    private Drive initializeDrive()
    {
        Drive drive = null;
        try
        {
            switch(Config.get().getConfigType())
            {
                case Config.ConfigType.PROTOTYPE_ROBOT:
                    drive = new TwoCanMotorControl(
                            Controller.get(1),
                            Config.get().getLeftMainMotorChannel(),
                            Config.get().getRightMainMotorChannel()
                    );
                    
                    break;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return drive;
    }
    
    public void disabledInit()
    {
        System.out.println("outputting history");
        outputHistory();
    }
}

/** Controller button map
 * new JoystickButton(controller, ControllerMap.LEFT_BUTTON)
                .whileHeld(moveArmsForwardsCommand);
                       
        new JoystickButton(controller, ControllerMap.RIGHT_BUTTON)
                .whileHeld(moveArmsBackwardsCommand);
        
        new JoystickButton(controller, ControllerMap.X_BUTTON)
                .whileHeld(squeezeCommand);
        
        new JoystickButton(controller, ControllerMap.B_BUTTON)
                .whileHeld(unsqueezeCommand);
        
        new JoystickButton(controller, ControllerMap.A_BUTTON)
                .whenPressed(roughThrowCommand);
        
        new JoystickButton(controller, ControllerMap.START_BUTTON)
                .whenPressed(goToHomeCommand);
 */
