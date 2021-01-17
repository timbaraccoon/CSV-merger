package com.csvmerger.controller;

import com.csvmerger.service.PathBringerService;
import com.csvmerger.service.ReportProcessingService;
import com.csvmerger.service.ZipReaderService;
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
    private final ZipReaderService zipReader;
    private final ReportProcessingService processingService;

    @Autowired
    public CsvMergerController(ApplicationContext context,
                               PathBringerService pathBringer,
                               ZipReaderService zipReader,
                               ReportProcessingService processingService) {
        this.context = context;
        this.pathBringer = pathBringer;
        this.zipReader = zipReader;
        this.processingService = processingService;
    }


    @EventListener(ApplicationReadyEvent.class)
    public void runMergeCSV() {
        while (true) {
            String pathToZip = getPathWithMessage("\n****\nEnter path to zip source " +
                                                  "in format \"D:\\folder\\target.zip\"" +
                                                  "\nTo escape - enter \"exit\"");
            if (pathToZip.equals("exit")) {
                break;
            }
            String pathToReport = getPathWithMessage("Enter path to target folder " +
                                                     "for reports in format like " +
                                                     "\"D:\\target_folder\\\"");
            System.out.println("\n****\nStart new session\n\nInput Data:\n");

            List<String> strings = zipReader.readStringsFrom(pathToZip);
            strings.forEach(System.out::println);
            processingService.runReportProcessing(strings, pathToReport);

            System.out.println("\nSession close.\n****\n");
        }
        shutdownContext();
    }

    private String getPathWithMessage(String message) {
        System.out.println(message);
        return pathBringer.getPath();
    }

    private void shutdownContext() {
        ((ConfigurableApplicationContext) context).close();
    }
}
