package org.usfirst.frc.team772.robot;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.ColorMode;
import com.ni.vision.NIVision.CoordinateSystem;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ImageType;
import com.ni.vision.NIVision.MeasurementType;
import com.ni.vision.NIVision.ParticleFilterCriteria2;
import com.ni.vision.NIVision.ParticleFilterOptions;
import com.ni.vision.NIVision.PointFloat;
import com.ni.vision.NIVision.Range;
import com.ni.vision.NIVision.ShapeReport;
import com.ni.vision.NIVision.TransformReport;

public class MoleyVision {
	
	final static int IVA_MAX_BUFFERS = 20;
	final static int IVA_STORE_RESULT_NAMES = 0;
	
	enum IVA_ResultType{
		IVA_NUMERIC, 
		IVA_BOOLEAN, 
		IVA_STRING
	}
	
	class IVA_ResultValue{
		double numVal;
	    boolean boolVal;
	    char strVal;
	}

	class IVA_Result{
	    char[] resultName = new char[256];           // Result name
	    IVA_ResultType  type;           // Result type
	    IVA_ResultValue resultVal;      // Result value
	}
	
	class IVA_StepResults{
		char[] stepName = new char[256];             // Step name
	    int numResults;         // number of results created by the step
	    IVA_Result results;            // array of results
	}
	
