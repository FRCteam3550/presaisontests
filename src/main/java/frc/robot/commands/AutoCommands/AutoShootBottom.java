// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoCommands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.ConvoyorSTOPCommand;
import frc.robot.commands.ConvoyorShootUpCommand;
import frc.robot.commands.RamasseurRamasseIndividualCommand;
import frc.robot.commands.ShooterSTOPCommand;
import frc.robot.subsystems.PhotoSwitches;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.SuperSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoShootBottom extends SequentialCommandGroup {
  /** Creates a new ShootBottom. */
  public AutoShootBottom(SuperSubsystem convoyeur, SuperSubsystem ramasseur, Shooter shooter, PhotoSwitches photoSwitches) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new ConvoyorShootUpCommand(convoyeur).withTimeout(0.50),
      //new RamasseurRamasseIndividualCommand(ramasseur).withTimeout(0.25),
      new ConvoyorSTOPCommand(convoyeur).withTimeout(0.25),
      new ShooterSTOPCommand(shooter).withTimeout(0.25)
    );
  }
}
