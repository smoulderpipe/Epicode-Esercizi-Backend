package composite;

public class Pagina implements LibroComponent{

    private int pageNumber;

    public Pagina(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    @Override
    public void print() {
        System.out.println("Printing page " + pageNumber);
    }

    @Override
    public int getPageCount() {
        return 1;
    }
}
