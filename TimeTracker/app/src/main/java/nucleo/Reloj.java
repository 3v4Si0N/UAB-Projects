package nucleo;
import java.util.Date;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

/** 
 *Esta clase es la responsable del funcionamiento del reloj. Se ha realizado segun el 
 *patron de diseño Observer, por eso hereda de la clase observable.
 *Se ha hecho una clase Observable porque hay una clase (Intervalo)
 *que necesita observar el tiempo de Reloj.
 *@author Mario, Hector, Edgar
 */

public class Reloj extends Observable {

  Date nuevaFecha = new Date();
  /**
   * Metodo que devuelve una fecha nueva.
   */
  public Date getNuevaFecha() {
    return nuevaFecha;
  }

  /**
   * @uml.property  name="timerTask"
   */
  private TimerTask timerTask = new TimerTask() {
    /**
     * Actualiza la fecha actual constantemente en cada periodo de reloj,
     * la cambia y notifica a los observadores con la nueva fecha.
     */
    public void run() { //Timer lo ejecutara en cada periodo
      try {
        Thread.sleep(periodo * 1000); // Se espera 2 segundos para luego notificar
        } catch (InterruptedException e) {
      // TODO Auto-generated catch block
          e.printStackTrace();
        }
        nuevaFecha = new Date(); // Crea una nueva fecha
        setChanged();
        notifyObservers(nuevaFecha); // Notifica a los observadores con la nueva fecha
      }
  };

  /** 
   * Objeto de tipo Timer, nos facilita la fecha y hora actual.
   * @uml.property name="timer"
   */
  private Timer timer;

  private int periodo; // en segundos, seria mejor poderlo pasar al constructor

  /**
   * Metodo que devuelve un periodo (fraccion de tiempo).
   */
  public int getPeriodo() {
    return periodo;
  }

  public void setPeriodo(int periodo) { this.periodo = periodo;}

  /**
   * Constructor de reloj.
   * Se crea un timer y se inicializa cada cuanto se notificara a los observadores.
   */
  private Reloj() {
    this.timer = new Timer();
    setPeriodo(1);
  }


  /**
   *  Metodo que inicia el reloj.
   */
  public void start() {
    timer.schedule(timerTask, 0, periodo);
  }

  /**
   *  Metodo que para el reloj.
   */
  public void stop() {
    timer.cancel();
  }

  private static Reloj instance = null;
  /**
   * Obtiene una instancia de reloj (Singleton).
   * Este metodo es util para poder tener solo un reloj en
   * vez de un reloj para cada periodo de tiempo.
   */
  public static Reloj getInstance() {
    if (instance == null) {
      instance = new Reloj(); 
    }
    return instance;
  }
}
