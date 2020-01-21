/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.conveyor.ToggleConveyorCommand;
import frc.robot.commands.drive.DriveCommand;
import frc.robot.commands.intake.ToggleExtensionCommand;
import frc.robot.commands.intake.ToggleRotationCommand;
import frc.robot.commands.shooter.ShooterCommand;
import frc.robot.subsystems.ConveyorSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class RobotContainer
{
    public OI oi = new OI();
    public static DriveSubsystem driveSubsystem = new DriveSubsystem();
    public static IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
    public static ConveyorSubsystem conveyorSubsystem = new ConveyorSubsystem();
    public static ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
    public static DriveCommand driveCommand = new DriveCommand();
    public static ShuffleboardTab sbTab = Shuffleboard.getTab("Subsystems");
    
    public RobotContainer()
    {
        configureButtonBindings();
    }
    
    private void configureButtonBindings()
    {
        oi.buttonOne.whenReleased(new ToggleExtensionCommand());
        oi.buttonTwo.whenReleased(new ToggleRotationCommand());
        oi.buttonThree.whileHeld(new ShooterCommand(10.0));
        oi.buttonThree.whenReleased(new ShooterCommand(0.0));
        oi.buttonFive.whenReleased(new ToggleConveyorCommand(false));
        oi.buttonSix.whenReleased(new ToggleConveyorCommand(true));
        
    }
    
    public Command getAutonomousCommand()
    {
        return null;
    }
}
