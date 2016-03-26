package backend_testing;


import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;

public class Demo {
	
	
	public static void main(String[] args){
		CvEngine processor = new CvEngine();
		Mat imgInput = new Mat();
		
		imgInput = Highgui.imread("C.PNG", Highgui.CV_LOAD_IMAGE_COLOR);
		// thresh red
		
		imgInput = new CvEngine()
						.setImage(imgInput)
						.resizeTo(640, 480)
						.getImage();
		
		processor
			.setImage(imgInput)
			.convertToThreeChannel()
			.toGray()
			.gaussianBlur(new Size(5, 5), 0, 0)
			.detectEdge(35, 90)
			.writeToFileWithName("edge")
			.findContours()
			.detectEllipse()
			.drawEllipses(imgInput)
			.computeRectsFromContours()
			.drawRects(imgInput)
			.writeToFileWithName("ellipse");

		
	}

}
