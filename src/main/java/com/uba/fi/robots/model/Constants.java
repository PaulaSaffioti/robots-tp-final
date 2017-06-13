package com.uba.fi.robots.model;

public final class Constants {

    private Constants() {}

    public static final String CREDIT_CARD_DATA_FILE = "./src/main/resources/creditcard.csv";
    public static final String TRAINING_DATASET = "./src/main/resources/training.csv";
    public static final String TEST_DATASET = "./src/main/resources/test.csv";
    public static final String CSV_DELIMITER = ",";

    public static final Integer MAX_ITERATIONS = 5000;
    public static final double MAX_ERROR = 0.01;
    public static final double LEARNING_RATE = 0.1;

    public static final String[] INPUT_ROWS = {
            "v1",  "v2",  "v3",  "v4",  "v5",  "v6",  "v7",  "v8",  "v9",  "v10",
            "v11", "v12", "v13", "v14", "v15", "v16", "v17", "v18", "v19", "v20",
            "v21", "v22", "v23", "v24", "v25", "v26", "v27", "v28"
            // "amount"
    };
    public static final Integer HIDDEN = 20;
    public static final String[] OUTPUT_ROWS = {"class"};

    public static final Integer TRAINING_FILES = 656;
    public static final Integer TOTAL_FILES = 984;

}