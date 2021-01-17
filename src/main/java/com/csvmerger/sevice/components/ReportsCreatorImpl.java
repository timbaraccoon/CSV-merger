package com.csvmerger.sevice.components;

import com.csvmerger.entity.Mark;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

@Component
public class ReportsCreatorImpl implements ReportsCreator {

    private String pathToReport;

    public ReportsCreatorImpl() {
        this.pathToReport = "";
    }


    public void createReportSummarize(List<Mark> availableMarks) {
        Map<String, Integer> report = new TreeMap<>(
                                String::compareToIgnoreCase);

        availableMarks.forEach(mark -> report.merge(mark.getName(),
                                                    mark.getQuantity(),
                                                    Integer::sum));

        String json = new Gson().toJson(report);
        writeJsonReportToFile(ReportType.MERGE_SUMMARIZE, json);
    }

    public void createReportInputMarks(List<Mark> availableMarks, List<String> resultMarkNames) {
        Map<String, Integer> report = new TreeMap<>(
                                Comparator.nullsFirst(String::compareToIgnoreCase));

        resultMarkNames.forEach(markName -> report.put(markName, null));

        availableMarks.stream()
                .filter(mark -> resultMarkNames.stream().anyMatch(mark.getName()::equalsIgnoreCase))
                .forEach(mark -> report.merge(mark.getName(),
                                              mark.getQuantity(),
                                              Integer::sum));


        Gson gson = new GsonBuilder().serializeNulls().create();
        String json = gson.toJson(report);
        writeJsonReportToFile(ReportType.MERGE_INPUT_MARKS, json);
    }


    public void createReportListQuantity(List<Mark> availableMarks) {
        Map<String, List<Integer>> report = new TreeMap<>(
                                        String::compareToIgnoreCase);

        availableMarks.forEach(mark -> {
            if (!report.containsKey(mark.getName())) {
                List<Integer> valueList = new ArrayList<>();
                valueList.add(mark.getQuantity());

                report.put(mark.getName(), valueList);

            } else {
                report.get(mark.getName())
                        .add(mark.getQuantity());
            }
        });

        report.keySet()
                .forEach(markName -> report.get(markName)
                        .sort(Comparator.reverseOrder()));

        String json = new Gson().toJson(report);
        writeJsonReportToFile(ReportType.MERGE_TO_LIST, json);

    }

    private void writeJsonReportToFile(ReportType reportType, String json) {
        String fileName = reportType.fileName();
        System.out.println("\n****\nCreated JSON " + fileName
                            + ":\n" + reportType.description());
        System.out.println(json);
        System.out.println("\nSaving " + fileName + " to: " + pathToReport);

        try (FileWriter file = new FileWriter(pathToReport + fileName + ".json")) {
            file.write(json);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Successfully saved.");
    }

    public void setPathToReport(String path) {
        this.pathToReport = path;
    }
}
