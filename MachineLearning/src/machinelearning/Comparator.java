package machinelearning;


public class Comparator {
    
    public ClassifierInterface.Type bestType(String dataSet){
        
        ClassifierInterface.Type bestType = null;
        double bestAccuracy = 0;
        
        for(ClassifierInterface.Type type : ClassifierInterface.Type.values()){
            ClassifierInterface cI = new ClassifierInterface(type, dataSet);
        
            double accuracy = cI.accuracy();
            
            if(accuracy >= bestAccuracy){
                bestAccuracy = accuracy;
                bestType = type;
            }
            
            System.out.println(type + ": " + accuracy);
        }
        
        return bestType;
    }
    
    public void dataSetComparator(String dataSet){
        String cabecalho = "====" + dataSet + "====";
        System.out.println(cabecalho);
        bestType(dataSet);
        for (int i = 0; i < cabecalho.length(); i++) {
            System.out.print("=");
        }
        System.out.println("\n");
    }
    
    public void iteraionsClassifier(String dataSet, int numIt){        
        String cabecalho = "====" + dataSet + "====";
        System.out.println(cabecalho);
        
        for(ClassifierInterface.Type type : ClassifierInterface.Type.values()){
            
            double acumuladorMedia = 0;
                    
            for(int i = 0; i < numIt; i++){
                
                ClassifierInterface cI = new ClassifierInterface(type, dataSet);
                acumuladorMedia += cI.accuracy();
                
            }
            
            System.out.println(type + ": " + acumuladorMedia/numIt);
            
        }
        
        for (int i = 0; i < cabecalho.length(); i++) {
            System.out.print("=");
        }
        System.out.println("\n");
    }
    
}
