package com.csvmerger.controller;

import com.csvmerger.sevice.PathBringerService;
import com.csvmerger.sevice.ReportProcessingService;
import com.csvmerger.sevice.ZipReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CsvMergerController {

    private final ApplicationContext context;
    private final PathBringerService pathBringer;
    private final ZipReaderService zipReaderService;
    private final ReportProcessingService processingService;

    @Autowired
    public CsvMergerController(ApplicationContext context,
                               PathBringerService pathBringer,
                               ZipReaderService zipReaderService,
                               ReportProcessingService processingService) {
        this.context = context;
        this.pathBringer = pathBringer;
        this.zipReaderService = zipReaderService;
        this.processingService = processingService;
    }


    @EventListener(ApplicationReadyEvent.class)
    public void runMergeCSV() {

        while (true) {
            String pathToZip = getPathWithMessage("Enter path to target folder " +
                                                  "for reports in format like " +
                                                  "\"D:\\target_folder\\\"");
            if (pathToZip.equals("exit")) {
                break;
            }
            String pathToReport = getPathWithMessage("\n****\nEnter path to zip source " +
                                                     "in format \"D:\\folder\\target.zip\"" +
                                                     "\nTo escape - enter \"exit\"");

            System.out.println("\n****\nStart new session\n\nInput Data:\n");

            List<String> strings = zipReaderService.readStringsFrom(pathToZip);
            strings.forEach(System.out::println);

            processingService.runReportProcessing(strings, pathToReport);

            System.out.println("\nSession close.\n****\n");
        }

        shutdownContext();
    }

    private String getPathWithMessage(String message) {
        System.out.println();
        return pathBringer.getPath();
    }

    private void shutdownContext() {
        ((ConfigurableApplicationContext) context).close();
    }
}
