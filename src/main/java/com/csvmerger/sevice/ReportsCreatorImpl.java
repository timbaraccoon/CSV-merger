package com.csvmerger.sevice;

import com.csvmerger.entity.Mark;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Component
public class ReportsCreatorImpl implements ReportsCreator {

    @Override
    public void createReport1(List<Mark> availableMarks) {
        Map<String, Integer> report = new TreeMap<>(String::compareToIgnoreCase);

        for(Mark mark : availableMarks) {
            report.merge(mark.getMarkName(), mark.getQuantity(), Integer::sum);
        }

        System.out.println("****");
        System.out.println(report);
    }

    @Override
    public void createReport2() {

    }

    @Override
    public void createReport3() {

    }
}
