package ru.netology.basket;

import java.util.Scanner;

public class Basket {


    public static void groceryBasket() {
        int productNumber = 0;
        int productCount = 0;
        Scanner toolBox = new Scanner(System.in);
        // переменная итого
        int total = 0;
        // массив продуктов
        String[] products = {"Молоко", "Хлеб", "Яблоко", "Гречневая крупа", "Сок"};
        // массив цен
        int[] price = {64, 50, 100, 40, 150};
        // массив пользовательской корзины
        int[] userBasket = new int[5];
        System.out.println("Список возможных товаров для покупки");
        // вывод списка продуктов
        for (int i = 0; i < products.length; i++) {
            System.out.println(i + " : " + products[i] + " - " + price[i] + " руб./шт.");
        }
        // Цикл ввода данных пользователем для наполнения корзины
        while (true) {
            System.out.println("Выберите товар и количество или введите `end`");
            String shop = toolBox.nextLine();
            //Завершение ввода данных и вывод корзины
            if ("end".equals(shop)) {
                System.out.println("Ваша корзина");
                for (int i = 0; i < userBasket.length; i++) {
                    if ((userBasket[i]) > 0) {
                        int sumProducts = price[i] * userBasket[i];
                        System.out.println(products[i] + " " + userBasket[i] + " шт. " + price[i] +
                                " руб./шт. " + sumProducts + " руб. в сумме");
                        total += sumProducts;
                    }
                }
                System.out.println("Итого: " + total + "руб.");
                break;
            }
            try {
                //перевод полученых данных из строки в цифры и заполнение параметров корзины
                String[] parts = shop.split(" ");
                if (parts.length != 2){
                    System.out.println("Введены не корректные данные, необходимо вводить номер продукта и кол-во");
                }
                productNumber = Integer.parseInt(parts[0]);
                productCount = (Integer.parseInt(parts[1]));
                if (productCount > 50) {
                    System.out.println("Введено не корректное количество, тут Вам не оптовая база!)");
                } else if (productCount < 0) {
                    System.out.println("Введено не корректное количество, кол-во не может быть отрицательным!)");
                }
                userBasket[productNumber] += productCount;
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
}