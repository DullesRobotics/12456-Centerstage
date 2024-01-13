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
//
//    public static void clawRelease(Robot r, Controller ctrl){
//        r.addThread(new Thread(() -> {
//            Servo outtakeServo = r.getServo("CLAW");
//            outtakeServo.get().setPosition(clawOpenPos);
//            while(r.op().opModeIsActive()){
//                if(ctrl.rightBumper()){
//                    outtakeServo.get().setPosition(clawOpenPos);
//                }
//                else if(ctrl.leftBumper()){
//                    outtakeServo.get().setPosition(clawGripPos);
//                }
//                else{
//                    outtakeServo.get().setPosition(clawClosedPos);
//                }
//            }
//        }), true);
//    }
//
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
