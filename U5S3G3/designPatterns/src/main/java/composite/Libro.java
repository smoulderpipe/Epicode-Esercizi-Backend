package composite;

import java.util.ArrayList;
import java.util.List;

public class Libro {
    private List<LibroComponent> components = new ArrayList<>();
    private List<String> authors = new ArrayList<>();
    private double price;

    public Libro(double price) {
        this.price = price;
    }

    public void addComponent(LibroComponent component) {
        components.add(component);
    }

    public void addAuthor(String author) {
        authors.add(author);
    }

    public void print() {
        System.out.println("Printing book...");
        for (LibroComponent component : components) {
            component.print();
        }
    }

    public int getNumberOfPages() {
        int total = 0;
        for (LibroComponent component : components) {
            total += component.getPageCount();
        }
        return total;
    }

    public double getPrice() {
        return price;
    }

    public List<String> getAuthors() {
        return authors;
    }
}
