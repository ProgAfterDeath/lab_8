package com.example.lab_8;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class Controller implements Initializable {

    @FXML private ResourceBundle resources;
    @FXML private URL location;

    @FXML private Button add_house;
    @FXML private Button delete_house;
    @FXML private Button see_all;
    @FXML private Button see_floor_numbers;
    @FXML private Button search_house;

    @FXML private TextField flat_number;
    @FXML private TextField area;
    @FXML private TextField floor_number;
    @FXML private TextField number_of_rooms;
    @FXML private TextField street_name;

    @FXML private TextField number_of_rooms_search;
    @FXML private TextField floor_number_low;
    @FXML private TextField floor_number_high;
    @FXML private TextField area_min;

    @FXML private ListView list_view;

    @FXML private ComboBox<String> comboBox1;
    @FXML private ComboBox<String> comboBox2;

    private final ErrorController error = new ErrorController();
    private House flat = new House();
    private List<House> flats = new ArrayList<>();      // основная коллекция
    private List<House> tempFlats = new ArrayList<>();  // "временная" коллекция для хранения выбранных
                                                             // элементов из основной коллекции
    private final Show show = new Show();
    private final Logic logic = new Logic();
    private final File file = new File("House List.txt");
    private final ReadObject readFile = new ReadObject();
    private final WriteObject writeFile = new WriteObject();
    private Optional<Integer> id;
    private final String [] listForComboBox1  = {"Id", "Area", "Floor Number"};
    private final String [] listForComboBox2  = {"Ascending", "Descending"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBox1.setValue(listForComboBox1[0]);        // устанавливаем изначальное значение для comboBox1
        comboBox2.setValue(listForComboBox2[0]);        // устанавливаем изначальное значение для comboBox2
        comboBox1.getItems().addAll(listForComboBox1);  // получаем значения для comboBox1 из listForComboBox1
        comboBox2.getItems().addAll(listForComboBox2);  // получаем значения для comboBox2 из listForComboBox2
        try {
            flats = readFile.readObject(file);          // считываем содержимое из файла и присваиваем коллекции
        } catch (IOException e) {                       // обрабатываем исключение, если возникнет ошибка при
            e.printStackTrace();                        // чтении из файла, то: выводим сведения об ошибке в консоль
        }

        show.showHouseList(flats, list_view);           // выводим содержимое основной коллекции
        tempFlats = flats;                              // присваиваем "временной" коллекции элементы основной

        comboBox1.setOnAction(event -> {                // при заданном поле comboBox1
            comboBoxLogic(tempFlats, list_view);        // отрабатываем все варианты использования полей comboBox1
        });                                             // в корреляции с comboBox2

        comboBox2.setOnAction(event -> {                // при заданном поле comboBox2
            comboBoxLogic(tempFlats, list_view);        // отрабатываем все варианты использования полей comboBox2
        });                                             // в корреляции с comboBox1

        add_house.setOnAction(event ->{     // при нажатии на кнопку Add House
            if(!flat_number.getText().equals("") && !area.getText().equals("") &&               // если все поля для добавления
                    !floor_number.getText().equals("") && !number_of_rooms.getText().equals("") // нового элемента в
                    && !street_name.getText().equals(""))                                  // коллекцию НЕ пусты, то:
            {
                id = logic.countId(flats);   // определяем id для нового элемента
                flat = logic.addHouse(id, Integer.parseInt(flat_number.getText()),  // присваиваем элементу
                        Double.parseDouble(area.getText()),                         // соответствующие параметры
                        Integer.parseInt(floor_number.getText()),                   // считанные с полей
                        Integer.parseInt(number_of_rooms.getText()), street_name.getText().trim());
                flats.add(flat); // добавляем элемент в коллекцию
                show.showHouseList(flats, list_view); // выводим содержимое основной коллекции
                show.clearHouseEditor(flat_number, area, floor_number,  // очищаем считанные поля для
                        number_of_rooms, street_name);                  // ввода данных из "House Editor"
                try {
                    flats = writeFile.writeObject(file, flats); // записываем полученную коллекцию в файл
                } catch (IOException e) {    // обрабатываем исключение, если возникнет ошибка при записи в файл, то:
                    e.printStackTrace();     // выводим сведения об ошибке в консоль
                }
            }
            else{              // иначе
                error.errorWindow(); // выводим всплывающее окно для пользователя об ошибке ввода данных
            }
        });

        list_view.setOnMouseClicked(event -> {           // при нажатии мышью на НЕ нулевые элементы списка
            delete_house.setOnAction(actionEvent -> {    // при нажатии на кнопку "Delete House"
                int index = list_view.getSelectionModel().getSelectedIndex();  // создаем переменную типа int и присвиеваем ей индекс выбранного элемента
                list_view.getItems().remove(index);      // удаляем из списка элемент с соответствующим индексом
                flats.remove(index);                     // удаляем из коллекции элемент с соответствующим индексом
                try {
                    flats = writeFile.writeObject(file, flats);  // записываем оставшуюся после удаления элемента коллекцию в файл
                } catch (IOException e) { e.printStackTrace(); } // обрабатываем исключение
            });
        });

        see_all.setOnAction(event -> {                 // при нажатии на кнопку "See All"
            tempFlats = logic.sortedByIdAsc(flats);    // присваиваем "временной" коллекции элементы основной, отсортированные по id
            show.showHouseList(tempFlats, list_view);  // отображаем в списке всю коллекцию
            show.clearHouseSearch(number_of_rooms_search,             // очищаем поля для ввода данных
                    floor_number_low, floor_number_high, area_min);   // из "House Searching"
            comboBox1.setValue(listForComboBox1[0]);   // устанавливаем изначальное значение для comboBox1
            comboBox2.setValue(listForComboBox2[0]);   // устанавливаем изначальное значение для comboBox2
        });

        see_floor_numbers.setOnAction(event ->{        // при нажатии на кнопку "See Floor Numbers"
            show.showFloorNumberList(logic.listOfFlatsOnFloors(flats), list_view); // отображаем в списке карту со
        });                                            // списком квартир (значений ключей) для каждого этажа (ключа)


        search_house.setOnAction(event -> {            // при нажатии на кнопку "Search House"
            tempFlats = fieldsUseCasesForSearching(flats);  // присваиваем "временной" коллекции элементы основной
                                                       // коллекции удовлетворяющие заданным полям поиска
            show.showHouseList(tempFlats, list_view);  // выводим содержимое "временной" коллекции
        });

    }
    // метод, описывающий комбинации выбранных полей из comboBox1 и comboBox2
    public void comboBoxLogic(List<House> flats, ListView<House> list_view) {
        if (comboBox1.getValue().equals(listForComboBox1[0]) && comboBox2.getValue().equals(listForComboBox2[0])) {
            flats = logic.sortedByIdAsc(flats);   // переопределяем порядок элементов основной коллекции по возрастанию id
            show.showHouseList(flats, list_view); // выводим содержимое основной коллекции
        } else if (comboBox1.getValue().equals(listForComboBox1[0]) && comboBox2.getValue().equals(listForComboBox2[1])) {
            flats = logic.sortedByIdDesc(flats);  // переопределяем порядок элементов основной коллекции по убыванию id
            show.showHouseList(flats, list_view); // выводим содержимое основной коллекции
        } else if (comboBox1.getValue().equals(listForComboBox1[1]) && comboBox2.getValue().equals(listForComboBox2[0])) {
            flats = logic.sortedByAreaAsc(flats); // переопределяем порядок элементов основной коллекции по возрастанию площади
            show.showHouseList(flats, list_view); // выводим содержимое основной коллекции
        } else if (comboBox1.getValue().equals(listForComboBox1[1]) && comboBox2.getValue().equals(listForComboBox2[1])) {
            flats = logic.sortedByAreaDesc(flats);// переопределяем порядок элементов основной коллекции по убыванию площади
            show.showHouseList(flats, list_view); // выводим содержимое основной коллекции
        }
        else if (comboBox1.getValue().equals(listForComboBox1[2]) && comboBox2.getValue().equals(listForComboBox2[0])) {
            flats = logic.sortedByFloorNumberAsc(flats);// переопределяем порядок элементов основной коллекции по возрастанию номера квартиры
            show.showHouseList(flats, list_view); // выводим содержимое основной коллекции
        }
        else if (comboBox1.getValue().equals(listForComboBox1[2]) && comboBox2.getValue().equals(listForComboBox2[1])) {
            flats = logic.sortedByFloorNumberDesc(flats);// переопределяем порядок элементов основной коллекции по убыванию номера квартиры
            show.showHouseList(flats, list_view); // выводим содержимое основной коллекции
        }
    }
    // метод, описывающий возможные комбинации ввода параметров для поиска элементов
    public List<House> fieldsUseCasesForSearching(List<House> flats) {
        List<House> temp = new ArrayList<>();      // создаем ещё одну "временную" коллекцию
        if (!number_of_rooms_search.getText().equals("")) {
            temp = logic.housesWithNumOfRooms(flats,
                    Integer.parseInt(number_of_rooms_search.getText()));
        } else if (floor_number_low.getText() != "" && floor_number_high.getText() != "") {
            temp = logic.housesWithFloorNumInRange(flats,
                    Integer.parseInt(floor_number_low.getText()),
                    Integer.parseInt(floor_number_high.getText()));
        } else if (area_min.getText() != "") {
            temp = logic.housesWithMinArea(flats,
                    Double.parseDouble(area_min.getText()));
        }

        if (number_of_rooms_search.getText() != "" && floor_number_low.getText() != "" &&
                floor_number_high.getText() != "") {
            temp = logic.housesWithNumOfRoomsAndFloorNumInRange(flats,
                    Integer.parseInt(number_of_rooms_search.getText()),
                    Integer.parseInt(floor_number_low.getText()),
                    Integer.parseInt(floor_number_high.getText()));
        } else if (number_of_rooms_search.getText() != "" && area_min.getText() != "") {
            temp = logic.housesWithNumOfRoomsAndMinArea(flats,
                    Integer.parseInt(number_of_rooms_search.getText()),
                    Double.parseDouble(area_min.getText()));
        } else if (floor_number_low.getText() != "" && floor_number_high.getText() != "" &&
                area_min.getText() != "") {
            temp = logic.housesWithFloorNumInRangeAndMinArea(flats,
                    Integer.parseInt(floor_number_low.getText()),
                    Integer.parseInt(floor_number_high.getText()),
                    Double.parseDouble(area_min.getText()));
        }

        if (number_of_rooms_search.getText() != "" && floor_number_low.getText() != "" &&
                floor_number_high.getText() != "" && area_min.getText() != "") {
            temp = logic.housesWithAllParameters(flats,
                    Integer.parseInt(number_of_rooms_search.getText()),
                    Integer.parseInt(floor_number_low.getText()),
                    Integer.parseInt(floor_number_high.getText()),
                    Double.parseDouble(area_min.getText()));
        }

        if (number_of_rooms_search.getText().equals("") && floor_number_low.getText().equals("") &&  // если все поля из
                floor_number_high.getText().equals("") && area_min.getText().equals("")) {           // "House Searching" пусты, то:
            error.errorWindow(); // выводим всплывающее окно для пользователя об ошибке ввода данных
            temp = tempFlats;
        }

        return temp;
    }

}