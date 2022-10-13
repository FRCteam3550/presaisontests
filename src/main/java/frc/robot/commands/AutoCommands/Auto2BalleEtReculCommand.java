// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoCommands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.ConvoyorSTOPCommand;
import frc.robot.commands.ConvoyorUPCommand;
import frc.robot.commands.RamasseurRamasseCommand;
import frc.robot.commands.RamasseurStopCommand;
import frc.robot.commands.RunPathCommand;
import frc.robot.commands.ShooterONcommand;
import frc.robot.commands.ShooterSTOPCommand;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Grimpeur;
import frc.robot.subsystems.PhotoSwitches;
import frc.robot.subsystems.SuperSubsystem;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Auto2BalleEtReculCommand extends SequentialCommandGroup {
  /** Creates a new Auto1BalleEtReculCommand. */
  public Auto2BalleEtReculCommand(DriveTrain drivetrain, Grimpeur grimpeur, SuperSubsystem convoyeur, Shooter shooter,
      SuperSubsystem ramasseur, PhotoSwitches photoSwitches, String[] path_to_run) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
        new ConvoyorUPCommand(convoyeur, photoSwitches),
        parallel(new ConvoyorSTOPCommand(convoyeur), new ShooterONcommand(shooter)),
        parallel(new ShooterSTOPCommand(shooter), new RunPathCommand(drivetrain, path_to_run)), // RunPathCommand:
                                                                                                // reculer
        new RamasseurRamasseCommand(ramasseur, photoSwitches),
        new RamasseurStopCommand(ramasseur),
        parallel(new RunPathCommand(drivetrain, path_to_run), new ShooterONcommand(shooter)), // RunPathCommand: avancer
        parallel(new ConvoyorSTOPCommand(convoyeur), new ShooterONcommand(shooter)),
        new ShooterSTOPCommand(shooter));
  }
}
