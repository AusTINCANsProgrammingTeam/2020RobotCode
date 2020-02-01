/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.logging.Logger;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.conveyor.ToggleConveyorForwardCommand;
import frc.robot.commands.conveyor.ToggleConveyorReverseCommand;
import frc.robot.commands.conveyor.ToggleConveyorSolenoidCommand;
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
    public static Joystick controller = new Joystick(0);
    public static JoystickButton buttonOne = new JoystickButton(controller, 1);  
    public static JoystickButton buttonTwo = new JoystickButton(controller, 2);  
    public static JoystickButton buttonThree = new JoystickButton(controller, 3);  
    public static JoystickButton buttonFour = new JoystickButton(controller, 4);
    public static JoystickButton buttonFive = new JoystickButton(controller, 5);
    public static JoystickButton buttonSix = new JoystickButton(controller, 6);
    public static int joystick = 0;
    
    public static ShuffleboardTab sbTab = Shuffleboard.getTab("Subsystems");
    public static DriveSubsystem driveSubsystem = new DriveSubsystem();
    public static IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
    public static ConveyorSubsystem conveyorSubsystem = new ConveyorSubsystem();
    public static ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
    
    public static DriveCommand driveCommand = new DriveCommand();
    
        
    public RobotContainer()
    {
        driveSubsystem.setDefaultCommand(driveCommand);
        configureButtonBindings();
    }
    
    private void configureButtonBindings()
    {
        buttonOne.whenReleased(new ToggleExtensionCommand());
        buttonTwo.whenReleased(new ToggleRotationCommand());
        buttonThree.whileHeld(new ShooterCommand(10.0));
        buttonFour.whenReleased(new ToggleConveyorForwardCommand());
        buttonFive.whenReleased(new ToggleConveyorReverseCommand());
        buttonSix.whenReleased(new ToggleConveyorSolenoidCommand());
    }
    
    public Command getAutonomousCommand()
    {
        return null;
    }
}
