package com.csvmerger.service;

import java.util.List;

public interface ZipReaderService {

    List<String> readStringsFrom(String pathToZip);

}
