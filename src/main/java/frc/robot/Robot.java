/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot
{
    @Override
    public void robotInit()
    {
        new RobotContainer();
    }
    
    @Override
    public void robotPeriodic()
    {
        SmartDashboard.putNumber("Drive - leftVelocity", -1 * RobotContainer.driveSubsystem.getLeftVelocity());    
        SmartDashboard.putNumber("Drive - rightVelocity", -1 * RobotContainer.driveSubsystem.getRightVelocity());
        RobotContainer.driveSubsystem.updatePID();
    }
    
    @Override
    public void disabledInit()
    {
        
    }
    
    @Override
    public void disabledPeriodic()
    {
        
    }
    
    @Override
    public void autonomousInit()
    {
        
    }
    
    @Override
    public void autonomousPeriodic()
    {
        
    }

    @Override
    public void teleopInit()
    {
        RobotContainer.driveCommand.schedule();
    }
    
    @Override
    public void teleopPeriodic()
    {
        CommandScheduler.getInstance().run();
    }
    
    @Override
    public void testInit()
    {
        CommandScheduler.getInstance().cancelAll();
    }
    
    @Override
    public void testPeriodic()
    {
        
    }
}
