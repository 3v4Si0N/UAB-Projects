package nucleo;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Clase concreta para la construccion de los informes breves.
 * Los informes Breves solo tienen informacion de proyectos raiz,
 * su tiempo total y sus fechas de inicio y fin.
 * @author Mario, Hector, Edgar
 */


public class InformeBreve extends Informe {

  /**
   * Metodo que construye el esqueleto del informe breve,
   * añadimos todos los elementos que contiene y sus distintos apartados
   * como las tablas de periodo y de proyectos raiz.
   * @param root proyecto raiz del arbol
   * @param fechaDesde fecha inicial de informe
   * @param fechaHasta fecha final de informe
  */
    public InformeBreve(Proyecto root, Date fechaDesde, Date fechaHasta)
    {
        super(root, fechaDesde, fechaHasta);
        invariante(root, fechaDesde, fechaHasta);
        añadirElementoInforme(new Linea());
        añadirElementoInforme(new Titulo("Informe breve"));
        añadirElementoInforme(new Linea());
        añadirElementoInforme(new Subtitulo("Periodos"));
        añadirElementoInforme(crearTablaPeriodos(fechaDesde, fechaHasta)); // tablaSuperior del informe
        añadirElementoInforme(new Linea());
        añadirElementoInforme(new Subtitulo("Proyectos raiz"));
        añadirElementoInforme(crearTablaProyectosRaiz(fechaDesde, fechaHasta, root)); // tablaInferior dentro del informe
        añadirElementoInforme(new Linea());
        añadirElementoInforme(new Texto("Time Tracker v1.0"));
    }

    /**
     * Metodo que crea la tabla de periodos del informe breve.
     * La tabla periodos contiene la fecha donde empieza el informe, la fecha 
     * donde acaba el informe, y la fecha de generacion del informe.
     */
    @Override
    public Tabla crearTablaPeriodos(Date fechaDesde, Date fechaHasta) {

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
     * Metodo que crea la tabla de proyectos raiz del informe breve.
     * Esta tabla contiene los proyectos raiz, con sus tiempos de inicio, fin y duracion.
     */
    @Override
    public Tabla crearTablaProyectosRaiz(Date fechaDesde, Date fechaHasta, Proyecto root)
    {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String fecha;
        
        Tabla tabla = new Tabla(0,4);

        ArrayList<String> cabeceras = new ArrayList<String>();
        cabeceras.add("Proyectos");
        cabeceras.add("Tiempo inicio");
        cabeceras.add("Tiempo final");
        cabeceras.add("Tiempo total");
        tabla.insertarFila(cabeceras);
        
        //bucle para recorrer todos los proyectos
        for (Actividad actividad: root.getActividades())
        {
            if(actividad.getClass().toString().endsWith("Proyecto"))
            {
                Proyecto p = (Proyecto) actividad;
                ArrayList<String> fila = new ArrayList<String>();

                fila.add(p.getNombre());
                fecha = df.format(p.getFechaInicial());
                fila.add(fecha);
                fecha = df.format(p.getFechaFinal());
                fila.add(fecha);
                fila.add(actividad.conversorSegundosDigital(p.getDuracion()));

                tabla.insertarFila(fila);
            }
        }
        return tabla;
    }

    
    /**
     *Por tal de aplicar el diseño por contrato a los metodos de la clase
     *se ha implementado este metodo para establecer las invariantes y
     *las pre y post condiciones necesarias.
     */
    @Override
    public void invariante(Proyecto root, Date fechaDesde, Date fechaHasta) {
        // TODO Auto-generated method stub
        assert root != null : "Error, el informe breve debe tener un proyecto raiz";
        assert fechaDesde != null : "Error, la fecha de inicio de informe breve no puede ser nula";
        assert fechaHasta != null : "Error, la fecha de finalizacion del informe breve no puede ser nula";
    }
}
