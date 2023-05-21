public class InstrumentoViento extends Instrumento{

    /**
     * Constructor de un instrumento de viento. Hereda directamente los atributos de la clase Instrumento.
     * @param nombreInstrumento (nombre del instrumento).
     * @param material (material del que está hecho el instrumento).
     * @param codigoUnico (código con el cual se identifica el instrumento).
     * @param precio (precio del instrumento).
     * @param stock (unidades restantes del instrumento).
     */
    public InstrumentoViento(String nombreInstrumento, String material, String codigoUnico, int precio, int stock) {
        super(nombreInstrumento, material, codigoUnico, precio, stock);
    }
}
