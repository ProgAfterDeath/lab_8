package com.example.lab_8;

import java.util.*;
import java.util.stream.Collectors;

public class Logic {
    // метод, принимающий и устанавливающий все значения атрибутов нового элемента
    public House addHouse(Optional<Integer> id, int flat_number, double area, int floor_number, int number_of_rooms,
                          String street_name){
        House flat = new House();
        flat.setId(id.get());
        flat.setFlat_number(flat_number);
        flat.setArea(area);
        flat.setFloor_number(floor_number);
        flat.setNumber_of_rooms(number_of_rooms);
        flat.setStreet_name(street_name);
        return flat;
    }
    // метод, определяющий id для добавляемого элемента
    public Optional<Integer> countId(List<House> flats){
       return Optional.of(flats.stream()
                .map(House::getId)
                .max(Comparator.comparing(Integer::intValue)).orElse(0) + 1);
    }
    // метод, фильтрующий элементы коллекции согласно количеству комнат
    public List<House> housesWithNumOfRooms(List<House> flats, int number_of_rooms_search){
        return flats
                .stream().filter(House -> House.getNumber_of_rooms() == number_of_rooms_search)
                .collect(Collectors.toList());
    }
    // метод, фильтрующий элементы коллекции согласно диапазону в котором находится искомый этаж
    public List<House> housesWithFloorNumInRange(List<House> flats,
                                                      int floor_number_low, int floor_number_high){
        return flats.stream().filter(House -> (House.getFloor_number() >= floor_number_low &&
                        House.getFloor_number() <= floor_number_high))
                .collect(Collectors.toList());
    }
    // метод, фильтрующий элементы коллекции согласно минимальному размеру площади
    public List<House> housesWithMinArea(List<House> flats, double area_min){
        return flats.stream().filter(House -> House.getArea() > area_min)
                .collect(Collectors.toList());
    }
    // метод, фильтрующий элементы коллекции согласно количество комнат и диапазону в котором находится искомый этаж
    public List<House> housesWithNumOfRoomsAndFloorNumInRange(List<House> flats,
                                                                   int number_of_rooms_search,
                                                                   int floor_number_low, int floor_number_high){
        return flats.stream().filter(House -> (House.getNumber_of_rooms() == number_of_rooms_search &&
                        House.getFloor_number() >= floor_number_low && House.getFloor_number() <= floor_number_high))
                .collect(Collectors.toList());
    }
    // метод, фильтрующий элементы коллекции согласно количеству комнат и минимальному размеру площади
    public List<House> housesWithNumOfRoomsAndMinArea(List<House> flats, int number_of_rooms_search,
                                                    double area_min){
        return flats.stream().filter(House -> (House.getNumber_of_rooms() == number_of_rooms_search &&
                        House.getArea() > area_min))
                .collect(Collectors.toList());
    }
    // метод, фильтрующий элементы коллекции согласно диапазону в котором находится искомый этаж и минимальному размеру площади
    public List<House> housesWithFloorNumInRangeAndMinArea(List<House> flats,
                                                    int floor_number_low, int floor_number_high,
                                                    double area_min){
        return flats.stream().filter(House -> (House.getFloor_number() >= floor_number_low &&
                House.getFloor_number() <= floor_number_high && House.getArea() > area_min))
                .collect(Collectors.toList());
    }
    // метод, фильтрующий элементы коллекции согласно всем параметрам их поиска
    public List<House> housesWithAllParameters(List<House> flats, int number_of_rooms_search,
                                                    int floor_number_low, int floor_number_high,
                                                    double area_min){
        return flats.stream().filter(House -> (House.getNumber_of_rooms() == number_of_rooms_search &&
                House.getFloor_number() >= floor_number_low &&
                House.getFloor_number() <= floor_number_high && House.getArea() > area_min))
                .collect(Collectors.toList());
    }
    // метод, сортирующий элементы коллекции по возрастанию id
    public List<House> sortedByIdAsc(List<House> flats){
        return flats.stream().sorted(Comparator.comparing(House::getId))
                .collect(Collectors.toList());
    }
    // метод, сортирующий элементы коллекции по убыванию id
    public List<House> sortedByIdDesc(List<House> flats){
        return flats.stream().sorted(Comparator.comparing(House::getId).reversed())
                .collect(Collectors.toList());
    }
    // метод, сортирующий элементы коллекции по возрастанию номеров квартир
    public List<House> sortedByFloorNumberAsc(List<House> flats) {
        return flats.stream().sorted(Comparator.comparing(House::getFloor_number))
                .collect(Collectors.toList());
    }
    // метод, сортирующий элементы коллекции по убыванию номеров квартир
    public List<House> sortedByFloorNumberDesc(List<House> flats) {
        return flats.stream().sorted(Comparator.comparing(House::getFloor_number).reversed())
                .collect(Collectors.toList());
    }
    // метод, сортирующий элементы коллекции по возрастанию размера площади
    public List<House> sortedByAreaAsc(List<House> flats){
        return flats.stream().sorted(Comparator.comparing(House::getArea))
                .collect(Collectors.toList());
    }
    // метод, сортирующий элементы коллекции по убыванию размера площади; если площадь одинаковая – по возрастанию номера этажа
    public List<House> sortedByAreaDesc(List<House> flats) {
        return flats.stream()
                .sorted(Comparator.comparing(House::getArea).reversed()
                        .thenComparing(Comparator.comparing(House::getFloor_number)))
                .collect(Collectors.toList());
    }
    // метод, создающий карту с перечнем квартир на каждом этаже
    public Map<Integer, List<House>> listOfFlatsOnFloors(List<House> flats){
        return flats.stream()
                .collect(Collectors.groupingBy(House::getFloor_number));
    }

}
