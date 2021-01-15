package com.csvmerger.sevice;

import java.util.List;

public interface ZipReaderService {

    List<String> readStringsFrom(String pathToZip);

}
