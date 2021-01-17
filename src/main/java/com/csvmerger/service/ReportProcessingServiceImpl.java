package com.csvmerger.service;

import com.csvmerger.service.components.MarkParser;
import com.csvmerger.service.components.ReportsCreator;
import com.csvmerger.entity.Mark;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportProcessingServiceImpl implements ReportProcessingService {

    private final MarkParser markParser;
    private final ReportsCreator reportsCreator;

    public ReportProcessingServiceImpl(MarkParser markParser,
                                       ReportsCreator reportsCreator) {
        this.markParser = markParser;
        this.reportsCreator = reportsCreator;
    }

    @Override
    public void runReportProcessing(List<String> strings, String pathToReport) {
        List<Mark> availableMarks = markParser.createStreamOfMarksFrom(strings)
                .collect(Collectors.toList());

        List<String> markNames = List.of("mark01", "mark17", "mark23", "mark35",
                                        "markFV", "markFX", "markFT");

        reportsCreator.setPathToReport(pathToReport);

        reportsCreator.createReportSummarize(availableMarks);
        reportsCreator.createReportInputMarks(availableMarks, markNames);
        reportsCreator.createReportListOfValues(availableMarks);
    }
}
