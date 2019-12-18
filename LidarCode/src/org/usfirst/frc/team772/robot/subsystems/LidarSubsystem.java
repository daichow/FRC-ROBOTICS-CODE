package org.usfirst.frc.team772.robot.subsystems;

import java.util.TimerTask;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class LidarSubsystem extends PIDSubsystem {
	private I2C i2c;
	private byte[] distance;
	private java.util.Timer updater;
	private LIDARUpdater task;
	
	private final char LIDAR_ADDR = 0x62;
	private final char LIDAR_CONFIG_REGISTER = 0x00;
	private final char LIDAR_DISTANCE_REGISTER = 0x8f;
	boolean asd;
	
    public LidarSubsystem(Port port) {
    	super("LidarSubsystem", 0, 0, 0);
    	
    	i2c = new I2C(Port.kMXP, LIDAR_ADDR);
		distance = new byte[2];
		task = new LIDARUpdater();
		updater = new java.util.Timer();
    }
    
    public void initDefaultCommand() {
    }
    
    public boolean asd(){
    	return i2c.addressOnly();
    }
    // Distance in cm
 	public int getDistance() {
 		return (int)Integer.toUnsignedLong(distance[0] << 8) + Byte.toUnsignedInt(distance[1]);
 	}
  
 	// gets double of getDistance value
 	public double pidGet() {
 		return getDistance(); // returns distance
 	}
 	
 	// Start 10Hz polling
 	public void start() {
		updater.scheduleAtFixedRate(task, 0, 20);
 	}
 	
 	// Start polling for period in milliseconds
 	public void start(int period) {
		updater.scheduleAtFixedRate(task, 0, period);
 	}
 	
 	// stops the laser
 	public void stop() {
 		updater.cancel();
 	}
 	
 	// Update distance variable
 	public void update() {
 		i2c.write(LIDAR_CONFIG_REGISTER, 0x04); // Initiate measurement
		Timer.delay(0.04); // Delay for measurement to be taken
		i2c.read(LIDAR_DISTANCE_REGISTER, 2, distance); // Read in measurement
		Timer.delay(0.01); // Delay to prevent over polling
 	}
 	
 	public void write(){
 		i2c.write(LIDAR_CONFIG_REGISTER, 0x04);
 	}
 	
 	// reads distance
 	public void read(){
 		i2c.read(0x8f, 2, distance);
 	}
 	
 	// Timer task to keep distance updated
 	private class LIDARUpdater extends TimerTask {
 		public void run() {
 			while(true) {
 				update();
 				try {
 					Thread.sleep(10);
 				} catch (InterruptedException e) {
 					e.printStackTrace();
 				}
 			}
 		}
 	}
 	
    protected double returnPIDInput() {
    	return 0.0;
    }
    
    protected void usePIDOutput(double output) {
    }
}