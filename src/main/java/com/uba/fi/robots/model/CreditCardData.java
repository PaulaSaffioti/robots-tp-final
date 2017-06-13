package com.uba.fi.robots.model;

import java.util.HashMap;
import java.util.Map;

public class CreditCardData {
    Map<String, Double> data = new HashMap<>();

    public CreditCardData(String[] row) {
        this.data.put("time", Double.parseDouble(row[0]));
        this.data.put("v1", Double.parseDouble(row[1]));
        this.data.put("v2", Double.parseDouble(row[2]));
        this.data.put("v3", Double.parseDouble(row[3]));
        this.data.put("v4", Double.parseDouble(row[4]));
        this.data.put("v5", Double.parseDouble(row[5]));
        this.data.put("v6", Double.parseDouble(row[6]));
        this.data.put("v7", Double.parseDouble(row[7]));
        this.data.put("v8", Double.parseDouble(row[8]));
        this.data.put("v9", Double.parseDouble(row[9]));
        this.data.put("v10", Double.parseDouble(row[10]));
        this.data.put("v11", Double.parseDouble(row[11]));
        this.data.put("v12", Double.parseDouble(row[12]));
        this.data.put("v13", Double.parseDouble(row[13]));
        this.data.put("v14", Double.parseDouble(row[14]));
        this.data.put("v15", Double.parseDouble(row[15]));
        this.data.put("v16", Double.parseDouble(row[16]));
        this.data.put("v17", Double.parseDouble(row[17]));
        this.data.put("v18", Double.parseDouble(row[18]));
        this.data.put("v19", Double.parseDouble(row[19]));
        this.data.put("v20", Double.parseDouble(row[20]));
        this.data.put("v21", Double.parseDouble(row[21]));
        this.data.put("v22", Double.parseDouble(row[22]));
        this.data.put("v23", Double.parseDouble(row[23]));
        this.data.put("v24", Double.parseDouble(row[24]));
        this.data.put("v25", Double.parseDouble(row[25]));
        this.data.put("v26", Double.parseDouble(row[26]));
        this.data.put("v27", Double.parseDouble(row[27]));
        this.data.put("v28", Double.parseDouble(row[28]));
        this.data.put("amount", Double.parseDouble(row[29]));
        this.data.put("class", Double.parseDouble(row[30].replaceAll("\"", "")));
    }
}
