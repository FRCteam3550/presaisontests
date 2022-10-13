// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoCommands;

import frc.robot.subsystems.SuperSubsystem;
import frc.robot.subsystems.PhotoSwitches;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class ConvoyorUP2IndividualCommand extends CommandBase {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final SuperSubsystem m_Convoyor;
  private final PhotoSwitches m_PhotoSwitches;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ConvoyorUP2IndividualCommand(SuperSubsystem convoyeur, PhotoSwitches photoSwitches) {
    m_Convoyor = convoyeur;
    m_PhotoSwitches = photoSwitches;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_Convoyor, m_PhotoSwitches);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_Convoyor.setConvoyorUP(1.0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_Convoyor.setConvoyorUP(1.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}