package com.csvmerger.sevice;

import com.csvmerger.entity.Mark;

import java.util.List;

public interface ReportsCreator {

    void createReport1(List<Mark> availableMarks);

    void createReport2();

    void createReport3();

}
