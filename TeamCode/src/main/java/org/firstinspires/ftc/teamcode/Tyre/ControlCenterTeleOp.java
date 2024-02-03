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
    public static double planeLoad = 0.0, planeLaunch = 0.75;
    public static double originalLiftPos = 0.0, liftDownPow = 1, liftUpPow = 0.8;

    public static void ArmRotate(Robot r, Controller ctrl){
        r.addThread(new Thread(() -> {
            Motor armMotor = r.getMotor("ARMMOTOR");
            armMotor.get().setPower(originalLiftPos);
            while(r.op().opModeIsActive()){
                if(ctrl.leftTrigger() > 0){
                    armMotor.get().setPower(liftUpPow * ctrl.leftTrigger());
                }
                else if(ctrl.rightTrigger() > 0){
                    armMotor.get().setPower(liftDownPow * -ctrl.rightTrigger());
                }
                else{
                    armMotor.get().setPower(originalLiftPos);
                }
            }
        }), true);
    }

    public static void ArmLift(Robot r, Controller ctrl){
        r.addThread(new Thread(() -> {
            Motor leftArm = r.getMotor("LIFTLEFT");
            Motor rightArm = r.getMotor("LIFTRIGHT");
            leftArm.get().setPower(originalLiftPos);
            rightArm.get().setPower(originalLiftPos);
            while(r.op().opModeIsActive()){
                if(ctrl.leftBumper()){
                    leftArm.get().setPower(liftUpPow);
                    rightArm.get().setPower(liftUpPow);
                }
                else if (ctrl.rightBumper()) {
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
