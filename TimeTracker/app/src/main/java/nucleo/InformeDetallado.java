package nucleo;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Clase concreta para la construccion de los informes Detallados.
 * Los informes Detallados contienen la informacion de un informe breve,
 * mas las fechas de inicio y final de los subproyectos, tareas e intervalos.
 * @author Mario, Hector, Edgar
 */


public class InformeDetallado extends Informe {

    /**
     * metodo para construir el esqueleto del informe detallado,
     * añadimos todos los elementos que contiene y sus distintos apartados
     * como las tablas de periodo y de proyectos raiz y subproyectos.
     * @param fechaDesde tiempo inicio informe
     * @param fechaHasta tiempo finali informe
     * @param root proyecto raiz
     */
    public InformeDetallado(Proyecto root, Date fechaDesde, Date fechaHasta) {
        super(root, fechaDesde, fechaHasta);
        invariante(root, fechaDesde, fechaHasta);
        subproyectos = new ArrayList<Actividad>();
        tareas = new ArrayList<Actividad>();
        intervalos = new ArrayList<Intervalo>();

        recorrerArbol(root);
        
        añadirElementoInforme(new Linea());
        añadirElementoInforme(new Titulo("Informe detallado"));
        añadirElementoInforme(new Linea());
        añadirElementoInforme(new Subtitulo("Periodos"));
        añadirElementoInforme(crearTablaPeriodos(fechaDesde, fechaHasta));
        añadirElementoInforme(new Linea());

        añadirElementoInforme(new Subtitulo("Proyectos raiz"));
        añadirElementoInforme(crearTablaProyectosRaiz(fechaDesde, fechaHasta, root));
        añadirElementoInforme(new Linea());

        añadirElementoInforme(new Subtitulo("Subproyectos"));
        añadirElementoInforme(new Texto("Se incluyen en la siguiente tabla nada mas los subproyectos que " +
                "tengan alguna tarea con algun intervalo dentro del periodo."));
        añadirElementoInforme(crearTablaSubproyectos(fechaDesde, fechaHasta, root));
        añadirElementoInforme(new Linea());

        añadirElementoInforme(new Subtitulo("Tareas"));
        añadirElementoInforme(new Texto("Se incluyen en la siguiente tabla la duracion de todas las tareas " +
                "y el proyecto al cual pertenecen."));
        añadirElementoInforme(crearTablaTareas(fechaDesde, fechaHasta, root));
        añadirElementoInforme(new Linea());

        añadirElementoInforme(new Subtitulo("Intervalos"));
        añadirElementoInforme(new Texto("Se incluyen en la siguiente tabla el tiempo de inicio, el tiempo final " +
                "y la duracion de todos los intervalos entre la fecha inicial y la fecha final especificadas, " +
                "y la tarea y proyecto al cual pertenecen."));
        añadirElementoInforme(crearTablaIntervalos(fechaDesde, fechaHasta, root));
        añadirElementoInforme(new Linea());
        añadirElementoInforme(new Texto("Time Tracker v1.0"));
    }

    /**
     * Metodo que crea la tabla de periodos del informe detallado.
     * La tabla periodos contiene la fecha donde empieza el informe, la fecha 
     * donde acaba el informe, y la fecha de generacion del informe.
     */
    @Override
    public Tabla crearTablaPeriodos(Date fechaDesde, Date fechaHasta) {
        // TODO Auto-generated method stub
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String fecha;

        Tabla tabla = new Tabla(0,2);
        ArrayList<String> fila1 = new ArrayList<String>();
        fila1.add(" -- ");
        fila1.add("\tFecha");
        tabla.insertarFila(fila1);

        fecha = df.format(fechaDesde);
        ArrayList<String> fila2 = new ArrayList<String>();
        fila2.add("Desde     ");
        fila2.add(fecha);
        tabla.insertarFila(fila2);

        fecha = df.format(fechaHasta);
        ArrayList<String> fila3 = new ArrayList<String>();
        fila3.add("Hasta     ");
        fila3.add(fecha);
        tabla.insertarFila(fila3);

        fecha = df.format(new Date());
        ArrayList<String> fila4 = new ArrayList<String>();
        fila4.add("Generacion");
        fila4.add(fecha);
        tabla.insertarFila(fila4);

        return tabla;
    }

