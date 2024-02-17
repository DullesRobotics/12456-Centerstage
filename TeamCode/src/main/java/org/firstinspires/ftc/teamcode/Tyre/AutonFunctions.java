package org.firstinspires.ftc.teamcode.Tyre;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.RobotManager.MechanumDriveTrain;

@Config
public class AutonFunctions {
    private static volatile MechanumDriveTrain mainFrame;
    public static int timeToStrafeNear = 1825;

    public static void parkTime(LinearOpMode op, TeamColor t) {
        mainFrame = new MechanumDriveTrain(op);
        mainFrame.addHardware(Configurator.getHardware(mainFrame));
        op.waitForStart();
        if (op.isStopRequested()) return;
        long timeToDrive = timeToStrafeNear;
        boolean isRed = t == TeamColor.RED ? true : false;
        timeToDrive += System.currentTimeMillis();
        while (System.currentTimeMillis() < timeToDrive && op.opModeIsActive())
            //If team is on short side strafe left a short distance
            mainFrame.autoStrafeTimed(timeToStrafeNear, isRed);
        mainFrame.setIndividualDrivePower(-0.3, -0.3, 0.3, 0.3);
        mainFrame.autonWait(200);
        mainFrame.setSidedDrivePower(0, 0);
        op.requestOpModeStop();
    }

    public static void startNew(LinearOpMode op, TeamColor t) {
        mainFrame = new MechanumDriveTrain(op);
        mainFrame.addHardware(Configurator.getHardware(mainFrame));
        op.waitForStart();
        if (op.isStopRequested()) return;

    }

    public enum TeamColor {
        RED, BLUE
    }

    public enum Direction {
        RIGHT, LEFT
    }
}

