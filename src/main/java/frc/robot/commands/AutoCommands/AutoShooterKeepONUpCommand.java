// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.commands.AutoCommands;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj2.command.CommandBase;
/** An example command that uses an example subsystem. */
public class AutoShooterKeepONUpCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  //private final ExampleSubsystem m_subsystem;
  private final Shooter m_shooter;

 
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public AutoShooterKeepONUpCommand(Shooter shooter) {
    m_shooter = shooter;
   
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_shooter);
  }
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_shooter.setShooterLow();

  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}