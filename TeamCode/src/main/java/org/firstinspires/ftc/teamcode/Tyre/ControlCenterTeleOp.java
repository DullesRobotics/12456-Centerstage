package org.firstinspires.ftc.teamcode.Tyre;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Hardware.Controller;
import org.firstinspires.ftc.teamcode.Hardware.Motor.Motor;
import org.firstinspires.ftc.teamcode.Hardware.Servo;
import org.firstinspires.ftc.teamcode.RobotManager.Robot;

@Config
public class ControlCenterTeleOp {

    public static double clawClosedPos = 0.3, clawGripPos = 0.05, clawOpenPos = 0.4;
    public static double originalLiftPos = 0.0, liftDownPow = 1, liftUpPow = 0.8;

    public static void clawRelease(Robot r, Controller ctrl){
        r.addThread(new Thread(() -> {
            Servo outtakeServo1 = (Servo) r.getServo("CLAW");
            Servo outtakeServo2 = (Servo) r.getServo("CLAW2");
            outtakeServo1.get().setPosition(clawGripPos);
            outtakeServo2.get().setPosition(clawGripPos);
            while (r.op().opModeIsActive()) {
                if (ctrl.rightBumper()) {
                    outtakeServo2.get().setPosition(clawClosedPos);
                    outtakeServo1.get().setPosition(clawClosedPos);
                } else if (ctrl.leftBumper()) {
                    outtakeServo1.get().setPosition(clawOpenPos);
                    outtakeServo2.get().setPosition(clawOpenPos);
                } else {
                    outtakeServo1.get().setPosition(clawGripPos);
                    outtakeServo2.get().setPosition(clawGripPos);
                }
            }
        }), true);
    }

    public static void clawRotate(Robot r, Controller ctrl){
        r.addThread(new Thread(() ->{
            Motor clawMotor = r.getMotor("CLAWMOTOR");
            while(r.op().opModeIsActive()){
                if(ctrl.buttonLeft()) clawMotor.get().setPower(0.3);
                else if(ctrl.buttonRight()) clawMotor.get().setPower(-0.3);
            }
        }), true);
    }

    public static void ArmLift(Robot r, Controller ctrl){
        r.addThread(new Thread(() -> {
            Motor liftRightMotor = r.getMotor("LIFTRIGHT");
            Motor liftLeftMotor = r.getMotor("LIFTLEFT");
            liftRightMotor.get().setPower(originalLiftPos);
            liftLeftMotor.get().setPower(originalLiftPos);
            double currentLiftPower = liftLeftMotor.get().getPower();
            while(r.op().opModeIsActive()){
                if(ctrl.leftTrigger() > 0){
                    liftLeftMotor.get().setPower(liftUpPow * ctrl.leftTrigger());
                    liftRightMotor.get().setPower(liftUpPow * ctrl.leftTrigger());
                }
                else if(ctrl.rightTrigger() > 0){
                    liftLeftMotor.get().setPower(liftDownPow * -ctrl.rightTrigger());
                    liftRightMotor.get().setPower(liftDownPow * -ctrl.rightTrigger());
                }
                else{
                    liftLeftMotor.get().setPower(originalLiftPos);
                    liftRightMotor.get().setPower(originalLiftPos);
                }
            }
        }), true);
    }

//    public static void checkLiftPos(Robot r, double liftPower, Motor lift){
//        r.addThread(new Thread(() -> {
//            if(liftPower > 2){
//                lift.get().setPower(2);
//            }
//        }), true);
//    }
}
