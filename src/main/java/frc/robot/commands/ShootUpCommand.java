// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.PhotoSwitches;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.SuperSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ShootUpCommand extends SequentialCommandGroup {
  /** Creates a new ShootBottom. */
  public ShootUpCommand(SuperSubsystem convoyeur, SuperSubsystem ramasseur, Shooter shooter, PhotoSwitches photoSwitches, double speed) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      // TimeOut was 1 second before, and 4 april it's 0.5
      new ConvoyorShootUpCommand(convoyeur).withTimeout(0.75),
      // TimeOut was 1 second before and 4 april it's 0.5
      new RamasseurRamasseIndividualCommand(ramasseur, speed).withTimeout(0.5),
      // parallel(new ShooterONcommand(shooter), new ConvoyorShootUpCommand(convoyeur).withTimeout(1)),
      // new RamasseurRetractCommand(ramasseur).withTimeout(1),
      // new RamasseurStopCommand(ramasseur),
      // new ShooterSTOPCommand(shooter),
      new ConvoyorSTOPCommand(convoyeur).withTimeout(0.5),
      new ShooterSTOPCommand(shooter).withTimeout(0.5)
    );
  }
}
