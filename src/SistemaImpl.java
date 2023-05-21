import ucn.*;

import java.io.IOException;

/**
 * SistemaImpl: La clase que implementa a la interface.
 */
public class SistemaImpl implements Sistema{

    /**
     * Acá se posee el inventario, la lista de todos los instrumentos disponibles.
     */
    private Inventario inventario;

    /**
     * El constructor del sistema-impl. Entrega la cantidad máxima de instrumentos al inventario, e inicia el programa con el método iniciar.
     * @throws IOException (excepción).
     */
    public SistemaImpl() throws IOException {
        this.inventario = new Inventario(6000);
        this.iniciar();
    }


    //Método: agregarInstrumento (@Override).
    //Uno de los principales contratos del programa. Está totalmente documentado en la interface.
    @Override
    public void agregarInstrumento() throws IOException {
        StdOut.println("Se agregarán los instrumentos ingresados en el archivo instrumentos_a_agregar.csv.");
        //Se define el archivo de entrada, y se envía como argumento al método lecturaDeArchivo.

        ArchivoEntrada archivoEntrada = new ArchivoEntrada("instrumentos_a_agregar.csv");
        this.lecturaDeArchivo(archivoEntrada);

        //Se informa también la cantidad actual de todos los instrumentos, y en caso de alcanzar el límite, se imprime una advertencia indicando esto.
        StdOut.println("La cantidad actual de instrumentos es de " + inventario.getCantActual() + ".");

        if(inventario.getCantActual() == inventario.getCantMaxima()){
            StdOut.println("La cantidad máxima de instrumentos (" + inventario.getCantMaxima() + ") ha sido alcanzada.");
        }else{
            StdOut.println("Si se alcanza el límite de " + inventario.getCantMaxima() + " no se podrán agregar más instrumentos nuevos.");
            StdOut.println("Si desea añadir instrumentos nuevos o aumentar el stock de un instrumento, modifique el archivo instrumentos_a_agregar.csv");
            StdOut.println("No olvide indicar correctamente los campos para cada instrumento.");
            StdOut.println("Instrumento de viento: código,precio,stock,nombre de instrumento,material.");
            StdOut.println("Instrumento de percusión: código,precio,stock,nombre de instrumento,material,tipo de percusión,tipo de altura.");
            StdOut.println("Instrumento de cuerdas: código,precio,stock,nombre de instrumento,material,tipo de cuerdas,tipo de instrumento de cuerdas,cantidad de cuerdas.");
        }
    }

    //Método: venderInstrumento (@Override).
    //Es uno de los requerimientos del problema. Está totalmente documentado en la interface.
    @Override
    public void venderInstrumento() {
        //Se comprueba si hay instrumentos disponibles en el inventario. Si no, se despliega mensaje de error.

        if(inventario.getCantActual() > 0){
            StdOut.println("Ingrese el código único del instrumento que desee vender: ");

            String codigoUnicoAVender = StdIn.readString();
            Instrumento instrumentoAVender = inventario.obtenerInstrumento(codigoUnicoAVender);

            //Se busca el instrumento, a partir de su código. En caso de que no sea null (o sea, que exista), se comprueba su stock.
            //Si es mayor a 0, significa que el instrumento tiene unidades restantes y se puede vender. Si es igual a 0,
            //no tiene unidades y se despliega que ya no hay unidades para ese instrumento.
            //En cambio, si el instrumento es nulo (no existe), se despliega que no existe el instrumento.

            if (instrumentoAVender != null) {
                if (instrumentoAVender.getStock() > 0){
                    inventario.ventaDeInstrumento(instrumentoAVender);
                    StdOut.println("¡Venta realizada!");
                    StdOut.println("Detalles de la venta: ");
                    DespliegueDeDatos(instrumentoAVender);
                }else{
                    StdOut.println("Ya no hay stock disponible para este instrumento.");
                }
            }else{
                StdOut.println("El instrumento no se ha encontrado. Intente de nuevo.");
            }
        }else{
            StdOut.println("No se pueden realizar ventas porque no hay instrumentos en el inventario.");
        }
    }

