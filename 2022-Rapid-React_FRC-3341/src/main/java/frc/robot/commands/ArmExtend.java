// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class ArmExtend extends CommandBase {
  /** Creates a new ArmExtend. */
  private int lineNum;
  private int currPos;

  public ArmExtend(int lineNum) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.lineNum = lineNum;
    if(lineNum < RobotContainer.getArm().getArmMinPos()) lineNum = RobotContainer.getArm().getArmMinPos();
    if(lineNum > RobotContainer.getArm().getArmMaxPos()) lineNum = RobotContainer.getArm().getArmMaxPos();
    addRequirements(RobotContainer.getArm());
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    currPos = RobotContainer.getArm().getArmExtPos();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    currPos = RobotContainer.getArm().getArmExtPos();
    SmartDashboard.putNumber("lineNum: ", lineNum);
    SmartDashboard.putNumber("currPos: ", currPos);

    if(lineNum > currPos){
      RobotContainer.getArm().extendPow(0.3);
    } else if(lineNum < currPos){
      RobotContainer.getArm().extendPow(-0.3);
    }
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    
    RobotContainer.getArm().extendPow(0); 
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    
    SmartDashboard.putBoolean("isFInished: ", lineNum == RobotContainer.getArm().getArmExtPos());
    return (lineNum == RobotContainer.getArm().getArmExtPos());
  }
}
