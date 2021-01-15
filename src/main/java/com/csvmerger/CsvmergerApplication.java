package com.csvmerger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

@SpringBootApplication
public class CsvmergerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CsvmergerApplication.class, args);

//		String path = "C:\\Users\\Руслан\\IdeaProjects\\Test work projects\\" +
//				"ПермТехСпец\\csvmerger\\src\\main\\resources\\static\\source_archive.zip";
//
//		ZipInputStream zipStream = new ZipInputStream(channelSftp.get("Port_Increment_201405261400_2251.zip"));
//		zipStream.getNextEntry();
//
//		sc = new Scanner(zipStream);
//		while (sc.hasNextLine()) {
//			System.out.println(sc.nextLine());
//		}
//
//		try (ZipFile zipFile = new ZipFile(path)) {
//
//			Enumeration<? extends ZipEntry> entries = zipFile.entries();
//
//			while(entries.hasMoreElements()){
//				ZipEntry entry = entries.nextElement();
//
//				BufferedReader reader = Files.;
//
//
//				while (reader.ready()) {
//					System.out.println(reader.readLine());
//				}
//
////				List<String> strings = Files.readAllLines(entry);
////
////				strings.forEach(System.out::println);
//
//
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}


	}

}