    //Método: consultarInventario (@Override).
    //Uno de los contratos del problema. Está totalmente documentado en la interface.
    @Override
    public void consultarInventario() {
        if (inventario.getCantActual() > 0){

            //Submenú (cómo desplegar el inventario).
            while (true) {
                StdOut.println("¿Cómo desea realizar la consulta de inventario?");
                StdOut.println("[1] Todo el inventario con stock, por tipo.");
                StdOut.println("[2] Desplegar un tipo de instrumento en específico.");
                StdOut.println("[3] Buscar instrumento en específico (mediante código).");
                StdOut.println("[4] Regresar al menú anterior.");
                String opcion = StdIn.readString();

                //En caso de que la opción no sea válida.
                while (!opcion.equalsIgnoreCase("1") && !opcion.equalsIgnoreCase("2") && !opcion.equalsIgnoreCase("3")
                        && !opcion.equalsIgnoreCase("4")) {
                    StdOut.println("Opción inválida, intente de nuevo. Elija entre la opción 1,2,3 o 4.");
                    opcion = StdIn.readString();
                }

                //Desplegar todo el inventario con stock, por cada tipo.
                if(opcion.equalsIgnoreCase("1")){

                    int cantInstrumentosVientoStock = inventario.InstrumentosEnStock(1);
                    int cantInstrumentosCuerdaStock = inventario.InstrumentosEnStock(2);
                    int cantInstrumentosPercusionStock = inventario.InstrumentosEnStock(3);

                    //Si al menos hay un instrumento con stock en el inventario, se despliegan todos los instrumentos con stock, por tipo.
                    if(cantInstrumentosCuerdaStock > 0 || cantInstrumentosPercusionStock > 0 || cantInstrumentosVientoStock > 0) {

                        //Se despliega por cada tipo de instrumentos. Si algún tipo no posee instrumentos con stock disponible, se despliega
                        //una advertencia que no hay instrumentos con stock de ese tipo.

                        StdOut.println("Instrumentos de Viento: ");
                        if (cantInstrumentosVientoStock > 0) {
                            for (int i = 0; i < inventario.getCantActual(); i++) {
                                if (inventario.obtenerInstrumento(i).getStock() > 0 && inventario.obtenerInstrumento(i) instanceof InstrumentoViento) {
                                    DespliegueDeDatos(inventario.obtenerInstrumento(i));
                                    StdOut.println("");
                                }
                            }
                        }else{
                            StdOut.println("No hay instrumentos con stock de ese tipo en el inventario.");
                            StdOut.println("");
                        }

                        StdOut.println("Instrumentos de Percusión: ");
                        if (cantInstrumentosPercusionStock > 0){
                            for (int i = 0; i < inventario.getCantActual(); i++) {
                                if (inventario.obtenerInstrumento(i).getStock() > 0 && inventario.obtenerInstrumento(i) instanceof InstrumentoPercusion){
                                    DespliegueDeDatos(inventario.obtenerInstrumento(i));
                                    StdOut.println("");
                                }
                            }
                        }else{
                            StdOut.println("No hay instrumentos con stock de ese tipo en el inventario.");
                            StdOut.println("");
                        }

                        StdOut.println("Instrumentos de Cuerdas: ");
                        if (cantInstrumentosCuerdaStock > 0){
                            for (int i = 0; i < inventario.getCantActual(); i++) {
                                if (inventario.obtenerInstrumento(i).getStock() > 0 && inventario.obtenerInstrumento(i) instanceof InstrumentoCuerdas){
                                    DespliegueDeDatos(inventario.obtenerInstrumento(i));
                                    StdOut.println("");
                                }
                            }
                        }else{
                            StdOut.println("No hay instrumentos con stock de ese tipo en el inventario.");
                            StdOut.println("");
                        }
                    }else{
                        //En caso contrario, que no haya ni un instrumento con stock, se despliega mensaje de error.
                        StdOut.println("No hay instrumentos con stock en este momento.");
                    }
                }else if (opcion.equalsIgnoreCase("2")){

                    //Submenú que pide qué tipo de instrumento desplegar.
                    StdOut.println("¿Qué tipo de instrumento quiere consultar?");
                    StdOut.println("[1] Viento");
                    StdOut.println("[2] Cuerdas");
                    StdOut.println("[3] Percusión");
                    String opcionTipo = StdIn.readString();

                    //Si la opción escogida no es válida.
                    while(!opcionTipo.equalsIgnoreCase("1") && !opcionTipo.equalsIgnoreCase("2") && !opcionTipo.equalsIgnoreCase("3")) {
                        StdOut.println("Opción inválida, intente de nuevo. Elija la opción 1,2 o 3.");
                        opcionTipo = StdIn.readString();
                    }

                    //Se retorna si existen instrumentos con stock del tipo especificado, a partir del método InstrumentosEnStock.
                    int cantInstrumentosTipoStock = inventario.InstrumentosEnStock(Integer.parseInt(opcionTipo));

                    if(cantInstrumentosTipoStock > 0){
                        //Si hay al menos 1 instrumento con stock del tipo especificado, se despliegan los instrumentos de ese tipo.
                        for (int i = 0; i < inventario.getCantActual(); i++) {
                            if (inventario.obtenerInstrumento(i).getStock() > 0){
                                //Si el código (opción de tipo) coincide con la instancia del instrumento en una posición, y tiene stock, se despliega.
                                if ((inventario.obtenerInstrumento(i) instanceof InstrumentoViento && opcionTipo.equalsIgnoreCase("1"))
                                        || (inventario.obtenerInstrumento(i) instanceof InstrumentoCuerdas && opcionTipo.equalsIgnoreCase("2"))
                                        || (inventario.obtenerInstrumento(i) instanceof InstrumentoPercusion && opcionTipo.equalsIgnoreCase("3"))){
                                    DespliegueDeDatos(inventario.obtenerInstrumento(i));
                                    StdOut.println("");
                                }
                            }
                        }
                    }else{
                        //Si no hay stock de un tipo, mensaje de error.
                        StdOut.println("No hay instrumentos con stock de ese tipo en el inventario.");
                    }
                }else if (opcion.equalsIgnoreCase("3")){
                    //Se pide ingresar un código de instrumento.
                    StdOut.println("Ingrese el código del instrumento a buscar: ");
                    String codigo = StdIn.readString();
                    Instrumento instrumento = inventario.obtenerInstrumento(codigo);

                    //Si el instrumento no es nulo (existe), y su stock es mayor a 0, se despliega.
                    if (instrumento != null) {
                        if (instrumento.getStock() > 0){
                            DespliegueDeDatos(instrumento);
                            StdOut.println();
                        }else{
                            //Si el instrumento existe, pero no tiene stock, se despliega que no hay stock para este producto.
                            StdOut.println("Ya no hay stock disponible para este instrumento.");
                        }
                    }else{
                        //Si el instrumento es nulo (no existe), se despliega mensaje de error.
                        StdOut.println("El instrumento no se ha encontrado.");
                    }
                }else if (opcion.equalsIgnoreCase("4")){
                    //Se rompe el ciclo.
                    break;
                }
            }
        }else{
            StdOut.println("No hay instrumentos disponibles en el inventario.");
        }
    }


