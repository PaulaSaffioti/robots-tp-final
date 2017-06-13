package com.uba.fi.robots.model;

import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static com.uba.fi.robots.model.Constants.*;

public class DataSetLoader {
    public DataSet loadFromFile(String fileName) {
        BufferedReader br = null;
        String line;
        DataSet dataSet = new DataSet(INPUT_ROWS.length, OUTPUT_ROWS.length);
        try {
            br = new BufferedReader(new FileReader(fileName));
            while ((line = br.readLine()) != null) {
                double[] input = new double[INPUT_ROWS.length];
                double[] output = new double[OUTPUT_ROWS.length];
                String[] row = line.split(CSV_DELIMITER);
                int index = 0;
                for (int i = 0; i < INPUT_ROWS.length; i++) {
                    input[i] = Double.parseDouble(row[index++]);
                }
                for (int i = 0; i < OUTPUT_ROWS.length; i++) {
                    output[i] = Double.parseDouble(row[index++]);
                }
                dataSet.addRow(new DataSetRow(input, output));
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
        return dataSet;
    }
}
