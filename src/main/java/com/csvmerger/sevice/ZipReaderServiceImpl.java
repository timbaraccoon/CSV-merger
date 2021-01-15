package com.csvmerger.sevice;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

@Service
public class ZipReaderServiceImpl implements ZipReaderService {
    
    private List<String> stringsFromZip;

    public List<String> readStringsFrom(String pathToZip) {
        stringsFromZip = new ArrayList<>();
        addStringsToListFrom(pathToZip);

        return stringsFromZip;
    }

    private void addStringsToListFrom(String pathToZip) {
        try (ZipFile zipFile = new ZipFile(pathToZip)) {

            Enumeration<? extends ZipEntry> entries = zipFile.entries();

            while(entries.hasMoreElements()){
                ZipEntry entry = entries.nextElement();
                BufferedReader reader = new BufferedReader(new InputStreamReader(zipFile.getInputStream(entry)));

                while (reader.ready()) {
                    stringsFromZip.add(reader.readLine());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
