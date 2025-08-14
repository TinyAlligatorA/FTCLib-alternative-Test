package org.firstinspires.ftc.teamcode.Subsystems;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.seattlesolvers.solverslib.command.CommandScheduler;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.hardware.motors.Motor;

import java.sql.Driver;

public class Mecanum_WheelBase extends SubsystemBase {
    private final DcMotor MotorFR;
    private final DcMotor MotorFL;
    private final DcMotor MotorBR;
    private final DcMotor MotorBL;

    public Mecanum_WheelBase(HardwareMap hardwareMap) {
        MotorFR = hardwareMap.get(DcMotor.class, "MotorFR");
        MotorFL = hardwareMap.get(DcMotor.class, "MotorFL");
        MotorBR = hardwareMap.get(DcMotor.class, "MotorBR");
        MotorBL = hardwareMap.get(DcMotor.class, "MotorBL");
    }

    public void drive(double powerFR, double powerFL, double powerBR, double powerBL) {
        MotorFR.setPower(powerFR);
        MotorFL.setPower(powerFL);
        MotorBR.setPower(powerBR);
        MotorBL.setPower(powerBL);
    }

    public void movement(){
        double drive = -gamepad1.left_stick_y;
        double strafe = gamepad1.left_stick_x;
        double turn = gamepad1.right_stick_x;
        double max = Math.max(Math.abs(strafe) + Math.abs(drive) + Math.abs(turn), 1);

        double PowerFR = ((drive - strafe - turn) / max);
        double PowerFL = ((drive - strafe + turn) / max);
        double PowerBR = ((drive + strafe - turn) / max);
        double PowerBL = ((drive + strafe + turn) / max);

        drive(PowerFR,PowerFL,PowerBR,PowerBL);
    }
    public void stop() {
        drive(0, 0, 0, 0);
    }
}
