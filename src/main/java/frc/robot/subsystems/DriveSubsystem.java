package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class DriveSubsystem extends SubsystemBase
{
    private static CANSparkMax leftMotor1;
    private static CANSparkMax leftMotor2;
    private static CANSparkMax leftMotor3;
    private static CANSparkMax rightMotor1;
    private static CANSparkMax rightMotor2;
    private static CANSparkMax rightMotor3;
    private DifferentialDrive differentialDrive;
    private CANPIDController leftPIDController;
    private CANPIDController rightPIDController;
    private CANEncoder leftEncoder;
    private CANEncoder rightEncoder;
    private double P, I, D, Iz, FF, maxOutput, minOutput;
    
    public DriveSubsystem()
    {
        leftMotor1 = new CANSparkMax(0, MotorType.kBrushless);
        leftMotor2 = new CANSparkMax(1, MotorType.kBrushless);
        leftMotor3 = new CANSparkMax(2, MotorType.kBrushless);
        leftMotor1.restoreFactoryDefaults();
        leftMotor2.restoreFactoryDefaults();
        leftMotor3.restoreFactoryDefaults();
        leftMotor2.follow(leftMotor1);
        leftMotor3.follow(leftMotor1);
        
        rightMotor1 = new CANSparkMax(3, MotorType.kBrushless);
        rightMotor2 = new CANSparkMax(4, MotorType.kBrushless);
        rightMotor3 = new CANSparkMax(5, MotorType.kBrushless);
        rightMotor1.restoreFactoryDefaults();
        rightMotor2.restoreFactoryDefaults();
        rightMotor3.restoreFactoryDefaults();
        rightMotor2.follow(rightMotor1);
        rightMotor3.follow(rightMotor1);
        
        differentialDrive = new DifferentialDrive(leftMotor1, rightMotor1);
        
        leftPIDController = leftMotor1.getPIDController();
        rightPIDController = rightMotor1.getPIDController();
        
        leftEncoder = leftMotor1.getEncoder();
        rightEncoder = rightMotor1.getEncoder();
        
        P = Constants.P;
        I = Constants.I;
        D = Constants.D;
        Iz = Constants.Iz;
        FF = Constants.FF;
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
        
        RobotContainer.sbTab.add("L Drive Pos", leftEncoder.getPosition()).withPosition(0, 0).withSize(1, 1);
        RobotContainer.sbTab.add("L Drive Vel", leftEncoder.getVelocity()).withPosition(0, 1).withSize(1, 1);
        RobotContainer.sbTab.add("R Drive Pos", rightEncoder.getPosition()).withPosition(1, 0).withSize(1, 1);
        RobotContainer.sbTab.add("R Drive Vel", rightEncoder.getVelocity()).withPosition(1, 1).withSize(1, 1);
        //RobotContainer.driveTab.add("Left Drive PID", leftPIDController).withWidget(BuiltInWidgets.kPIDController).withPosition(0, 1).withSize(1, 2);
        //RobotContainer.driveTab.add("Right Drive PID", rightPIDController).withWidget(BuiltInWidgets.kPIDController).withPosition(4, 1).withSize(1, 2);
        
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
    
    @Override
    public void periodic()
    {
        SmartDashboard.putNumber("Drive - leftVelocity", -RobotContainer.driveSubsystem.getLeftVelocity());    
        SmartDashboard.putNumber("Drive - rightVelocity", -RobotContainer.driveSubsystem.getRightVelocity());
    }
    
    public void arcadeDrive(double velocity, double heading)
    {
        differentialDrive.arcadeDrive(velocity, heading, true);
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