    //Método: cierre (@Override).
    //Uno de los contratos del problema. Su documentación está presente en la interface.
    @Override
    public void cierre() throws IOException {
        StdOut.println("¡Hasta luego!");
        //Mensaje de despedida y escritura de archivo.
        this.escrituraDeArchivo();
    }

    //Método: escrituraDeArchivo (@Override)
    //El paso final, que consiste en sobreescribir la base de datos. Su documentación completa está en la interface.
    @Override
    public void escrituraDeArchivo() throws IOException {
        ArchivoSalida archivoSalida = new ArchivoSalida("base_de_instrumentos.csv");

        try {
            for (int i = 0; i < inventario.getCantActual(); i++) {
                Registro registro;
                try {
                    if (inventario.obtenerInstrumento(i) instanceof InstrumentoViento) {
                        //Recordar, que la cantidad de campos depende del tipo de instrumento que sea.
                        registro = new Registro(5);
                        registro.agregarCampo(inventario.obtenerInstrumento(i).getCodigoUnico());
                        registro.agregarCampo(inventario.obtenerInstrumento(i).getPrecio());
                        registro.agregarCampo(inventario.obtenerInstrumento(i).getStock());
                        registro.agregarCampo(inventario.obtenerInstrumento(i).getNombreInstrumento());
                        registro.agregarCampo(inventario.obtenerInstrumento(i).getMaterial());
                        archivoSalida.writeRegistro(registro);
                    } else if (inventario.obtenerInstrumento(i) instanceof InstrumentoPercusion) {
                        registro = new Registro(7);
                        registro.agregarCampo(inventario.obtenerInstrumento(i).getCodigoUnico());
                        registro.agregarCampo(inventario.obtenerInstrumento(i).getPrecio());
                        registro.agregarCampo(inventario.obtenerInstrumento(i).getStock());
                        registro.agregarCampo(inventario.obtenerInstrumento(i).getNombreInstrumento());
                        registro.agregarCampo(inventario.obtenerInstrumento(i).getMaterial());
                        registro.agregarCampo(((InstrumentoPercusion) inventario.obtenerInstrumento(i)).getTipoDePercusion());
                        registro.agregarCampo(((InstrumentoPercusion) inventario.obtenerInstrumento(i)).getTipoDeAltura());
                        archivoSalida.writeRegistro(registro);
                    } else if (inventario.obtenerInstrumento(i) instanceof InstrumentoCuerdas) {
                        registro = new Registro(8);
                        registro.agregarCampo(inventario.obtenerInstrumento(i).getCodigoUnico());
                        registro.agregarCampo(inventario.obtenerInstrumento(i).getPrecio());
                        registro.agregarCampo(inventario.obtenerInstrumento(i).getStock());
                        registro.agregarCampo(inventario.obtenerInstrumento(i).getNombreInstrumento());
                        registro.agregarCampo(inventario.obtenerInstrumento(i).getMaterial());
                        registro.agregarCampo(((InstrumentoCuerdas) inventario.obtenerInstrumento(i)).getTipoCuerdas());
                        registro.agregarCampo(((InstrumentoCuerdas) inventario.obtenerInstrumento(i)).getTipo());
                        registro.agregarCampo(((InstrumentoCuerdas) inventario.obtenerInstrumento(i)).getNumeroDeCuerdas());
                        archivoSalida.writeRegistro(registro);
                    }
                } catch (Exception exception) {
                    //Se captura excepción.
                }
            }
            //Se cierra el archivo.
            archivoSalida.close();
        }catch (Exception exception){
            //Se captura excepción.
        }
    }