	class IVA_Data{
		Image[] buffers = new Image[IVA_MAX_BUFFERS];            // Vision Assistant Image Buffers
	    IVA_StepResults[] stepResults;              // Array of step results
	    int numSteps;                               // Number of steps allocated in the stepResults array
	    CoordinateSystem baseCoordinateSystems;    // Base Coordinate Systems
	    CoordinateSystem MeasurementSystems;       // Measurement Coordinate Systems
	    int numCoordSys;                            // Number of coordinate systems
	}
//	
	static int success = 1;
//	
//	int IVA_ProcessImage(Image image)
//	{
//		success = 1;
//	    IVA_Data ivaData = null;
//	    int pParameter[] = {0};
//	    float plower[] = {0};
//	    float pUpper[] = {450};
//	    int pCalibrated[] = {0};
//	    int pExclude[] = {0};
//	    int pPixelMeasurements[] = {0,1,16,17,35};
//	    int pCalibratedMeasurements[] = {0};
//
//	    // Initializes internal data (buffers and array of points for caliper measurements)
//	    VisionErrChk(ivaData == IVA_InitData(5, 0), 1);
//	    if(success == 0) return success;
//	    
//		VisionErrChk(IVA_CLRThreshold(image, 0, 255, 0, 255, 242, 255, ColorMode.HSL) == 1, 1);
//		if(success == 0) return success;
//		
//		VisionErrChk(IVA_MatchShape(image, "C:\\Users\\2015p\\Desktop\\Moley Vision\\t3.png", 800, 1, ivaData, 2) == 1, 1);
//		if(success == 0) return success;
//
//		VisionErrChk(IVA_ParticleFilter(image, pParameter, plower, pUpper, pCalibrated, pExclude, 1, 0, 1) == 1, 1);
//		if(success == 0) return success;
//		
//		VisionErrChk(IVA_Particle(image, 1, pPixelMeasurements, 5, pCalibratedMeasurements, 0, ivaData, 4) == 1, 1);
//		if(success == 0) return success;
//		
//	    // Releases the memory allocated in the IVA_Data structure.
//	    IVA_DisposeData(ivaData);
//	    return success;
//	}
//
//	////////////////////////////////////////////////////////////////////////////////
//	//
//	// Function Name: IVA_CLRThreshold
//	//
//	// Description  : Thresholds a color image.
//	//
//	// Parameters   : image      -  Input image
////	                min1       -  Minimum range for the first plane
////	                max1       -  Maximum range for the first plane
////	                min2       -  Minimum range for the second plane
////	                max2       -  Maximum range for the second plane
////	                min3       -  Minimum range for the third plane
////	                max3       -  Maximum range for the third plane
////	                colorMode  -  Color space in which to perform the threshold
//	//
//	// Return Value : success
//	//
//	////////////////////////////////////////////////////////////////////////////////
	static int IVA_CLRThreshold(Image image, int min1, int max1, int min2, int max2, int min3, int max3, ColorMode colorMode)
	{
	    success = 1;
	    Image thresholdImage;
	    Range plane1Range = new Range();
	    Range plane2Range = new Range();
	    Range plane3Range = new Range();


	    //-------------------------------------------------------------------//
	    //                          Color Threshold                          //
	    //-------------------------------------------------------------------//

	    // Creates an 8 bit image for the thresholded image.
	    thresholdImage = NIVision.imaqCreateImage(ImageType.IMAGE_U8, 7);
	    
	    // Set the threshold range for the 3 planes.
	    plane1Range.minValue = min1;
	    plane1Range.maxValue = max1;
	    plane2Range.minValue = min2;
	    plane2Range.maxValue = max2;
	    plane3Range.minValue = min3;
	    plane3Range.maxValue = max3;

	    // Thresholds the color image.
	    NIVision.imaqColorThreshold(thresholdImage, image, 1, colorMode, plane1Range, plane2Range, plane3Range);
	    
	    // Copies the threshold image in the source image.
	    NIVision.imaqDuplicate(image, thresholdImage);
//	    if(success == 0) {imaqDispose(thresholdImage); return success;}
	    return success;
	}
//
//
//	////////////////////////////////////////////////////////////////////////////////
//	//
//	// Function Name: IVA_MatchShape
//	//
//	// Description  : Finds a shape in an image.
//	//
//	// Parameters   : image            -  Input image
////	                templatePath     -  Template image path
////	                minimumScore     -  Minimum score a match can have for the
////	                                    function to consider the match valid.
////	                scaleInvariance  -  If TRUE, searches for shapes regardless of size.
////	                ivaData          -  Internal Data structure
////	                stepIndex        -  Step index (index at which to store the results in the resuts array)
//	//
//	// Return Value : success
//	//
//	////////////////////////////////////////////////////////////////////////////////
//	static int IVA_MatchShape(Image image, String templatePath, double minimumScore, int scaleInvariance, IVA_Data ivaData, int stepIndex)
//	{
//	    success = 1;
//	    Image shapeImage;
//	    Image imageTemplate;
//	    int i,j;
//	    short[] lookupTable = new short[256];
//	    ShapeReport[] shapeReport = null;
//	    int numMatchesFound;
//	    PointFloat centroid;
//	    int numMatches = 0;
//	    int numObjectResults;
//	    IVA_Result shapeResults;
//	    long visionInfo;
//	    TransformReport realWorldPosition = null;
//
//
//	    //-------------------------------------------------------------------//
//	    //                           Shape Matching                          //
//	    //-------------------------------------------------------------------//
//
//	    // Creates a temporary image that will be used to perform the search.
//	    shapeImage = NIVision.imaqCreateImage(ImageType.IMAGE_U8, 7);
//	    // Applies a lookup table to the image because the input image for the
//	    // imaqMatchShape function must be a binary image that contains only
//	    // pixel values of 0 or 1
//
//	    lookupTable[0] = 0;
//	    for (i = 1 ; i < 256 ; i++)
//	        lookupTable[i] = 1;
//
//	    VisionErrChk(imaqLookup(shapeImage, image, lookupTable, null));
//	    if(success == 0){imaqDispose(imageTemplate); imaqDispose(shapeImage); imaqDispose(shapeReport); imaqDispose(realWorldPosition); return success;}
//	    
//	    // Creates and read the image template containing the shape to match
//	    imageTemplate = NIVision.imaqCreateImage(ImageType.IMAGE_U8, 7);
//	    NIVision.imaqReadFile(imageTemplate, templatePath);
//	    
//	    // Applies the same lookup table to obtain an image containing only pixel
//	    // values of 0 and 1.
//	    VisionErrChk(imaqLookup(imageTemplate, imageTemplate, lookupTable, null));
//	    if(success == 0){imaqDispose(imageTemplate); imaqDispose(shapeImage); imaqDispose(shapeReport); imaqDispose(realWorldPosition); return success;}
//	    
//	    // Finds the shape in the binary image.
//	    VisionErrChk(shapeReport = NIVision.imaqMatchShape(shapeImage, shapeImage, imageTemplate, scaleInvariance, 1, 0.5));
//	    if(success == 0){imaqDispose(imageTemplate); imaqDispose(shapeImage); imaqDispose(shapeReport); imaqDispose(realWorldPosition); return success;}
//	    // Log the results in the points array for future caliper operations.
//	    for (i = 0 ; i < numMatchesFound ; i++)
//	    {
//	        if (shapeReport[i].score >= minimumScore)
//	            numMatches++;
//	    }
//
//	    // ////////////////////////////////////////
//	    // Store the results in the data structure.
//	    // ////////////////////////////////////////
//	    
//	    // First, delete all the results of this step (from a previous iteration)
//	    IVA_DisposeStepResults(ivaData, stepIndex);
//
//	    // Check if the image is calibrated.
//	    NIVision.imaqGetVisionInfoTypes(image);
//
//	    // If the image is calibrated, we also need to log the calibrated position (x and y) . 5 results instead of 3
//	    numObjectResults = (visionInfo && NIVision.VisionInfoType.CALIBRATION_INFO ? 5 : 3);
//	        
//	    ivaData.stepResults[stepIndex].numResults = numMatches * numObjectResults + 1;
//	    ivaData.stepResults[stepIndex].results = malloc (sizeof(IVA_Result) * ivaData.stepResults[stepIndex].numResults);
//	    shapeResults = ivaData.stepResults[stepIndex].results;
//	    
//	    if (shapeResults)
//	    {
//	        if (IVA_STORE_RESULT_NAMES == 1)
//	            shapeResults.resultName = "# Matches".toCharArray();
//	        shapeResults.type = IVA_ResultType.IVA_NUMERIC;
//	        shapeResults.resultVal.numVal = numMatches;
//	        shapeResults.resultVal.numVal++;
//	        
//	        j = 0;
//	        
//	        for (i = 0 ; i < numMatchesFound ; i++)
//	        {
//	            if (shapeReport[i].score >= minimumScore)
//	            {
//	                j++;
//	                    
//	                if(IVA_STORE_RESULT_NAMES == 1)
//	                    shapeResults.resultName = ("Match " + j + ".X Position (Pix.)").toCharArray();
//	                shapeResults.type = IVA_ResultType.IVA_NUMERIC;
//	                shapeResults.resultVal.numVal = shapeReport[i].centroid.x;
//	                shapeResults.resultVal.numVal++;
//	            
//	                if(IVA_STORE_RESULT_NAMES == 1)
//	                    shapeResults.resultName = ("Match " + j + ".Y Position (Pix.)").toCharArray();
//	                shapeResults.type = IVA_ResultType.IVA_NUMERIC;
//	                shapeResults.resultVal.numVal = shapeReport[i].centroid.y;
//	                shapeResults.resultVal.numVal++;
//
//	                if (visionInfo && NIVision.VisionInfoType.CALIBRATION_INFO)
//	                {
//	                    centroid.x = shapeReport[i].centroid.x;
//	                    centroid.y = shapeReport[i].centroid.y;
//	                    realWorldPosition = NIVision.imaqTransformPixelToRealWorld(image, centroid);
//	                
//	                    if(IVA_STORE_RESULT_NAMES == 1)
//	                        shapeResults.resultName = ("Match " + j + ".X Position (World)").toCharArray();
//	                    shapeResults.type = IVA_ResultType.IVA_NUMERIC;
//	                    shapeResults.resultVal.numVal = realWorldPosition.points[0].x;
//	                    shapeResults.resultVal.numVal++;
//
//	                    if(IVA_STORE_RESULT_NAMES == 1)
//	                        shapeResults.resultName = ("Match " + j + ".Y Position (World)").toCharArray();
//	                    shapeResults.type = IVA_ResultType.IVA_NUMERIC;
//	                    shapeResults.resultVal.numVal = realWorldPosition.points[0].y;
//	                    shapeResults.resultVal.numVal++;
//	                }
//
//	               	if(IVA_STORE_RESULT_NAMES == 1)
//	                    shapeResults.resultName = ("Match " + j + ".Score").toCharArray();
//	                shapeResults.type = IVA_ResultType.IVA_NUMERIC;
//	                shapeResults.resultVal.numVal = shapeReport[i].score;
//	                shapeResults.resultVal.numVal++;
//	            }
//	        }
//	    }
//	}
//
//	////////////////////////////////////////////////////////////////////////////////
//	//
//	// Function Name: IVA_ParticleFilter
//	//
//	// Description  : Filters particles based on their morphological measurements.
//	//
//	// Parameters   : image          -  Input image
////	                pParameter     -  Morphological measurement that the function
////	                                  uses for filtering.
////	                plower         -  Lower bound of the criteria range.
////	                pUpper         -  Upper bound of the criteria range.
////	                pCalibrated    -  Whether to take a calibrated measurement or not.
////	                pExclude       -  TRUE indicates that a match occurs when the
////	                                  value is outside the criteria range.
////	                criteriaCount  -  number of particle filter criteria.
////	                rejectMatches  -  Set this parameter to TRUE to transfer only
////	                                  those particles that do not meet all the criteria.
////	                                  Set this parameter to FALSE to transfer only those
////	                                  particles that meet all the criteria to the destination.
////	                connectivity   -  Set this parameter to 1 to use connectivity-8
////	                                  to determine whether particles are touching.
////	                                  Set this parameter to 0 to use connectivity-4
////	                                  to determine whether particles are touching.
//	//
//	// Return Value : success
//	//
//	////////////////////////////////////////////////////////////////////////////////
//	static int IVA_ParticleFilter(Image image, int pParameter[], float plower[], float pUpper[], int pCalibrated[], int pExclude[], int criteriaCount, int rejectMatches, int connectivity)
//	{
//	    success = 1;
//	    ParticleFilterCriteria2 particleCriteria = null;
//	    int i;
//	    ParticleFilterOptions particleFilterOptions;
//	    int numParticles;
//
//
//	    //-------------------------------------------------------------------//
//	    //                          Particle Filter                          //
//	    //-------------------------------------------------------------------//
//
//	    if (criteriaCount > 0)
//	    {
//	        // Fill in the ParticleFilterCriteria2 structure.
//	        particleCriteria = (ParticleFilterCriteria2)malloc(criteriaCount * sizeof(ParticleFilterCriteria2));
//
//	        for (i = 0 ; i < criteriaCount ; i++)
//	        {
//	            particleCriteria.parameter = pParameter[i];
//	            particleCriteria.lower = plower[i];
//	            particleCriteria.upper = pUpper[i];
//	            particleCriteria.calibrated = pCalibrated[i];
//	            particleCriteria.exclude = pExclude[i];
//	        }
//	        
//	        particleFilterOptions.rejectMatches = rejectMatches;
//	        particleFilterOptions.rejectBorder = 0;
//	        particleFilterOptions.connectivity8 = connectivity;
//	        
//	        // Filters particles based on their morphological measurements.
//	        VisionErrChk(NIVision.imaqParticleFilter3(image, image, particleCriteria, criteriaCount, particleFilterOptions, numParticles));
//	        if(success == 0){free(particleCriteria); return success;}
//	    }
//	}
//
//
//	////////////////////////////////////////////////////////////////////////////////
//	//
//	// Function Name: IVA_Particle
//	//
//	// Description  : Computes the number of particles detected in a binary image and
////	                a 2D array of requested measurements about the particle.
//	//
//	// Parameters   : image                      -  Input image
////	                connectivity               -  Set this parameter to 1 to use
////	                                              connectivity-8 to determine
////	                                              whether particles are touching.
////	                                              Set this parameter to 0 to use
////	                                              connectivity-4 to determine
////	                                              whether particles are touching.
////	                pixelMeasurements          -  Array of measuremnets parameters
////	                numPixelMeasurements       -  Number of elements in the array
////	                calibratedMeasurements     -  Array of measuremnets parameters
////	                numCalibratedMeasurements  -  Number of elements in the array
////	                ivaData                    -  Internal Data structure
////	                stepIndex                  -  Step index (index at which to store
////	                                              the results in the resuts array)
//	//
//	// Return Value : success
//	//
//	////////////////////////////////////////////////////////////////////////////////
//	static int IVA_Particle(Image image, int connectivity, int pPixelMeasurements[], int numPixelMeasurements, int pCalibratedMeasurements[], int numCalibratedMeasurements, IVA_Data ivaData, int stepIndex)
//	{
//	    success = 1;
//	    int numParticles;
//	    double[] pixelMeasurements;
//	    double calibratedMeasurements = 0.0;
//	    long visionInfo;
//	    IVA_Result particleResults;
//	    int i;
//	    int j;
//	    double centerOfMassX;
//	    double centerOfMassY;
//
//
//	    //-------------------------------------------------------------------//
//	    //                         Particle Analysis                         //
//	    //-------------------------------------------------------------------//
//
//	    // Counts the number of particles in the image.
//	    NIVision.imaqCountParticles(image, connectivity);
//	    
//	    // Allocate the arrays for the measurements.
//	    pixelMeasurements = (double)malloc(numParticles * numPixelMeasurements * sizeof(double));
//	    calibratedMeasurements = (double)malloc(numParticles * numCalibratedMeasurements * sizeof(double));
//
//	    // Delete all the results of this step (from a previous iteration)
//	    IVA_DisposeStepResults(ivaData, stepIndex);
//
//	    // Check if the image is calibrated.
//	    NIVision.imaqGetVisionInfoTypes(image);
//	    
//	    // If the image is calibrated, we also need to log the calibrated position (x and y)
//	    ivaData.stepResults[stepIndex].numResults = (visionInfo && NIVision.VisionInfoType.CALIBRATION_INFO ?
//	                                                  numParticles * 4 + 1 : numParticles * 2 + 1);
//	    ivaData.stepResults[stepIndex].results = malloc (sizeof(IVA_Result) * ivaData.stepResults[stepIndex].numResults);
//	    
//	    particleResults = ivaData.stepResults[stepIndex].results;
//
//	    if(IVA_STORE_RESULT_NAMES == 1)
//	        particleResults.resultName = "Object #".toCharArray();
//	    particleResults.type = IVA_ResultType.IVA_NUMERIC;
//	    particleResults.resultVal.numVal = numParticles;
//	    particleResults.resultVal.numVal++;
//	    
//	    for (i = 0 ; i < numParticles ; i++)
//	    {
//	        // Computes the requested pixel measurements about the particle.
//	        for (j = 0 ; j < numPixelMeasurements ; j++)
//	        {
//	        	NIVision.imaqMeasureParticle(image, i, 0, NIVision.MeasurementType.MT_AREA);
//	        }
//
//	        // Computes the requested calibrated measurements about the particle.
//	        for (j = 0 ; j < numCalibratedMeasurements ; j++)
//	        {
//	            NIVision.imaqMeasureParticle(image, i, 1, NIVision.MeasurementType.MT_AREA);
//	        }
//	        
//	        if(IVA_STORE_RESULT_NAMES == 1)
//	        	particleResults.resultName = ("Particle " + (i + 1) + ".X Position (Pix.)").toCharArray();
//	        particleResults.type = IVA_ResultType.IVA_NUMERIC;
//	        NIVision.imaqMeasureParticle(image, i, 0, NIVision.MeasurementType.MT_CENTER_OF_MASS_X);
//	        if(success == 0){free(pixelMeasurements); free(calibratedMeasurements); return success;}
//	        particleResults.resultVal.numVal = centerOfMassX;
//	        particleResults.resultVal.numVal++;
//
//	        if(IVA_STORE_RESULT_NAMES == 1)
//	            particleResults.resultName = ("Particle " + (i + 1) + ".Y Position (Pix.)").toCharArray();
//	        particleResults.type = IVA_ResultType.IVA_NUMERIC;
//	        NIVision.imaqMeasureParticle(image, i, 0, MeasurementType.MT_CENTER_OF_MASS_Y);
//	        particleResults.resultVal.numVal = centerOfMassY;
//	        particleResults.resultVal.numVal++;
//
//	        if (visionInfo && NIVision.VisionInfoType.CALIBRATION_INFO)
//	        {
//	            if(IVA_STORE_RESULT_NAMES == 1)
//	                particleResults.resultName = ("Particle " + (i + 1) + ".X Position (Calibrated)").toCharArray();
//	            particleResults.type = IVA_ResultType.IVA_NUMERIC;
//	            NIVision.imaqMeasureParticle(image, i, 1, MeasurementType.MT_CENTER_OF_MASS_X);
//	            particleResults.resultVal.numVal = centerOfMassX;
//	            particleResults.resultVal.numVal++;
//
//	            if(IVA_STORE_RESULT_NAMES == 1)
//	                particleResults.resultName = ("Particle " + (i + 1) + ".Y Position (Calibrated)").toCharArray();
//	            particleResults.type = IVA_ResultType.IVA_NUMERIC;
//	            NIVision.imaqMeasureParticle(image, i, 1, MeasurementType.MT_CENTER_OF_MASS_Y);
//	            particleResults.resultVal.numVal = centerOfMassY;
//	            particleResults.resultVal.numVal++;
//	        }
//	    }
//	}
//
//
//	////////////////////////////////////////////////////////////////////////////////
//	//
//	// Function Name: IVA_InitData
//	//
//	// Description  : Initializes data for buffer management and results.
//	//
//	// Parameters   : # of steps
////	                # of coordinate systems
//	//
//	// Return Value : success
//	//
//	////////////////////////////////////////////////////////////////////////////////
//	static IVA_Data IVA_InitData(int numSteps, int numCoordSys)
//	{
//	    success = 1;
//	    IVA_Data ivaData = null;
//	    int i;
//
//
//	    // Allocate the data structure.
//	    VisionErrChk(ivaData = (IVA_Data)malloc(sizeof (IVA_Data)));
//	    if(success == 0) return ivaData;
//	    
//	    // Initializes the image pointers to null.
//	    for (i = 0 ; i < IVA_MAX_BUFFERS ; i++)
//	        ivaData.buffers[i] = null;
//
//	    // Initializes the step results array to numSteps elements.
//	    ivaData.numSteps = numSteps;
//
//	    ivaData.stepResults = (IVA_StepResults)malloc(ivaData.numSteps * sizeof(IVA_StepResults));
//	    for (i = 0 ; i < numSteps ; i++)
//	    {
//	        if(IVA_STORE_RESULT_NAMES == 1)
//	           ivaData.stepResults[i].stepName = "".toCharArray();
//	        ivaData.stepResults[i].numResults = 0;
//	        ivaData.stepResults[i].results = null;
//	    }
//
//	    // Create the coordinate systems
//	    ivaData.baseCoordinateSystems = null;
//	    ivaData.MeasurementSystems = null;
//	    if (numCoordSys)
//	    {
//	        ivaData.baseCoordinateSystems = (CoordinateSystem)malloc(sizeof(CoordinateSystem) * numCoordSys);
//	        ivaData.MeasurementSystems = (CoordinateSystem)malloc(sizeof(CoordinateSystem) * numCoordSys);
//	    }
//
//	    ivaData.numCoordSys = numCoordSys;
//	}
//
//
//	////////////////////////////////////////////////////////////////////////////////
//	//
//	// Function Name: IVA_DisposeData
//	//
//	// Description  : Releases the memory allocated in the IVA_Data structure
//	//
//	// Parameters   : ivaData  -  Internal data structure
//	//
//	// Return Value : success
//	//
//	////////////////////////////////////////////////////////////////////////////////
//	static int IVA_DisposeData(IVA_Data ivaData)
//	{
//	    int i;
//
//	    // Releases the memory allocated for the image buffers.
//	    for (i = 0 ; i < IVA_MAX_BUFFERS ; i++)
//	        imaqDispose(ivaData.buffers[i]);
//
//	    // Releases the memory allocated for the array of measurements.
//	    for (i = 0 ; i < ivaData.numSteps ; i++)
//	        IVA_DisposeStepResults(ivaData, i);
//
//	    free(ivaData.stepResults);
//
//	    // Dispose of coordinate systems
//	    if (ivaData.numCoordSys)
//	    {
//	        free(ivaData.baseCoordinateSystems);
//	        free(ivaData.MeasurementSystems);
//	    }
//
//	    free(ivaData);
//
//	    return 1;
//	}
//
//
//	////////////////////////////////////////////////////////////////////////////////
//	//
//	// Function Name: IVA_DisposeStepResults
//	//
//	// Description  : Dispose of the results of a specific step.
//	//
//	// Parameters   : ivaData    -  Internal data structure
////	                stepIndex  -  step index
//	//
//	// Return Value : success
//	//
//	////////////////////////////////////////////////////////////////////////////////
//	static int IVA_DisposeStepResults(IVA_Data ivaData, int stepIndex)
//	{
//	    int i;
//	    
//	    for (i = 0 ; i < ivaData.stepResults[stepIndex].numResults ; i++)
//	    {
//	        if (ivaData.stepResults[stepIndex].results[i].type == IVA_ResultType.IVA_STRING)
//	            free(ivaData.stepResults[stepIndex].results[i].resultVal.strVal);
//	    }
//	    
//	    free(ivaData.stepResults[stepIndex].results);
//
//	    return 1;
//	}
//	
//	void VisionErrChk(boolean function, int num){
//		if(!function){
//			success = 0;
//		}
//	}
}