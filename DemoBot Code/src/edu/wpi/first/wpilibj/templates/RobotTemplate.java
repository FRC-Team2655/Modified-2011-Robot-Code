package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.IterativeRobot;

public class RobotTemplate extends IterativeRobot {

    TeamJoystick DriveStick;

    DriveSystem driveSystem;

    public void robotInit() {
        Global.EMERGENCY_STOPPED = false;
        
        DriveStick = new TeamJoystick(1);

        driveSystem = new DriveSystem(DriveStick);
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

    }

}
