package com.uba.fi.robots.main;

import com.uba.fi.robots.model.BpTrainer;
import com.uba.fi.robots.model.CreditCardDataLoader;
import com.uba.fi.robots.model.DataSetLoader;
import org.neuroph.core.data.DataSet;

import static com.uba.fi.robots.model.Constants.*;

public class FraudDetector {
    public static void main(String[] args) {
        //CreditCardDataLoader creditCardDataLoader = new CreditCardDataLoader();
        //creditCardDataLoader.loadFromFile(CREDIT_CARD_DATA_FILE);
        //DataSet trainingDataSet = creditCardDataLoader.getTrainingDataSet();
        //DataSet testDataSet = creditCardDataLoader.getTestDataSet();
        DataSetLoader dataSetLoader = new DataSetLoader();
        DataSet trainingDataSet = dataSetLoader.loadFromFile(TRAINING_DATASET);
        DataSet testDataSet = dataSetLoader.loadFromFile(TEST_DATASET);
        BpTrainer bpTrainer = new BpTrainer();
        bpTrainer.train(trainingDataSet, testDataSet);
    }
}
