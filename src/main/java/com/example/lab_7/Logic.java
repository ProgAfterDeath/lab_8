package com.example.lab_7;

import java.util.*;

public class Logic {
    // метод, принимающий и устанавливающий все значения атрибутов нового элемента
    public House addHouse(int id, int flat_number, double area, int floor_number, int number_of_rooms,
                          String street_name){
        House flat = new House();
        flat.setId(id);
        flat.setFlat_number(flat_number);
        flat.setArea(area);
        flat.setFloor_number(floor_number);
        flat.setNumber_of_rooms(number_of_rooms);
        flat.setStreet_name(street_name);
        return flat;
    }
    // метод, определяющий id для добавляемого элемента
    public int countId(ArrayList<House> flats){
        int id;
        if (flats.size() != 0){
            id = Integer.parseInt(String.valueOf(flats.get(flats.size()-1).getId())) + 1;
        }
        else{
            id = 1;
        }
        return id;
    }
    // метод, фильтрующий элементы коллекции согласно количеству комнат
    public ArrayList<House> housesWithNumOfRooms(ArrayList<House> flats, int number_of_rooms_search){
        ArrayList<House> housesWithNumOfRooms = new ArrayList<>();
        for (House flat: flats){
            if (flat.getNumber_of_rooms() == number_of_rooms_search){
                housesWithNumOfRooms.add(flat);
            }
        }
        return housesWithNumOfRooms;
    }
    // метод, фильтрующий элементы коллекции согласно диапазону в котором находится искомый этаж
    public ArrayList<House> housesWithFloorNumInRange(ArrayList<House> flats,
                                                      int floor_number_low, int floor_number_high){
        ArrayList<House> housesWithFloorNumInRange = new ArrayList<>();
        for (House flat:flats){
            if(flat.getFloor_number() >= floor_number_low && flat.getFloor_number() <= floor_number_high){
                housesWithFloorNumInRange.add(flat);
            }
        }
        return housesWithFloorNumInRange;
    }
    // метод, фильтрующий элементы коллекции согласно минимальному размеру площади
    public ArrayList<House> housesWithMinArea(ArrayList<House> flats, double area_min){
        ArrayList<House> housesWithMinArea = new ArrayList<>();
        for (House flat:flats){
            if(flat.getArea() > area_min){
                housesWithMinArea.add(flat);
            }
        }
        return housesWithMinArea;
    }
    // метод, фильтрующий элементы коллекции согласно количество комнат и диапазону в котором находится искомый этаж
    public ArrayList<House> housesWithNumOfRoomsAndFloorNumInRange(ArrayList<House> flats,
                                                                   int number_of_rooms_search,
                                                                   int floor_number_low, int floor_number_high){
        ArrayList<House> temp = new ArrayList<>();
        for (House flat:flats){
            if(flat.getNumber_of_rooms() == number_of_rooms_search &&
                    flat.getFloor_number() >= floor_number_low &&
                    flat.getFloor_number() <= floor_number_high){
                temp.add(flat);
            }
        }
        return temp;
    }
    // метод, фильтрующий элементы коллекции согласно количеству комнат и минимальному размеру площади
    public ArrayList<House> housesWithNumOfRoomsAndMinArea(ArrayList<House> flats, int number_of_rooms_search,
                                                    double area_min){
        ArrayList<House> temp = new ArrayList<>();
        for (House flat:flats){
            if(flat.getNumber_of_rooms() == number_of_rooms_search &&
                    flat.getArea() > area_min){
                temp.add(flat);
            }
        }
        return temp;
    }
    // метод, фильтрующий элементы коллекции согласно диапазону в котором находится искомый этаж и минимальному размеру площади
    public ArrayList<House> housesWithFloorNumInRangeAndMinArea(ArrayList<House> flats,
                                                    int floor_number_low, int floor_number_high,
                                                    double area_min){
        ArrayList<House> temp = new ArrayList<>();
        for (House flat:flats){
            if(flat.getFloor_number() >= floor_number_low &&
                    flat.getFloor_number() <= floor_number_high &&
                    flat.getArea() > area_min){
                temp.add(flat);
            }
        }
        return temp;
    }
    // метод, фильтрующий элементы коллекции согласно всем параметрам их поиска
    public ArrayList<House> housesWithAllParameters(ArrayList<House> flats, int number_of_rooms_search,
                                                    int floor_number_low, int floor_number_high,
                                                    double area_min){
        ArrayList<House> temp = new ArrayList<>();
        for (House flat:flats){
            if(flat.getNumber_of_rooms() == number_of_rooms_search &&
                    flat.getFloor_number() >= floor_number_low &&
                    flat.getFloor_number() <= floor_number_high &&
                    flat.getArea() > area_min){
                temp.add(flat);
            }
        }
        return temp;
    }
    // метод, сортирующий элементы коллекции по возрастанию id
    public ArrayList<House> sortedByIdAsc(ArrayList<House> flats){
        // инициализируем карту, которая будет сортировать содержащиеся в ней ключи в порядке возрастания
        TreeMap<Integer, Integer> mapId = new TreeMap<>();
        ArrayList<House> temp = new ArrayList<>();
        for (House flat:flats){
            mapId.put(flat.getId(), flats.size());
        }
        for (Integer key: mapId.keySet()){
            for (House flat:flats) {
                if (key.equals(flat.getId())){
                    temp.add(flat);
                }
            }
        }
        return temp;
    }
    // метод, сортирующий элементы коллекции по убыванию id
    public ArrayList<House> sortedByIdDesc(ArrayList<House> flats){
        // инициализируем карту, которая будет сортировать содержащиеся в ней ключи в порядке убывания
        TreeMap<Integer, Integer> mapId = new TreeMap<>(Collections.reverseOrder());
        ArrayList<House> temp = new ArrayList<>();
        for (House flat:flats){
            mapId.put(flat.getId(), flats.size());
        }
        for (Integer key: mapId.keySet()){
            for (House flat:flats) {
                if (key.equals(flat.getId())){
                    temp.add(flat);
                }
            }
        }
        return temp;
    }
    // метод, сортирующий элементы коллекции по возрастанию номеров квартир
    public ArrayList<House> sortedByFloorNumberAsc(ArrayList<House> flats) {
        // инициализируем карту, которая будет сортировать содержащиеся в ней ключи в порядке возрастания
        TreeMap<Integer, Integer> mapId = new TreeMap<>();
        ArrayList<House> temp = new ArrayList<>();
        for (House flat:flats){
            mapId.put(flat.getFloor_number(), flats.size());
        }
        for (Integer key: mapId.keySet()){
            for (House flat:flats) {
                if (key.equals(flat.getFloor_number())){
                    temp.add(flat);
                }
            }
        }
        return temp;
    }
    // метод, сортирующий элементы коллекции по убыванию номеров квартир
    public ArrayList<House> sortedByFloorNumberDesc(ArrayList<House> flats) {
        // инициализируем карту, которая будет сортировать содержащиеся в ней ключи в порядке убывания
        TreeMap<Integer, Integer> mapId = new TreeMap<>(Collections.reverseOrder());
        ArrayList<House> temp = new ArrayList<>();
        for (House flat:flats){
            mapId.put(flat.getFloor_number(), flats.size());
        }
        for (Integer key: mapId.keySet()){
            for (House flat:flats) {
                if (key.equals(flat.getFloor_number())){
                    temp.add(flat);
                }
            }
        }
        return temp;
    }

