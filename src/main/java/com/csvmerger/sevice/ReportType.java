package com.csvmerger.sevice;

public enum ReportType {
    MERGE_SUMMARIZE("report1",
            "Merge to: available mark - total quantity."),
    MERGE_INPUT_MARKS("report2",
            "Merge to: input mark - total quantity."),
    MERGE_TO_LIST("report3",
            "Merge to: available mark - list of quantities.");

    private final String fileName;
    private final String description;

    ReportType(String fileName, String description) {
        this.fileName = fileName;
        this.description = description;
    }

    public String fileName() {
        return fileName;
    }

    public String description() {
        return description;
    }

}
