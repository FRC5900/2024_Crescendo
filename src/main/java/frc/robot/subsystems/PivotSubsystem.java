// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

/* 
Notes
One motor has to be inverted for pivot
*/
package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PivotConstants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PivotSubsystem extends SubsystemBase {
  CANSparkMax m_pivot1 = new CANSparkMax(PivotConstants.kPivotMotorCanId1, MotorType.kBrushless);
  CANSparkMax m_pivot2 = new CANSparkMax(PivotConstants.kPivotMotorCanId2, MotorType.kBrushless);
  DutyCycleEncoder m_encoder = new DutyCycleEncoder(PivotConstants.kEncoderPort);

  /** Creates a new PivotSubsystem. */
  public PivotSubsystem() {
    //set to 0.5 units per rotation
  m_encoder.setDistancePerRotation(1);
  m_encoder.reset();
  //m_encoder.setPositionOffset(0.47);
  }

  @Override
  public void periodic() {
    double adjustedangle;
    final double ZERO_OFFSET = 0.0;
    adjustedangle = m_encoder.getAbsolutePosition() - ZERO_OFFSET;
    if(adjustedangle<0){
      adjustedangle= 1 + adjustedangle;
    }
    adjustedangle = adjustedangle * 360;
    SmartDashboard.putNumber("Encoder", m_encoder.getDistance());
    SmartDashboard.putNumber("absolute pos", m_encoder.getAbsolutePosition());
    SmartDashboard.putNumber("encoder pos", adjustedangle);

   
    // This method will be called once per scheduler run
  }

  public void setmotor(double speed){
    if(speed< 0){
      if(pivotencoder() < 172){
        speed = 0;
      }
    }

    if(speed> 0){
      if(pivotencoder() > 294){
        speed = -0.1;
      }
    }
    m_pivot1.set(speed);
    m_pivot2.set(-speed);
    SmartDashboard.putNumber("pivspeed", speed);
  }
  

  public void resetpivotencoder(){
    m_encoder.reset();
  }

  public double pivotencoder(){
    double adjustedangle;
    final double ZERO_OFFSET = 0.0;
    adjustedangle = m_encoder.getAbsolutePosition() - ZERO_OFFSET;
    if(adjustedangle<0){
      adjustedangle= 1 + adjustedangle;
    }
    adjustedangle = adjustedangle * 360;
    return adjustedangle;
  }

  public void ToGround(){
    if (pivotencoder() > PivotConstants.kGroundPoint + 5){
      setmotor(-.4);
    }
    else{
      if (pivotencoder() < PivotConstants.kGroundPoint - 0.5){
        setmotor(0.4);
      }
    }
  }

  public void ToIdleState(){
    if (pivotencoder() < PivotConstants.kStartPoint - 5){
      setmotor(0.3);
    }
    else{
      if (pivotencoder() > PivotConstants.kStartPoint + 5){
        setmotor(-0.3);
      }
    }
  }

  public void ToScore(){
    if (pivotencoder() > PivotConstants.kShootingPoint + 4){
      setmotor(-0.4);
    }
    else{
      if (pivotencoder() < PivotConstants.kShootingPoint - 4){
        setmotor(0.4);
      }
    }
  }

  public void ToAmp(){
    if (pivotencoder() > PivotConstants.kAmpPoint + 5){
      setmotor(-0.5);
    }
    else{
      if (pivotencoder() < PivotConstants.kAmpPoint - 5){
        setmotor(0.2);
      }
    }
  }
}
