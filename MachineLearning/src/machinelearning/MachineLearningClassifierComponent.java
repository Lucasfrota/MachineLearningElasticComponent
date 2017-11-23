package machinelearning;

import java.io.Serializable;
import java.util.ArrayList;
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
    
    public Instance createNewRegister(List<Object> object, Instances instances){
        Instance registro = new DenseInstance(numAttrib);
        registro.setDataset(instances);
        
        for(int i = 0; i < numAttrib-1; i++){
            if(i != classIndex){
                String classAux = object.get(i).getClass().toString();
                
                System.out.println(classAux);
                
                switch (classAux){
                    case "class java.lang.String":
                        System.out.println(i + " - " + object.get(i));
                        registro.setValue(i, ((String) object.get(i)).toString() );
                        break;
                
                    case "class java.lang.Float":
                        System.out.println(i + " - " + object.get(i));
                        registro.setValue(i, (float) object.get(i));
                        break;
                
                    case "class java.lang.Integer":
                        System.out.println(i + " - " + object.get(i));
                        registro.setValue(i, (Integer) object.get(i));
                        break;
                
                    default:
                        System.out.println(i + " -d- " + object.get(i));
                        break;
                }
                
                //registro.setValue(i,  classAux object.get(i) );
                
            }
        }
        
        /*
        registro.setValue(1, object.get(1).getClass().cast(object.get(1)) );
        registro.setValue(2, SEX);
        registro.setValue(3, STEROID);
        registro.setValue(4, ANTIVIRALS);
        registro.setValue(5, FATIGUE);
        registro.setValue(6, MALAISE);
        registro.setValue(7, ANOREXIA);
        registro.setValue(8, LIVER_BIG);
        registro.setValue(9, LIVER_FIRM);
        registro.setValue(10, SPLEEN_PALPABLE);
        registro.setValue(11, SPIDERS);
        registro.setValue(12, ASCITES);
        registro.setValue(13, VARICES);
        registro.setValue(14, BILIRUBIN);
        registro.setValue(15, ALK_PHOSPHATE);
        registro.setValue(16, SGOT);
        registro.setValue(17, ALBUMIN);
        registro.setValue(18, PROTIME);
        registro.setValue(19, HISTOLOGY);
        */
        
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