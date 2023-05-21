public class InstrumentoPercusion extends Instrumento{

    /**
     * El tipo de percusión del instrumento.
     */
    private String tipoDePercusion;

    /**
     * El tipo de altura del instrumento.
     */
    private String tipoDeAltura;

    /**
     * Constructor de un instrumento de percusión. Hereda directamente los atributos de la clase Instrumento, más 2 atributos nuevos
     * exclusivos de un instrumento de percusión.
     * @param nombreInstrumento (nombre del instrumento).
     * @param material (material del que está hecho el instrumento).
     * @param codigoUnico (código con el cual se identifica el instrumento).
     * @param precio (precio del instrumento).
     * @param stock (unidades restantes del instrumento).
     * @param tipoDePercusion (el tipo de percusión: membranófono o idiófono).
     * @param tipoDeAltura (tipo de altura: definida o indefinida).
     */
    public InstrumentoPercusion(String nombreInstrumento, String material, String codigoUnico, int precio, int stock, String tipoDePercusion, String tipoDeAltura) {
        super(nombreInstrumento, material, codigoUnico, precio, stock);
        this.tipoDePercusion = tipoDePercusion;
        this.tipoDeAltura = tipoDeAltura;
    }

    /**
     * Método: getTipoDePercusion.
     * @return el tipo de percusión del instrumento.
     */
    public String getTipoDePercusion() {
        return tipoDePercusion;
    }

    /**
     * Método: getTipoDeAltura.
     * @return el tipo de altura del instrumento.
     */
    public String getTipoDeAltura() {
        return tipoDeAltura;
    }
}