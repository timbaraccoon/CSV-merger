package com.csvmerger.sevice;

import java.util.List;

public interface ReportProcessingService {

    void runReportProcessing(List<String> strings, String pathToReport);
}
