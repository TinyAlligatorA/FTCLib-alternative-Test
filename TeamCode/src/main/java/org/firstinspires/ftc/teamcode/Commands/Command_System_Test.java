package org.firstinspires.ftc.teamcode.Commands;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.seattlesolvers.solverslib.command.CommandBase;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.Mecanum_WheelBase;

@TeleOp(name="Command System Test")
public class Command_System_Test extends CommandBase {

    private final Mecanum_WheelBase movement;

    // Locates Subsystem then assign motors
    public Command_System_Test(Mecanum_WheelBase movement) {
        this.movement = movement;
        addRequirements(movement); // Claim exclusive control of Wheel Motors
    }

    @Override
    public void execute() {
        // Read joystick values here
        double drive = -gamepad1.left_stick_y;
        double strafe = gamepad1.left_stick_x;
        double turn = gamepad1.right_stick_x;
        double max = Math.max(Math.abs(strafe) + Math.abs(drive) + Math.abs(turn), 1);

        double powerFR = (drive - strafe - turn) / max;
        double powerFL = (drive - strafe + turn) / max;
        double powerBR = (drive + strafe - turn) / max;
        double powerBL = (drive + strafe + turn) / max;

        // Call your subsystem's method to set motor powers
        movement.drive(powerFR, powerFL, powerBR, powerBL);
    }

    @Override
    public void end(boolean interrupted) {
        movement.stop();
    }

    @Override
    public boolean isFinished() {
        return false; // Runs until interrupted
    }
}
