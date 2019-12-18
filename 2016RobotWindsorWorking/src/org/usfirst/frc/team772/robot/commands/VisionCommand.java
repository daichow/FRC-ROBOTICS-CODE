package org.usfirst.frc.team772.robot.commands;

import java.util.Vector;

import org.usfirst.frc.team772.robot.Robot;
import org.usfirst.frc.team772.robot.RobotMap;
import org.usfirst.frc.team772.robot.vision.Particle;
import org.usfirst.frc.team772.robot.vision.ScoreAnalyser;
import org.usfirst.frc.team772.robot.vision.Scores;

import com.ni.vision.NIVision;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class VisionCommand extends Command {
	// declaring variables and setting defaults
	double centerX = 0;
	double centerY = 0;
	double width = 0;
	double height = 0;
	
	// max min values for allowed target finding
	// 179,210
	// 79,248
	// 28,110
	NIVision.Range TOTE_HUE_RANGE = new NIVision.Range(171,188);	//Default hue range for yellow tote 0, 103
	NIVision.Range TOTE_SAT_RANGE = new NIVision.Range(43,133);	//Default saturation range for yellow tote 78, 255
	NIVision.Range TOTE_LUM_RANGE = new NIVision.Range(122,191);	//Default value range for yellow tote 160, 255
	double AREA_MINIMUM = 0.5; //Default Area minimum for particle as a percentage of total image area
	double LONG_RATIO = 20/14; //Tote long side = 26.9 / Tote height = 12.1 = 2.22
	double SHORT_RATIO = 20/14; //Tote short side = 16.9 / Tote height = 12.1 = 1.4
	double SCORE_MIN = 10.0;  //Minimum score to be considered a tote
	double VIEW_ANGLE = 49.4; //View angle fo camera, set to Axis m1011 by default, 64 for m1013, 51.7 for 206, 52 for HD3000 square, 60 for HD3000 640x480
	NIVision.ParticleFilterCriteria2 criteria[] = new NIVision.ParticleFilterCriteria2[1];
	NIVision.ParticleFilterOptions2 filterOptions = new NIVision.ParticleFilterOptions2(0,0,1,1);

    public VisionCommand() {
        criteria[0] = new NIVision.ParticleFilterCriteria2(NIVision.MeasurementType.MT_AREA_BY_IMAGE_AREA, AREA_MINIMUM, 100.0, 0, 0);
    }

    protected void initialize() {
    	Robot.isTracking = true;
    }

    protected void execute() {
    	if (Robot.counter > Robot.camLim) {
			try{
				// thresholds by colour
		   		NIVision.imaqColorThreshold(Robot.binaryFrame, Robot.frame, 255, NIVision.ColorMode.HSV, TOTE_HUE_RANGE, TOTE_SAT_RANGE, TOTE_LUM_RANGE);
		   		
		   		//Send particle count to dashboard
				int numParticles = NIVision.imaqCountParticles(Robot.binaryFrame, 1); // puts particles on the frame
				SmartDashboard.putNumber("Masked particles", numParticles); // puts the number of particles on the dashboard underneath variables
		   		
				CameraServer.getInstance().setImage(Robot.binaryFrame); // gets camera and sets the image to the frame
				
		   		//filter out small particles
				float areaMin = (float)SmartDashboard.getNumber("Area min %", AREA_MINIMUM); // puts minimum area on the dash under variables
				criteria[0].lower = areaMin; // sets area min to the smallest val foumnd in array
				
				//Send particle count after filtering to dashboard
				numParticles = NIVision.imaqCountParticles(Robot.binaryFrame, 1); // sets numParticles to the ones found on binary frame
				SmartDashboard.putNumber("Filtered particles", numParticles); // puts particles on dashboard underneath variables
				
				if(numParticles > 0) // does the rest if finds particles
				{
					//Measure particles and sort by particle size
					Vector<Particle> particles = new Vector<Particle>();
					for(int particleIndex = 0; particleIndex < numParticles; particleIndex++)
					{
						Particle p = new Particle(Robot.binaryFrame, particleIndex); // create particle
						Scores s = new Scores(p, Robot.binaryFrame); // calc score
						if (ScoreAnalyser.isAcceptable(s)) {
							SmartDashboard.putString("DB/String 6", "acceptable");
							particles.add(p);
							RobotMap.isTarget = s.isTarget;
							RobotMap.distance = s.distance;
						} else {
							RobotMap.isTarget = false;
							RobotMap.distance = 0;
							RobotMap.centerX = 0;
						}
					}
				
					particles.sort(null);
					
					RobotMap.centerX = particles.elementAt(0).centerX - 320 + 27;
				} else {
					RobotMap.centerX = 0;
				}
		  	}catch(Exception e){
		   		e.printStackTrace(); // prints the error if it doesnt work, tells you what the error is
		   		SmartDashboard.putString("DB/String 1", "catch");
		   	}
    	}
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.isTracking = false;
    }

    protected void interrupted() {
    	end();
    }
}