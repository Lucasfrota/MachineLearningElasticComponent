package machinelearning;

import Exceptions.ParametersException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MachineLearning {
    
    public static void main(String[] args) throws Exception{
    
        ElasticClassifier hepatitisClassifier = new ElasticClassifier(ElasticClassifier.Type.NAIVEBAYES, "dataSets/HepatitisDataSet", 0);
        
        List<Object> features = Arrays.asList(40,"1","1","2","1","2","2","2","1","2","2","2","2",0.60f,62,166,4.0f,63,"1");
        String hepatiteClass = hepatitisClassifier.classify(features);
        
        System.out.println("Class hepatite dataSet: " + hepatiteClass);
        System.out.println("Accuracy hepatite dataSet: " + hepatitisClassifier.accuracy() + "\n");
        
        
        ElasticClassifier plantsClassifier = new ElasticClassifier(ElasticClassifier.Type.NAIVEBAYES, "dataSets/plantas");
        String plantsClass = plantsClassifier.classify(Arrays.asList(11, 2));
        
        System.out.println("Class plants dataSet: " + plantsClass);
        System.out.println("Accuracy plants dataSet: " + plantsClassifier.accuracy());
        
    }
    
}
