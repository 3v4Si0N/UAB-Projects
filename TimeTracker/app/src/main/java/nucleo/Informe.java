package nucleo;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *Un informe es un documento que contiene la informacion de nuestros proyectos,
 *tareas y sus respectivos tiempos, dentro de un margen comprendido entre dos fechas dadas.
 *Puede haber dos tipos de informes, segun el tipo de informacion que contengan.
 *Esta clase se encarga de crear los informes utilizando una serie de elementos que estos contienen:
 *lineas, titulos, subtitulos, texto y tablas.
 * @author HECTORDEARMAS, Edgar, Mario
 */
public abstract class Informe{
   /**
    * Funcion que inicializa un informe con las fechas de donde empieza y donde acaba
    */
  public Informe(Proyecto root, Date fechaDesde, Date fechaHasta) {
    this.fechaDesde = fechaDesde;
    this.fechaHasta = fechaHasta;
    this.root = root;
  }

  /**
   * @uml.property  name="fechaHasta"
   */
  private Date fechaHasta;
  private Proyecto root;

  /**
   * Getter of the property <tt>fechaHasta</tt>
   * @return  Returns the fechaHasta.
   * @uml.property  name="fechaHasta"
   */
  public Date getFechaHasta() {
    return fechaHasta;
  }

  /**
   * Setter of the property <tt>fechaHasta</tt>
   * @param fechaHasta  The fechaHasta to set.
   * @uml.property  name="fechaHasta"
   */
  public void setFechaHasta(Date fechaHasta) {
    this.fechaHasta = fechaHasta;
  }
  /**
   * metodo que devuelve el proyecto raiz.
   */
  public Proyecto getRoot() {
    return root;
  }

  /**
   * @uml.property  name="fechaDesde"
   */
  private Date fechaDesde;

  /**
   * Getter of the property <tt>fechaDesde</tt>
   * @return  Returns the fechaDesde.
   * @uml.property  name="fechaDesde"
   */
  public Date getFechaDesde() {
    return fechaDesde;
  }

  /**
   * Setter of the property <tt>fechaDesde</tt>
   * @param fechaDesde  The fechaDesde to set.
   * @uml.property  name="fechaDesde"
   */
  public void setFechaDesde(Date fechaDesde) {
    this.fechaDesde = fechaDesde;
  }

  /**
   * Lista de elementos de los que constara un informe.
   * @uml.property   name="elementoInforme"
   * @uml.associationEnd   multiplicity="(0 -1)" ordering="true" aggregation="shared" inverse="informe:ElementoInforme"
   */
  private List<ElementoInforme> elementoInforme = new ArrayList<ElementoInforme>();

  /** 
   * Getter of the property <tt>elementoInforme</tt>
   * @return  Returns the elementoInforme.
   * @uml.property  name="elementoInforme"
   */
  public List<ElementoInforme> getElementoInforme() {
    return elementoInforme;
  }

  /** 
   * Setter of the property <tt>elementoInforme</tt>
   * @param elementoInforme  The elementoInforme to set.
   * @uml.property  name="elementoInforme"
   */
  public void setElementoInforme(List<ElementoInforme> elementoInforme) {
    this.elementoInforme = elementoInforme;
  }


  /**
   * La clase ElementoInforme contiene todos los elementos, a traves de ella podemos añadirlos
   */
  public void añadirElementoInforme(ElementoInforme elemento){
    elementoInforme.add(elemento);
  }
  
  /**
   * Funcion que llama a la funcion escribirFichero de la
   * clase FormatoInforme para guardar el informe generado en la 
   * ruta especificada.
   * @param ruta String que contiene el path del fichero donde
   * queremos generar el informe junto con su nombre de fichero.
   * @param formato: a traves de este parametro podemos especificar
   * el tipo de formato con el que queremos escribir el informe.
   */
  public void escribirInforme(String ruta, FormatoInforme formato) {
    try {
      formato.escribirFichero(ruta);
    }
    catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  /**
   * Metodo que llama a la funcion imprimirInforme de la clase FormatoTxt
   * para imprimir el informe por consola en formato de texto.
  */
  public void imprimirInforme(FormatoTxt txt)
  {
    txt.imprimirInforme();
  }

  /**
  * Metodo que nos devuelve el formato del informe segun los elementos que este contenga.
  */
  public FormatoInforme escribir(FormatoInforme formato) {
    int nElementos = elementoInforme.size();
    System.out.println(nElementos); 
    for(int i = 0; i < nElementos; i++) {
        (elementoInforme.get(i)).acceptFormat(formato); }
    return formato;
  }

  /**
  * Metodo para crear la tabla de periodos, que es la tabla que indica
  * las dos fechas entre las que se mostraran proyectos.
  */
  public abstract Tabla crearTablaPeriodos(Date fechaDesde, Date fechaHasta);
  
  /**
  * Metodo para crear la tabla de proyectos, que es la tabla donde se muestran los proyectos comprendidos
  * entre las dos fechas del periodo.
  */
  public abstract Tabla crearTablaProyectosRaiz(Date fechaDesde, Date fechaHasta, Proyecto root);
  public abstract void invariante(Proyecto root, Date fechaDesde, Date fechaHasta);
}