    //Método: iniciar (@Override).
    //El método que permite comenzar con la lógica del programa, y acá está presente el menú principal de ella. Documentación en la interface.
    @Override
    public void iniciar() throws IOException {
        //Se lee el archivo de entrada (base de datos).
        ArchivoEntrada archivoEntrada = new ArchivoEntrada("base_de_instrumentos.csv");
        this.lecturaDeArchivo(archivoEntrada);

        //Si no hay instrumentos en el inventario, se lee automáticamente el archivo de instrumentos a agregar.
        if(inventario.getCantActual() == 0){
            ArchivoEntrada archivoEntrada1 = new ArchivoEntrada("instrumentos_a_agregar.csv");
            this.lecturaDeArchivo(archivoEntrada1);
        }

        String opcion = "";

        //Menú principal del programa.
        //Mientras la opción no sea 4 (Salir del programa), el programa sigue en ejecución.
        while (!opcion.equalsIgnoreCase("4")){
            StdOut.println("[****************************]");
            StdOut.println("¡Bienvenid@ a Beat The Rhythm!");
            StdOut.println("Ingrese una opción:");
            StdOut.println("[1]: Agregar instrumentos (mediante archivo CSV).");
            StdOut.println("[2]: Vender instrumento.");
            StdOut.println("[3]: Consultar instrumentos en el inventario.");
            StdOut.println("[4]: Cerrar la aplicación.");
            StdOut.println("[**************************]");
            opcion = StdIn.readString();

            //Switch dependiendo de la opción elegida. Se imprime mensaje de error en caso de que no sea válida.
            switch (opcion){
                case "1" -> this.agregarInstrumento();
                case "2" -> this.venderInstrumento();
                case "3" -> this.consultarInventario();
                case "4" -> this.cierre();
                default -> StdOut.println("Opción inválida, intente de nuevo.");
            }
        }
    }

