package org.usfirst.frc.team772.robot.subsystems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.text.NumberFormat;
import java.text.ParseException;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LidarSubsystem extends Subsystem {

	SerialPort lidar = new SerialPort(115200, SerialPort.Port.kUSB1);
	Spark motor = new Spark(1);
	
	public double lidarDistance(){
//		String asd = lidar.readString();
//		NumberFormat f = NumberFormat.getInstance();
//		String [] array = new String[2];
//		array = lidar.readString().split(" ");
//		double lidarDouble = 0;
//		String asd;
//			
//			try {
//				BufferedReader bf = new BufferedReader(new StringReader(lidar.readString()));
////				asd = 
//				lidarDouble = Double.parseDouble(bf.readLine());
//			} catch (IOException e) {
//				e.printStackTrace();
//				lidarDouble = 0;
//			}//25.4;
//		double lidarDouble = Double.parseDouble(array[0]);
//		String asd = lidar.readString().substring(1, 2);
		
//		return lidar.readString();
//		return lidarDouble;
		return 0;
	}
	
	public double asd(){
		NumberFormat f = NumberFormat.getInstance();
		double lidarDouble = 0;
		try {
			lidarDouble = f.parse(lidar.readString()).doubleValue();
		} catch (ParseException e) {
			lidarDouble =0;
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		lidarDouble = lidarDouble/2.54;
////		lidarDouble = lidarDouble/0.393701;
//		return lidar.readString();
		return lidarDouble;
		
	}
	
	public void testLidar(double speed){
		motor.set(speed);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

