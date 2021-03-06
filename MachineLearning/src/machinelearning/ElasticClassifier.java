package machinelearning;

import Exceptions.ParametersException;
import java.io.Serializable;
import java.text.DecimalFormat;
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

public class ElasticClassifier implements Serializable{

    private Type type;
    private MachineLearningClassifierComponent mLC;
    private Instances dataSetInstances;
    
    public enum Type{
        DECISION_TREE, NAIVEBAYES, KNN, MULTILAYER_PERCEPTRON, SUPPORT_VECTOR_MACHINE;
    }
    
    public ElasticClassifier(Type type, String dataSet) throws Exception{
        this.type = type;
        Class cls = getTechnique();
        mLC = createInstance(cls, dataSet);
    }
    
    public ElasticClassifier(Type type, String dataSet, int classIndex) throws Exception{
        this.type = type;
        Class cls = getTechnique();
        mLC = createInstance(cls, dataSet, classIndex);
    }
    
    
    public String classify(List<Object> features) throws Exception{
        
        Instance newRegister = mLC.createNewUnclassifiedInstance(features, dataSetInstances);
        
        return mLC.predict(newRegister);
    }
    
    public double accuracy(){
        return mLC.accuracy(dataSetInstances);
    }
    
    public String confusionMatrix(String title){
        return mLC.confusionMatrixString(title);
    }
    
    public String confusionMatrix(){
        return mLC.confusionMatrixString("Confusion Matrix");
    }
    
    public String getReliability(int index){
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        return df.format(mLC.getDistibuition(index) * 100).toString();
        
    }
    
    public String getTechniqueType(){
        return type.toString();
    }
    
    
    //Private methods
    private <T extends Classifier> MachineLearningClassifierComponent createInstance(Class<T> cls, String dataSet) throws Exception{
        
        MachineLearningClassifierComponent<T> mLC;

        mLC = new MachineLearningClassifierComponent<T>(cls, dataSet);
        
        dataSetInstances = mLC.getBase();
        mLC.learn(dataSetInstances);
        return mLC;
    }
    
    private <T extends Classifier> MachineLearningClassifierComponent createInstance(Class<T> cls, String dataSet, int classIndex) throws Exception{
        
        MachineLearningClassifierComponent<T> mLC;

        mLC = new MachineLearningClassifierComponent<T>(cls, dataSet, classIndex);
                
        dataSetInstances = mLC.getBase();
        mLC.learn(dataSetInstances);
        return mLC;
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
