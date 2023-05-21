public abstract class Instrumento {
    /**
     * El nombre que posee el instrumento.
     */
    private String nombreInstrumento;

    /**
     * El material de construcción del instrumento.
     */
    private String material;

    /**
     * El código único que posee el instrumento, permite identificarlo.
     */
    private String codigoUnico;

    /**
     * El precio del instrumento.
     */
    private int precio;

    /**
     * El stock o unidades restantes que quedan del instrumento.
     */
    private int stock;

    /**
     * Constructor del instrumento.
     * @param nombreInstrumento (nombre del instrumento).
     * @param material (material del que está hecho el instrumento).
     * @param codigoUnico (código con el cual se identifica el instrumento).
     * @param precio (precio del instrumento).
     * @param stock (unidades restantes del instrumento).
     */
    public Instrumento(String nombreInstrumento, String material, String codigoUnico, int precio, int stock) {
        this.nombreInstrumento = nombreInstrumento;
        this.material = material;
        this.codigoUnico = codigoUnico;
        this.precio = precio;
        this.stock = stock;
    }

    /**
     * Método: getNombreInstrumento.
     * @return el nombre del instrumento.
     */
    public String getNombreInstrumento() {
        return nombreInstrumento;
    }


    /**
     * Método: getMaterial.
     * @return el material del instrumento.
     */
    public String getMaterial() {
        return material;
    }

    /**
     * Método: getCodigoUnico.
     * @return el código con el que se identifica el instrumento.
     */
    public String getCodigoUnico() {
        return codigoUnico;
    }

    /**
     * Método: getPrecio.
     * @return el precio del instrumento.
     */
    public int getPrecio() {
        return precio;
    }

    /**
     * Método: getStock.
     * @return el stock del instrumento (unidades restantes).
     */

    public int getStock() {
        return stock;
    }

    /**
     * Método: setStock.
     * Settea o designa un valor al stock.
     * @param stock (unidades restantes del instrumento).
     */
    public void setStock(int stock){
        this.stock = stock;
    }
}