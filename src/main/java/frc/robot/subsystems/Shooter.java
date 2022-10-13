// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.subsystems;
import java.util.ResourceBundle.Control;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
public class Shooter extends SubsystemBase {
  private final WPI_TalonFX m_kShooterMoteur = new WPI_TalonFX(Constants.Shooter.kShooterMotor);

  public Shooter() {
    m_kShooterMoteur.configFactoryDefault();
    m_kShooterMoteur.setNeutralMode(NeutralMode.Coast);
    m_kShooterMoteur.configOpenloopRamp(0.5);
    m_kShooterMoteur.configVoltageCompSaturation(11);
    m_kShooterMoteur.enableVoltageCompensation(true);
  }

  @Override
  public void periodic() {
  }
  
  public void setShooterON(){
    // Low Goal (0.3)
    // High Goal (1.0)
    m_kShooterMoteur.set(-1.0);
  }

  public void setShooterONPractice() {
    m_kShooterMoteur.set(-0.65);
  }

  public void setShooterAutoONPractice() {
    m_kShooterMoteur.set(-0.70);
  }

  public double returnSpeed() {
    return m_kShooterMoteur.get();
  }

  public void setShooterLow() {
    m_kShooterMoteur.set(-0.3);
  }

  public void setShooterHigh() {
    m_kShooterMoteur.set(-0.6);
  }

  public void setShooterHighAuto() {
    m_kShooterMoteur.set(-0.75);
  }

  public void setShooterSTOP(){
    m_kShooterMoteur.set(0);
  }

  public void setShooterReverse(){
    m_kShooterMoteur.set(0.3);
  }
  public void setAutoShooterOnHigh(double speed){
    m_kShooterMoteur.set(ControlMode.Velocity, speed);
  }

  public void SetButtons() {
  }

  @Override
  public void simulationPeriodic() {
  }
}