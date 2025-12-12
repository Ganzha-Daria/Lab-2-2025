import functions.FunctionPoint;
import functions.TabulatedFunction;

public class Main {
    public static void main(String[] args){
        double[] values = {0.0, 1.0, 4.0, 16.0};
        TabulatedFunction function = new TabulatedFunction(0.0,4.0, values);
        printFunctionInfo(function);

         System.out.println("\nFunction's values:");
         double[] points={-1.0, 0.0, 0.5, 1.0, 1.5, 2.0, 2.5, 3.0, 3.5, 4.0, 5.0};
         for (double x: points){
            double y = function.getFunctionValue(x);
            System.out.printf("f(%.1f) = %s%n", x, Double.isNaN(y) ? "undefined" : String.format("%.2f", y));
         }
          System.out.println("\n Changing points");
          function.setPointY(2,5.0);
          System.out.println("After changing points");
          printFunctionInfo(function);

          System.out.println("Adding points");
          FunctionPoint newPoint = new FunctionPoint(1.5,2.5);
          function.addPoint(newPoint);
          System.out.println("After adding");
        printFunctionInfo(function);

           System.out.println("Deleting points:");
           function.deletePoint(2);
           System.out.println("After deleting points:");
           printFunctionInfo(function);

           System.out.println("\n New Function's values:");
           for (double x: points){
            double y = function.getFunctionValue(x);
            System.out.printf("f(%.1f) = %s%n", x, Double.isNaN(y) ? "undefined" : String.format("%.2f", y));
         }
         System.out.println("\nBorders:");
            System.out.println("\n Left border: " + function.getLeftDomainBorder());
            System.out.println("\n Right border: " + function.getRightDomainBorder());
            System.out.println("\n Counts of points: " + function.getPointsCount());
    }
     private static void printFunctionInfo(TabulatedFunction function) {
        System.out.println("Function's points:");
        for (int i = 0; i < function.getPointsCount(); i++) {
            double x = function.getPointX(i);
            double y = function.getPointY(i);
            System.out.printf(" %d %.1f %.1f %n", i, x, y);
        }
    }
}