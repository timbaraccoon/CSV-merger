package com.csvmerger.components;

import com.csvmerger.entity.Mark;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class MarkParserImpl implements MarkParser {

    @Override
    public Stream<Mark> createMarksFrom(List<String> strings) {

        return strings.stream().filter(x -> !x.startsWith("#"))
                .map(str -> str.split(","))
                .map(s -> new Mark(s[0], Integer.parseInt(s[1])));
    }
}