    /**
     * Metodo que crea la tabla de proyectos raiz del informe detallado.
     * Esta tabla contiene los proyectos raiz, con sus tiempos de inicio, fin y duracion.
     */
    @Override
    public Tabla crearTablaProyectosRaiz(Date fechaDesde, Date fechaHasta, Proyecto root) {
        // TODO Auto-generated method stub
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

        Tabla tabla = new Tabla(0,4);

        ArrayList<String> cabeceras = new ArrayList<String>();
        cabeceras.add("No.");
        cabeceras.add("Proyecto");
        cabeceras.add("Tiempo inicio");
        cabeceras.add("Tiempo final");
        cabeceras.add("Tiempo total");
        tabla.insertarFila(cabeceras);

        Tabla proyectosRaiz = crearTablaCompartida(tabla, root, df, root.getActividades());
        return proyectosRaiz;
    }
    
    /**
     * Metodo que crea la tabla de subproyectos del informe detallado.
     * Esta tabla contiene los subproyectos, con sus tiempos de inicio, fin y duracion.
     * @param fechaDesde tiempo inicio informe
     * @param fechaHasta tiempo finali informe
     * @param root proyecto raiz
     * @return
     */
    public Tabla crearTablaSubproyectos(Date fechaDesde, Date fechaHasta, Proyecto root) {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

        Tabla tabla = new Tabla(0,4);

        ArrayList<String> cabeceras = new ArrayList<String>();
        cabeceras.add("No.");
        cabeceras.add("Subproyecto");
        cabeceras.add("Tiempo inicio");
        cabeceras.add("Tiempo final");
        cabeceras.add("Tiempo total");
        tabla.insertarFila(cabeceras);

        Tabla tablaSubproyectos = crearTablaCompartida(tabla, root, df, subproyectos);
        return tablaSubproyectos;
    }

    /**
     * Metodo que crea la tabla de tareas del informe detallado.
     * Esta tabla contiene las tareas, con sus tiempos de inicio, fin, duracion
     * y el proyecto al que pertenecen.
     * @param fechaDesde tiempo inicio informe
     * @param fechaHasta tiempo finali informe
     * @param root proyecto raiz
     * @return
     */
    public Tabla crearTablaTareas(Date fechaDesde, Date fechaHasta, Proyecto root){
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

        Tabla tabla = new Tabla(0,4);

        ArrayList<String> cabeceras = new ArrayList<String>();
        cabeceras.add("Proyecto");
        cabeceras.add("Tarea");
        cabeceras.add("Tiempo inicio");
        cabeceras.add("Tiempo final");
        cabeceras.add("Tiempo total");
        tabla.insertarFila(cabeceras);

        Tabla tablaTareas = crearTablaCompartida(tabla, root, df, tareas);
        return tablaTareas;
    }
 
    /**
     * Metodo que crea la tabla de intervalos del informe detallado.
     * Esta tabla contiene los intervalos, con sus tiempos de inicio, fin, duracion
     * y la tarea a la que pertenecen.
     * @param fechaDesde tiempo inicio informe
     * @param fechaHasta tiempo finali informe
     * @param root proyecto raiz
     * @return tabla devuelve la tabla con todos los datos insertados en ella.
     */
    public Tabla crearTablaIntervalos(Date fechaDesde, Date fechaHasta, Proyecto root) {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

        Tabla tabla = new Tabla(0,4);

        ArrayList<String> cabeceras = new ArrayList<String>();
        cabeceras.add("Tarea");
        cabeceras.add("Intervalo");
        cabeceras.add("Tiempo inicio");
        cabeceras.add("Tiempo final");
        cabeceras.add("Tiempo total");
        tabla.insertarFila(cabeceras);

        int count = 0;
        String fecha;
        for (int i = 0; i < intervalos.size(); i++)
        {
            Intervalo interv = intervalos.get(i); 
            ArrayList<String> fila = new ArrayList<String>();
            count++;

            fila.add(getTareaPadre(interv).getNombre());
            if((i != 0) && (getTareaPadre(interv).getNombre() != intervalos.get(i-1).getTareaPadre().getNombre()))
            {
                count = 1;
            }
            fila.add(String.valueOf(count));
            fecha = df.format(interv.getFechaInicial());
            fila.add(fecha);
            fecha = df.format(interv.getFechaFinal());
            fila.add(fecha);
            fila.add(getTareaPadre(interv).conversorSegundosDigital(interv.getDuracion()));

            tabla.insertarFila(fila);


        }
        return tabla;
    }

