package com.meruzhan.main;

import com.meruzhan.model.City;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("directory.csv"));
        String line = null;
        Scanner scanner = null;
        int index = 0;

        List<City> cities = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            City city = new City();
            scanner = new Scanner(line);
            scanner.useDelimiter(";");
            while (scanner.hasNext()) {
                String info = scanner.next();
                if (index == 0) city.setId(Integer.parseInt(info));
                else if (index == 1) city.setName(info);
                else if (index == 2) city.setRegion(info);
                else if (index == 3) city.setDistrict(info);
                else if (index == 4) city.setPopulation(Integer.parseInt(info));
                else if (index == 5) city.setFoundation(info);
                index++;
            }
            index = 0;
            cities.add(city);
        }
        reader.close();

        System.out.println(cities);

        City searchByCityName = cities.stream().filter(customer -> "Москва".equals(customer.getName())).findAny().orElse(null);

        System.out.println(searchByCityName);
    }
}