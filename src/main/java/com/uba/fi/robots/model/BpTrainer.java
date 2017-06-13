package com.uba.fi.robots.model;

import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.core.events.LearningEvent;
import org.neuroph.core.events.LearningEventListener;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.BackPropagation;

import static com.uba.fi.robots.model.Constants.*;

public class BpTrainer implements LearningEventListener {

    public void train(DataSet trainingDataSet, DataSet testDataSet) {
        MultiLayerPerceptron perceptron = new MultiLayerPerceptron(INPUT_ROWS.length, HIDDEN, OUTPUT_ROWS.length);

        BackPropagation backPropagation = new BackPropagation();
        backPropagation.addListener(this);
        backPropagation.setMaxIterations(MAX_ITERATIONS);
        backPropagation.setMaxError(MAX_ERROR);
        backPropagation.setLearningRate(LEARNING_RATE);
        perceptron.learn(trainingDataSet, backPropagation);

        int success = 0;
        int failure = 0;
        for(DataSetRow testSetRow : testDataSet) {
            perceptron.setInput(testSetRow.getInput());
            perceptron.calculate();
            double[] obtained = perceptron.getOutput();
            double[] expected = testSetRow.getDesiredOutput();
            int expectedAsInt = (int) expected[0];
            int obtainedAsInt = (obtained[0] > 0.5) ? 1 : 0;
            if (obtainedAsInt == expectedAsInt) {
                success++;
            } else {
                failure++;
            }

        }
        System.out.println("Casos favorables = " + (success * 100) / (success + failure) + "%");
    }

    @Override
    public void handleLearningEvent(LearningEvent event) {
        BackPropagation bp = (BackPropagation) event.getSource();
        if (event.getEventType().equals(LearningEvent.Type.LEARNING_STOPPED)) {
            double error = bp.getTotalNetworkError();
            System.out.println("Training completed in " + bp.getCurrentIteration() + " iterations, ");
            System.out.println("With total error: " + (error));
        } else {
            System.out.println("Iteration: " + bp.getCurrentIteration() + " | Network error: " + bp.getTotalNetworkError());
        }
    }
}
