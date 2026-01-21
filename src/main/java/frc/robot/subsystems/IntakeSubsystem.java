// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class IntakeSubsystem extends SubsystemBase {
  private CANSparkMax m_intake = new CANSparkMax(IntakeConstants.kIntakeMotorCanId1, MotorType.kBrushless);
  private CANSparkMax m_intake2 = new CANSparkMax(IntakeConstants.kIntakeMotorCanId2, MotorType.kBrushless);
  private DigitalInput sensor = new DigitalInput(IntakeConstants.kSensorPort);
  /** Creates a new IntakeSubsystem. */
  public IntakeSubsystem() {}

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("Sensor", sensor.get());
    // This method will be called once per scheduler run

  }
  public boolean get_sensor(){
    return(sensor.get());
  }

  public void setmotor(double speed){
    m_intake.set(-speed);
    m_intake2.set(-speed);
  }
}
