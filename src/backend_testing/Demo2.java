package backend_testing;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import org.opencv.objdetect.CascadeClassifier;

public class Demo2 {
	
	static{
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}
	
	public static void main(String[] args){
		CvPipeline processor = new CvPipeline();
		Mat imgInput = new Mat();
		
		String classiferName = "haarcascade_frontalface_alt.xml";
		
		CascadeClassifier classifier = new CascadeClassifier();
		classifier.load(classiferName);
		
		imgInput = Highgui.imread("FQ.png", Highgui.CV_LOAD_IMAGE_COLOR);
		
		processor
			.setImage(imgInput)
			.convertToThreeChannel()
			.toGray()
			.detectFaces(classifier)
			.drawRects(imgInput)
			.writeToFileWithName("Face");
	}
}
