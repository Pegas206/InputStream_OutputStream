package ru.netology.basket;

import java.io.*;
import java.util.Scanner;

public class Basket {
    private static String[] products;
    private static int[] prices;
    private static int[] counts;

    // массив пользовательской корзины
    private static int[] userBasket = new int[5];
    // переменная итого
    private static int total = 0;
private Basket(){

}
    public Basket(String[] products, int[] prices) {
        this.products = products;
        this.prices = prices;
        this.counts = new int[products.length];
    }

    public static void groceryBasket() {

        int productNumber = 0;
        int productCount = 0;
        Scanner toolBox = new Scanner(System.in);

        System.out.println("Список возможных товаров для покупки");
        // вывод списка продуктов
        for (int i = 0; i < products.length; i++) {
            System.out.println(i + " : " + products[i] + " - " + prices[i] + " руб./шт.");
        }
        // Цикл ввода данных пользователем для наполнения корзины
        while (true) {
            System.out.println("Выберите товар и количество или введите `end`");
            String shop = toolBox.nextLine();
            //Завершение ввода данных и вывод корзины
            if ("end".equals(shop)) {
                printCart();
                break;
            }
            try {
                //перевод полученых данных из строки в цифры и заполнение параметров корзины
                String[] parts = shop.split(" ");
                if (parts.length != 2) {
                    System.out.println("Введены не корректные данные, необходимо вводить номер продукта и кол-во");
                }
                productNumber = Integer.parseInt(parts[0]);
                productCount = (Integer.parseInt(parts[1]));
                if (productCount > 50) {
                    System.out.println("Введено не корректное количество, тут Вам не оптовая база!)");
                } else if (productCount < 0) {
                    System.out.println("Введено не корректное количество, кол-во не может быть отрицательным!)");
                }
                addToCart(productNumber, productCount);
            } catch (NumberFormatException s) {
            } catch (ArrayIndexOutOfBoundsException s) {
                if (productNumber > userBasket.length) {
                    System.out.println("Не верно указан номер продукта");
                } else if (productNumber < 0) {
                    System.out.println("Не верно указан номер продукта, номер не может быть отрицательным");
                }
            }
        }
    }

    public static void addToCart(int productNumber, int productCount) {
        userBasket[productNumber] += productCount;
    }

    public static void printCart() {
        System.out.println("Ваша корзина");
        for (int i = 0; i < userBasket.length; i++) {
            if ((userBasket[i]) > 0) {
                int sumProducts = prices[i] * userBasket[i];
                System.out.println(products[i] + " " + userBasket[i] + " шт. " + prices[i] +
                        " руб./шт. " + sumProducts + " руб. в сумме");
                total += sumProducts;
            }
        }
        System.out.println("Итого: " + total + "руб.");
    }

    public static String[] getProducts() {
        return products;
    }

    public int[] getPrice() {
        return prices;
    }
    public void saveTxt(File textFile) throws FileNotFoundException {
       try (PrintWriter out = new PrintWriter(textFile);){
           out.println(userBasket.length);
           for (int i = 0; i < userBasket.length; i++) {
               if ((userBasket[i]) > 0) {

                   out.println(products[i]);
                   out.println(userBasket[i]);
                   out.println(prices[i]);
               }
           }
       }
}
    public static Basket loadFromTxtFile(File textFile) throws IOException {
       try (Scanner scanner = new Scanner(textFile)) {
           Basket basket = new Basket();
           int size = Integer.parseInt(scanner.nextLine());
           basket.products = new String[size];
           basket.prices = new int[size];
           basket.counts = new int[size];

           for (int i = 0; i < size; i++) {
               if ((userBasket[i]) > 0) {
                   basket.products[i] = scanner.nextLine();
                   basket.counts[i] = Integer.parseInt(scanner.nextLine());
                   basket.prices[i] = Integer.parseInt(scanner.nextLine());
               }

           }
           return basket;
       }
 }

}