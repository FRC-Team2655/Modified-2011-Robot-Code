/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Zephan
 */
public class Arm {

    Joystick xbox;

    double motor1;
    double motor2;

    DigitalInput shortarm;    //short arm moving up
    DigitalInput shortarm2;   //short arm moving dwn
    DigitalInput longarm;     //Does not exist

    RobotDrive endeffector;
    RobotDrive arm;

    private final Thread thread;

    private final boolean IS_NOT_ENABLED = false;

    private int counter;

    public Arm() {
        Joystick xbox = new Joystick(2);

        counter = 0;

        endeffector = new RobotDrive(3, 4);
        arm = new RobotDrive(5, 6);

        shortarm = new DigitalInput(1);
        shortarm2 = new DigitalInput(2);
        longarm = new DigitalInput(3);

        thread = new ArmThread();
        thread.start();
    }

    private class ArmThread extends Thread {

        public ArmThread() {
        }

        public void run() {

            while (true) {
                try {
                    
                    Global.EMERGENCY_STOPPED = xbox.getRawButton(4);

//                    if (Global.EMERGENCY_STOPPED == IS_NOT_ENABLED) {
                    if (xbox.getRawAxis(2) < 0 && longarm.get() == true) {
                        motor1 = (xbox.getRawAxis(2) / 1.5);
                    } // xbox.getRawAxis(2) == left analog stick x axis
                    else if (xbox.getRawAxis(2) > 0 /*&& longarm2.get() == true*/) {
                        motor1 = xbox.getRawAxis(2);
                    } else {
                        motor1 = 0;
                    }
                    if (xbox.getRawAxis(5) > 0 && shortarm.get() == true) {
                        motor2 = xbox.getRawAxis(5);
                    } // xbox.getRawAxis(5) == right analog stick x axis
                    else if (xbox.getRawAxis(5) < 0 && shortarm2.get() == true) {
                        motor2 = xbox.getRawAxis(5);
                    } else {
                        motor2 = 0;
                    }
                    //orignaly divided by 1.5
                    arm.tankDrive(motor1, motor2);
                    // arm.tankDrive(xbox.getRawAxis(2), xbox.getRawAxis(5))
                    if ((xbox.getRawButton(5) && xbox.getRawButton(6)) || (!xbox.getRawButton(5) && !xbox.getRawButton(6))) { // buttons
                        if (xbox.getRawAxis(3) != 0) { //triggers
                            endeffector.tankDrive(xbox.getRawAxis(3), xbox.getRawAxis(3));
                        } // trigers
                        else {
                            endeffector.tankDrive(0.0, 0.0);
                        }
                    } // triggers/buttons = not pushed = no power to the motors
                    else if (xbox.getRawButton(5) == true) { //leftbumper = pull tube in
                        endeffector.tankDrive(1.0, -1.0);
                    } else if (xbox.getRawButton(6) == true) { //rightbumper = push tube out
                        endeffector.tankDrive(-1.0, 1.0);
                    }
//                    }
                    Thread.sleep(Global.controllerIdleTime);
                }  catch (Exception ex) {
                }

            }

        }

    }
}
