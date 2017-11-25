package machinelearning;

import Exceptions.ParametersException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
        Class cls = getTechnique();
        mLC = createInstance(cls, dataSet);
    }
    
    ClassifierInterface(Type type, String dataSet, int classIndex){
        this.type = type;
        Class cls = getTechnique();
        mLC = createInstance(cls, dataSet, classIndex);
    }
    
    private <T extends Classifier> MachineLearningClassifierComponent createInstance(Class<T> cls, String dataSet){
        
        MachineLearningClassifierComponent<T> mLC;

        mLC = new MachineLearningClassifierComponent<T>(cls, dataSet);
        
        splitDataset(mLC.getBase(), 70);
        mLC.learn(instancesTrain);
        return mLC;
    }
    
    private <T extends Classifier> MachineLearningClassifierComponent createInstance(Class<T> cls, String dataSet, int classIndex){
        
        MachineLearningClassifierComponent<T> mLC;

        mLC = new MachineLearningClassifierComponent<T>(cls, dataSet, classIndex);
        
        splitDataset(mLC.getBase(), 70);
        mLC.learn(instancesTrain);
        return mLC;
    }
    
    public String classifier(List<Object> object) throws Exception{
        
        Instance newRegister = mLC.createNewRegister(object, instancesTrain);
        
        return mLC.prediction(newRegister);
    }
    
    public double accuracy(){
        return mLC.accuracy(instancesTrain, instancesTest);
    }
    
    private void splitDataset(Instances registros, int percent){
        int trainSize = (int) Math.round(registros.numInstances() * percent/100);
        int testSise = registros.numInstances() - trainSize;
        
        instancesTrain = new Instances(registros, 0, trainSize);
        instancesTest = new Instances(registros, trainSize, testSise);
    }
    
    private Class getTechnique(){
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
        
        return cls;
    }
    
}