// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.ConvoyorSTOPCommand;
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
public class Auto2BalleEtReculCommandV2 extends SequentialCommandGroup {
  /** Creates a new Auto1BalleEtRecul2Command. */
  public Auto2BalleEtReculCommandV2(DriveTrain drivetrain, Grimpeur grimpeur, SuperSubsystem convoyeur, Shooter shooter,
      SuperSubsystem ramasseur, PhotoSwitches photoSwitches, String[] path_to_run1, String[] path_to_run2) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(

        new ShooterONAutoPracticecommand(shooter).withTimeout(0.5),
        parallel(new ConvoyorUPIndividualCommand(convoyeur, photoSwitches).withTimeout(1.0)),
                new RamasseurExtendCommand(ramasseur).withTimeout(1.0),
                

        parallel( //new ShooterONAutoPracticecommand(shooter).withTimeout(0.5),
                new RamasseurRamasseIndividualAutoCommand(ramasseur).withTimeout(0.5),
                new RunPathCommand(drivetrain, path_to_run1).withTimeout(3.5)),

        // Ce qui etait avant :
        // new AutoPreShootBottom(ramasseur, convoyeur,
        // photoSwitches).withTimeout(1.25),

        // Ce qui est le 4 avril :
        new RamasseurRamasseIndividualAutoCommand(ramasseur).withTimeout(0.25),
        new RamasseurStopAutoCommand(ramasseur).withTimeout(0.2),
        parallel(new RunPathCommand(drivetrain, path_to_run2).withTimeout(3.0),
                 new ConvoyorUPAutoIndividualCommand(convoyeur, photoSwitches)),
        new ConvoyorSTOPCommand(convoyeur).withTimeout(0.25),

       // new RunPathCommand(drivetrain, path_to_run2).withTimeout(3.0),
        new AutoShootBottom(convoyeur, ramasseur, shooter, photoSwitches).withTimeout(1.0),
        new ShootStopper(convoyeur, ramasseur, shooter, photoSwitches));
  }
}
