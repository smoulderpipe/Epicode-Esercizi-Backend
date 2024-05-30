package composite;

import java.util.ArrayList;
import java.util.List;

public class Sezione implements LibroComponent{
    private String title;
    private List<LibroComponent> components = new ArrayList<>();

    public Sezione(String title) {
        this.title = title;
    }

    public void addComponent(LibroComponent component) {
        components.add(component);
    }

    @Override
    public void print() {
        System.out.println("Section: " + title);
        for (LibroComponent component : components) {
            component.print();
        }
    }

    @Override
    public int getPageCount() {
        int total = 0;
        for (LibroComponent component : components) {
            total += component.getPageCount();
        }
        return total;
    }
}