    //Método: DespliegueDeDatos (@Override).
    //Se encarga de desplegar los datos, de un instrumento dado, dependiendo del tipo de instrumento que sea. Documentado en la interface.
    @Override
    public void DespliegueDeDatos(Instrumento instrumento) {
        if(instrumento instanceof InstrumentoViento){
            //Si el instrumento es una instancia de instrumento de viento, se despliegan sus datos que corresponden.
            StdOut.println("Nombre de instrumento: " + instrumento.getNombreInstrumento());
            StdOut.println("Tipo de instrumento: Viento");
            StdOut.println("Material de construcción: " + instrumento.getMaterial());
            StdOut.println("Código de producto: " + instrumento.getCodigoUnico());
            StdOut.println("Precio: " + instrumento.getPrecio());
            StdOut.println("Stock restante: " + instrumento.getStock());
        }else if (instrumento instanceof InstrumentoCuerdas){
            //Si el instrumento es una instancia de instrumento de cuerdas, se despliegan no solamente sus 5 características principales,
            //sino que también los adicionales al ser instrumento de cuerdas.
            StdOut.println("Nombre de instrumento: " + instrumento.getNombreInstrumento());
            StdOut.println("Tipo de instrumento: Cuerdas");
            StdOut.println("Material de construcción: " + instrumento.getMaterial());
            StdOut.println("Código de producto: " + instrumento.getCodigoUnico());
            StdOut.println("Precio: " + instrumento.getPrecio());
            StdOut.println("Stock restante: " + instrumento.getStock());
            StdOut.println("Tipo de cuerdas: " + ((InstrumentoCuerdas) instrumento).getTipoCuerdas());
            StdOut.println("Cantidad de cuerdas: " + ((InstrumentoCuerdas) instrumento).getNumeroDeCuerdas() + " cuerdas");
            StdOut.println("Tipo: " + ((InstrumentoCuerdas) instrumento).getTipo());
        }else if (instrumento instanceof InstrumentoPercusion){
            //Si el instrumento es una instancia de instrumento de percusión, se despliegan las 5 características principales
            //de un instrumento, y sus atributos adicionales al ser de percusión.
            StdOut.println("Nombre de instrumento: " + instrumento.getNombreInstrumento());
            StdOut.println("Tipo de instrumento: Percusión");
            StdOut.println("Material de construcción: " + instrumento.getMaterial());
            StdOut.println("Código de producto: " + instrumento.getCodigoUnico());
            StdOut.println("Precio: " + instrumento.getPrecio());
            StdOut.println("Stock restante: " + instrumento.getStock());
            StdOut.println("Tipo de percusión: " + ((InstrumentoPercusion) instrumento).getTipoDePercusion());
            StdOut.println("Tipo de altura: " + ((InstrumentoPercusion) instrumento).getTipoDeAltura());
        }
    }

