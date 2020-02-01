package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class ShooterSubsystem extends SubsystemBase
{
    private static CANSparkMax sparkMax;
    private CANPIDController PIDController;
    private CANEncoder encoder;
    private double P, I, D, Iz, FF, maxOutput, minOutput;
    
    public ShooterSubsystem()
    {
        sparkMax = new CANSparkMax(9, MotorType.kBrushless);
        sparkMax.restoreFactoryDefaults();
        
        PIDController = sparkMax.getPIDController();
        encoder = sparkMax.getEncoder();
        
        P = Constants.P;
        I = Constants.I;
        D = Constants.D;
        Iz = Constants.Iz;
        FF = Constants.FF;
        maxOutput = 1; 
        minOutput = -1;
        
        PIDController.setP(P);
        PIDController.setI(I);
        PIDController.setD(D);
        PIDController.setIZone(Iz);
        PIDController.setFF(FF);
        PIDController.setOutputRange(minOutput, maxOutput);
        PIDController.setReference(0.5, ControlType.kVelocity);
        
        RobotContainer.sbTab.add("Shooter Pos", encoder.getPosition()).withPosition(4, 0).withSize(1, 1);
        RobotContainer.sbTab.add("Shooter Vel", encoder.getVelocity()).withPosition(4, 1).withSize(1, 1);
        //RobotContainer.sbTab.add("Intake PID", PIDController).withWidget(BuiltInWidgets.kPIDController).withPosition(7, 3).withSize(1, 2);
        
        SmartDashboard.putNumber("Shooter - P", P);
        SmartDashboard.putNumber("Shooter - I", I);
        SmartDashboard.putNumber("Shooter - D", D);
        SmartDashboard.putNumber("Shooter - Iz", Iz);
        SmartDashboard.putNumber("Shooter - FF", FF);
        SmartDashboard.putNumber("Shooter - minOutput", minOutput);
        SmartDashboard.putNumber("Shooter - maxOutput", maxOutput);
    }
    
    public void updatePID()
    {
        double p = SmartDashboard.getNumber("Shooter - P", 0);
        double i = SmartDashboard.getNumber("Shooter - I", 0);
        double d = SmartDashboard.getNumber("Shooter - D", 0);
        double min = SmartDashboard.getNumber("Shooter - minOutput", 0);
        double max = SmartDashboard.getNumber("Shooter - maxOutput", 0);
        
        if(p != P)
        {
            PIDController.setP(p);
            P = p;
        }
        if(i != I)
        {
            PIDController.setI(i);
            I = i;
        }
        if(d != D)
        {
            PIDController.setD(d);
            D = d;
        }
        if(max != maxOutput || min != minOutput)
        {
            PIDController.setOutputRange(min, max);
            minOutput = min;
            maxOutput = max;
        }
    }
    
    public void setPIDReference(double velocity)
    {
        PIDController.setReference(velocity, ControlType.kVelocity);
    }
}