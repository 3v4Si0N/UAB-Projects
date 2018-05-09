package nucleo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

/**
 * Un intervalo es una porcion de tiempo en que se realiza una tarea.
 * Una tarea puede estar compuesta por un solo intervalo o por varios intervalos.
 * Los intervalos de una tarea no tienen por que ser consecutivos.
 * La clase intervalo es la clase que observa el clock segun el patron de diseño Observer
 * durante la cronometracion de la tarea en la variable duracion se guarda el intervalo que es la 
 * diferencia entre la fecha inicial de la tarea y la fecha final. 
 * @author Mario, Hector, Edgar
 * @uml.dependency   supplier="Reloj" 
 */
@SuppressWarnings({ "serial", "unused" })
public class Intervalo implements Observer, Serializable{

    /**
     * Inicializacion para la definicion de los niveles de depuracion.
     * @uml.property  name="logger"
     */
    //private static Logger logger = LoggerFactory.getLogger(Actividad.class);

    /**
     * Fecha de inicio del intervalo.
     * @uml.property  name="fechaInicial"
     */
  private Date fechaInicial;

    /**
     * Fecha de final del intervalo.
     * @uml.property  name="fechaFinal"
     */
  private Date fechaFinal;

    /**
     * Variable que guarda el tiempo que esta activo este intervalo.
     * @uml.property  name="duracion"
     */
  private int duracion;

    /**
     * @uml.property  name="tareaPadre"
     */
  
  public Date getFechaInicial() {
    return fechaInicial;
  }


  public Date getFechaFinal() {
    return fechaFinal;
  }

  public int getDuracion() {
    return duracion;
  }
  
  private Tarea tareaPadre;

    /**
     * Getter of the property <tt>tareaPadre</tt>
     * @return  Returns the tareaPadre.
     * @uml.property  name="tareaPadre"
     */
  public Tarea getTareaPadre() {
    return tareaPadre;
  }

  /**
     * Setter of the property <tt>tareaPadre</tt>
     * @param tareaPadre  The tareaPadre to set.
     * @uml.property  name="tareaPadre"
     */
  public void setTareaPadre(Tarea tareaPadre) {
    this.tareaPadre = tareaPadre;
  }


  /**
     * Constructor que inicializa los intervalos de cada tarea.
     * Tarea padre: tarea a la que pertenece el intervalo
     */ 
  public Intervalo(Tarea padre) {
    setTareaPadre(padre); // Se le asigna a este intervalo su tarea padre
    Reloj reloj = Reloj.getInstance();
    tareaPadre.addIntervalo(this); // Añade este intervalo a la lista de
                                   //intervalos de su tarea padre
    duracion = 0;
    fechaInicial = reloj.getNuevaFecha(); // Obtiene una fechaInicial de reloj
    fechaFinal = null;
  }



  /** 
   * Metodo que actualiza la duracion y fechaFinal de un intervalo y a su vez actualiza hasta root.
   * Observable arg0: la clase observable.
   * Object arg1: fecha del reloj
   */  


  public void update(Observable arg0, Object arg1) {
    // TODO Auto-generated method stub
    Reloj reloj = (Reloj) arg0;

    fechaFinal = reloj.getNuevaFecha(); // Asigna al intervalo su fecha final en ese momento
    duracion += reloj.getPeriodo(); // Asigna al intervalo su duracion en ese momento

    // Actualiza fechaFinal, fechaInicial si hace falta y duracion hasta root
    // La fecha que se le pasa a tarea y proyecto es la fechaInicial del primer intervalo
    tareaPadre.actualizar(reloj.getNuevaFecha(), getIntervalo(0).getFechaInicial(), 
            reloj.getPeriodo());

    //logger.debug("Duracion del intervalo--> " + duracion);
    //System.out.println("intervalo de->" + tareaPadre.getNombre() + fechaInicial + " " 
    //+ fechaFinal + " " + duracion);
  }
  
  public Intervalo getIntervalo(int num) {
    return tareaPadre.getIntervalo(num);
  }

  /**
   * Metodo que empieza un intervalo.
   */
  public void empezarIntervalo() {
    getRelojInstance().addObserver(this);
  }


  /**
   * Metodo que para un intervalo.
   */
  public void pararIntervalo() {
    getRelojInstance().deleteObserver(this);
  }


  /**
   * Metodo que devuelve una instancia del reloj (Singleton).
   */
  private Reloj getRelojInstance() {
    return Reloj.getInstance();
  }
}