    //Método: lecturaDeArchivo (@Override).
    //Uno de los aspectos más importantes del programa, y sus datos. Explicación y documentación de su funcionamiento en la interface.
    @Override
    public void lecturaDeArchivo(ArchivoEntrada archivoEntrada){
        try{
            while (!archivoEntrada.isEndFile()) {
                try {
                    Registro registro = archivoEntrada.getRegistro();
                    String codigoUnico = registro.getString();
                    int precio = registro.getInt();
                    int stock = registro.getInt();
                    String nombreDeInstrumento = registro.getString();
                    String material = registro.getString();
                    String campo6 = registro.getString();
                    String campo7 = registro.getString();
                    String campo8 = registro.getString();

                    //Se valida que el stock y precio sean válidos.
                    //Si no, se lanza una excepción y por ende, se salta la línea errónea.
                    if (stock < 0 || precio <= 0) {
                        throw new Exception();
                    }

                    //Se obtiene el instrumento (null si no existe).
                    Instrumento instrumentoABuscar = inventario.obtenerInstrumento(codigoUnico);
                    boolean instrumentoExiste;

                    //Se determina un booleano, si el instrumento existe.
                    if (instrumentoABuscar == null) {
                        instrumentoExiste = false;
                    }else{
                        instrumentoExiste = true;
                    }

                    //El campo 8 vendría a ser la cantidad de cuerdas de un instrumento de cuerdas.
                    //Si no es nulo, significa que sería un instrumento de cuerdas, por eso se transforma a una variable int.
                    int int_campo8;
                    if(campo8 != null){
                        int_campo8 = Integer.parseInt(campo8);
                    }else{
                        int_campo8 = 0;
                    }

                    //Si todas las características coinciden con el instrumento buscado (que, existe), se le suma su stock.
                    //Hay que tener en cuenta que no solo debe coincidir las 4 características principales (excluyendo stock), sino
                    //que también en las otras cualidades exclusivas de cada tipo.

                    //Si no coincide en alguna característica, significa que es un instrumento diferente pero con el mismo código
                    //de un instrumento ya existente, y por ende, no se agrega al sistema.

                    if (instrumentoExiste && instrumentoABuscar.getNombreInstrumento().equalsIgnoreCase(nombreDeInstrumento) && instrumentoABuscar.getMaterial().equalsIgnoreCase(material)
                            && instrumentoABuscar.getPrecio() == precio && instrumentoABuscar.getCodigoUnico().equals(codigoUnico)) {

                        if (instrumentoABuscar instanceof InstrumentoViento || (instrumentoABuscar instanceof InstrumentoCuerdas && ((InstrumentoCuerdas) instrumentoABuscar).getTipoCuerdas().equalsIgnoreCase(campo6)
                                && ((InstrumentoCuerdas) instrumentoABuscar).getTipo().equalsIgnoreCase(campo7) && ((InstrumentoCuerdas) instrumentoABuscar).getNumeroDeCuerdas() == int_campo8) || (instrumentoABuscar instanceof InstrumentoPercusion
                                && ((InstrumentoPercusion) instrumentoABuscar).getTipoDePercusion().equalsIgnoreCase(campo6) && ((InstrumentoPercusion) instrumentoABuscar).getTipoDeAltura().equalsIgnoreCase(campo7))) {
                            instrumentoABuscar.setStock(instrumentoABuscar.getStock() + stock);
                        }
                    }

                    //Acá se valida cada campo, teniendo en cuenta que el instrumento no existe.
                    //Si coinciden con las condiciones de algún if, se agrega al inventario.

                    if (!instrumentoExiste) {
                        if(nombreDeInstrumento != null && codigoUnico != null && material != null) {
                            //Acá se valida que los campos principales no sean nulos.

                            if (campo6 == null && campo7 == null && campo8 == null) {
                                //Si el campo 6,7 y 8 son nulos, es un instrumento de viento.
                                Instrumento instrumentoViento = new InstrumentoViento(nombreDeInstrumento, material, codigoUnico, precio, stock);
                                inventario.agregarInstrumento(instrumentoViento);

                            } else if (campo6 != null && campo7 != null && campo8 == null) {
                                //Si el campo 6 y 7 no son nulos, pero el campo 8 sí, es un instrumento de percusión.
                                String tipoDePercusion = campo6;
                                String tipoDeAltura = campo7;
                                Instrumento instrumentoDePercusion = new InstrumentoPercusion(nombreDeInstrumento, material, codigoUnico, precio, stock, tipoDePercusion, tipoDeAltura);
                                inventario.agregarInstrumento(instrumentoDePercusion);

                            } else if (campo6 != null && campo7 != null && campo8 != null) {
                                //Si ningún campo adicional es nulo, es un instrumento de cuerdas.
                                String tipoDeCuerdas = campo6;
                                String tipo = campo7;
                                int cantCuerdas = Integer.parseInt(campo8);
                                //Se valida también que la cantidad de cuerdas en el campo 8 sea mayor a 0.
                                if (cantCuerdas > 0) {
                                    Instrumento instrumentoCuerdas = new InstrumentoCuerdas(nombreDeInstrumento, material, codigoUnico, precio, stock, tipoDeCuerdas, tipo, cantCuerdas);
                                    inventario.agregarInstrumento(instrumentoCuerdas);
                                }
                            }
                        }
                    }
                } catch (Exception exception) {
                    //Captura de excepción.
                }
            }
            //Se cierra el archivo.
            archivoEntrada.close();
        }catch (Exception exception){
            //Captura de excepción.
        }
    }


