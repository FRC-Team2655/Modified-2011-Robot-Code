package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.RobotDrive;

public class DriveSystem {

    private final TeamJoystick driveStick;

    private final Thread thread;
    private final RobotDrive mainDrive;

    private int driveMode;

    private final boolean IS_NOT_ENABLED = false;

    public class ControlModeEnum {
//      Possible states the robot can be in.  

        static final int Disabled = 0;
        static final int Autonomous = 1;
        static final int Teleop = 2;
        static final int Test = 3;
    }

    private class DriveSystemThread extends Thread {

        public DriveSystemThread() {

        }

        public void run() {
//      Runs... Forever  
            while (true) {

                try {
                    if (driveMode == ControlModeEnum.Teleop && Global.EMERGENCY_STOPPED == IS_NOT_ENABLED) {
                        // If the robot is in teleop it will accept input from the joysticks.
                        mainDrive.arcadeDrive(driveStick);
//                        mainDrive.mecanumDrive_Cartesian(0, driveStick.getAxis(TeamJoystick.AxisType.kY), driveStick.getAxis(TeamJoystick.AxisType.kZ), 0);
                    } else {
                    }

                    Thread.sleep(Global.driveIdleTime);
                } catch (InterruptedException ex) {
                }
            }
        }
    }

    public DriveSystem(TeamJoystick driveStick) {
        this.driveStick = driveStick;

        driveMode = ControlModeEnum.Disabled;
        mainDrive = new RobotDrive(1, 2);

        thread = new DriveSystemThread();
        thread.start();
    }

    public void setDisabled() {
//  Sets the drive mode to Disabled.        
        driveMode = ControlModeEnum.Disabled;

    }

    public void setAutonomous() {
//  Sets the drive mode to Autonomous.
        driveMode = ControlModeEnum.Autonomous;

    }

    public void setTeleop() {
//  Sets the drive mode to Teleop.        
        driveMode = ControlModeEnum.Teleop;

    }

    public void setTest() {
//  Sets the drive mode to Teleop.        
        driveMode = ControlModeEnum.Test;

    }

}
