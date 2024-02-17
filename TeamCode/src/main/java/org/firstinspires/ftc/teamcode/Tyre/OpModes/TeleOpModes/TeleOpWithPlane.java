package org.firstinspires.ftc.teamcode.Tyre.OpModes.TeleOpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RobotManager.MechanumDriveTrain;
import org.firstinspires.ftc.teamcode.RobotManager.StandardDriveTrain;
import org.firstinspires.ftc.teamcode.Tyre.Configurator;
import org.firstinspires.ftc.teamcode.Tyre.ControlCenterTeleOp;

@TeleOp
public class TeleOpWithPlane extends LinearOpMode {
    private MechanumDriveTrain baseRobot;
    @Override
    public void runOpMode() throws InterruptedException {
        baseRobot = new MechanumDriveTrain(this);
        baseRobot.addHardware(Configurator.getHardware(baseRobot));

        waitForStart();

        baseRobot.driveWithController(baseRobot.ctrl1());
        ControlCenterTeleOp.ArmLift(baseRobot, baseRobot.ctrl2());
        ControlCenterTeleOp.intakeMotor(baseRobot, baseRobot.ctrl2());
        ControlCenterTeleOp.outakeServo(baseRobot, baseRobot.ctrl2());
        ControlCenterTeleOp.planeLaunch(baseRobot, baseRobot.ctrl2());


        while (opModeIsActive())
            baseRobot.getLogger().updateLog();

        baseRobot.stopAllThreads();
    }
}

