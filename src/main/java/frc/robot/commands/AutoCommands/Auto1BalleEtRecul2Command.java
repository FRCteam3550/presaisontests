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
public class Auto1BalleEtRecul2Command extends SequentialCommandGroup {
  /** Creates a new Auto1BalleEtRecul2Command. */
  public Auto1BalleEtRecul2Command(DriveTrain drivetrain, Grimpeur grimpeur, SuperSubsystem convoyeur, Shooter shooter,
      SuperSubsystem ramasseur, PhotoSwitches photoSwitches, String[] path_to_run1, String[] path_to_run2) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(

    //Prepare le shooter au tire, tire avec convoyeurUp et extension du ramasseur
        new ShooterONAutoPracticecommand(shooter).withTimeout(0.5),
        parallel(new ConvoyorUPIndividualCommand(convoyeur, photoSwitches).withTimeout(1.0)),
                new RamasseurExtendCommand(ramasseur).withTimeout(1.0),
                
   //Activation du moteur du ramasseur et parcour de la premiere partie de la trajectoire
        parallel(new RamasseurDebugCommand(ramasseur).withTimeout(0.5),
                new RunPathCommand(drivetrain, path_to_run1).withTimeout(3.5)),


     //Ramassage de la balle, parcour de la deuxieme partie de la trajectoire et positionnement de la balle dans le convoyeur
        parallel(new RunPathCommand(drivetrain, path_to_run2).withTimeout(3.0),
                 new ConvoyorUPAutoIndividualCommand(convoyeur, photoSwitches)),
        new ConvoyorSTOPCommand(convoyeur).withTimeout(0.25),

     //Tire de la deuxieme balle
        new AutoShootBottom(convoyeur, ramasseur, shooter, photoSwitches).withTimeout(1.0),
        new ShootStopper(convoyeur, ramasseur, shooter, photoSwitches),
        new DriveCommand(()->0, ()->0, drivetrain).withTimeout(120)
        );
  }
}
