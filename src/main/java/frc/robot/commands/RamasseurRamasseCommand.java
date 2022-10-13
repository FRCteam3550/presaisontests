// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.PhotoSwitches;
import frc.robot.subsystems.SuperSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class RamasseurRamasseCommand extends CommandBase {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final SuperSubsystem m_Ramasse;
  private final PhotoSwitches m_photoSwitches;

  private boolean initialPhotoSwitchHigh;
  private boolean initialPhotoSwitchLow;

  private boolean finalPhotoSwitchHigh;
  private boolean finalPhotoSwitchLow;

  private boolean isFinished;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public RamasseurRamasseCommand(SuperSubsystem rammaseur, PhotoSwitches photoSwitches) {
    m_Ramasse = rammaseur;
    m_photoSwitches = photoSwitches;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_Ramasse, m_photoSwitches);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    initialPhotoSwitchHigh = m_photoSwitches.getPhotoSwitchHigh();
    initialPhotoSwitchLow = m_photoSwitches.getPhotoSwitchLow();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      // if (m_Ramasse.detectBall() == true){
      //   m_Ramasse.arreter();
      //   // apeller l'autamisme la commande pickupstoppickupshootallcommand
      // } else if (m_Ramasse.detectBall() == false) {
      //   m_Ramasse.ramasser();
      // }

      finalPhotoSwitchHigh = m_photoSwitches.getPhotoSwitchHigh();
      finalPhotoSwitchLow = m_photoSwitches.getPhotoSwitchLow();

      m_Ramasse.ramasser();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_Ramasse.arreter();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (initialPhotoSwitchLow == false && initialPhotoSwitchHigh == false && finalPhotoSwitchLow == false && finalPhotoSwitchHigh == false){
      isFinished = false;
    } else if (initialPhotoSwitchLow == false && initialPhotoSwitchHigh == false && finalPhotoSwitchLow == true && finalPhotoSwitchHigh == false) {
      isFinished = false;
    } else if (initialPhotoSwitchLow == false && initialPhotoSwitchHigh == false && finalPhotoSwitchLow == false && finalPhotoSwitchHigh == true) {
      isFinished = true;
    } else if (initialPhotoSwitchLow == false && initialPhotoSwitchHigh == false && finalPhotoSwitchLow == true && finalPhotoSwitchHigh == true) {
      isFinished = true;

    } else if (initialPhotoSwitchLow == true && initialPhotoSwitchHigh == false && finalPhotoSwitchLow == false && finalPhotoSwitchHigh == false){
      isFinished = false;
    } else if (initialPhotoSwitchLow == true && initialPhotoSwitchHigh == false && finalPhotoSwitchLow == true && finalPhotoSwitchHigh == false) {
      isFinished = false;
    } else if (initialPhotoSwitchLow == true && initialPhotoSwitchHigh == false && finalPhotoSwitchLow == false && finalPhotoSwitchHigh == true) {
      isFinished = true;
    } else if (initialPhotoSwitchLow == true && initialPhotoSwitchHigh == false && finalPhotoSwitchLow == true && finalPhotoSwitchHigh == true) {
      isFinished = true;
    
    } else if (initialPhotoSwitchLow == false && initialPhotoSwitchHigh == true && finalPhotoSwitchLow == false && finalPhotoSwitchHigh == false){
      isFinished = false;
    } else if (initialPhotoSwitchLow == false && initialPhotoSwitchHigh == true && finalPhotoSwitchLow == true && finalPhotoSwitchHigh == false) {
      isFinished = false;
    } else if (initialPhotoSwitchLow == false && initialPhotoSwitchHigh == true && finalPhotoSwitchLow == false && finalPhotoSwitchHigh == true) {
      isFinished = false;
    } else if (initialPhotoSwitchLow == false && initialPhotoSwitchHigh == true && finalPhotoSwitchLow == true && finalPhotoSwitchHigh == true) {
      isFinished = true;
    
    } else if (initialPhotoSwitchLow == true && initialPhotoSwitchHigh == true && finalPhotoSwitchLow == false && finalPhotoSwitchHigh == false){
      isFinished = true;
    } else if (initialPhotoSwitchLow == true && initialPhotoSwitchHigh == true && finalPhotoSwitchLow == true && finalPhotoSwitchHigh == false) {
      isFinished = false;
    } else if (initialPhotoSwitchLow == true && initialPhotoSwitchHigh == true && finalPhotoSwitchLow == false && finalPhotoSwitchHigh == true) {
      isFinished = false;
    } else if (initialPhotoSwitchLow == true && initialPhotoSwitchHigh == true && finalPhotoSwitchLow == true && finalPhotoSwitchHigh == true) {
      isFinished = false;
    }

    return isFinished;
  }
}
