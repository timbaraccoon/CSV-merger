package com.csvmerger.controller;

import com.csvmerger.sevice.PathBringerService;
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



    @Autowired
    public CsvMergerController(PathBringerService pathBringer,
                               ZipReaderService zipReaderService,
                               ApplicationContext context) {
        this.pathBringer = pathBringer;
        this.zipReaderService = zipReaderService;
        this.context = context;
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

        shutdownContext();
    }


    private void shutdownContext() {
        ((ConfigurableApplicationContext) context).close();
    }
}
