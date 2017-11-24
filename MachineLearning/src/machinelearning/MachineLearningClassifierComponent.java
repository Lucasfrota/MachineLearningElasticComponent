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
    
    private String ARFF = "src/dataSets/";
    
    private int numAttrib;
    private int classIndex;
    
    private T technique;

    MachineLearningClassifierComponent(Class<T> cls) {
        this.cls = cls;
        ARFF = "src/dataSets/HepatitisDataSet.arff";
    }
    
    MachineLearningClassifierComponent(Class<T> cls, String dataSet) {
        this.cls = cls;
        ARFF += dataSet + ".arff";
    }

    
    public Instances getBase(){
        Instances registros = null;
        
        try{
            
            ConverterUtils.DataSource dataSource = new ConverterUtils.DataSource(ARFF);
            registros = dataSource.getDataSet();            
            numAttrib = registros.numAttributes();
            classIndex = 0;
            registros.setClassIndex(classIndex);
            
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("O arquivo arff " + ARFF + " não existe...");
        }
        
        return registros;
    }
    
    public void learn(Instances registros){
        try{
            
            technique = cls.newInstance();
            technique.buildClassifier(registros);
            //System.out.println(technique);
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public Instance createNewRegister(List<Object> object, Instances instances) throws ParametersException{
        Instance registro = new DenseInstance(numAttrib);
        registro.setDataset(instances);
        
        int index = 0;
        int indexObj = 0;

        try{
            
            for(int i = 0; i < numAttrib-1; i++){
                if(index != classIndex){

                    String classAux = object.get(indexObj).getClass().toString();

                    switch (classAux){
                        case "class java.lang.String":
                            registro.setValue(index, (String) object.get(indexObj));
                            break;

                        case "class java.lang.Float":
                            registro.setValue(index, (float) object.get(indexObj));
                            break;

                        case "class java.lang.Integer":
                            registro.setValue(index, (Integer) object.get(indexObj));
                            break;

                        default:
                            System.out.println(index + " -d- " + object.get(i));
                            break;

                    }
                    indexObj++;
                }

                index++;

            }
        }catch(Exception e){
            throw new ParametersException("");
        }
        
        return registro;
    }
    
    public String prediction(Instance registroDesconhecido){
        String classeInferida = null;
        
        try{
            
            int index = Double.valueOf(technique.classifyInstance(registroDesconhecido)).intValue();
            
            Attribute atributoClasse = registroDesconhecido.classAttribute();
            classeInferida = atributoClasse.value(index);
            
        }catch(Exception e){
            e.printStackTrace();
    
    
        }
        
        return classeInferida;
    }
    
    public double accuracy(Instances trainDataset, Instances testDataset){
        double correctlyClassified = 0;
        
        try{
            
            Evaluation evaluation = new Evaluation(trainDataset);
              
            evaluation.crossValidateModel(technique, trainDataset, 10, new Random());
                
            //evaluation.evaluateModel(technique, testDataset);    
            
            correctlyClassified = evaluation.pctCorrect();
            //System.out.println(evaluation.toMatrixString());
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return correctlyClassified;
    }

}