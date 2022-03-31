package com.example.lab_7;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class WriteObject {

    public ArrayList<House> writeObject(File file, ArrayList<House> flats) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(file));
        for(int i = 0; i < flats.size(); i++) {
            pw.write(flats.get(i).getId() + ", ");
            pw.write(flats.get(i).getFlat_number() + ", ");
            pw.write(String.valueOf(flats.get(i).getArea()) + ", ");
            pw.write(flats.get(i).getFloor_number() + ", ");
            pw.write(flats.get(i).getNumber_of_rooms() + ", ");
            pw.write(flats.get(i).getStreet_name() + "\n");
        }
        pw.close();
        return flats;
    }

}
