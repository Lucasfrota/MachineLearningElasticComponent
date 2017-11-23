package machinelearning;

public class MachineLearning {
    
    public static void main(String[] args) {
    
        ClassifierInterface cI = new ClassifierInterface(ClassifierInterface.Type.NAIVEBAYES, null);
        
        String clas = cI.classifier(40,"1","1","2","1","2","2","2","1","2","2","2","2",0.60f,62,166,4.0f,63,"1");
        String nameClass = "";
        
        if(clas.equals("1")){
            nameClass = "Die";
        }else{
            nameClass = "Live";
        }
        
        System.out.println("Class: " + nameClass);
        
        Serializer<ClassifierInterface> serializer = new Serializer<ClassifierInterface>();
        
        serializer.SerializeObject(cI, "object");
        
        ClassifierInterface c = serializer.DeserializeObject("object");
        
        System.out.println(c.classifier(40,"1","1","2","1","2","2","2","1","2","2","2","2",0.60f,62,166,4.0f,63,"1"));
        
    }
    
}
