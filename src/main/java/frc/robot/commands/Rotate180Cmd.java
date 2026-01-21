// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;

public class Rotate180Cmd extends Command {
  /** Creates a new Rotate180Cmd. */
  DriveSubsystem s_drive;
  double d_gyro;
  double rotategyro;
  public Rotate180Cmd(DriveSubsystem drive, double gyro) {
    s_drive = drive;
    d_gyro = gyro;
    addRequirements(drive);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    rotategyro = d_gyro + 178;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    if(rotategyro > 180){
      rotategyro = -360 + rotategyro;
    }
    else if (rotategyro < -180){
      rotategyro = 360 - rotategyro;
    }

    if (d_gyro < rotategyro && rotategyro >= 0 | d_gyro > rotategyro && rotategyro < 0){
      s_drive.drive(0, 0, 0.5, true, true);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    s_drive.drive(0, 0, 0, true, true);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
