// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.commands;
import frc.robot.subsystems.DriveTrain;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
//import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.math.trajectory.TrajectoryUtil;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.CommandBase;
/** An example command that uses an example subsystem. */
public class CuriousCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveTrain m_drivetrain;
  private final Timer m_timer = new Timer();
  private Trajectory m_trajectory;
  private Field2d  m_field;
  private final RamseteController m_ramsete = new RamseteController();
  String trajectoryJSON ="paths/Curious.wpilib.json";
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public CuriousCommand(DriveTrain drivetrain) {
    m_drivetrain = drivetrain;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_drivetrain);
  }
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_timer.reset();
    m_timer.start();
     m_trajectory=defineTrajectoryfromFile(trajectoryJSON);
     //generateTrajectory();
    //  m_field = new Field2d();
    //  SmartDashboard.putData(m_field);
    //  m_field.getObject("traj").setTrajectory(m_trajectory);
    m_drivetrain.resetOdometry(m_trajectory.getInitialPose());
    System.out.println("in intialize");
  }
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    callperiodicAuto();
  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drivetrain.resetOdometry(m_trajectory.getInitialPose());
  }
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
  public Trajectory generateTrajectory() {
    // Flush NetworkTables every loop. This ensures that robot pose and other values
    // are sent during every iteration.
    //setNetworkTablesFlushEnabled(true);
    // 2018 cross scale auto waypoints.
    var sideStart = new Pose2d(0.47, 7.08, Rotation2d.fromDegrees(1.31));
    var crossScale = new Pose2d(7.22, 2.07, Rotation2d.fromDegrees(-10));
    var interiorWaypoints = new ArrayList<Translation2d>();
    interiorWaypoints.add(new Translation2d(4.43, 7.08));
    interiorWaypoints.add(new Translation2d(5.94, 5.56));
    //interiorWaypoints.add(new Translation2d(6.100968, 3.919634));
    TrajectoryConfig config = new TrajectoryConfig(Units.feetToMeters(12), Units.feetToMeters(12));
    config.setReversed(false);
    return m_trajectory = TrajectoryGenerator.generateTrajectory(sideStart, interiorWaypoints, crossScale, config);
    // m_trajectory =
    // TrajectoryGenerator.generateTrajectory(
    // new Pose2d(2, 2, new Rotation2d()),
    // List.of(),
    // new Pose2d(6, 4, new Rotation2d()),
    // new TrajectoryConfig(2, 2));
  }
  public void initTrajectory(){
    m_timer.reset();
    m_timer.start();
    generateTrajectory();
    m_drivetrain.resetOdometry(m_trajectory.getInitialPose());
  }
  public void callperiodicAuto(){
    double elapsed = m_timer.get();
    Trajectory.State reference = m_trajectory.sample(elapsed);
    ChassisSpeeds speeds = m_ramsete.calculate(m_drivetrain.getPose(), reference);
    m_drivetrain.drive(speeds.vxMetersPerSecond, speeds.omegaRadiansPerSecond);
  }
  public Trajectory defineTrajectoryfromFile(String trajectoryJSON){
    Trajectory localTrajectory = new Trajectory();
   // Pose2d xorigine = new Pose2d();
   try {
    Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON);
    localTrajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
  } catch (IOException ex) {
    DriverStation.reportError("Unable to open trajectory: " + trajectoryJSON, ex.getStackTrace());
  }
    // xorigine = new Pose2d(0,0, Rotation2d.fromDegrees(0));
    //return localTrajectory.transformBy(transform);
    return localTrajectory;
  }
}