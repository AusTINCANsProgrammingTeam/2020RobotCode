package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class DriveSubsystem extends SubsystemBase
{
    private DifferentialDrive differentialDrive;
    private CANPIDController leftPIDController;
    private CANPIDController rightPIDController;
    private CANEncoder leftEncoder;
    private CANEncoder rightEncoder;
    private double P, I, D, Iz, FF, maxOutput, minOutput;
    
    public DriveSubsystem()
    {
        CANSparkMax leftMotor1 = new CANSparkMax(0, MotorType.kBrushless);
        CANSparkMax leftMotor2 = new CANSparkMax(1, MotorType.kBrushless);
        CANSparkMax leftMotor3 = new CANSparkMax(2, MotorType.kBrushless);
        leftMotor2.restoreFactoryDefaults();
        leftMotor3.restoreFactoryDefaults();
        leftMotor2.follow(leftMotor1);
        leftMotor3.follow(leftMotor1);
        
        CANSparkMax rightMotor1 = new CANSparkMax(3, MotorType.kBrushless);
        CANSparkMax rightMotor2 = new CANSparkMax(4, MotorType.kBrushless);
        CANSparkMax rightMotor3 = new CANSparkMax(5, MotorType.kBrushless);
        rightMotor2.restoreFactoryDefaults();
        rightMotor3.restoreFactoryDefaults();
        rightMotor2.follow(rightMotor1);
        rightMotor3.follow(rightMotor1);
        
        differentialDrive = new DifferentialDrive(leftMotor1, rightMotor1);
        
        leftPIDController = leftMotor1.getPIDController();
        rightPIDController = rightMotor1.getPIDController();
        
        leftEncoder = leftMotor1.getEncoder();
        rightEncoder = rightMotor1.getEncoder();
        
        P = 0.00010; 
        I = 0;
        D = .0000; 
        Iz = 0; 
        FF = 0.000175; 
        maxOutput = 1; 
        minOutput = -1;

        leftPIDController.setP(P);
        leftPIDController.setI(I);
        leftPIDController.setD(D);
        leftPIDController.setIZone(Iz);
        leftPIDController.setFF(FF);
        leftPIDController.setOutputRange(minOutput, maxOutput);
        
        rightPIDController.setP(P);
        rightPIDController.setI(I);
        rightPIDController.setD(D);
        rightPIDController.setIZone(Iz);
        rightPIDController.setFF(FF);
        rightPIDController.setOutputRange(minOutput, maxOutput);
        
        RobotContainer.sbTab.add("Left Drive Encoder", leftEncoder).withWidget(BuiltInWidgets.kEncoder).withPosition(0, 0).withSize(2, 1);
        RobotContainer.sbTab.add("Right Drive Encoder", rightEncoder).withWidget(BuiltInWidgets.kEncoder).withPosition(2, 0).withSize(2, 1);
        RobotContainer.sbTab.add("Left Drive PID", leftPIDController).withWidget(BuiltInWidgets.kPIDController).withPosition(0, 1).withSize(1, 2);
        RobotContainer.sbTab.add("Right Drive PID", rightPIDController).withWidget(BuiltInWidgets.kPIDController).withPosition(4, 1).withSize(1, 2);
        
        SmartDashboard.putNumber("Drive - P", P);
        SmartDashboard.putNumber("Drive - I", I);
        SmartDashboard.putNumber("Drive - D", D);
        SmartDashboard.putNumber("Drive - Iz", Iz);
        SmartDashboard.putNumber("Drive - FF", FF);
        SmartDashboard.putNumber("Drive - minOutput", minOutput);
        SmartDashboard.putNumber("Drive - maxOutput", maxOutput);
    }

    public void updatePID()
    {
        double p = SmartDashboard.getNumber("Drive - P", 0);
        double i = SmartDashboard.getNumber("Drive - I", 0);
        double d = SmartDashboard.getNumber("Drive - D", 0);
        double min = SmartDashboard.getNumber("Drive - minOutput", 0);
        double max = SmartDashboard.getNumber("Drive - maxOutput", 0);
        
        if(p != P)
        {
            leftPIDController.setP(p);
            rightPIDController.setP(p);
            P = p;
        }
        if(i != I)
        {
            leftPIDController.setI(i);
            rightPIDController.setI(i);
            I = i;
        }
        if(d != D)
        {
            leftPIDController.setD(d);
            rightPIDController.setD(d);
            D = d;
        }
        if(max != maxOutput || min != minOutput)
        {
            minOutput = min;
            maxOutput = max;
        }
    }
    
    public void arcadeDrive(double velocity, double heading)
    {
        differentialDrive.arcadeDrive(velocity, heading * 0.7, true);
    }
    
    public void setLeftPIDReference(double velocity)
    {
        leftPIDController.setReference(velocity, ControlType.kVelocity);
    }
    
    public void setRightPidVelocitySetpoint(double velocity)
    {
        rightPIDController.setReference(velocity, ControlType.kVelocity);
    }
    
    public double getLeftVelocity()
    {
        return leftEncoder.getVelocity();
    }

    public double getRightVelocity()
    {
        return rightEncoder.getVelocity();
    }
}