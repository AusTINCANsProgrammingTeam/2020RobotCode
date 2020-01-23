package frc.robot.commands.drive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class DriveCommand extends CommandBase
{
    private Joystick joystick = new Joystick(RobotContainer.joystick);
    
    public DriveCommand()
    {
        addRequirements(RobotContainer.driveSubsystem);
    }
    
    @Override
    public void execute()
    {
        RobotContainer.driveSubsystem.arcadeDrive(joystick.getRawAxis(1), joystick.getRawAxis(2) * 0.7);
    }
    
    @Override
    public boolean isFinished()
    {
        return false;
    }
}