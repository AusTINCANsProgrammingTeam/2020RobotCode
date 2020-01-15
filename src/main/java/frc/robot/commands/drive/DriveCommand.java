package frc.robot.commands.drive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.OI;
import frc.robot.RobotContainer;

public class DriveCommand extends CommandBase
{
    private Joystick joystick = new Joystick(OI.joystick);
    
    public DriveCommand()
    {
        addRequirements(RobotContainer.driveSubsystem);
    }
    
    @Override
    public void execute()
    {
        RobotContainer.driveSubsystem.arcadeDrive(joystick.getRawAxis(1), joystick.getRawAxis(2));
    }
    
    @Override
    public boolean isFinished()
    {
        return false;
    }
}