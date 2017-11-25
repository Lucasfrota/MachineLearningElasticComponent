package machinelearning;

import Exceptions.ParametersException;
import Exceptions.quantityParametersException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MachineLearning {
    
    public static void main(String[] args) throws ParametersException, quantityParametersException{
    
        ClassifierInterface hepatitisClassifier = new ClassifierInterface(ClassifierInterface.Type.NAIVEBAYES, null, 0);
        String hepatiteClass = hepatitisClassifier.classifier(Arrays.asList(40,"1","1","2","1","2","2","2","1","2","2","2","2",0.60f,62,166,4.0f,63,"1"));
        
        System.out.println("Class hepatite dataSet: " + hepatiteClass);
        
        
        ClassifierInterface plantsClassifier = new ClassifierInterface(ClassifierInterface.Type.NAIVEBAYES, "plantas");
        String plantsClass = plantsClassifier.classifier(Arrays.asList(11, 2));
        
        System.out.println("Class plants dataSet: " + plantsClass);
        
        
    }
    
}
