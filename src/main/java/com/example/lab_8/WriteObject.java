package com.example.lab_8;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class WriteObject {

    public List<House> writeObject(File file, List<House> flats) throws IOException {
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
