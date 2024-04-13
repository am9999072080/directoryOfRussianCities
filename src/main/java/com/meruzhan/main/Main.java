package com.meruzhan.main;

import com.meruzhan.model.City;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


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
                if (index == 1) city.setName(info);
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
        System.out.println("----After sorting----");
        System.out.println(cities);

        System.out.println("\n----Sorted by city----");
        Comparator<City> compareByName = Comparator.comparing(City::getName);
        ArrayList<City> sortedByCity = cities.stream()
                .sorted(compareByName)
                .collect(Collectors.toCollection(ArrayList::new));
        System.out.println(sortedByCity);

        System.out.println("\n----Sorted by district----");
        Comparator<City> compareByDistrict = Comparator
                .comparing(City::getDistrict)
                .thenComparing(City::getName).reversed()
                .thenComparing(City::getRegion).reversed();

        ArrayList<City> sortedByDistrict = cities.stream()
                .sorted(compareByDistrict)
                .collect(Collectors.toCollection(ArrayList::new));
        System.out.println(sortedByDistrict);

        System.out.println("\n----Maximum population----");
        City[] arrayCities = cities.toArray(new City[0]);
        int ind = -1;
        int value = Integer.MIN_VALUE;
        for (int i = 0; i < arrayCities.length; i++) {
            int population = arrayCities[i].getPopulation();
            if (population > value) {
                ind = i;
                value = population;
            }
        }
        System.out.println("[" + ind + "]=" + value);
    }
}
