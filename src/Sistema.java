import ucn.ArchivoEntrada;
import ucn.ArchivoSalida;

import java.io.IOException;

public interface Sistema{

    /**
     * Método: agregarInstrumento (@Override).
     * Se añaden nuevos instrumentos o más stock al inventario, a partir de un archivo .CSV (instrumentos_a_agregar.csv).
     * Luego se ejecuta el método lecturaDeArchivo con este archivo. Se despliega también la cantidad de instrumentos que hay
     * en el inventario, para dejar constancia de cuantos instrumentos faltan para que se llegue al límite máximo.
     * @throws IOException (excepción).
     */
    public void agregarInstrumento() throws IOException;

    /**
     * Método: venderInstrumento (@Override).
     * Es uno de los contratos del problema. Permite vender algún instrumento del inventario, a partir
     * de un código de instrumento que se ingresa. Si el instrumento con ese código existe en el inventario,
     * se vende y su stock decrementa en 1. Luego, se genera una boleta con características del instrumento.
     * Si el instrumento, al buscar su código único, no existe o no hay stock del instrumento, no se puede vender.
     */
    public void venderInstrumento();

    /**
     * Método: consultarInventario (@Override).
     * Uno de los contratos del problema. Consiste en poder desplegar o consultar datos de instrumentos con stock disponible
     * en el inventario. Primeramente, se comprueba la cantidad de instrumentos en el inventario. Si no hay instrumentos, se despliega
     * un error. Luego, se entra en un ciclo while, que vendría a ser un sub-menú, dando opciones de como realizar la consulta del inventario,
     * a gusto del usuario. Mientras el usuario no ingrese una opción válida, se desplegará un mensaje de error.
     *
     * Opción 1: Todo el inventario. En primer lugar, se realiza el conteo de instrumentos con stock de cada tipo, para comprobar
     * si hay stock disponible en algún instrumento del inventario. Si, hay al menos un instrumento con stock, se recorre el arreglo
     * de instrumentos, comprobando el stock de cada uno, y si es mayor a 0, se despliega con el método DespliegueDeDatos, por cada tipo.
     * Si, todos los instrumentos en el inventario no tienen stock, se despliegan que no hay instrumentos con stock, así como también
     * si no hay instrumentos con stock de algún tipo en específico.
     *
     * Opción 2: Desplegar por tipo de instrumentos. Se abre otro menú, en el que se pide al usuario ingresar el número 1,2 o 3,
     * para desplegar instrumentos de un tipo en específico (respectivamente, viento, cuerdas, percusión). Mediante el método
     * InstrumentosEnStock, se retorna la cantidad de instrumentos en stock del tipo especificado. Se despliegan todos los instrumentos
     * que, al recorrer la lista, sean de esa instancia de tipo de instrumento, que coincida con el buscado, y el stock sea mayor a 0.
     * En caso de que no hayan instrumentos de ese tipo con stock, se despliega mensaje de error.
     *
     * Opción 3: Buscar instrumento en específico con su código. Se ingresa un código de instrumento, y se busca.
     * Si el instrumento retornado es nulo, no existe y se despliega un mensaje de error.
     * En caso contrario, se despliegan sus datos mediante el método DespliegueDeDatos.
     *
     * Opción 4: Regresar al menú anterior. Regresa al menú principal.
     */
    public void consultarInventario();

    /**
     * Método: cierre (@Override).
     * Se imprime un mensaje de despedida, y se realiza la escritura del archivo de la base de datos.
     * @throws IOException (excepción).
     */
    public void cierre() throws IOException;


    /**
     * Método: escrituraDeArchivo.
     * Permite sobreescribir el archivo de la base de datos, para guardarlos.
     * Se genera el archivo de salida (el mismo que el de entrada).
     * Para cada instrumento en el inventario, se crea un registro. Pero, dependiendo de qué tipo sean (a qué instancia pertenecen),
     * se escriben en una cantidad específica de campos. Si es de viento, será de 5 campos, si es de percusión, de 7 campos, y si
     * es de cuerdas, de 8 campos. Se escriben en el mismo orden que venían en el archivo de entrada.
     * Luego se escribe el registro, y al final, se cierra el archivo.
     * Todo está dentro de un try, que puede capturar alguna excepción que pueda crashear el programa.
     * @throws IOException (excepción).
     */
    public void escrituraDeArchivo() throws IOException;


