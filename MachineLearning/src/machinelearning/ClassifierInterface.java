package machinelearning;

import java.io.Serializable;
import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.functions.SMO;
import weka.classifiers.lazy.IBk;
import weka.classifiers.trees.J48;
import weka.core.Instance;
import weka.core.Instances;

public class ClassifierInterface implements Serializable{

    private Type type;
    private MachineLearningClassifierComponent mLC;
    private Instances instancesTrain;
    private Instances instancesTest;
    
    public enum Type{
        DECISION_TREE, NAIVEBAYES, KNN, MULTILAYER_PERCEPTRON, SUPPORT_VECTOR_MACHINE;
    }
    
    ClassifierInterface(Type type, String dataSet){
        this.type = type;
        
        Class cls = null;
        
        switch(this.type){
            case DECISION_TREE:
                cls = J48.class;
                break;
                
            case NAIVEBAYES:
                cls = NaiveBayes.class;
                break;

            case KNN:
                cls = IBk.class;
                break;
                
            case MULTILAYER_PERCEPTRON:
                cls = MultilayerPerceptron.class;
                break;
                
            case SUPPORT_VECTOR_MACHINE:
                cls = SMO.class;
                break;
        }
        
        mLC = createInstance(cls, dataSet);
    }
    
    public String classifier(int AGE, String SEX, String STEROID,
            String ANTIVIRALS, String FATIGUE, String MALAISE, String ANOREXIA,
            String LIVER_BIG, String LIVER_FIRM, String SPLEEN_PALPABLE,
            String SPIDERS, String ASCITES, String VARICES, float BILIRUBIN, float ALK_PHOSPHATE,
            float SGOT, float ALBUMIN, float PROTIME, String HISTOLOGY){
        
        Instance newRegister = mLC.createNewRegister(AGE, SEX, STEROID,
                                ANTIVIRALS, FATIGUE, MALAISE, ANOREXIA,
                                LIVER_BIG, LIVER_FIRM, SPLEEN_PALPABLE,
                                SPIDERS, ASCITES, VARICES, BILIRUBIN,
                                ALK_PHOSPHATE, SGOT, ALBUMIN, PROTIME, HISTOLOGY, instancesTrain);
        
        return mLC.prediction(newRegister);
    }
    
    public double accuracy(){
        return mLC.accuracy(instancesTrain, instancesTest);
    }
    
    private <T extends Classifier> MachineLearningClassifierComponent createInstance(Class<T> cls, String data){
        
        MachineLearningClassifierComponent<T> mLC;

        if(data == null){
            mLC = new MachineLearningClassifierComponent<T>(cls);
        }else{
            mLC = new MachineLearningClassifierComponent<T>(cls, data);
        }
        
        splitDataset(mLC.getBase(), 70);
        mLC.learn(instancesTrain);
        return mLC;
    }
    
    public void splitDataset(Instances registros, int percent){
        int trainSize = (int) Math.round(registros.numInstances() * percent/100);
        int testSise = registros.numInstances() - trainSize;
        
        instancesTrain = new Instances(registros, 0, trainSize);
        instancesTest = new Instances(registros, trainSize, testSise);
    }
    
}