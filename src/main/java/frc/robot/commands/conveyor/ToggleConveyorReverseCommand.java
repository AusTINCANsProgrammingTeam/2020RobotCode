package frc.robot.commands.conveyor;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class ToggleConveyorReverseCommand extends CommandBase
{
    public ToggleConveyorReverseCommand()
    {
        addRequirements(RobotContainer.conveyorSubsystem);
    }
    
    @Override
    public void initialize()
    {
        RobotContainer.conveyorSubsystem.toggleRotation(true);
    }
    
    @Override
    public boolean isFinished()
    {
        return true;
    }
}