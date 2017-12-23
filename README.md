# MachineLearningElasticComponent

This project aims to be an easy and reusable way to use supervised machine learning techniques.

# How to use

First of all you'll need to import weka API and MachineLearningElasticComponent API to your project, once those API are inside your project you can access all of its functionalities. some of those functionalities are listed below.

## classifications

To generate a knowledge model it is required to choose your machine learning technique then you'll have to have an .arff file and specify its path in the constructor method just as the index of the predictive class( in case of your index is in the last position you don't have to declare it ).

```
ElasticClassifier hepatitisClassifier = new ElasticClassifier(ElasticClassifier.Type.NAIVEBAYES, "HepatitisDataSet", 0);
```

Then if you want to classify a new register to obtain a prediction of its class you'll use the classifier method, its parameter is a List of Objects which has to contain the features of your new register

```
List<Object> features = Arrays.asList(40,"1","1","2","1","2","2","2","1","2","2","2","2",0.60f,62,166,4.0f,63,"1");
String hepatiteClass = hepatitisClassifier.classifier(features);
```

Now you have classified an unknown instance :) to know the given classification just print the result

```
System.out.println("Class hepatite dataSet: " + hepatiteClass);
```

# How it works

All the artificial intelligence techniques used in this project was provided by Weka's API version 3.8. More information about weka may be found in [their website](https://www.cs.waikato.ac.nz/ml/weka/)

# Information about the data

* [Machine learning repository](http://archive.ics.uci.edu/ml/datasets.html) - More dataSets like HepatitisDataSet
* [Used dataSet](http://archive.ics.uci.edu/ml/datasets/Hepatitis) - More information specifically about the used dataSet
* [data](http://archive.ics.uci.edu/ml/machine-learning-databases/hepatitis/hepatitis.data) - The data itself may be found here
* the data of "plantas" dataset was collected from my backyard garden :)
