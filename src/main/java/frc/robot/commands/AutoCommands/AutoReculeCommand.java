// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.ConvoyorSTOPCommand;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.RamasseurDebugCommand;
import frc.robot.commands.RamasseurExtendCommand;
import frc.robot.commands.RamasseurRamasseIndividualCommand;
import frc.robot.commands.RunPathCommand;
import frc.robot.commands.ShootStopper;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Grimpeur;
import frc.robot.subsystems.PhotoSwitches;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.SuperSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoReculeCommand extends SequentialCommandGroup {
  /** Creates a new Auto1BalleEtRecul2Command. */
  public AutoReculeCommand(DriveTrain drivetrain, String[] path_to_run1) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
    new RunPathCommand(drivetrain, path_to_run1).withTimeout(3),
    new DriveCommand(()->0, ()->0, drivetrain).withTimeout(120)
    );
  }
}
