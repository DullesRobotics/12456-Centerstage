package org.firstinspires.ftc.teamcode.Tyre;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Hardware.Controller;
import org.firstinspires.ftc.teamcode.Hardware.Motor.Motor;
import org.firstinspires.ftc.teamcode.Hardware.Servo;
import org.firstinspires.ftc.teamcode.RobotManager.Robot;


@Config
public class ControlCenterTeleOp {

    public static double planeLoad = 0.0, planeLaunch = 0.75;
    public static double originalLiftPos = 0.0, liftDownPow = 1, liftUpPow = 0.8;
    public static double outakeBeg = 0.0, outakeFlip = 0.25;
    public static int isDown = 0;

    public static void ArmLift(Robot r, Controller ctrl){
        r.addThread(new Thread(() -> {
            Motor leftArm = r.getMotor("LIFTLEFT");
            Motor rightArm = r.getMotor("LIFTRIGHT");
            leftArm.get().setPower(originalLiftPos);
            rightArm.get().setPower(originalLiftPos);
            while(r.op().opModeIsActive()){
                if(ctrl.leftTrigger() >= 1){
                    leftArm.get().setPower(liftUpPow);
                    rightArm.get().setPower(liftUpPow);
                }
                else if (ctrl.rightTrigger() >= 1) {
                    leftArm.get().setPower(-liftDownPow);
                    rightArm.get().setPower(-liftDownPow);
                }
                else{
                    leftArm.get().setPower(originalLiftPos);
                    rightArm.get().setPower(originalLiftPos);
                }
            }
        }), true);
    }

    public static void intakeMotor(Robot r, Controller ctrl){
        r.addThread(new Thread(() ->{
            Motor intake = r.getMotor("INTAKE");
            intake.get().setPower(0);
            while(r.op().opModeIsActive()){
                if(ctrl.buttonDown()) isDown++;
                switch (isDown%2){
                    case 0:
                        intake.get().setPower(0);
                    case 1:
                        intake.get().setPower(-0.8);
                }
            }
        }), true);
    }

    public static void outakeServo(Robot r, Controller ctrl){
        r.addThread(new Thread(() ->{
            Servo outake =  (Servo)r.getServo("OUTAKE");
            outake.get().setPosition(outakeBeg);
            while (r.op().opModeIsActive()){
                if (ctrl.buttonUp()) {
                    outake.get().setPosition(outakeFlip);
                }
                else{
                    outake.get().setPosition(outakeBeg);
                }
            }
        }), true);
    }

    public static void planeLaunch(Robot r, Controller ctrl){
        r.addThread(new Thread(() -> {
            Servo planeMotor = (Servo) r.getServo("PLANE");
            planeMotor.get().setPosition(planeLoad);
            while(r.op().opModeIsActive()){
                if(ctrl.buttonA()){
                    planeMotor.get().setPosition(planeLaunch);
                }
                else planeMotor.get().setPosition(planeLoad);
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
