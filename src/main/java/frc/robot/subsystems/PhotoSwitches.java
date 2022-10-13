// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class PhotoSwitches extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */

  private DigitalInput m_photoSwitch2 = new DigitalInput(Constants.Ramasseur.kPhotoSwitch);
  private DigitalInput m_photoSwitch = new DigitalInput(Constants.Convoyeur.kPhotoSwitch2);

  private boolean isHereR;
  private boolean isHereC;

  public PhotoSwitches() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putBoolean("Photo Switch Ramasseur", getPhotoSwitchLow());
    SmartDashboard.putBoolean("Photo Switch Convoyor", getPhotoSwitchHigh());
  }

  public boolean getPhotoSwitchLow() {
    return !m_photoSwitch2.get();
  }

  public boolean getPhotoSwitchHigh() {
    return !m_photoSwitch.get();
  }

  public boolean detectBallR() {
    if (getPhotoSwitchLow() == false) {
      isHereR = false;
    } else if (getPhotoSwitchLow() == true) {
      isHereR = true;
    }

    return isHereR;
  }

  public boolean detectBallC() {
    if (getPhotoSwitchHigh() == false) {
      isHereC = false;
    } else if (getPhotoSwitchHigh() == true) {
      isHereC = true;
    }

    return isHereC;
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}