package machinelearning;

import Exceptions.ParametersException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MachineLearning {
    
    public static void main(String[] args) throws Exception{
    
        ElasticClassifier hepatitisClassifier = new ElasticClassifier(ElasticClassifier.Type.DECISION_TREE , "dataSets/tic-tac-toe");//HepatitisDataSet", 0);
        String classify = hepatitisClassifier.classify(Arrays.asList("x","x","x","x","o","o","x","o","o"));
        System.out.println(classify);
                
                //hepatitisClassifier.classify(Arrays.asList("0",28.0,38.0,91.0,39.0,"0","0","?","0","?","0","?","?","?","?","0","0"));
                
                //System.out.println(hepatitisClassifier.getReliability(0));
                //System.out.println(hepatitisClassifier.getReliability(1));
                //System.out.println(hepatitisClassifier.getReliability(2));
                //Comparator c = new Comparator("dataSets/APP_V5");
                
                //c.printBestType();
                //c.printConfusionMatrix();
                
                //Serializer<ElasticClassifier> s = new Serializer<ElasticClassifier>();
                
                //s.SerializeObject(hepatitisClassifier, "MyTDCTest");
                
                
                /*
                List<Object> features = Arrays.asList("1",1931,75,52,81,48,"0","?","?","?","?","?","?","1",40407,"?","1","1");
                List<Object> features1 = Arrays.asList("1",1957,123.8,70.4,80.8,23.8,"1","0","?","2","1","?",47,"0","?","?","1","1");
                List<Object> features2 = Arrays.asList("0",1949,83,89,371,269,"0","0","?","?","?","?","?","0","?","?","1","1");
                
                String hepatiteClass = hepatitisClassifier.classify(features);
                System.out.println(hepatitisClassifier.getReliability());
                String hepatiteClass1 = hepatitisClassifier.classify(features1);
                System.out.println(hepatitisClassifier.getReliability());
                String hepatiteClass2 = hepatitisClassifier.classify(features2);
                System.out.println(hepatitisClassifier.getReliability());
                */
                
                /*
                System.out.println("Class hepatite dataSet: " + hepatiteClass);
                System.out.println("Accuracy hepatite dataSet: " + hepatitisClassifier.accuracy() + "\n");
                
                
                ElasticClassifier plantsClassifier = new ElasticClassifier(ElasticClassifier.Type.NAIVEBAYES, "dataSets/plantas");
                String plantsClass = plantsClassifier.classify(Arrays.asList(11, 2));
                
                System.out.println("Class plants dataSet: " + plantsClass);
                System.out.println("Accuracy plants dataSet: " + plantsClassifier.accuracy() + "\n");
                
                ElasticClassifier ticTacToeClassifier = new ElasticClassifier(ElasticClassifier.Type.NAIVEBAYES, "dataSets/tic-tac-toe");
                String ticTacToeClass = ticTacToeClassifier.classify(Arrays.asList("x","x","x","x","o","o","x","o","o"));
                
                System.out.println("Class tic tac toe dataSet: " + ticTacToeClass);
                System.out.println("Accuracy tic tac toe dataSet: " + ticTacToeClassifier.accuracy());
                */;
    }
    
    
}