    /**
     * Método: iniciar.
     * Este método permite comenzar el programa en sí. Se lee el archivo csv de la base de datos de los instrumentos, mediante
     * el método de lecturaDeArchivo. Si no hay instrumentos en la base de datos, se lee el archivo de instrumentos a agregar.
     * Se despliega el menú principal del programa, con sus 4 requerimientos centrales: agregar instrumentos mediante
     * otro archivo CSV, vender instrumentos, consultar el inventario, o cerrar el programa.
     * Se tiene un switch dependiendo de la opción ingresada, permitiendo ejecutar el método indicado, o imprimir un
     * mensaje de error.
     * @throws IOException (excepción).
     */
    public void iniciar() throws IOException;


    /**
     * Método: DespliegueDeDatos.
     * Usado para desplegar los datos de un instrumento, dependiendo del tipo que sea (si es instancia de algún tipo).
     * Se utiliza para generar la boleta al momento de vender un instrumento, o al momento de consultar el inventario.
     * @param instrumento el instrumento cuyos datos se imprimirán.
     */
    public void DespliegueDeDatos(Instrumento instrumento);


    /**
     * Método: lecturaDeArchivo.
     * Este método se encarga de leer los archivos, y registrar los datos en el sistema.
     * De parámetro recibe el archivo de entrada (la base de datos, "base_de_instrumentos.csv", o los instrumentos
     * a agregar, "instrumentos_a_agregar.csv").
     *
     * Primeramente, se leen las 5 características principales que comparten los instrumentos. (código, precio, stock, nombre del instrumento, su material de construcción).
     * Luego, se leen otros 3 campos más. En breve se explicará su funcionamiento.
     * Se valida también que el stock y el precio sea válido (que haya más de 0 de precio, con stock igual o mayor a 0). En caso de que el precio o stock sea inválido,
     * se lanza una excepción, omitiendo el instrumento leído en ese registro.
     *
     * Se busca el instrumento a partir de su código, y se define un booleano (InstrumentoExiste) para comprobar si existe o no.
     *
     * Si el instrumento existe, y a su vez, las características leídas en el registro, coinciden con el instrumento retornado (tanto las 5 primeras
     * características, como las otras adicionales dependiendo del tipo), se suma el stock con el cual tienen en el inventario.
     *
     * Pero, si el instrumento no existe, habrá que validar sus campos para ver si el instrumento es válido.
     * Si los primeros 5 campos están bien ingresados y no nulos, se revisan los otros 3 campos restantes.
     * Si el campo 6, 7 y 8 son nulos, corresponde a un instrumento de viento (ya que solo tiene 5 campos válidos).
     * Si solo el campo 8 es nulo, corresponde a un instrumento de percusión (tiene 7 campos válidos).
     * Si ningún campo es nulo, es un instrumento de cuerdas (es la cantidad máxima y mayor de campos entre los 3 tipos de instrumentos).
     * El campo 8 correspondería a la cantidad de cuerdas en un instrumento de ese tipo, se transforma a int y se valida que la cantidad sea mayor a 0.
     * Luego, se crea el objeto (dependiendo del caso), instrumento, y se agrega al inventario.
     *
     * Todo está dentro de un try, para que el catch capture alguna excepción que crashee el programa.
     * Estructura de cada registro, por cada tipo de instrumento:
     * Viento: código,precio,stock,nombre de instrumento,material.
     * Percusión: código,precio,stock,nombre de instrumento,material,tipo de percusión,tipo de altura.
     * Cuerdas: código,precio,stock,nombre de instrumento,material,tipo de cuerdas,tipo de instrumento de cuerdas,cantidad de cuerdas.
     *
     * @param archivoEntrada (el archivo del cual se leerán sus datos).
     */
    public void lecturaDeArchivo(ArchivoEntrada archivoEntrada);
}