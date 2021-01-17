package com.csvmerger.sevice;

import com.csvmerger.sevice.components.MarkParser;
import com.csvmerger.sevice.components.ReportsCreator;
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

        List<String> markNames = List.of("mark01", "mark11", "mark13", "markft");

        reportsCreator.setPathToReport(pathToReport);

        reportsCreator.createReportSummarize(availableMarks);
        reportsCreator.createReportInputMarks(availableMarks, markNames);
        reportsCreator.createReportListQuantity(availableMarks);
    }
}
