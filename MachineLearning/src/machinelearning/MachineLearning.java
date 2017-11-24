package machinelearning;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MachineLearning {
    
    public static void main(String[] args) {
    
        ClassifierInterface cI = new ClassifierInterface(ClassifierInterface.Type.NAIVEBAYES, null);
        
        //List<Object> atributos = Arrays.asList(40,"1","1","2","1","2","2","2","1","2","2","2","2",0.60f,62,166,4.0f,63,"1");
        
        String clas = cI.classifier(Arrays.asList(40,"1","1","2","1","2","2","2","1","2","2","2","2",0.60f,62,166,4.0f,63,"1"));
        
        String nameClass = "";
        
        if(clas.equals("1")){
            nameClass = "Die";
        }else{
            nameClass = "Live";
        }
        
        System.out.println("Class: " + nameClass);
        
        
    }
    
}
