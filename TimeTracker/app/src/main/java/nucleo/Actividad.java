package nucleo;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import timetracker.app.practicads.com.timetracker.R;

/**
 * Clase que implementa la interficie serializable para poder serializar todos sus objetos.
 * Esta clase es la principal del patron composite.
 * El patron Composite sirve para construir objetos complejos a partir de otros mas simples y similares entre si,
 * gracias a la composicion recursiva y a una estructura en forma de arbol.
 * Se necesita la clase abstracta Actividad para que Tarea y Proyecto se puedan tratar como actividades.
 * @author Mario, Hector, Edgar
 */
@SuppressWarnings("serial")
public abstract class Actividad implements Serializable, Comparable<Actividad> {


    /**
     * Funcion que inicializa una actividad.
     * Siempre y cuando el padre de esta no sea nulo, se añade una actividad.
     * @param nombre: nombre de la actividad
     * @param descripcion: descripcion de la actividad
     * @param padre: padre de la actividad
     */
    public Actividad(String nombre, String descripcion, Proyecto padre){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.padre = padre;
        duracion = 0;
        fechaInicial = null;
        fechaFinal = null;

        System.out.println("Creada Actividad " + this.nombre + " con descripcion " + this.descripcion);

        //Añade una actividad a la lista de actividades del padre solo si este no es nulo.
        if (padre != null)
            padre.añadirActividad(this);
    }

    
    /** 
     * @uml.property name="nombre"
     */
    private String nombre;

