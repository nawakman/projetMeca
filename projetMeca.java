//PROJET MECA 4  
import java.util.Scanner;

class Run {
    public static void main(String[] args) {
        Utility.startIO();

        //CODE
        //System.out.println(DragCoefficientFromReynolds.getValueFromCurve(6.6e5));
        ConstantDiameterPole p=new ConstantDiameterPole(12,0.5);//in m
        System.out.println("The wind is applying "+Math.round(p.CalculateDragForce())+" N to the pole");//dragCoeff=0.6 for 166 666 Re
        //END CODE

        Utility.endIO();
    }
}
class Utility{
    private static Scanner reader;

    public static void startIO(){
        reader = new Scanner(System.in);  // Reading from System.in
    }
    public static void endIO(){
        reader.close();
    }
    public static double askDouble(String question){
        System.out.println(question);
        return reader.nextDouble();        
    }
}

class DragCoefficientFromReynolds{//very bad implementation using linear interpolation
    private final static double[] curveX={0.1, 0.5, 1, 5, 10, 50, 100, 500, 1000.0, 5000.0, 10000.0, 100000.0, 200000.0, 350000.0, 500000.0, 1000000.0, 2000000.0, 5000000.0, 10000000.0};
    private final static double[] curveY=  {60, 15, 10, 4.5, 3, 2, 1.8, 1, 0.9, 1.1, 1.25, 1.3, 1, 0.6, 0.2, 0.31, 0.42, 0.6, 0.7};
    
    public static double getValueFromCurve(double reynolds){
        //System.out.println(""+curveX.length+"/"+curveY.length);//to check if we have the same numbers of entries in each axis
        for(int i=0;i<curveX.length;i++){
            if (curveX[i]>reynolds){//so we need to sample the point between i-1 and i
                double alpha=(reynolds-curveX[i-1])/(curveX[i]-curveX[i-1]);//alpha between 0 and 1 for lerp //we normalize relative to the x distance between curveX[i-1] and curveX[i]
                //System.out.println("alpha:"+alpha);
                return curveY[i-1]+((curveY[i]-curveY[i-1])*alpha);//lerp formula
            }
        }
        System.out.println("Reynolds too big, not on the curve");
        return 0;
    }
}

class Environment{
    public final static double gravityConstant=9.81;
    public final static double windSpeed=50;//in m/s
    public final static double volumicMassAir=1.2;// in kg.m-3
    public final static double airViscosity=15e-6;//in m2.s-1 at 20C, 1atm 
}
class ConstantDiameterPole{
    private double height;
    private double diameter;
    public ConstantDiameterPole(double height, double diameter ){
        this.height=height;
        this.diameter=diameter;
    }
    public double CalculateDragForce(){
        double maitreCouple=height*diameter;//projection of pole shape to a plane behind it
        double Reynolds=(Environment.windSpeed*diameter)/Environment.airViscosity;
        //double dragCoefficient=Utility.askDouble("drag coefficient for "+Reynolds+" Reynolds ?");
        double dragCoefficient=DragCoefficientFromReynolds.getValueFromCurve(Reynolds);
        return (Environment.volumicMassAir*maitreCouple*dragCoefficient*Math.pow(Environment.windSpeed, 2))/2;
    } 

}