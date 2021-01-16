package com.csvmerger.controller;

import com.csvmerger.components.MarkParser;
import com.csvmerger.entity.Mark;
import com.csvmerger.sevice.PathBringerService;
import com.csvmerger.sevice.ReportsCreator;
import com.csvmerger.sevice.ZipReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CsvMergerController {

    private final ApplicationContext context;
    private final PathBringerService pathBringer;
    private final ZipReaderService zipReaderService;
    private final MarkParser markParser;
    private final ReportsCreator reportsCreator;



    @Autowired
    public CsvMergerController(PathBringerService pathBringer,
                               ZipReaderService zipReaderService,
                               ApplicationContext context,
                               MarkParser markParser, ReportsCreator reportsCreator) {
        this.pathBringer = pathBringer;
        this.zipReaderService = zipReaderService;
        this.context = context;
        this.markParser = markParser;
        this.reportsCreator = reportsCreator;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runMergeCSV() {


//        READY PART
//        while (true) {
//            String path = pathBringer.getPath();
//            if (path.equals("exit")) {
//                break;
//            }
//
//            System.out.println(path);
//        }
//
//        shutdownContext();

        String pathToZip = "D:\\1\\source_archive.zip";
        List<String> strings = zipReaderService.readStringsFrom(pathToZip);

        strings.forEach(System.out::println);

        List<Mark> availableMarks = markParser.createStreamOfMarksFrom(strings)
                                        .collect(Collectors.toList());

        reportsCreator.createReport1(availableMarks);

        shutdownContext();

        // TODO сделать отчеты
        // TODO потом прибрать проект
        // TODO сделать тесты
    }


    private void shutdownContext() {
        ((ConfigurableApplicationContext) context).close();
    }
}
