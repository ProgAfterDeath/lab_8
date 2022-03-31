package com.example.lab_8;

import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Show {
    // метод, отображающий коллекцию в списке
    public void showHouseList(List<House> flats, ListView<House> list_view){
        list_view.getItems().clear();
        list_view.getItems().addAll(flats);
    }
    // метод, очищающий все поля из "House Editor"
    public void clearHouseEditor(TextField flat_number, TextField area, TextField floor_number,
                                 TextField number_of_rooms, TextField street_name){
        flat_number.clear();
        area.clear();
        floor_number.clear();
        number_of_rooms.clear();
        street_name.clear();
    }
    // метод, очищающий все поля из "House Searching"
    public void clearHouseSearch(TextField number_of_rooms_search, TextField floor_number_low,
                                 TextField floor_number_high, TextField area_min){
        number_of_rooms_search.clear();
        floor_number_low.clear();
        floor_number_high.clear();
        area_min.clear();
    }
    // метод, отображающий перечень квартир на каждом этаже
    public void showFloorNumberList(Map<Integer, List<House>> map, ListView<String> list_view){
        list_view.getItems().clear();
        for(Integer key: map.keySet()){
            list_view.getItems().add("Этаж № "+ key + ", Квартиры на этаже: " + map.get(key)
                    .stream().map(House::getFlat_number).sorted().collect(Collectors.toList()));
        }
    }

}
