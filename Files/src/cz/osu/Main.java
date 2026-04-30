package cz.osu;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        URL numbersFile = Main.class.getClassLoader().getResource("numbers.csv");
        String path = URLDecoder.decode(numbersFile.getFile());
        System.out.println(numbersFile);
        File file = new File(path);
        System.out.println(file.exists());


        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String line = br.readLine();

            if (line != null) {
                while (line != null) {
                    System.out.println(line);
                    line = br.readLine();
                }
            } else System.out.printf("Soubor %s je prázdný", file.getName());


        } catch (FileNotFoundException e) {
            System.out.println("Soubor " + file.getName() + "neexistuje");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        URL jsonUrl = Main.class.getClassLoader().getResource("input.json");
        String inputPathString = URLDecoder.decode(jsonUrl.getFile());

        try {
            String wholeInputFileString = Files.readString(Paths.get(inputPathString));
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Item>>(){}.getType();
            ArrayList<Item> items = gson.fromJson(wholeInputFileString, listType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
