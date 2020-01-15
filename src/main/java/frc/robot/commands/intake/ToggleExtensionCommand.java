package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class ToggleExtensionCommand extends CommandBase
{
    public ToggleExtensionCommand()
    {
        addRequirements(RobotContainer.intakeSubsystem);
    }
    
    @Override
    public void initialize()
    {
        RobotContainer.intakeSubsystem.toggleExtension();
    }
    
    @Override
    public boolean isFinished()
    {
        return true;
    }
}