// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.commands.AutoCommands;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj2.command.CommandBase;
/** An example command that uses an example subsystem. */
public class ShooterKeepOnAutoCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  //private final ExampleSubsystem m_subsystem;
  private final Shooter m_shooter;
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ShooterKeepOnAutoCommand(Shooter shooter) {
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
    m_shooter.setShooterHighAuto();

    // if(m_shooter.returnSpeed() <= -0.6)  {
    //   m_pilot.setRumble(RumbleType.kLeftRumble, 1.0);
    //   m_pilot.setRumble(RumbleType.kRightRumble, 1.0);
    // } else {
    //   m_pilot.setRumble(RumbleType.kLeftRumble, 0.0);
    //   m_pilot.setRumble(RumbleType.kRightRumble, 0.0);
    // }
  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_shooter.setShooterHighAuto();
    // m_pilot.setRumble(RumbleType.kLeftRumble, 0.0);
    // m_pilot.setRumble(RumbleType.kRightRumble, 0.0);
  }
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}