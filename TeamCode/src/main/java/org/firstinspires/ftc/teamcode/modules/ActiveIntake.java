package org.firstinspires.ftc.teamcode.modules;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.hardware.ConditionalHardwareDevice;
import org.firstinspires.ftc.teamcode.modules.core.Module;

public class ActiveIntake extends Module {
    /**
     * The power the motor spins at when active
     */
    public static final double INTAKE_POWER = 0.8;

    /**
     * The name of the intake motor
     */
    public static final String INTAKE_MOTOR_NAME = "Intake Motor";

    /**
     * The intake motor
     */
    private final ConditionalHardwareDevice<DcMotor> intakeMotor;

    private boolean isRunning;

    /**
     * Initializes the module and registers it with the specified OpMode.  This is where references to any hardware
     * devices used by the module are loaded.
     *
     * @param registrar The OpMode initializing the module
     */
    public ActiveIntake(OpMode registrar) {
        super(registrar);

        intakeMotor = ConditionalHardwareDevice.tryGetHardwareDevice(registrar.hardwareMap, DcMotor.class, INTAKE_MOTOR_NAME);
        intakeMotor.runIfAvailable(motor -> {
            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            getTelemetry().addLine("[Active Intake] found motor");
        });

        isRunning = false;
    }

    /**
     * Starts the active intake motor
     */
    public void activate() {
        intakeMotor.runIfAvailable(motor -> motor.setPower(INTAKE_POWER));
        isRunning = true;
    }

    /**
     * Stops the active intake motor
     */
    public void deactivate() {
        intakeMotor.runIfAvailable(motor -> motor.setPower(0.0));
        isRunning = false;
    }

    /**
     * Activates the intake if it is inactive, and deactivates it if it is active
     */
    public void toggleActivity() {
        if (isRunning) {
            deactivate();
        }
        else {
            activate();
        }
    }

    /**
     * Logs data about the module to telemetry
     */
    @Override
    public void log() {
        getTelemetry().addData("[Active Intake] is running", isRunning);
    }
}
