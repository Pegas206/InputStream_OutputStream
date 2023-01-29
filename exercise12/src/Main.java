import ru.netology.basket.*;

import java.io.File;

import java.io.IOException;



public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Basket basket = new Basket(new String[]{"Молоко", "Хлеб", "Яблоко", "Гречневая крупа", "Сок"},
        new int[]{64, 50, 100, 40, 150});

        File textFile =new File("basket.bin");

        if (textFile.exists()) {
            System.out.println("Файл с сохраненной корзиной присутствует, поэтому корзина будет восстановлена из файла");
            Basket recaveryBasket = Basket.loadFromBin(new File("basket.bin"));
        }
        else {
            System.out.println("Файл с сохраненной корзиной отсутствует, необходимо создать новую");
            Basket.groceryBasket();

            basket.saveBin(new File("basket.bin"));
        }

    }
}
