package frc.robot.commands.conveyor;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class ToggleConveyorSolenoidCommand extends CommandBase
{
    public ToggleConveyorSolenoidCommand()
    {
        addRequirements(RobotContainer.conveyorSubsystem);
    }
    
    @Override
    public void initialize()
    {
        RobotContainer.conveyorSubsystem.toggleSolenoid();
    }
    
    @Override
    public boolean isFinished()
    {
        return false;
    }
}