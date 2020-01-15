package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class ToggleRotationCommand extends CommandBase
{
    public ToggleRotationCommand()
    {
        addRequirements(RobotContainer.intakeSubsystem);
    }
    
    @Override
    public void initialize()
    {
        RobotContainer.intakeSubsystem.toggleRotation();
    }
    
    @Override
    public boolean isFinished()
    {
        return true;
    }
}