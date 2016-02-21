package backend_testing;


import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;

public class Demo {
	
	
	public static void main(String[] args){
		CvPipeline processor = new CvPipeline();
		Mat imgInput = new Mat();
		
		imgInput = Highgui.imread("C.png", Highgui.CV_LOAD_IMAGE_COLOR);
		// thresh red
		
		Mat imgThRed = processor
						.setImage(imgInput)
						.toHSV()
						.setLowHSV(150, 120, 40)
						.setHighHSV(179, 255, 255)
						.threshold()
						.writeToFileWithName("Red")
						.getImage();
		
		// thresh black
		
		Mat imgThBlack = processor
							.setLowHSV(1, 1, 1)
							.setHighHSV(179, 255, 75)
							.setImage(imgInput)
							.toHSV()
							.threshold()
							.writeToFileWithName("Black")
							.getImage();
		
		// thresh green
		
		Mat imgThGreen = processor
							.setLowHSV(90, 110, 85)
							.setHighHSV(140, 255, 255)
							.setImage(imgInput)
							.toHSV()
							.threshold()
							.writeToFileWithName("Green")
							.getImage();
		
		// combined 
		
		Mat imgCombined = processor
							.getBlackEmptyMat(imgThBlack)
							.convertToThreeChannel()
							.getImage();
		
		imgCombined = processor
						.setLowHSV(1, 1, 1)
						.setHighHSV(179, 255, 255)
						.setImage(imgCombined)
						.writeToFileWithName("Stage_0")
						.combineWith(new CvPipeline()
										.setImage(imgThBlack)
										.convertToThreeChannel()
										.writeToFileWithName("Black_Combine")
										.getImage(), 0.0)
						.writeToFileWithName("Stage_1")
						.combineWith(new CvPipeline()
											.setImage(imgThGreen)
											.convertToThreeChannel()
											.getImage(), 0.5)
						.threshold()
						.convertToThreeChannel()
						.writeToFileWithName("Stage_2")
						.combineWith(new CvPipeline()
											.setImage(imgThRed)
											.convertToThreeChannel()
											.getImage(), 0.5)
						.writeToFileWithName("Stage_2.5")
						.setLowHSV(0, 0, 0)
						.setHighHSV(179, 255, 10)
						.threshold()
						.invert()
						.writeToFileWithName("Stage_X")
						.getImage();
		
		processor
			.setImage(imgCombined)
			.convertToThreeChannel()
			.toBGR()
			.toGray()
			.findContours()
			.computeRectsFromContours()
			.reduceRectsToOne()
			.drawRects(imgInput)
			.drawCircleOnCenter()
			.writeToFileWithName("Stage_Combined_Contours");
		
	}
}
