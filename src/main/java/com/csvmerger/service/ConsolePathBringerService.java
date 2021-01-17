package com.csvmerger.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class ConsolePathBringerService implements PathBringerService {

    @Override
    public String getPath() {
        String path = "";

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            path = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return path;
    }

}
