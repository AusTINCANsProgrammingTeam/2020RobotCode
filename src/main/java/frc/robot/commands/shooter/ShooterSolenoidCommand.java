package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class ShooterSolenoidCommand extends CommandBase
{
    public ShooterSolenoidCommand()
    {
        addRequirements(RobotContainer.shooterSubsystem);
    }
    
    @Override
    public void initialize()
    {
        RobotContainer.shooterSubsystem.toggleSolenoid();
    }
    
    @Override
    public boolean isFinished()
    {
        return false;
    }
}