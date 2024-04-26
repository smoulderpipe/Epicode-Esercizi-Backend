package it.epicode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Product> productList = new ArrayList<>();

        productList.add(new Product(1, "Libro 1", "Books", 20.0));
        productList.add(new Product(2, "Libro 2", "Books", 25.0));
        productList.add(new Product(3, "Libro 3", "Books", 15.0));
        productList.add(new Product(4, "Computer", "Electronics", 800.0));
        productList.add(new Product(5, "Telefono", "Electronics", 500.0));
        productList.add(new Product(6, "Tavolo", "Baby", 150.0));

//        productList.forEach((product) -> System.out.println(product.id));

        List<Product> onlyExpBooks = productList.stream().filter((product) -> product.getPrice() > 100 && product.getCategory().equals("Books")).toList();

        List<Order> orderList = new ArrayList<>();

        LocalDate data1 = LocalDate.now();
        LocalDate data2 = LocalDate.of(2020,5,8);

        orderList.add(new Order(1l, "ordered", data2, data1, productList, new Customer(3, "SeanConnery", 2) ));
        orderList.add(new Order(6l, "ordered", data2, data1, productList, new Customer(7, "JenniferAniston", 3) ));
        orderList.add(new Order(8l, "ordered", data2, data1, new ArrayList<Product>(), new Customer(16, "TimCook", 2) ));
        orderList.add(new Order(14l, "ordered", data2, data1, productList, new Customer(82, "GierriSchiotti", 1) ));

        List<Order> filteredBabyOrders = orderList.stream()
                .filter(order -> order.getProducts().stream()
                .anyMatch(product -> product.getCategory().equals("Baby")))
                .toList();

        for (Order order : filteredBabyOrders) {
            System.out.println(order.getId());
        }

    }
}