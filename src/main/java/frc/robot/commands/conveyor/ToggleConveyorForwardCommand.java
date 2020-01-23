package frc.robot.commands.conveyor;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class ToggleConveyorForwardCommand extends CommandBase
{
    public ToggleConveyorForwardCommand()
    {
        addRequirements(RobotContainer.conveyorSubsystem);
    }
    
    @Override
    public void initialize()
    {
        RobotContainer.conveyorSubsystem.toggleRotation(false);
    }
    
    @Override
    public boolean isFinished()
    {
        return true;
    }
}