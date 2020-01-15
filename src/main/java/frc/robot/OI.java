package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class OI
{
    public Joystick controller = new Joystick(0);
    public JoystickButton buttonOne = new JoystickButton(controller, 1);  
    public JoystickButton buttonTwo = new JoystickButton(controller, 2);  
    public JoystickButton buttonThree = new JoystickButton(controller, 3);  
    public JoystickButton buttonFour = new JoystickButton(controller, 4);
    public JoystickButton buttonFive = new JoystickButton(controller, 5);
    public JoystickButton buttonSix = new JoystickButton(controller, 6);
    
    public static int joystick = 0;
}