    // метод, сортирующий элементы коллекции по возрастанию размера площади
    public ArrayList<House> sortedByAreaAsc(ArrayList<House> flats){
        // инициализируем карту, которая будет сортировать содержащиеся в ней ключи в порядке возрастания
        TreeMap<Double, Integer> mapArea = new TreeMap<>();
        ArrayList<House> temp = new ArrayList<>(); // инициализируем "временную" коллекцию
        for (House flat:flats){
            mapArea.put(flat.getArea(), flats.size());
        }
        for (Double key: mapArea.keySet()){
            for (House flat:flats) {
                if (key.equals(flat.getArea())){
                    temp.add(flat);
                }
            }
        }
        return temp;
    }
    // метод, сортирующий элементы коллекции по убыванию размера площади; если площадь одинаковая – по возрастанию номера этажа
    public ArrayList<House> sortedByAreaDesc(ArrayList<House> flats){
        // инициализируем карту, которая будет сортировать содержащиеся в ней ключи в порядке убывания
        TreeMap<Double, ArrayList<Integer>> mapArea = new TreeMap<>(Collections.reverseOrder());
        for(House flat: flats){                          // перебираем поэлементно основную коллекцию "домов"
            if(mapArea.containsKey(flat.getArea())){     // если карта уже содержит в себе ключ с данным значением, то:
                ArrayList<Integer> listOfFloorNumbers = new ArrayList<>();  // инициализируем коллекцию c номерами этажей
                for(Double key: mapArea.keySet()){           // перебираем ключи частично заполненной карты
                    if(key.equals(flat.getArea())){  // если значение ключа совпадает со значением площади квартиры, то:
                        listOfFloorNumbers = mapArea.get(key); // заполняем созданную коллекцию значениями соответствующих ключей
                    }
                }
                if(!listOfFloorNumbers.contains(flat.getFloor_number())) { // если коллекция ещё не содержит данного
                    listOfFloorNumbers.add(flat.getFloor_number());        // номера этажа – добавляем его
                }
                listOfFloorNumbers = valueSortingAsc(listOfFloorNumbers);  // сортируем элементы коллекции по возрастанию
                mapArea.put(flat.getArea(), listOfFloorNumbers);           // заполняем карту
            }
            else{                                                          // иначе:
                ArrayList<Integer> listOfFloorNumbers = new ArrayList<>(); // инициализируем коллекцию c номерами этажей
                listOfFloorNumbers.add(flat.getFloor_number());            // заполняем коллекцию соответствующими элементами
                mapArea.put(flat.getArea(), listOfFloorNumbers);           // заполняем карту
            }
        }
        ArrayList<House> tempMain = new ArrayList<>();  // инициализируем коллекцию для отсортированных элементов
        for(Double key: mapArea.keySet()){              // перебираем ключи карты
            for(Integer i: mapArea.get(key)) {          // перебираем значения принадлежащие ключам карты
                for (House flat : flats) {              // перебираем элементы(квартиры) основной коллекции
                    if (key.equals(flat.getArea())) {   // если ключ карты равен атрибуту (площади) элемента основной коллекции
                        if (i.equals(flat.getFloor_number())) {  // если значение принадлежащее ключу равно атрибуту (номеру этажа) элемента основной коллекции, то
                            tempMain.add(flat);         // (номеру этажа) элемента основной коллекции, то: добавляем этот
                        }                               // элемент в коллекцию для отсортированных элементов
                    }
                }
            }
        }
        return tempMain;
    }
    // метод, сортирующий значения коллекции по возрастанию
    public ArrayList<Integer> valueSortingAsc(ArrayList<Integer> listOfFloorNumbers){
        // инициализируем карту, которая будет сортировать содержащиеся в ней ключи в порядке возрастания
        TreeMap<Integer, Integer> valueMap = new TreeMap<>();
        ArrayList<Integer> sortedList = new ArrayList<>();  // инициализируем коллекцию для отсортированных элементов
        for (Integer value: listOfFloorNumbers){            // перебираем коллекцию "номеров этажей"
            valueMap.put(value, listOfFloorNumbers.size()); // записываем все элементы коллекции в карту
        }
        for(Integer key: valueMap.keySet()) {       // перебираем ключи карты
            for(Integer value: listOfFloorNumbers)  // перебираем коллекцию "номеров этажей"
            if(key == value){                       // если ключ равен значению элемента коллекции, то:
                sortedList.add(value);              // добавляем этот элемент в коллекцию для отсортированных элементов
            }
        }
        return sortedList;
    }
    // метод, создающий карту с перечнем квартир на каждом этаже
    public TreeMap<Integer, ArrayList<Integer>> listOfFlatsOnFloors(ArrayList<House> flats){
        // инициализируем карту, которая будет сортировать содержащиеся в ней ключи в порядке убывания
        TreeMap<Integer, ArrayList<Integer>> mapFN = new TreeMap<>(Collections.reverseOrder());
        for(House flat: flats){                             // перебираем поэлементно коллекцию "домов"
            if(mapFN.containsKey(flat.getFloor_number())){  // если карта уже содержит в себе ключ с данным значением, то:
                ArrayList<Integer> listOfFlatNumbers = new ArrayList<>();  // инициализируем коллекцию c номерами квартир
                for(Integer key: mapFN.keySet()){           // перебираем ключи частично заполненной карты
                    if(key.equals(flat.getFloor_number())){ // если значение ключа совпадает со значением этажа квартиры, то
                        listOfFlatNumbers = mapFN.get(key); // заполняем созданную коллекцию значениями соответствующих ключей
                    }
                }
                if(!listOfFlatNumbers.contains(flat.getFlat_number())) { // если коллекция ещё не содержит данного
                    listOfFlatNumbers.add(flat.getFlat_number());        // номера квартиры – добавляем его
                }
                mapFN.put(flat.getFloor_number(), listOfFlatNumbers); // заполняем карту
            }
            else{                                                          // иначе:
                ArrayList<Integer> listOfFlatNumbers = new ArrayList<>();  // инициализируем коллекцию c номерами квартир
                listOfFlatNumbers.add(flat.getFlat_number());              // заполняем коллекцию соответствующими элементами
                mapFN.put(flat.getFloor_number(), listOfFlatNumbers);      // заполняем карту
            }
        }

        return mapFN;
    }

}
