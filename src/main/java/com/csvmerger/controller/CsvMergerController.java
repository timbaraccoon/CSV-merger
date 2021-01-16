package com.csvmerger.controller;

import com.csvmerger.components.MarkParser;
import com.csvmerger.entity.Mark;
import com.csvmerger.sevice.PathBringerService;
import com.csvmerger.sevice.ZipReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Stream;

@Controller
public class CsvMergerController {

    private final ApplicationContext context;
    private final PathBringerService pathBringer;
    private final ZipReaderService zipReaderService;
    private final MarkParser markParser;



    @Autowired
    public CsvMergerController(PathBringerService pathBringer,
                               ZipReaderService zipReaderService,
                               ApplicationContext context, MarkParser markParser) {
        this.pathBringer = pathBringer;
        this.zipReaderService = zipReaderService;
        this.context = context;
        this.markParser = markParser;
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

//        strings.stream().filter(x -> !x.startsWith("#"))
//                .forEach(System.out::println);

        strings.forEach(System.out::println);

        markParser.createMarksFrom(strings)
                .forEach(System.out::println);

        shutdownContext();

        // TODO сделать Entity и отчеты
        // TODO потом прибрать проект
    }


    private void shutdownContext() {
        ((ConfigurableApplicationContext) context).close();
    }
}
