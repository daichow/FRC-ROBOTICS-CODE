package org.usfirst.frc.team772.robot.commands;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ImageType;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.vision.AxisCamera;


public class VisionTest2 {
	
	AxisCamera camera;
	
	Image frame;
	Image binaryFrame;
	int imaqError;
	
	public VisionTest2(){
		camera = new AxisCamera("10.7.72.11");
        frame = NIVision.imaqCreateImage(ImageType.IMAGE_HSL, 0);
		while(true){
			try{
	    		camera.getImage(frame);
	    		CameraServer.getInstance().setImage(frame);
	    	}catch(Exception e){
	    	}
		}
	}
}
