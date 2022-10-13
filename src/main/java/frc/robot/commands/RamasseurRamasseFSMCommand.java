// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.commands;
import frc.robot.subsystems.PhotoSwitches;
import frc.robot.subsystems.SuperSubsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.CommandBase;
/** An example command that uses an example subsystem. */
public class RamasseurRamasseFSMCommand extends CommandBase {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private static SuperSubsystem m_Ramasse;
  private final PhotoSwitches m_photoSwitches;
  private boolean initialPhotoSwitchHigh;
  private boolean initialPhotoSwitchLow;
  private boolean finalPhotoSwitchHigh;
  private boolean finalPhotoSwitchLow;
  private boolean isFinished;
  private State currentState;
  private State nextState;
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public RamasseurRamasseFSMCommand(SuperSubsystem rammaseur, PhotoSwitches photoSwitches) {
    m_Ramasse = rammaseur;
    m_photoSwitches = photoSwitches;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_Ramasse, m_photoSwitches);
  }
  public enum State{
    ZEROBALLIN{
      @Override
      public void actionsPerformed(){
        m_Ramasse.ramasser();
        m_Ramasse.setConvoyorUP(0.3);
        //return "actionPerformed1";
      }
      @Override
      public State setNextState(){
        return ONEBALLIN;
        //return "actionPerformed1";
      }
    },
    ONEBALLIN{
    @Override
    public void actionsPerformed(){
      m_Ramasse.setConvoyorSTOP();
      m_Ramasse.ramasser();
      // m_Ramasse.setConvoyorUP(1.0);
      //return "actionPerformed2";
    }
    @Override
      public State setNextState(){
        return TWOBALLIN;
        //return "actionPerformed1";
      }
  },
    TWOBALLIN{
    @Override
    public void actionsPerformed(){
      m_Ramasse.arreter();
      m_Ramasse.setConvoyorSTOP();
    }
    @Override
      public State setNextState(){
        return ZEROBALLIN;
        //return "actionPerformed1";
      }
  };
    public abstract void actionsPerformed();
    public abstract State setNextState();
  }
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    initialPhotoSwitchHigh = m_photoSwitches.getPhotoSwitchHigh();
    initialPhotoSwitchLow = m_photoSwitches.getPhotoSwitchLow();
    isFinished = false;
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
        if((m_photoSwitches.getPhotoSwitchLow() == false) && (m_photoSwitches.getPhotoSwitchHigh() == false) ){
          currentState = State.ZEROBALLIN;
          currentState.actionsPerformed();
          isFinished = false;
        }
        if((m_photoSwitches.getPhotoSwitchLow() == false) && (m_photoSwitches.getPhotoSwitchHigh() == true) ){
          currentState = State.ONEBALLIN;
          currentState.actionsPerformed();
          isFinished = false;
        }
        if((m_photoSwitches.getPhotoSwitchLow() == true) && (m_photoSwitches.getPhotoSwitchHigh() == true) ){
          currentState = State.TWOBALLIN;
          currentState.actionsPerformed();
          isFinished = true;
        }
/*
      finalPhotoSwitchHigh = m_photoSwitches.getPhotoSwitchHigh();
      finalPhotoSwitchLow = m_photoSwitches.getPhotoSwitchLow(); */
     // m_Ramasse.ramasser();
  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_Ramasse.arreter();
    m_Ramasse.setConvoyorSTOP();
  }
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    /* if (initialPhotoSwitchLow == false && initialPhotoSwitchHigh == false && finalPhotoSwitchLow == false && finalPhotoSwitchHigh == false){
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
 */
    return isFinished;
  }
}