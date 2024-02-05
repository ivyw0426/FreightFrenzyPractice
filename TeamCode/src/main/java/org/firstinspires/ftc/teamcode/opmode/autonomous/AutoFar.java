package org.firstinspires.ftc.teamcode.opmode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;

public class AutoNear extends LinearOpMode {

    private SampleMecanumDrive driverToPosition;

    protected SampleMecanumDrive getDriverToPosition() {
        return driverToPosition;
    }
    @Override
    public void runOpMode() throws InterruptedException {
        driveToBarcode();

    }

    protected void driveToBarcode() {
        getDriverToPosition().followTrajectory(
                getDriverToPosition().trajectoryBuilder(getDriverToPosition().getPoseEstimate())
                        .forward(AutoConstants.TILE_SIDE_LENGTH_IN * 0.5)
                        .build()
        );

    }

    protected void detectDuck(){
        //TODO
    }

}
