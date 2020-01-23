package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class ShooterCommand extends CommandBase
{
    private double velocity;
    
    public ShooterCommand(double velocity)
    {
        addRequirements(RobotContainer.shooterSubsystem);
        this.velocity = velocity;
    }
    
    @Override
    public void initialize()
    {
        RobotContainer.shooterSubsystem.setPIDReference(velocity);
    }
    
    @Override
    public void end(boolean interrupted)
    {
        RobotContainer.shooterSubsystem.setPIDReference(0.0);
    }
    
    @Override
    public boolean isFinished()
    {
        return false;
    }
}