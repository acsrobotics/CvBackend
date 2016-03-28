package main;


import java.time.Duration;
import java.time.Instant;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;

public class Demo {
	
	
	public static void main(String[] args){
		CvEngine processor = new CvEngine();
		Mat imgInput = new Mat();
		
		imgInput = Highgui.imread("B.jpg", Highgui.CV_LOAD_IMAGE_COLOR);
		// thresh red
		
		imgInput = new CvEngine()
						.setImage(imgInput)
						.resizeTo(640, 480)
						.getImage();
		
		Instant start = Instant.now();
		
		processor
			.setImage(imgInput)
			.convertToThreeChannel()
			.toGrayScale()
			.gaussianBlur(new Size(5, 5), 0, 0)
			.detectEdge(35, 90)
//			.writeToFileWithName("edge")
			.detectLinesQuick(100, 30, 50)
			.drawLines(imgInput);
//			.writeToFileWithName("houghLines");
		
		System.out.println(Duration.between(Instant.now(), start));
		
	}

}
