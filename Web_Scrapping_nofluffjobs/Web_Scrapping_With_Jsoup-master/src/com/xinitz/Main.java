package com.xinitz;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println("Ready.....");
        FetchAllTheCountriesOfWorld allTheCountriesOfWorld = new FetchAllTheCountriesOfWorld();


        allTheCountriesOfWorld.printAllCountries();
        System.out.println("=================================================================");

        allTheCountriesOfWorld.printDescription();
        System.out.println("=================================================================");

        allTheCountriesOfWorld.printCountriesByFirstLetter("Z");


    }
}
