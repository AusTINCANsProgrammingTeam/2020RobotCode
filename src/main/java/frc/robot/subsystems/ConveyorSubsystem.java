package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class ConveyorSubsystem extends SubsystemBase
{
    private CANEncoder encoder;
    private CANPIDController PIDController;
    private double P, I, D, Iz, FF, maxOutput, minOutput;
    private boolean spinning;
    
    public ConveyorSubsystem()
    {
        CANSparkMax motor = new CANSparkMax(10, MotorType.kBrushless);
        motor.restoreFactoryDefaults();
        encoder = motor.getEncoder();
        PIDController = motor.getPIDController();
        
        spinning = false;
        
        P = 0.00010; 
        I = 0;
        D = .0000; 
        Iz = 0; 
        FF = 0.000175; 
        maxOutput = 1; 
        minOutput = -1;
        
        PIDController.setP(P);
        PIDController.setI(I);
        PIDController.setD(D);
        PIDController.setIZone(Iz);
        PIDController.setFF(FF);
        PIDController.setOutputRange(minOutput, maxOutput);
        PIDController.setReference(0.0, ControlType.kVelocity);
        
        RobotContainer.sbTab.add("Conveyor Encoder", encoder).withWidget(BuiltInWidgets.kEncoder).withPosition(8, 1).withSize(2, 1);
        RobotContainer.sbTab.add("Conveyor PID", PIDController).withWidget(BuiltInWidgets.kPIDController).withPosition(10, 1).withSize(1, 2);
        
        SmartDashboard.putNumber("Conveyor - P", P);
        SmartDashboard.putNumber("Conveyor - I", I);
        SmartDashboard.putNumber("Conveyor - D", D);
        SmartDashboard.putNumber("Conveyor - Iz", Iz);
        SmartDashboard.putNumber("Conveyor - FF", FF);
        SmartDashboard.putNumber("Conveyor - minOutput", minOutput);
        SmartDashboard.putNumber("Conveyor - maxOutput", maxOutput);
        
        motor.close();
    }
    
    public void updatePID()
    {
        double p = SmartDashboard.getNumber("Conveyor - P", 0);
        double i = SmartDashboard.getNumber("Conveyor - I", 0);
        double d = SmartDashboard.getNumber("Conveyor - D", 0);
        double min = SmartDashboard.getNumber("Conveyor - minOutput", 0);
        double max = SmartDashboard.getNumber("Conveyor - maxOutput", 0);
        
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
    
    public void toggleRotation(boolean reversed)
    {
        if(spinning)
        {
            setPIDReference(0.0);
        }
        else
        {
            if(reversed)
            {
                setPIDReference(-10.0);
            }
            else
            {
                setPIDReference(10.0);
            }
        }
        spinning = !spinning;
    }
}