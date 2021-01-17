package com.csvmerger.service;

import java.util.List;

public interface ReportProcessingService {

    void runReportProcessing(List<String> strings, String pathToReport);
}