    /**
     * Metodo que nos ayuda a crear tres tablas que contienen datos diferentes utilizando el parametro lista,
     * con el que podremos crear la tabla de los proyectos raiz, la tabla de los subproyectos
     * y la tabla de las tareas sin tener que repetir tres veces el mismo c�digo.
     * @param tabla
     * @param root
     * @param df
     * @param lista
     * @return tabla
     */
    public Tabla crearTablaCompartida(Tabla tabla, Proyecto root, DateFormat df, List<Actividad> lista)
    {
        int count = 0;
        String fecha;
        for (Actividad actividad: lista)
        {
            Actividad act = actividad;
            ArrayList<String> fila = new ArrayList<String>();
            count++;

            if(lista == tareas){
                fila.add(act.getNombrePadre());
            } else {
                fila.add(String.valueOf(count));
            }
            fila.add(act.getNombre());
            System.out.println("FECHA INICIAL--> " + act.getFechaInicial());
            fecha = df.format(act.getFechaInicial());
            fila.add(fecha);
            fecha = df.format(act.getFechaFinal());
            fila.add(fecha);
            fila.add(actividad.conversorSegundosDigital(act.getDuracion()));

            tabla.insertarFila(fila);
        }
        return tabla;
    }

    /**
     * 
     */
    private List<Actividad> subproyectos;
    private List<Actividad> tareas;
    private List<Intervalo> intervalos;

    /**
     * Metodo que sirve para recorrer el arbol de actividades e insertar
     * los subproyectos del proyecto raiz en una lista,
     * las tareas en otra y los intervalos en otra,
     * para que posteriormente se puedan crear las tablas utilizando estas listas.
     * @param root se recorrera el arbol desde el proyecto root hasta llegar a los intervalos de cada tarea
     */
    private void recorrerArbol(Proyecto root) {
        for (int i = 0; i < root.getActividades().size(); i++) {
            //Si es un proyecto
            if (getActividad(root, i).getClass().toString().endsWith("Proyecto")) {
                subproyectos.add(getActividad(root, i));
                System.out.println("PROYECTOS INSERTADOS --> " + getActividad(root, i).getNombre());
                recorrerArbol((Proyecto) getActividad(root, i));
            } else {
                //Si es una tarea
                if(getActividad(root, i).getFechaInicial() != null) // Comprobamos que la tarea no tenga fechas nulas
                {
                    tareas.add(getActividad(root, i));
                    System.out.println("TAREAS INSERTADAS --> " + getActividad(root, i).getNombre());
                    //Recorremos los intervalos de cada tarea y los añadimos a la lista
                    for (int j = 0; j < ((Tarea) getActividad(root, i)).getIntervalos().size(); j++)
                    { 
                        intervalos.add(((Tarea) getActividad(root, i)).getIntervalos().get(j));
                    }
                }
            }
        }
    }
    
    /**
     *Por tal de aplicar el diseño por contrato a los metodos de la clase
     *se ha implementado este metodo para establecer las invariantes y
     *las pre y post condiciones necesarias.
     */
    @Override
    public void invariante(Proyecto root, Date fechaDesde, Date fechaHasta) {
        // TODO Auto-generated method stub
        assert root != null : "Error, el informe detallado debe tener un proyecto raiz";
        assert fechaDesde != null : "Error, la fecha de inicio de informe detallado no puede ser nula";
        assert fechaHasta != null : "Error, la fecha de finalizacion del informe detallado no puede ser nula";
    }


    private Tarea getTareaPadre(Intervalo interv)
    {
        return interv.getTareaPadre();
    }

    private Actividad getActividad(Proyecto proyecto, int i)
    {
        return proyecto.getActividades().get(i);
    }
}

