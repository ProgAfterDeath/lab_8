package com.example.lab_8;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadObject {

    public List<House> readObject(File file) throws IOException {
        List<House> fileFlats = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String lines;

        List<String []> lineWords = new ArrayList<>();
        while((lines = br.readLine()) != null){
            String[] words = lines.split(", ");
            lineWords.add(words);
        }

        for(String[]words: lineWords){
            House flat = new House();
            flat.setId(Integer.parseInt(words[0]));
            flat.setFlat_number(Integer.parseInt(words[1]));
            flat.setArea(Double.parseDouble(words[2]));
            flat.setFloor_number(Integer.parseInt(words[3]));
            flat.setNumber_of_rooms(Integer.parseInt(words[4]));
            flat.setStreet_name(words[5]);
            fileFlats.add(flat);
        }
        br.close();
        return fileFlats;
    }

}
