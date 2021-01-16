package com.csvmerger.components;

import com.csvmerger.entity.Mark;

import java.util.List;
import java.util.stream.Stream;

public interface MarkParser {

    Stream<Mark> createMarksFrom(List<String> strings);
}
