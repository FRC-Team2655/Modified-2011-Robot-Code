package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RobotTemplate extends IterativeRobot {

    DriveSystem driveSystem;
    Arm arm;

    public RobotTemplate() {
        driveSystem = new DriveSystem();
        arm = new Arm();
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
    }

}
