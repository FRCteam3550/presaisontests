// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoCommands;
import frc.robot.subsystems.DriveTrain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class AutoDriveCommand extends CommandBase {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final DriveTrain m_drive;

  private File speedFile;
  private File rotateFile;

  private Scanner inputSpeed;
  private Scanner inputRotate;


  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public AutoDriveCommand(DriveTrain drive) {
    m_drive = drive;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    File deployDir = Filesystem.getDeployDirectory();
    speedFile = new File(deployDir, "speed.txt");
    rotateFile = new File(deployDir, "rotate.txt");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    while (inputSpeed.hasNextDouble()){
      double lSpeed = inputSpeed.nextDouble();
      double lRotate = inputRotate.nextDouble();
      m_drive.drive2(lSpeed, lRotate);
      System.out.println(lSpeed);
      System.out.println(lRotate);

      /*
      if first option dosen't work (check first why it dosen't work) then use the : 
         readSpeedFile();
      */
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    inputSpeed.close();
    inputRotate.close();
    m_drive.drive2(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  public void readSpeedFile(){
    BufferedReader reader;
    try {
      reader = new BufferedReader(new FileReader(speedFile));
      String line = reader.readLine();
      while (line != null) {
        line = reader.readLine();
        if (line == "null" || line == null) {
          //is null then skip part
        } else if(line != "null" || line != null) {
          //isn't null then read
          System.out.println(line);
        }
      }
      reader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
