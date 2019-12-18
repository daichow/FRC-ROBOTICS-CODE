package org.echoServer;
/*
 * Copyright (c) 2013, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
 
import java.net.*;

import org.opencv.core.Scalar;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.echoClasses.*;
//import org.echoServer.GripPipeline.BlurType;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.HashMap;

//import edu.wpi.first.wpilibj.vision.VisionPipeline;












import org.opencv.core.*;
import org.opencv.core.Core.*;
import org.opencv.features2d.FeatureDetector;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.*;
import org.opencv.objdetect.*;

 
public class EchoServer  {
 

private static final int CV_CAP_PROP_FRAME_COUNT = 7;
static Semaphore semaphore = new Semaphore(1);
static BufferedImage latestImage;
static Mat latestMat,latestMatProcessed, camFrameMat, blurOutputMat, hsvThresholdOutputMat;
static long  pipelineDelta;

static myAllTargets matt = new myAllTargets();




public static void main(String[] args) throws IOException {
	 
	System.out.println("Starting up DS Stat Listener Server");
	 	 System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	 
 //   (new Thread(new EchoServer())).start();
	
	// max 1 access to image-target info at a time.
		
        if (args.length != 1) {
            System.err.println("Usage: java DSechoStatServre <port number>");
            System.exit(1);
        }
        
         
        int portNumber = Integer.parseInt(args[0]);
         
        
        while (true) {
       
        		ServerSocket serverSocket =
                new ServerSocket(Integer.parseInt(args[0]));
            
        		Socket clientSocket = serverSocket.accept();
         		ObjectOutputStream outt = 
   	        	    new ObjectOutputStream(clientSocket.getOutputStream());
        		
        		ObjectInputStream inn = 
           	       	    new ObjectInputStream(clientSocket.getInputStream());


        		
       
        	System.out.println("server Waiting on input");

         //   	clientSocket.setKeepAlive(true);
       	
        	System.out.println("Client IP address is:" + clientSocket.getInetAddress().toString());
        	SnapshotStats sss = new SnapshotStats();
            
            try {
				sss = (SnapshotStats) inn.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            	System.out.println("Got some input:");
            	
            	System.out.println("Pipeline took: " + sss.pipeLineDelta + " ms");
            	
            	for (int x = 0; x < sss.targets.size(); x++) {
            		
            		System.out.println("Target #:" + x);
            		org.echoClasses.myAllTargets.printTargetInfo(sss.targets, x);
            		System.out.println("---------------------------");
            	
            	
            	inn.close();
          		outt.close();
          		serverSocket.close();
          		System.out.println("Starting img section of code");
     
          		ServerSocket serverImgSocket = new ServerSocket(5802);

          		Socket clientImgSocket = serverImgSocket.accept();
          		
        		ObjectInputStream imageinstream = 
         	       	    new ObjectInputStream(clientImgSocket.getInputStream()); 
        		
        		MatWrapper mw = new MatWrapper();
        		try {
					mw = (MatWrapper) imageinstream.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    
        		Mat mattt = new Mat();
				mattt.put(0, 0, mw.buff);
						
						
						System.out.println("file should have been created");
						Imgcodecs.imwrite("c:\\Users\\2015p\\Downloads\\camimage.jpg", mattt);
					 
					
					imageinstream.close();
					
	          		serverImgSocket.close();
				
            		
           		System.out.println("Continue as you were...");
             
            	
          
 
}


}
        
}




private static byte[] readStream(InputStream stream ) throws IOException {
	// copy content of the image to byte-array
	ByteArrayOutputStream buffer = new ByteArrayOutputStream();
	int nRead;
	
	byte[] data = new byte[16384];
	
	while ((nRead = stream.read(data,0,data.length)) != -1) {
		
		buffer.write(data, 0, nRead);
		
	}
	buffer.flush();
	byte[] temporaryImageInMemory = buffer.toByteArray();
	buffer.close();
	stream.close();
	
	return temporaryImageInMemory;
	
}

}


//byte[] temporaryImageInMemory = readStream(imageinstream);

//	mattt = Imgcodecs.imdecode(mw.buff, 0);


//	try {
//		JAXBContext jc = JAXBContext.newInstance(Mat.class);
//		Unmarshaller unmarshaller = jc.createUnmarshaller();
//		mattt = (Mat) unmarshaller.unmarshal(imageinstream);
//		
//	} catch (Exception e) {
//		System.out.println("Marshalling input failed");
//	}
//	


//ObjectOutputStream imageoutstream = 
//new ObjectOutputStream(clientImgSocket.getOutputStream());
//


//BufferedReader imageinstream = 
//new BufferedReader(new InputStreamReader(clientImgSocket.getInputStream()));

//InputStream imageinstream = new InputStream(clientImgSocket.getInputStream();
	
//	Mat mattt = new Mat();
//	Mat buff = null;
//	MatWrapper mw = new MatWrapper();
//	
//	try {
//		mw = (MatWrapper) imageinstream.readObject();
//	} catch (ClassNotFoundException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
	
