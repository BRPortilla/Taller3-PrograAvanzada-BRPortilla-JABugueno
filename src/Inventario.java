public class Inventario {
    //Inventario: Almacena todos los instrumentos que se tienen.
    //Inventario: Lista/Contenedor de Instrumentos.

    /**
     * Cantidad actual de instrumentos almacenados.
     */
    private static int cantActual;

    /**
     * La cantidad máxima de instrumentos que se pueden almacenar.
     */
    private int cantMaxima;

    /**
     * El arreglo de instrumentos.
     */
    private static Instrumento[] listaInstrumentos;


    /**
     * Constructor del inventario (el contenedor de los instrumentos).
     * La cantidad de instrumentos, se inicializa con 0.
     * @param cantMaxima (cantidad máxima de instrumentos que se pueden almacenar).
     */
    public Inventario(int cantMaxima){
        cantActual = 0;
        this.cantMaxima = cantMaxima;
        listaInstrumentos = new Instrumento[this.cantMaxima];
    }

    /**
     * Método: agregarInstrumento.
     * Añade un instrumento a la lista de instrumentos. En caso la cantidad actual de instrumentos sea igual a
     * la cantidad máxima (o sea, que esté lleno) se lanza una excepción (atrapada por un catch).
     * @param instrumento (El instrumento a agregar).
     * @throws Exception (en caso de que esté llena la lista).
     */

    public void agregarInstrumento(Instrumento instrumento) throws Exception {
        if (cantActual == this.cantMaxima){
            throw new Exception("La cantidad máxima de instrumentos se ha alcanzado.");
        }

        listaInstrumentos[cantActual] = instrumento;
        cantActual++;
    }

    /**
     * Método: obtenerInstrumento (parámetro de entrada: código único).
     * Busca y retorna un instrumento de la lista, a partir del código único del instrumento.
     * En caso de encontrarlo, lo retorna directamente. En el caso contrario, retorna null
     * (no se encontró).
     * @param codigoUnico (El código que identifica al instrumento, ya que es único, se usa como el criterio de búsqueda).
     * @return el instrumento si se encuentra, null si no se encuentra.
     */

    public Instrumento obtenerInstrumento(String codigoUnico){
        for (int i = 0; i < cantActual; i++) {
            if(listaInstrumentos[i].getCodigoUnico().equalsIgnoreCase(codigoUnico)){
                //Se obtiene del código único, ya que identifica a cada instrumento.
                return listaInstrumentos[i];
            }
        }
        //Si no se encuentra, se retorna null.
        return null;
    }

    /**
     * Método: obtenerInstrumento (parámetro de entrada: posición)
     * Esta versión del método obtenerInstrumento, se usa especificamente al momento de recorrer la lista,
     * cuando se sabe que ya existe un instrumento.
     * (Sobrecarga de métodos).
     * @param posicion (la posición de la lista: se usa principalmente dentro de los ciclos for).
     * @return el instrumento en esa posición de la lista.
     */

    public Instrumento obtenerInstrumento(int posicion){
        return listaInstrumentos[posicion];
    }

    /**
     * Método: ventaDeInstrumento (parámetro de entrada: el instrumento).
     * El método se usa para el contrato "Vender instrumento". Si el instrumento que se busca con el código existe,
     * se realiza su venta, y su stock decrementa en 1. Se llama al método setStock, del instrumento.
     * @param instrumento (el instrumento a vender).
     */
    public void ventaDeInstrumento(Instrumento instrumento){
        int stock = instrumento.getStock() - 1;
        instrumento.setStock(stock);
    }

    /**
     * Método: getCantActual.
     * @return la cantidad actual de instrumentos.
     */

    public int getCantActual() {
        return cantActual;
    }

    /**
     * Método: getCantMaxima.
     * @return la cantidad máxima de instrumentos de la lista.
     */

    public int getCantMaxima() {
        return cantMaxima;
    }


    /**
     * Método: InstrumentosEnStock (parámetro de entrada: código de tipo de instrumento).
     * Este método se usa en el contrato "Consultar inventario".
     * Realiza el conteo de instrumentos de algún tipo en específico, que se identifica con un código, usado
     * como parámetro de entrada.
     * @param codigoDeTipoInstrumento (el código del instrumento que identifica qué tipo de instrumento se está comprobando
     * el stock: 1 para Viento, 2 para Cuerdas, y 3 para Percusión).
     * @return la cantidad de instrumentos que poseen stock del tipo que se está buscando.
     */

    public int InstrumentosEnStock (int codigoDeTipoInstrumento){

        //Cantidad de instrumentos en stock comienza en 0.
        int cantidadEnStock = 0;

        //Se cuenta la cantidad de instrumentos con stock, dependiendo del código del tipo de instrumento
        //ingresado, si en la posición de la lista es una instancia de ese tipo de instrumento y si su stock
        //es mayor a 0.

        if (codigoDeTipoInstrumento == 1){
            for (int i = 0; i < cantActual; i++) {
                if (listaInstrumentos[i] instanceof InstrumentoViento && listaInstrumentos[i].getStock() > 0){
                    cantidadEnStock++;
                }
            }
            return cantidadEnStock;
        }else if (codigoDeTipoInstrumento == 2){
            for (int i = 0; i < cantActual; i++) {
                if (listaInstrumentos[i] instanceof InstrumentoCuerdas && listaInstrumentos[i].getStock() > 0){
                    cantidadEnStock++;
                }
            }
            return cantidadEnStock;
        }else if (codigoDeTipoInstrumento == 3){
            for (int i = 0; i < cantActual; i++) {
                if (listaInstrumentos[i] instanceof InstrumentoPercusion && listaInstrumentos[i].getStock() > 0){
                    cantidadEnStock++;
                }
            }
            return cantidadEnStock;
        }
        //Si no se encuentra stock, se retorna 0.
        return cantidadEnStock;
    }
}
