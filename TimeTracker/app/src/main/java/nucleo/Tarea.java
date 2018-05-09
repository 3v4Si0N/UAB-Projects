package nucleo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
/** 
 * Una tarea es una actividad concreta que realiza un usuario y pertenece a un proyecto.
 * La duracion de una tarea se obtiene contando el tiempo que se dedica a realizar dicha tarea.
 * El tiempo en que estamos haciendo la tarea lo llamamos intervalo. 
 * Una tarea puede empezarse en un momento, pararse y seguir mas tarde o no.
 * El tiempo total que dura una tarea lo obtendremos con la suma de sus intervalos.
 * Esta Clase se encarga de crear, empezar y parar una tarea.
 * @author Mario, Hector, Edgar
 */
@SuppressWarnings("serial")
public class Tarea extends Actividad {

  /**
   * Inicializacion para la definicion de los niveles de depuracion.
   * @uml.property  name="logger"
   */
  private static Logger logger = LoggerFactory.getLogger(Actividad.class);

  /**
   * @uml.property  name="intervalos"
   */
  private ArrayList<Intervalo> intervalos;

  /**
   * Getter of the property <tt>intervalos</tt>
   * @return  Returns the intervalos.
   * @uml.property  name="intervalos"
   */
  public ArrayList<Intervalo> getIntervalos() {
    return intervalos;
  }


  /**
   * Costructor encargado de inicializar una tarea.
   * String nombre: nombre de la tarea
   * String descripcion: descripcion de la tarea
   * Proyecto padre: padre de la tarea
   */
  public Tarea(String nombre, String descripcion, Proyecto padre) {
    super(nombre, descripcion, padre);
    logger.info("Tarea con nombre --> " + nombre + ", descripcion--> " + descripcion);
    intervalos = new ArrayList<Intervalo>();
  }


  /**
   * Metodo que empieza una tarea creando un intervalo para esta.
   */

  public void empezarTarea() {
    Intervalo newIntervalo = new Intervalo(this); // Crea un nuevo intervalo
    newIntervalo.empezarIntervalo(); // Empieza el intervalo de esta tarea creado anteriormente
    logger.debug("Ha empezado la tarea --> " + this.getNombre());
    estaEncendido = true;
  }

  /**
   * Metodo que detiene la tarea parando el ultimo intervalo activo.
   */

  private boolean estaEncendido = false;

  public void pararTarea() {
    intervalos.get(intervalos.size() - 1).pararIntervalo(); // Para el ultimo intervalo activo
    logger.debug("Se ha parado la tarea --> " + this.getNombre());
    estaEncendido = false;
  }

  public boolean esCronometroEncendido()
  {
    return estaEncendido;
  }


  /**
   * Metodo que añade un intervalo a la lista de
   * intervalos de esta tarea (una tarea puede tener varios intervalos).
   * @param intervalo: intervalo que va a ser añadido a la lista.
   */
  public void addIntervalo(Intervalo intervalo) {
    intervalos.add(intervalo);
  }
  
  public Intervalo getIntervalo(int num) {
    return getIntervalos().get(num);
  }

}