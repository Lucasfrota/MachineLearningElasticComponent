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
        String hepatiteClass = hepatitisClassifier.classifier(features);
        
        System.out.println("Class hepatite dataSet: " + hepatiteClass);
        
        
        ElasticClassifier plantsClassifier = new ElasticClassifier(ElasticClassifier.Type.NAIVEBAYES, "dataSets/plantas");
        String plantsClass = plantsClassifier.classifier(Arrays.asList(11, 2));
        
        System.out.println("Class plants dataSet: " + plantsClass);
        
        Comparator compHepatitis = new Comparator("dataSets/HepatitisDataSet", 0);
        
        compHepatitis.printBestType();
        
        Comparator compPlants = new Comparator("dataSets/plantas");
        
        compPlants.printBestType();
        
        
    }
    
}
