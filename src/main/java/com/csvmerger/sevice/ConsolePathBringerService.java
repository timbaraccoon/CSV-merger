package com.csvmerger.sevice;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class ConsolePathBringerService implements PathBringerService {

    @Override
    public String getPath() {
        String path = "";
        System.out.println("\n*************" +
                "\nEnter path to target zip source. " +
                "\nTo escape - enter \"exit\"");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            path = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return path;
    }

}
