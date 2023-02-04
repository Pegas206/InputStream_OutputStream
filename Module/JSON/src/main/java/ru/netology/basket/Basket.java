package ru.netology.basket;

import com.google.gson.Gson;

import java.io.*;
import java.util.Scanner;


public class Basket {
    private String[] products;
    private int[] prices;
    private int[] counts;

    // массив пользовательской корзины
    private int[] userBasket = new int[5];
    // переменная итого
    private int total = 0;

    private Basket() {

    }

    public Basket(String[] products, int[] prices) {
        this.products = products;
        this.prices = prices;
        this.counts = new int[products.length];
    }

    public void groceryBasket() {
        ClientLog clientLog = new ClientLog();
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
                //перевод полученных данных из строки в цифры и заполнение параметров корзины
                String[] parts = shop.split(" ");
                if (parts.length != 2) {
                    System.out.println("Введены не корректные данные, необходимо вводить номер продукта и кол-во");
                }
                productNumber = Integer.parseInt(parts[0]);
                productCount = (Integer.parseInt(parts[1]));
                clientLog.log(productNumber, productCount);
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

    public void addToCart(int productNumber, int productCount) {
        userBasket[productNumber] += productCount;
    }

    public void printCart() {
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


    public void saveJson(File textFile) throws IOException {
        try (PrintWriter out = new PrintWriter(textFile)) {
            Gson gson = new Gson();
            String json = gson.toJson(this);
            out.println(json);
        }
    }

    public static Basket loadFromJson(File textFile) throws IOException {
        try (Scanner scanner = new Scanner(textFile)) {
            Gson gson = new Gson();
            String json = scanner.nextLine();
            Basket basket = gson.fromJson(json, Basket.class);
            return basket;
        }

    }

}