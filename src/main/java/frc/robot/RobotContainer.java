/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.drive.DriveCommand;
import frc.robot.commands.intake.ToggleExtensionCommand;
import frc.robot.commands.intake.ToggleRotationCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

public class RobotContainer
{
    public OI oi = new OI();
    public static DriveSubsystem driveSubsystem = new DriveSubsystem();
    public static IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
    public static DriveCommand driveCommand = new DriveCommand();
    
    public RobotContainer()
    {
        configureButtonBindings();
    }
    
    private void configureButtonBindings()
    {
        oi.buttonOne.whenReleased(new ToggleExtensionCommand());
        oi.buttonTwo.whenReleased(new ToggleRotationCommand());
    }
    
    public Command getAutonomousCommand()
    {
        return null;
    }
}
