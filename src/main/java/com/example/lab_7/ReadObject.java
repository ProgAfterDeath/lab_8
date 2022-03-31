package com.example.lab_7;

import java.io.*;
import java.util.ArrayList;

public class ReadObject {

    public ArrayList<House> readObject(File file) throws IOException {
        ArrayList<House> fileFlats = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String lines;
        ArrayList<String []> lineWords = new ArrayList<>();
        while((lines = br.readLine())!=null){
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
