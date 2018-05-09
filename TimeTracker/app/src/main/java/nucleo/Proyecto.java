package nucleo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/** 
 * Un proyecto es un conjunto de actividades que realiza un usuario.
 * En un proyecto se cuenta el tiempo que dura cada actividad, y tambien el tiempo total de la suma de las actividades.
 * Internamente, un proyecto es el padre de todos los elementos que contiene directamente ya
 * sean subproyectos o tareas. Es una de las clases que se integran en el composite.
 * @author Mario, Hector, Edgar
 */


@SuppressWarnings("serial")
public class Proyecto extends Actividad {
  /**
   * Inicializacion para la definicion de los niveles de depuracion.
   *
   * @uml.property name="logger"
   */
  private static Logger logger = LoggerFactory.getLogger(Actividad.class);

  /**
   * Constructor
   * Inicializa un proyecto
   *
   * @param nombre:        nombre del proyecto
   * @param descripcion:   descripcion del proyecto
   * @param proyectoPadre: proyecto padre de este proyecto
   */
  public Proyecto(String nombre, String descripcion, Proyecto proyectoPadre) {
    super(nombre, descripcion, proyectoPadre);
    logger.info("Proyecto con nombre --> " + nombre + ", descripcion--> " + descripcion);
  }

  /**
   * @uml.property name="actividades"
   * @uml.associationEnd multiplicity="(0 -1)" dimension="1" ordering="true" aggregation="shared" inverse="proyecto:Actividad"
   */
  private List<Actividad> actividades = new java.util.ArrayList<Actividad>();

  /**
   * Getter of the property <tt>actividades</tt>
   *
   * @return Returns the actividades.
   * @uml.property name="actividades"
   */
  public List<Actividad> getActividades() {
    return actividades;
  }

  /**
   * Metodo que añade una actividad a una lista de actividades.
   */
  public void añadirActividad(Actividad actividad) {
    actividades.add(actividad);
  }
    
}