package com.uba.fi.robots.model;

import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.uba.fi.robots.model.Constants.*;

public class CreditCardDataLoader {
    private List<CreditCardData> frauds;
    private List<CreditCardData> notFrauds;
    private List<CreditCardData> all;

    public void loadFromFile(String fileName) {
        BufferedReader br = null;
        String line;
        this.all = new ArrayList<>();
        this.frauds = new ArrayList<>();
        this.notFrauds = new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader(fileName));
            while ((line = br.readLine()) != null) {
                if (line.startsWith("\"Time\"")) {
                    continue;
                }
                String[] row = line.split(CSV_DELIMITER);
                if (row[30].equals("\"1\"")) {
                    CreditCardData creditCardData = new CreditCardData(row);
                    this.frauds.add(creditCardData);
                } else if (this.notFrauds.size() < (TOTAL_FILES / 2)) {
                    CreditCardData creditCardData = new CreditCardData(row);
                    this.notFrauds.add(creditCardData);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        this.all.addAll(this.frauds);
        this.all.addAll(this.notFrauds);
        Collections.shuffle(this.all);
    }

    public DataSet getTrainingDataSet() {
        DataSet trainingSet = new DataSet(INPUT_ROWS.length, OUTPUT_ROWS.length);
        for (int i = 0; i < TRAINING_FILES; i++) {
            double[] input = this.loadInputRow(this.all.get(i));
            double[] output = this.loadOutputRow(this.all.get(i));
            trainingSet.addRow(new DataSetRow(input, output));
        }
        return trainingSet;
    }

    public DataSet getTestDataSet() {
        DataSet testSet = new DataSet(INPUT_ROWS.length, OUTPUT_ROWS.length);
        for (int i = TRAINING_FILES; i < TOTAL_FILES; i++) {
            double[] input = this.loadInputRow(this.all.get(i));
            double[] output = this.loadOutputRow(this.all.get(i));
            testSet.addRow(new DataSetRow(input, output));
        }
        return testSet;
    }

    private double[] loadInputRow(CreditCardData creditCardData) {
        double[] input = new double[INPUT_ROWS.length];
        int i = 0;
        for (String index : INPUT_ROWS) {
            input[i++] = creditCardData.data.get(index);
        }
        return input;
    }

    private double[] loadOutputRow(CreditCardData creditCardData) {
        double[] output = new double[OUTPUT_ROWS.length];
        int i = 0;
        for (String index : OUTPUT_ROWS) {
            output[i++] = creditCardData.data.get(index);
        }
        return output;
    }

    public void saveTrainingSetToFile() {
        String toSave = "";
        for (int i = 0; i < TRAINING_FILES; i++) {
            double[] input = this.loadInputRow(this.all.get(i));
            double[] output = this.loadOutputRow(this.all.get(i));
            for (int j = 0; j < INPUT_ROWS.length; j++) {
                toSave += input[j] + CSV_DELIMITER;
            }
            for (int j = 0; j < (OUTPUT_ROWS.length - 1); j++) {
                toSave += output[j] + CSV_DELIMITER;
            }
            toSave += output[(OUTPUT_ROWS.length - 1)];
            toSave += System.lineSeparator();
        }
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(TRAINING_DATASET));
            writer.write(toSave);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveTestSetToFile() {
        String toSave = "";
        for (int i = TRAINING_FILES; i < TOTAL_FILES; i++) {
            double[] input = this.loadInputRow(this.all.get(i));
            double[] output = this.loadOutputRow(this.all.get(i));
            for (int j = 0; j < INPUT_ROWS.length; j++) {
                toSave += input[j] + CSV_DELIMITER;
            }
            for (int j = 0; j < (OUTPUT_ROWS.length - 1); j++) {
                toSave += output[j] + CSV_DELIMITER;
            }
            toSave += output[(OUTPUT_ROWS.length - 1)];
            toSave += System.lineSeparator();
        }
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(TEST_DATASET));
            writer.write(toSave);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
