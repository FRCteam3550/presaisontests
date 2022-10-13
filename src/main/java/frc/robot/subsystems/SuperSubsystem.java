// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;


import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class SuperSubsystem extends SubsystemBase {

  private WPI_TalonFX m_ramasseur = new WPI_TalonFX(Constants.Ramasseur.kRamasseurMoteur);
  private WPI_TalonFX m_convoyeurGauche = new WPI_TalonFX(Constants.Convoyeur.kConvoyeurMoteur);

  private PWMSparkMax m_led = new PWMSparkMax(Constants.Led.kLedSide1Port);
  private PWMSparkMax m_led2 = new PWMSparkMax(Constants.Led.kLedSide2Port);

  private final DoubleSolenoid m_doubleSolenoid = new DoubleSolenoid(
      PneumaticsModuleType.CTREPCM, 
      Constants.Ramasseur.kDoubleSolenoidForward, 
      Constants.Ramasseur.kDoubleSolenoidReverse
  );

  // + Double Solenoide a ajouter plus tard


  /** Creates a new DriveTrain. */
  public SuperSubsystem() { // constructeur
    m_ramasseur.configFactoryDefault(); // configure factory to default
    m_convoyeurGauche.configFactoryDefault(); // configure factory to default
  }

  @Override
  public void periodic() {}
  
  public void extendArm(){
    m_doubleSolenoid.set(Value.kReverse);
  }

  // Ramasseur Part

  public void retractArm(){
    m_doubleSolenoid.set(Value.kForward);
  }

  public void solenoidOff(){
    m_doubleSolenoid.set(Value.kOff);
  }
  
  public void ramasser() {
    m_ramasseur.set(0.6);
  }

  public void ramasserManual(double speed){
    m_ramasseur.set(speed);
  }

  public void jetter() {
    m_ramasseur.set(-0.6);
  }

  public void arreter() {
    m_ramasseur.set(0);
  }

  // Convoyor Part

  //led ramasseur
  public void drawLedPatterns(double patterns) {
    m_led.set(patterns);
  }
//pour led convoyor
  public void drawLedPatterns2(double patterns) {
    m_led2.set(patterns);
  }

  public void setConvoyorUP(double speed) {
    m_convoyeurGauche.set(speed);
  }

  public void setConvoyorDOWN() {
    m_convoyeurGauche.set(-0.5);
  }

  public void setConvoyorSTOP() {
    m_convoyeurGauche.set(0);
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
