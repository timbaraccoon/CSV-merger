package com.csvmerger.sevice;

import com.csvmerger.entity.Mark;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Component
public class ReportsCreatorImpl implements ReportsCreator {

    private String pathToReport;

    public ReportsCreatorImpl() {
        this.pathToReport = "";
    }

    public void setPathToReport(String path) {
        this.pathToReport = path;
    }

    @Override
    public void createReport1(List<Mark> availableMarks) {
        Map<String, Integer> report = new TreeMap<>(String::compareToIgnoreCase);

        for(Mark mark : availableMarks) {
            report.merge(mark.getMarkName(), mark.getQuantity(), Integer::sum);
        }

        System.out.println("\n****");
        System.out.println("Created JSON report1: ");

        String json = new Gson().toJson(report);

        System.out.println(json);
        System.out.println("\nSaving report1 to: " + pathToReport);

        try (FileWriter file = new FileWriter(pathToReport + "report1.json", false)) {
            file.write(json);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Successfully saved.\n");
    }

    @Override
    public void createReport2() {

    }

    @Override
    public void createReport3() {

    }
}
