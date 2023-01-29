import ru.netology.basket.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static jdk.xml.internal.SecuritySupport.isFileExists;

public class Main {
    public static void main(String[] args) throws IOException {

        Basket basket = new Basket(new String[]{"Молоко", "Хлеб", "Яблоко", "Гречневая крупа", "Сок"},
        new int[]{64, 50, 100, 40, 150});

        File textFile =new File("basket.txt");

        if (textFile.exists()) {
            System.out.println("Файл с сохраненной корзиной присутствует, поэтому корзина будет восстановлена из файла");
            Basket recaveryBasket = Basket.loadFromTxtFile(new File("basket.txt"));
        }
        else {
            System.out.println("Файл с сохраненной корзиной отсутствует, необходимо создать новую");
            Basket.groceryBasket();

            basket.saveTxt(new File("basket.txt"));
        }

    }
}
