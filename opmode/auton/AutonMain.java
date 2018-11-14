package org.firstinspires.ftc.teamcode.opmode.auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.hardware.HardwareTank;
import org.firstinspires.ftc.teamcode.util.vision.VisionManager;
import org.firstinspires.ftc.teamcode.hardware.Constants;

@Autonomous(name="Auton: Depot Everything", group="Auton")
public class AutonMain extends LinearOpMode {
    /* Private OpMode members */
    private ElapsedTime     runtime = new ElapsedTime();

    /* Robot hardware */
    private HardwareTank robot = new HardwareTank(this);

    /**
     * Runs the autonomous routine.
     */
    @Override
    public void runOpMode() {
        // Initialize CV
        VisionManager visionManager = new VisionManager();
        visionManager.goldAlignInit(hardwareMap);

        // Initialize robot
        robot.init(hardwareMap);

        // Wait until we're told to go
        waitForStart();
        runtime.reset();  // Start counting run time from now.

        robot.land();
        robot.drivetrain.turn(0.3, 15.0, 3.0);
        telemetry.addData("Status", "Turned 30 degrees");
        robot.drivetrain.driveToPos(0.3, -4, -4, 4.0);
        telemetry.addData("Status", "Forward towards sampling " + (Constants.landerToSample-Constants.robotLength));
        robot.drivetrain.turn(0.3, -105.0, 5.0);
        telemetry.addData("Status", "Turn -120 degrees ");
        robot.sample(visionManager);
        telemetry.addData("Status", "Finished sampling " + runtime.toString());
        robot.drivetrain.driveToPos(0.3, 12,12,3);
        robot.drivetrain.turn(0.3, 105.0, 3.0);
        telemetry.addData("Status", "Turn 120 degrees ");
        robot.drivetrain.driveToPos(0.5, 24,24,5);
        robot.drivetrain.driveToPos(0.5, -50,-50,5);

//        robot.getCubeDetails();
//        sleep(1000);

        // Stop CV
        visionManager.goldAlignStop();

    }
}
