//package org.firstinspires.ftc.teamcode.Tyre;
//
//import com.acmerobotics.dashboard.config.Config;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//
//import org.firstinspires.ftc.teamcode.Hardware.ComponentArea;
//import org.firstinspires.ftc.teamcode.Hardware.Motor.Motor;
//import org.firstinspires.ftc.teamcode.Hardware.Servo;
//import org.firstinspires.ftc.teamcode.Libraries.RoadRunner.Drive.SampleMecanumDrive;
//import org.firstinspires.ftc.teamcode.RobotManager.MechanumDriveTrain;
//
//import java.lang.reflect.Field;
//import java.text.FieldPosition;
//
//@Config
//public class AutonFunctions {
//    private static volatile MechanumDriveTrain mainFrame;
//    private static volatile SampleMecanumDrive roadRunner;
//    public static int timeToPark = 5000;
//    public static int parkingTime = 600;
//    public static double motorMovementPower = 0.5;
//
//    public static void start(LinearOpMode op, TeamColor t, FieldPosition position){
//        mainFrame = new MechanumDriveTrain(op);
//        mainFrame.addHardware(Configurator.getHardware(mainFrame));
//        op.waitForStart();
//        if(op.isStopRequested()) return;
//        long timeToDrive = position == FieldPosition.SAME ? timeToPark : timeToPark;
//        timeToDrive += System.currentTimeMillis();
//        while(System.currentTimeMillis() < timeToDrive && op.opModeIsActive())
//            mainFrame.setIndividualDrivePower(0,0,-0.3,-0.3);
//        mainFrame.setSidedDrivePower(0,0);
//        op.requestOpModeStop();
//    }
//
//    public static void startNew(LinearOpMode op, TeamColor t, FieldPosition position){
//        roadRunner = new SampleMecanumDrive(op);
//        mainFrame = roadRunner.getDriveTrain();
//        Servo Claw = mainFrame.getServo("CLAW");
//        boolean isRed = t == TeamColor.RED;
//        double motorPower = isRed ? motorMovementPower : -motorMovementPower;
//
//        op.waitForStart();
//
//        if(op.isStopRequested()) return;
//
//        if(position == FieldPosition.SAME){
//            //motorPower = 0.5; parking time = 600
//            Claw.get().setPosition(0.15);
//            mainFrame.autonWait(200);
//            mainFrame.setIndividualDrivePower(motorPower,-motorPower,-motorPower,motorPower);
//            mainFrame.autonWait(parkingTime);
//            mainFrame.setIndividualDrivePower(-motorPower/5,0,-motorPower/5,0);
//            mainFrame.autonWait(750);
//            mainFrame.setIndividualDrivePower(-0.25,-0.25,-0.25,-0.25);
//            mainFrame.autonWait(700);
//            Claw.get().setPosition(0.3);
//            mainFrame.autonWait(400);
//            mainFrame.setIndividualDrivePower(0.25,0.25,0.25,0.25);
//            mainFrame.autonWait(700);
//            mainFrame.setIndividualDrivePower(0,-motorPower/5,0,-motorPower/5);
//            mainFrame.autonWait(750);
//            mainFrame.setIndividualDrivePower(motorPower,-motorPower,-motorPower,motorPower);
//            mainFrame.autonWait(500);
//            mainFrame.setIndividualDrivePower(0,0,0,0);
//            mainFrame.autonWait(750);
//        }
//        else if(position == FieldPosition.DIFFERENT){
//            mainFrame.setIndividualDrivePower(-motorPower,motorPower,motorPower,-motorPower);
//            mainFrame.autonWait(parkingTime);
//            mainFrame.setIndividualDrivePower(-motorPower/5,0,-motorPower/5,0);
//            mainFrame.autonWait(750);
//            mainFrame.setIndividualDrivePower(-0.25,-0.25,-0.25,-0.25);
//            mainFrame.autonWait(750);
//            mainFrame.setIndividualDrivePower(0.25,0.25,0.25,0.25);
//            mainFrame.autonWait(700);
//            mainFrame.setIndividualDrivePower(0,-motorPower/5,0,-motorPower/5);
//            mainFrame.autonWait(200);
//            mainFrame.setIndividualDrivePower(-motorPower,motorPower,motorPower,-motorPower);
//            mainFrame.autonWait(500);
//            mainFrame.setIndividualDrivePower(0,-motorPower/5,0,-motorPower/5);
//            mainFrame.autonWait(700);
//            mainFrame.setIndividualDrivePower(-0.5,-0.5,-0.5,-0.5);
//            mainFrame.autonWait(600);
//        }
//    }
//
//    public enum TeamColor{
//        RED, BLUE
//    }
//
//    public enum Direction {
//        RIGHT, LEFT;
//    }
//    public enum FieldPosition{
//        SAME, DIFFERENT
//    }
//
//
//}
