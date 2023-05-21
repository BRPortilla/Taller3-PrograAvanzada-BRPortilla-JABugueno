public class InstrumentoCuerdas extends Instrumento{

    /**
     * El tipo de cuerdas del instrumento (de qué material son las cuerdas).
     */
    private String tipoCuerdas;

    /**
     * El tipo de instrumento de cuerdas (acústica o eléctrica).
     */
    private String tipo;

    /**
     * La cantidad de cuerdas que tiene el instrumento.
     */
    private int numeroDeCuerdas;

    /**
     * Constructor de un instrumento de cuerdas. Hereda directamente los atributos de la clase Instrumento, y se agregan
     * 3 atributos exclusivos a un instrumento de cuerdas.
     * @param nombreInstrumento (nombre del instrumento).
     * @param material (material del que está hecho el instrumento).
     * @param codigoUnico (código con el cual se identifica el instrumento).
     * @param precio (precio del instrumento).
     * @param stock (unidades restantes del instrumento).
     * @param tipoCuerdas (el tipo de cuerdas, de qué material son las cuerdas).
     * @param tipo (el tipo de instrumento de cuerdas, acústica o eléctrica).
     * @param numeroDeCuerdas (la cantidad de cuerdas que posee el instrumento).
     */

    public InstrumentoCuerdas(String nombreInstrumento, String material, String codigoUnico, int precio, int stock, String tipoCuerdas, String tipo, int numeroDeCuerdas) {
        super(nombreInstrumento, material, codigoUnico, precio, stock);
        this.tipoCuerdas = tipoCuerdas;
        this.tipo = tipo;
        this.numeroDeCuerdas = numeroDeCuerdas;
    }

    /**
     * Método: getTipoCuerdas.
     * @return el tipo de cuerdas, su material.
     */
    public String getTipoCuerdas() {
        return tipoCuerdas;
    }

    /**
     * Método: getTipo
     * @return el tipo de instrumento de cuerdas.
     */

    public String getTipo() {
        return tipo;
    }

    /**
     * Método: getNúmeroDeCuerdas.
     * @return la cantidad de cuerdas que tiene el instrumento.
     */

    public int getNumeroDeCuerdas() {
        return numeroDeCuerdas;
    }
}