package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class IntakeSubsystem extends SubsystemBase
{
    private static CANSparkMax sparkMax;
    private DoubleSolenoid extensionSolenoid;
    private CANPIDController PIDController;
    private CANEncoder encoder;
    private double P, I, D, Iz, FF, maxOutput, minOutput;
    private boolean spinning;
    private boolean extended;
    
    public IntakeSubsystem()
    {
        sparkMax = new CANSparkMax(6, MotorType.kBrushless);
        sparkMax.restoreFactoryDefaults();
        
        PIDController = sparkMax.getPIDController();
        encoder = sparkMax.getEncoder();
        
        extensionSolenoid = new DoubleSolenoid(6, 7);
        extensionSolenoid.set(Value.kOff);
        
        spinning = false;
        extended = false;
        
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
        PIDController.setReference(0.0, ControlType.kVelocity);
        
        RobotContainer.sbTab.add("Intake Pos", encoder.getPosition()).withPosition(2, 0).withSize(1, 1);
        RobotContainer.sbTab.add("Intake Vel", encoder.getVelocity()).withPosition(2, 1).withSize(1, 1);
        //RobotContainer.sbTab.add("Intake PID", PIDController).withWidget(BuiltInWidgets.kPIDController).withPosition(7, 1).withSize(1, 2);
        
        SmartDashboard.putNumber("Intake - P", P);
        SmartDashboard.putNumber("Intake - I", I);
        SmartDashboard.putNumber("Intake - D", D);
        SmartDashboard.putNumber("Intake - Iz", Iz);
        SmartDashboard.putNumber("Intake - FF", FF);
        SmartDashboard.putNumber("Intake - minOutput", minOutput);
        SmartDashboard.putNumber("Intake - maxOutput", maxOutput);
    }
    
    public void updatePID()
    {
        double p = SmartDashboard.getNumber("Intake - P", 0);
        double i = SmartDashboard.getNumber("Intake - I", 0);
        double d = SmartDashboard.getNumber("Intake - D", 0);
        double min = SmartDashboard.getNumber("Intake - minOutput", 0);
        double max = SmartDashboard.getNumber("Intake - maxOutput", 0);
        
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
    
    public void toggleExtension()
    {
        if(extended)
        {
            extensionSolenoid.set(Value.kReverse);
        }
        else
        {
            extensionSolenoid.set(Value.kForward);
        }
        extended = !extended;
    }
    
    public void toggleRotation()
    {
        if(spinning)
        {
            setPIDReference(0.0);
        }
        else
        {
            setPIDReference(10.0);
        }
        spinning = !spinning;
    }
}