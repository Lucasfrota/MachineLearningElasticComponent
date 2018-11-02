package machinelearning;

import Exceptions.ParametersException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;

public class MachineLearning {
    
    public static void main(String[] args) throws Exception{
    
        ElasticClassifier iris = new ElasticClassifier(ElasticClassifier.Type.SUPPORT_VECTOR_MACHINE , "dataSets/iris");

        String cls = iris.classify(Arrays.asList(5.1,3.5,1.4,0.2));
        System.out.println("Class: " + cls);
        
        Serializer<ElasticClassifier> serializer = new Serializer<ElasticClassifier>();

        serializer.SerializeObject(iris, "iris.model");
    }
    
}
