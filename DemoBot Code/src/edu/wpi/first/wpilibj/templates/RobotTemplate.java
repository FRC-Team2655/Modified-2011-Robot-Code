package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RobotTemplate extends IterativeRobot {

    DriveSystem driveSystem;
    Arm arm;

    public RobotTemplate() {
        driveSystem = new DriveSystem();
        arm = new Arm();

        Global.DEMO_DRIVE = true;
    }

    public void robotInit() {
        Global.EMERGENCY_STOPPED = false;
    }

    public void disabledInit() {
        driveSystem.setDisabled();
    }

    public void autonomousInit() {
        driveSystem.setAutonomous();
    }

    public void autonomousPeriodic() {

    }

    public void teleopInit() {
        driveSystem.setTeleop();
    }

    public void teleopPeriodic() {
        SmartDashboard.putBoolean("Stop State", Global.EMERGENCY_STOPPED);
        SmartDashboard.putBoolean("Drive State", Global.DEMO_DRIVE);

        if (Global.DEMO_DRIVE) { // Restrained motor controls for allowing non team members to drive the robot
            Global.zSensitivity = -1;
            Global.ySensitivity = 0.55;
            Global.xSensitivity = 0.75;
        } else { // Unrestrained motor controls
            Global.zSensitivity = -1;
            Global.ySensitivity = 1;
            Global.xSensitivity = 1;
        }
    }

}
