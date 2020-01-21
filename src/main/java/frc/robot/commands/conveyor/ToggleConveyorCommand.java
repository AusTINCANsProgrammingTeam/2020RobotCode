package frc.robot.commands.conveyor;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class ToggleConveyorCommand extends CommandBase
{
    private boolean reversed;
    
    public ToggleConveyorCommand(boolean reversed)
    {
        addRequirements(RobotContainer.conveyorSubsystem);
        this.reversed = reversed;
    }
    
    @Override
    public void initialize()
    {
        RobotContainer.conveyorSubsystem.toggleRotation(reversed);
    }
    
    @Override
    public boolean isFinished()
    {
        return true;
    }
}