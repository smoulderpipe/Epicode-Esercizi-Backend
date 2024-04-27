public abstract class ElementoCatalogo {

    private String isbn;
    private String titolo;
    private Integer anno;
    private Integer pagine;
    private String tipoElemento;

    public ElementoCatalogo(String isbn, String titolo, Integer anno, Integer pagine, String tipoElemento) {
        this.isbn = isbn;
        this.titolo = titolo;
        this.anno = anno;
        this.pagine = pagine;
        this.tipoElemento = tipoElemento;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitolo() {
        return this.titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public Integer getAnno() {
        return this.anno;
    }

    public void setAnno(Integer anno) {
        this.anno = anno;
    }

    public Integer getPagine() {
        return this.pagine;
    }

    public void setPagine(Integer pagine) {
        this.pagine = pagine;
    }

    public String getTipoElemento(){
        return this.tipoElemento;
    }

    public void setTipoElemento(String tipoElemento){
        this.tipoElemento = tipoElemento;
    }

    @Override
    public String toString() {
        return "ISBN: " + isbn + ", Titolo: " + titolo + ", Anno: " + anno + ", Pagine: " + pagine;
    }
}
