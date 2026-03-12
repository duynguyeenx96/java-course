package mobile.id.vn;
import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        int numPoints = 0;
        for (Point currPt : s.getPoints()) {
          numPoints++;
        }
        return numPoints;
    }

    public double getAverageLength(Shape s) {
        int np = getNumPoints(s);
        double p = getPerimeter(s);
        return p/np;
    }

    public double getLargestSide(Shape s) {
        double largestSide = 0.0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()){
            double currDist = prevPt.distance(currPt);
            if (currDist > largestSide){
                largestSide = currDist;
            }
        }
        return largestSide;
    }

    public double getLargestX(Shape s) {
        double largestX = 0.0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()){
            double currX = currPt.getX();
            if (currX > largestX){
                largestX = currX;
            }
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        double largestPerimeterMultiple = 0.0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double length = getPerimeter(s);
            if (length > largestPerimeterMultiple){
                largestPerimeterMultiple = length;
            }
        }
        return largestPerimeterMultiple;
    }

    public String getFileWithLargestPerimeter() {
        double largestPerimeterMultiple = 0.0;
        File temp = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double length = getPerimeter(s);
            if (length > largestPerimeterMultiple){
                largestPerimeterMultiple = length;
                temp = f;
            }
        }
        return temp.getName();
    }

    public void testPerimeter (String filename) {
        FileResource fr = new FileResource(filename);
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        int numPoints = getNumPoints(s);
        System.out.println("perimeter = " + Math.floor(length * 100)/100);
        System.out.println("num points = " + numPoints);

        double al = getAverageLength(s);
        System.out.println("average length = " + Math.floor(al*100)/100);

        double largestSide = getLargestSide(s);
        System.out.println("largest side " + Math.floor(largestSide*100)/100);

        double largestX = getLargestX(s);
        System.out.println("largestX " + Math.floor(largestX*100)/100);

    }

    public void testPerimeterMultipleFiles() {
        double largestPerimeter = getLargestPerimeterMultipleFiles();
        System.out.println("largest perimeter: " + Math.floor(largestPerimeter*100)/100);
    }

    public void testFileWithLargestPerimeter() {
         String fileName = getFileWithLargestPerimeter();
         System.out.println("name of file with largest perimeter: " + fileName);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
//        pr.testPerimeter("/Users/duynguyen/Documents/Java/DukeAssignments/src/main/java/mobile/id/vn/datatest1.txt");

//        pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
    }
}
