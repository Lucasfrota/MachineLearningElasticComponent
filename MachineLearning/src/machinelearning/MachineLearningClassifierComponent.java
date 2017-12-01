package machinelearning;

import Exceptions.ParametersException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.trees.J48;
import weka.core.Attribute;
import weka.core.Debug.Random;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;


public class MachineLearningClassifierComponent<T extends Classifier> implements Serializable{
    
    private final Class<T> cls;
    
    private String ARFF = "src/";
    
    private int numAttrib;
    private int classIndex = -1;
    
    private T technique;
    
    //constructor method
    MachineLearningClassifierComponent(Class<T> cls, String dataSetPath) {
        this.cls = cls;
        if(dataSetPath != null){
            ARFF += dataSetPath + ".arff";
        }
    }

    MachineLearningClassifierComponent(Class<T> cls, String dataSetPath, int classIndex) {
        this.cls = cls;
        this.classIndex = classIndex;
        if(dataSetPath != null){
            ARFF += dataSetPath + ".arff";
        }
    }
    
    
    //public methods
    public Instances getBase() throws Exception{
        Instances registros = null;
        
        try{
            
            ConverterUtils.DataSource dataSource = new ConverterUtils.DataSource(ARFF);
            registros = dataSource.getDataSet();            
            numAttrib = registros.numAttributes();
            if(classIndex == -1){
                classIndex = numAttrib - 1;
            }
            registros.setClassIndex(classIndex);
            
        }catch(Exception e){
            throw new ParametersException("the file " + ARFF + " could not be found");
        }
        
        return registros;
    }
    
    public void learn(Instances instances){
        try{
            
            technique = cls.newInstance();
            technique.buildClassifier(instances);
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public Instance createNewUnclassifiedInstance(List<Object> featuresList, Instances instances) throws Exception{
        Instance instance = new DenseInstance(numAttrib);
        instance.setDataset(instances);
        
        checkParametersTypes(featuresList, instances);
        checkParametersLength(featuresList.size());
        
        instance = setParametersListToInstance(featuresList, instance);
        
        return instance;
    }
    
    public String predict(Instance unclassifiedInstance){
        String classeInferida = null;
        
        try{
            
            int index = Double.valueOf(technique.classifyInstance(unclassifiedInstance)).intValue();
            
            Attribute atributoClasse = unclassifiedInstance.classAttribute();
            classeInferida = atributoClasse.value(index);
            
        }catch(Exception e){
            e.printStackTrace();
    
    
        }
        
        return classeInferida;
    }
    
    public double accuracy(Instances trainDataset){
        double correctlyClassified = 0;
        
        try{
            
            Evaluation evaluation = new Evaluation(trainDataset);
              
            evaluation.crossValidateModel(technique, trainDataset, 10, new Random());  
            
            correctlyClassified = evaluation.pctCorrect();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return correctlyClassified;
    }
    
    
    //private methods
    private void checkParametersTypes(List<Object> featuresList, Instances instances) throws ParametersException{
        
        int index = 0;
        
        for(int i = 0; i < featuresList.size() + 1; i++){
            if(i != classIndex){
                String classAux = featuresList.get(index).getClass().toString();

                switch (classAux){
                    case "class java.lang.String":
                        if(!instances.attribute(i).isNominal()){
                            throw new ParametersException("the type of attribute " + index + " is wrong.");
                        }
                        break;
                    case "class java.lang.Float":
                        if(!instances.attribute(i).isNumeric() ){
                            throw new ParametersException("the type of attribute " + index + " is wrong.");
                        }
                        break;
                    case "class java.lang.Integer":
                        if(!instances.attribute(i).isNumeric() ){
                            throw new ParametersException("the type of attribute " + index + " is wrong.");
                        }
                        break;
                }
                index++;
            }
        }
    }
    
    private void checkParametersLength(int featuresListSize) throws Exception{
        if(numAttrib - 1 != featuresListSize){
            throw new ParametersException("The length of the inputted vector must be " + (numAttrib - 1)  + ", currently it is " + featuresListSize);
        }
    }

    private Instance setParametersListToInstance(List<Object> featuresList, Instance instance){
        
        int index = 0;
        int indexObj = 0;

        try{
            
            for(int i = 0; i < numAttrib-1; i++){
                if(index != classIndex){
                    
                    String classAux = featuresList.get(indexObj).getClass().toString();

                    switch (classAux){
                        case "class java.lang.String":
                            instance.setValue(index, (String) featuresList.get(indexObj));
                            break;
                        case "class java.lang.Float":
                            instance.setValue(index, (float) featuresList.get(indexObj));
                            break;
                        case "class java.lang.Integer":
                            instance.setValue(index, (Integer) featuresList.get(indexObj));
                            break;
                    }
                    indexObj++;
                }
                index++;
            }
            
        }catch(Exception e){
            
        }
        
        return instance;
    }
}