    /** 
     * Getter of the property <tt>nombre</tt>
     * @return  Returns the nombre.
     * @uml.property  name="nombre"
     */
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) { this.nombre = nombre;}


    /** 
     * @uml.property name="proyecto"
     * @uml.associationEnd inverse="actividades:Proyecto"
     */
    private Proyecto proyecto;

    /** 
     * Getter of the property <tt>proyecto</tt>
     * @return  Returns the proyecto.
     * @uml.property  name="proyecto"
     */
    public Proyecto getProyecto() {
        return proyecto;
    }

    
    /**
     * @uml.property  name="descripcion"
     */
    private String descripcion;

    /**
     * Getter of the property <tt>descripcion</tt>
     * @return  Returns the descripcion.
     * @uml.property  name="descripcion"
     */
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    
    /**
     * @uml.property  name="padre"
     */
    private Proyecto padre;

    /**
     * Getter of the property <tt>padre</tt>
     * @return  Returns the padre.
     * @uml.property  name="padre"
     */
    public Proyecto getPadre() {
        return padre;
    }


    /**
     * Getter of the property <tt>nombrePadre</tt>
     * @return  Returns the nombrePadre.
     */
    public String getNombrePadre(){
        return padre.getNombre();
    }

    
    /**
     * Funcion que convierte segundos en horas minutos y segundos.
     * @param segundos estos segundos se convertiran en un formato HH:mm:ss.
     * @return formatoFecha devuelve los segundos pasados por parametro convertidos en el formato hh:mm:ss.
     */
    public String conversorSegundosDigital(int segundos){
        Date fecha = new Date(segundos*1000);
        String formatoFecha = new SimpleDateFormat("00:mm:ss").format(fecha); 
        return formatoFecha;
    }

    
    /**
     * Funcion que devuelve la lista de actividades de un proyecto en concreto.
     * @param proyecto
     * @return List<Actividad>
     */
    public List<Actividad> getActividadesProyecto(Proyecto proyecto) {
        return proyecto.getActividades();
    }

    public int getImg()
    {
        return R.drawable.ic_folder_black_48dp;
    }


    /**
     * Funcion que muestra por pantalla el estado del proyecto en un instante determinado de tiempo.
     */
    public void imprimir(){
        Proyecto aux = padre;
        /*
         * Busca el proyecto root
         */
        while(aux.getPadre() != null)
            aux = aux.getPadre();

        Proyecto root = aux; // Asigna a la variable root aux (que es la raiz del arbol)
        Proyecto proyecto1 = (Proyecto) getActividadesProyecto(root).get(0); //P1


        // Printa todo el arbol

        System.out.println("   Nombre               Tiempo de Inicio"
        + "                      Tiempo Final                   Duracion        ");
        System.out.println("--------------|------------------------------------|"
        + "----------------------------------|-------------------");

        System.out.println("    "+proyecto1.getNombre() + "            "
        + proyecto1.getFechaInicial() + "       "
        + proyecto1.getFechaFinal() + "          " + conversorSegundosDigital(proyecto1.getDuracion()));

        Tarea tarea = (Tarea) getActividadesProyecto(proyecto1).get(0); //T3

        System.out.println("    "+tarea.getNombre() + "        "
        + tarea.getFechaInicial() + "       "
        + tarea.getFechaFinal() + "          " + conversorSegundosDigital(tarea.getDuracion()));

        Proyecto proyecto2 = (Proyecto) getActividadesProyecto(proyecto1).get(1); //P2

        System.out.println("    "+proyecto2.getNombre() + "            "
        + proyecto2.getFechaInicial() + "       "
        + proyecto2.getFechaFinal() + "          " + conversorSegundosDigital(proyecto2.getDuracion()));


        tarea =  (Tarea) getActividadesProyecto(proyecto2).get(0); //T2

        System.out.println("    "+tarea.getNombre() + "        "
        + tarea.getFechaInicial() + "       "
        + tarea.getFechaFinal() + "          " + conversorSegundosDigital(tarea.getDuracion()));

        tarea = (Tarea) getActividadesProyecto(proyecto2).get(1); //T1

        System.out.println("    "+tarea.getNombre() + "        "
        + tarea.getFechaInicial() + "       "
        + tarea.getFechaFinal() + "          " + conversorSegundosDigital(tarea.getDuracion()));

        System.out.println("--------------|------------------------------------|"
        + "----------------------------------|-------------------");
    }

    /**
     * @uml.property  name="fechaFinal"
     */
    private Date fechaFinal;

    /**
     * Getter of the property <tt>fechaFinal</tt>
     * @return  Returns the fechaFinal.
     * @uml.property  name="fechaFinal"
     */
    public Date getFechaFinal() {
        return fechaFinal;
    }

    /**
     * @uml.property  name="fechaInicial"
     */
    private Date fechaInicial;

    /**
     * Getter of the property <tt>fechaInicial</tt>
     * @return  Returns the fechaInicial.
     * @uml.property  name="fechaInicial"
     */
    public Date getFechaInicial() {
        return fechaInicial;
    }


    /**
     * @uml.property  name="duracion"
     */
    private int duracion;

    /**
     * Getter of the property <tt>duracion</tt>
     * @return  Returns the duracion.
     * @uml.property  name="duracion"
     */
    public int getDuracion() {
        return duracion;
    }


    /**
     * Funcion que recibe la fechaFinal y la duracion de un intervalo.
     * Con estos datos, modifica la fecha y duracion por los datos correctos
     * que corresponden al instante de tiempo.
     * Cada clase se encarga de actualizarse ella misma gracias a esta funcion.
     * @param fechaFinal: fechaFinal que viene desde intervalo (desde update())
     * @param fechaInicial: fechaInicial que viene desde intervalo (desde update())
     * @param dur: duracion que viene desde intervalo (desde update())
     */
    public void actualizar(Date fechaFinal, Date fechaInicial, int dur){

        if(this.fechaInicial == null)
            this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal; // Actualiza fechaFinal hasta root
        duracion += dur; // Actualiza duracion hasta root

        //System.out.println("Actividad ->" + this.getNombre() + fechaInicial + " " + fechaFinal + " " + duracion);

        /**
         * Actualizamos hasta que padre sea nulo
         */
        if (padre != null)
        {
            padre.actualizar(fechaFinal, fechaInicial, dur);
        }

    }


    /**
     * Setter of the property <tt>proyecto</tt>
     * @param proyecto  The proyecto to set.
     * @uml.property  name="proyecto"
     */
    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }


    /**
     * Método que ordena la lista de actividades por proyectos y tareas
     * @param o
     * @return
     */
    @Override
    public int compareTo(Actividad o) {
        int compare = nombre.compareTo(o.nombre);
        if (compare < 0) {
            return -1;
        } else {
            return 1;
        }
    }

}
