import ru.netology.basket.*;

import java.io.File;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {

        Basket basket = new Basket(new String[]{"Молоко", "Хлеб", "Яблоко", "Гречневая крупа", "Сок"},
                new int[]{64, 50, 100, 40, 150});

        File textFile = new File("basket.json");

        if (textFile.exists()) {
            System.out.println("Файл с сохраненной корзиной присутствует, поэтому корзина будет восстановлена из файла");
            Basket recoveryBasket = Basket.loadFromJson(new File("basket.json"));

        } else {
            System.out.println("Файл с сохраненной корзиной отсутствует, необходимо создать новую");
            basket.groceryBasket();
            basket.saveJson(new File("basket.json"));
            ClientLog clientLog = new ClientLog();
            clientLog.exportAsCSV(new File("log.csv"));

        }

    }
}
