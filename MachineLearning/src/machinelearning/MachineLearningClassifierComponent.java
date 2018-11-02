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
import weka.core.converters.ConverterUtils.DataSource;


public class MachineLearningClassifierComponent<T extends Classifier> implements Serializable{
    
    private final Class<T> cls;
    
    private String ARFF = "";
    
    private int numAttrib;
    private int classIndex = -1;
    
    private double distribuition[] = null;
    
    private Evaluation evaluation;
    
    private T technique;
    
    private Instances instancesTrain;
    private Instances instancesTest;
    
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
            
            DataSource dataSource = new ConverterUtils.DataSource(ARFF);
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
            
            splitDataset(instances, 70);
            
            technique = cls.newInstance();
            technique.buildClassifier(instancesTrain);
            
            evaluation = new Evaluation(instancesTrain);//dataset);
            evaluation.evaluateModel(technique, instancesTest);
            //evaluation //.crossValidateModel(technique, instances, 10, new Random());
            
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
            
            this.distribuition = technique.distributionForInstance(unclassifiedInstance);
            
            Attribute atributoClasse = unclassifiedInstance.classAttribute();
            
            classeInferida = atributoClasse.value(index);
            
        }catch(Exception e){
            e.printStackTrace();
    
    
        }
        
        return classeInferida;
    }
    
    public double getDistibuition(int index){
        return this.distribuition[index];
        
    }
    
    public double accuracy(Instances dataset){
        
        double correctlyClassified = 0;
        
        try{
            correctlyClassified = evaluation.pctCorrect();
            System.out.println("\nRecall");
            
            System.out.println(evaluation.recall(0));
            System.out.println(evaluation.recall(1));
            System.out.println(evaluation.recall(2));
            System.out.println("\nprecision");
            System.out.println(evaluation.precision(0));
            System.out.println(evaluation.precision(1));
            System.out.println(evaluation.precision(2));
            System.out.println("\nF1 score");
            System.out.println( 2 * evaluation.precision(0) * evaluation.recall(0)/ (evaluation.precision(0) + evaluation.recall(0)) );
            System.out.println( 2 * evaluation.precision(1) * evaluation.recall(1)/ (evaluation.precision(1) + evaluation.recall(1)) );
            System.out.println( 2 * evaluation.precision(2) * evaluation.recall(2)/ (evaluation.precision(2) + evaluation.recall(2)) );
            System.out.println("");
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return correctlyClassified;
    }
    
    public String confusionMatrixString(String title){
        
        String matrix = "";
        
        try{
            matrix = evaluation.toMatrixString(title) ;
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return matrix;
    }
        
    
    //private methods
    private void checkParametersTypes(List<Object> featuresList, Instances instances) throws ParametersException{
        
        int index = 0;
        
        for(int i = 0; i < featuresList.size() + 1; i++){
            if(i != classIndex && !featuresList.get(index).equals("?")){
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
    
    private void splitDataset(Instances registros, int percent){
        int trainSize = (int) Math.round(registros.numInstances() * percent/100);
        int testSise = registros.numInstances() - trainSize;
        
        instancesTrain = new Instances(registros, 0, trainSize);
        instancesTest = new Instances(registros, trainSize, testSise);
    }
}
