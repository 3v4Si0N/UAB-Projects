package nucleo;
/**
 * Clase abstracta que contiene todos los "elementos"
 * del que va a estar formado un informe: linea, titulo, subtitulo, texto y tabla.
 * Esta clase encabeza la jerarquia de operaciones que recibira el visitor.
 * @author Mario, Hector, Edgar
 */


public abstract class ElementoInforme {
  public abstract void acceptFormat(FormatoInforme formato);
  
  public abstract void invariante();
  /**
   * informe que llama a los elementos
   * @uml.property  name="informe"
   */
  private Informe informe;

  /**
     * Getter of the property <tt>informe</tt>
     * @return  Returns the informe.
     * @uml.property  name="informe"
     */
  public Informe getInforme() {
    return informe;
  }

  /**
    * Setter of the property <tt>informe</tt>
    * @param informe  The informe to set.
    * @uml.property  name="informe"
    */
  public void setInforme(Informe informe) {
    this.informe = informe;
  }
}
