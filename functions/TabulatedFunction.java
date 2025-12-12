package functions;

public class TabulatedFunction {
    private FunctionPoint[] points;
    

    public TabulatedFunction(double leftX, double rightX, int pointsCount) {
        this.points = new FunctionPoint[pointsCount];
        
        double step = (rightX - leftX) / (pointsCount - 1);
        for (int i = 0; i < pointsCount; i++) {
            double x = leftX + step * i;
            points[i] = new FunctionPoint(x, 0.0);
        }
    }

    public TabulatedFunction(double leftX, double rightX, double[] values) {
        this.points = new FunctionPoint[values.length];
        
        double step = (rightX - leftX) / (values.length - 1);
        for (int i = 0; i < values.length; i++) {
            double x = leftX + step * i;
            points[i] = new FunctionPoint(x, values[i]);
        }
    }

    public double getLeftDomainBorder() {
        return points[0].getX();
    }

    public double getRightDomainBorder() {
        return points[points.length - 1].getX();
    }

   public double getFunctionValue(double x) {
    double leftBorder = getLeftDomainBorder();
    double rightBorder = getRightDomainBorder();
    
    if (x < leftBorder || x > rightBorder) {
        return Double.NaN;
    }
    

    if (x == leftBorder) {
        return points[0].getY();
    }
    if (x == rightBorder) {
        return points[points.length - 1].getY();
    }
    
    
    for (int i = 0; i < points.length - 1; i++) {
        double x1 = points[i].getX();
        double x2 = points[i + 1].getX();
        
        
        if (x >= x1 && x <= x2) {
            double y1 = points[i].getY();
            double y2 = points[i + 1].getY();
            
            
            return y1 + (y2 - y1) * (x - x1) / (x2 - x1);
        }
    }
    
    return Double.NaN;
}

    public int getPointsCount() {
        return points.length;
    }

    public FunctionPoint getPoint(int index) {
        return new FunctionPoint(points[index]);
    }

    public void setPoint(int index, FunctionPoint point) {
        double newX = point.getX();
        
        points[index] = new FunctionPoint(point);
    }

    public double getPointX(int index) {
        return points[index].getX();
    }

    public void setPointX(int index, double x) {    
        double currentY = points[index].getY();
        points[index] = new FunctionPoint(x, currentY);
    }

    public double getPointY(int index) {
        return points[index].getY();
    }

    public void setPointY(int index, double y) {
        points[index].setY(y);
    }

    public void deletePoint(int index) {    
        FunctionPoint[] newPoints = new FunctionPoint[points.length - 1];
        System.arraycopy(points, 0, newPoints, 0, index);
        System.arraycopy(points, index + 1, newPoints, index, points.length - index - 1);
        points = newPoints;
    }

    public void addPoint(FunctionPoint point) {
        
        int insertIndex = 0;
        while (insertIndex < points.length && points[insertIndex].getX() < point.getX()) {
            insertIndex++;
        }
       FunctionPoint[] newPoints = new FunctionPoint[points.length + 1];
        System.arraycopy(points, 0, newPoints, 0, insertIndex);
        newPoints[insertIndex] = new FunctionPoint(point);
        System.arraycopy(points, insertIndex, newPoints, insertIndex + 1, points.length - insertIndex);
        points = newPoints;
    }
}