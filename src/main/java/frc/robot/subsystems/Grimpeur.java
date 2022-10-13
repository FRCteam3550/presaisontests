// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.subsystems;
//import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
public class Grimpeur extends SubsystemBase {
  private final WPI_TalonFX m_motor1 = new WPI_TalonFX(Constants.Grimpeur.kGrimpeurMoteur);

  private DigitalInput m_limitSwitchHaut = new DigitalInput(Constants.Grimpeur.kLimitSwitchHaut);
  private DigitalInput m_limitSwitchBas = new DigitalInput(Constants.Grimpeur.kLimitSwitchBas);

  /** Creates a new ExampleSubsystem. */
  public Grimpeur() {
    m_motor1.configFactoryDefault();

    m_motor1.setNeutralMode(NeutralMode.Brake);

    m_motor1.configOpenloopRamp(0.5);

    m_motor1.configVoltageCompSaturation(11);

    m_motor1.enableVoltageCompensation(true);
  }

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("Limit Switch Haut State", getUpperLimitSwitch());
    SmartDashboard.putBoolean("Limit Switch Bas State", getLowerLimitSwitch());
  }

  public boolean getUpperLimitSwitch() {
    return !m_limitSwitchHaut.get();
  }
  
  public boolean getLowerLimitSwitch() {
    return !m_limitSwitchBas.get();
  }

  public void setClimberUp(){
    m_motor1.set(TalonFXControlMode.PercentOutput, 1.0);
  }

  public void setClimberDown(){
    m_motor1.set(TalonFXControlMode.PercentOutput, -1.0);
  }

  public void stopClimber(){
    m_motor1.stopMotor();
  }

  @Override
  public void simulationPeriodic() {}
}