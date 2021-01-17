package com.csvmerger.sevice.components;

import com.csvmerger.entity.Mark;

import java.util.List;

public interface ReportsCreator {

    void setPathToReport(String path);

    void createReportSummarize(List<Mark> availableMarks);

    void createReportInputMarks(List<Mark> availableMarks, List<String> resultMarkNames);

    void createReportListQuantity(List<Mark> availableMarks);
}
