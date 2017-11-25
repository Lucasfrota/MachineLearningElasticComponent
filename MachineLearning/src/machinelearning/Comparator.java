package machinelearning;


public class Comparator {
    
    private String dataSet = "";
    private int classIndex = -1;

    public Comparator(String dataSet) {
        this.dataSet = dataSet;
    }
    
    public Comparator(String dataSet, int classIndex){
        this.dataSet = dataSet;
        this.classIndex = classIndex;
    }
    
    public ClassifierInterface.Type bestType(){
        ClassifierInterface.Type bestType = null;
        
        if(classIndex != -1){
            
            double bestAccuracy = 0;

            for(ClassifierInterface.Type type : ClassifierInterface.Type.values()){
                ClassifierInterface cI = new ClassifierInterface(type, dataSet);

                double accuracy = cI.accuracy();

                if(accuracy >= bestAccuracy){
                    bestAccuracy = accuracy;
                    bestType = type;
                }
            }

            return bestType;
            
        }else{
            double bestAccuracy = 0;

            for(ClassifierInterface.Type type : ClassifierInterface.Type.values()){
                ClassifierInterface cI = new ClassifierInterface(type, dataSet, classIndex);

                double accuracy = cI.accuracy();

                if(accuracy >= bestAccuracy){
                    bestAccuracy = accuracy;
                    bestType = type;
                }
            }
        }
        return bestType;
    }
    
    public void printBestType(){
        String cabecalho = "====" + dataSet + "====";
        System.out.println(cabecalho);
        innerBestType();
        for (int i = 0; i < cabecalho.length(); i++) {
            System.out.print("=");
        }
        System.out.println("\n");
    }
    
    public void printBestTypePerIteration(int numIt){
        if(classIndex != -1){
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
        }else{
            String cabecalho = "====" + dataSet + "====";
            System.out.println(cabecalho);

            for(ClassifierInterface.Type type : ClassifierInterface.Type.values()){

                double acumuladorMedia = 0;

                for(int i = 0; i < numIt; i++){ 
                    ClassifierInterface cI = new ClassifierInterface(type, dataSet, classIndex);
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
    
    private void innerBestType(){
        ClassifierInterface.Type bestType = null;
        
        if(classIndex != -1){
            
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
            
        }else{
            double bestAccuracy = 0;

            for(ClassifierInterface.Type type : ClassifierInterface.Type.values()){
                ClassifierInterface cI = new ClassifierInterface(type, dataSet, classIndex);

                double accuracy = cI.accuracy();

                if(accuracy >= bestAccuracy){
                    bestAccuracy = accuracy;
                    bestType = type;
                }

                System.out.println(type + ": " + accuracy);
            }
        }
    }
}
