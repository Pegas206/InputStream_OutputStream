package ru.netology.basket;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.*;

public class ClientLog {


    static List logShop = new ArrayList();

    public void log(int productNumber, int productCount) {

        logShop.add(productNumber);
        logShop.add(productCount);
    }

    public void prn() {
        System.out.println(ClientLog.logShop);
    }

    public void exportAsCSV(File txtFile) throws IOException {

// Создаем экземпляр Writer
        try (Writer writer = new FileWriter("client.csv")) {
            StringBuilder csvString = new StringBuilder();
            csvString.append("productNum");
            csvString.append(',');
            csvString.append("amount");
            csvString.append("\n");
            Iterator countriesIterator = logShop.iterator();

            while (countriesIterator.hasNext()) {
                csvString.append(countriesIterator.next().toString());
                csvString.append(',');
                csvString.append(countriesIterator.next().toString());
                csvString.append("\n");
            }
            writer.append(csvString.toString());
            writer.flush();
        }
    }

}


