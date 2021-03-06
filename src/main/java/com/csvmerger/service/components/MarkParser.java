package com.csvmerger.service.components;

import com.csvmerger.entity.Mark;

import java.util.List;
import java.util.stream.Stream;

public interface MarkParser {

    Stream<Mark> createStreamOfMarksFrom(List<String> strings);
}
