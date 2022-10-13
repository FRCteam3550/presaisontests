// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DriveTrain;

import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class BoostDriveCommand extends CommandBase {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final DriveTrain m_DriveTrain;
  private DoubleSupplier m_vitesse;
  private DoubleSupplier m_rotation;

  private DoubleSupplier m_trigger;

  private double currentSpeed = 0.0;
  private double deltaSpeed = 0.01;


  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public BoostDriveCommand(DoubleSupplier trigger, DoubleSupplier vitesse, DoubleSupplier rotation, DriveTrain driveTrain) {
    m_DriveTrain = driveTrain;
    m_vitesse = vitesse;
    m_rotation = rotation;
    m_trigger = trigger;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_DriveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // double joystickSpeed = m_vitesse.getAsDouble();
    // double joystickRotation = m_rotation.getAsDouble();

    // if (joystickSpeed > currentSpeed + deltaSpeed) {
    //   currentSpeed = currentSpeed + deltaSpeed;
    // } else {
    //   currentSpeed = joystickSpeed;
    // }

    // if (joystickRotation > currentRotation + deltaRotation) {
    //   currentRotation = currentRotation + deltaRotation;
    // } else {
    //   currentRotation = joystickRotation;
    // }

    m_DriveTrain.boostDrive(m_trigger.getAsDouble(), m_vitesse.getAsDouble(), m_rotation.getAsDouble());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_DriveTrain.drive2(0, 0); //end value
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}