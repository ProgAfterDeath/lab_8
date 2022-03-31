package com.example.lab_7;

public class House {

    private int id;
    private int flat_number;
    private double area;
    private int floor_number;
    private int number_of_rooms;
    private String street_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFlat_number() {
        return flat_number;
    }

    public void setFlat_number(int flat_number) {
        this.flat_number = flat_number;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public int getFloor_number() {
        return floor_number;
    }

    public void setFloor_number(int floor_number) {
        this.floor_number = floor_number;
    }

    public int getNumber_of_rooms() {
        return number_of_rooms;
    }

    public void setNumber_of_rooms(int number_of_rooms) {
        this.number_of_rooms = number_of_rooms;
    }

    public String getStreet_name() {
        return street_name;
    }

    public void setStreet_name(String street_name) {
        this.street_name = street_name;
    }

    @Override
    public String toString() {
        return  "Id: " + id +
                ", Номер квартири: " + flat_number +
                ", Площа: " + area +
                " м2, Поверх: " + floor_number +
                ", Кількість кімнат: " + number_of_rooms +
                ", Вулиця: '" + street_name + '\'';
    }
}
