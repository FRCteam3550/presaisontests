// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Grimpeur;

/** An example command that uses an example subsystem. */
public class GrimpeurUPCommand extends CommandBase {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final Grimpeur m_Grimpeur;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public GrimpeurUPCommand(Grimpeur grimpeur) {
    m_Grimpeur = grimpeur;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_Grimpeur);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (m_Grimpeur.getUpperLimitSwitch()) {
       m_Grimpeur.stopClimber();
    } else {
      m_Grimpeur.setClimberUp();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_Grimpeur.stopClimber();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
} 