    //copiar y pegar en caso de//

    /*
Cuerdas:

if((nombreDeInstrumento.equalsIgnoreCase("Guitarra") || nombreDeInstrumento.equalsIgnoreCase("Bajo") || nombreDeInstrumento.equalsIgnoreCase("Arpa") ||
 nombreDeInstrumento.equalsIgnoreCase("Violín")) && (tipoCuerdas.equalsIgnoreCase("Nylon") || tipoCuerdas.equalsIgnoreCase("Acero") || tipoCuerdas.equalsIgnoreCase("Tripa"))
  && (cantidadCuerdas > 0) && (material.equalsIgnoreCase("Madera") || material.equalsIgnoreCase("Metal")) && (tipo.equalsIgnoreCase("Acústico") || tipo.equalsIgnoreCase("Eléctrico"))){

}


Percusión:

if((nombreDeInstrumento.equalsIgnoreCase("Bongó") || nombreDeInstrumento.equalsIgnoreCase("Cajón") || nombreDeInstrumento.equalsIgnoreCase("Campanas tubulares") ||
 nombreDeInstrumento.equalsIgnoreCase("Bombo")) && (tipoDePercusión.equalsIgnoreCase("Membranófono") || tipoDePercusión.equalsIgnoreCase("Idiófono"))
  && (material.equalsIgnoreCase("Madera") || material.equalsIgnoreCase("Metal") || material.equalsIgnoreCase("Piel")) &&
  (tipoDeAltura.equalsIgnoreCase("Definida") || tipoDeAltura.equalsIgnoreCase("Indefinida"))){
}

Viento:

if((nombreDeInstrumento.equalsIgnoreCase("Trompeta") || nombreDeInstrumento.equalsIgnoreCase("Saxofón") || nombreDeInstrumento.equalsIgnoreCase("Clarinete") ||
 nombreDeInstrumento.equalsIgnoreCase("Flauta traversa")) && (material.equalsIgnoreCase("Madera") || material.equalsIgnoreCase("Metal"))){

 }
     */
}