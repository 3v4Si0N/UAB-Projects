package nucleo;
/**
 * Una linea es un elemento separador que forma parte de un informe.
 * En esta clase se crea el elemento linea.
 * @author Mario, Hector, Edgar
 */


public class Linea extends ElementoInforme {

    /**
     * @uml.property  name="linea"
     */
  private String linea;
  
  /**
   * metodo que define como es una linea.
   */
  public Linea() {
    linea = "----------------------------------------"
              + "-----------------------------------------";
    invariante();
  }
  /**
   * Getter of the property <tt>linea</tt>
   * @return  Returns the linea.
   * @uml.property  name="linea"
   */
  public String getLinea() {
    return linea;
  }

  /**
   * metodo encargado de recibir y aceptar el visitor.
   * recibe un argumento que hace referencia a la clase abstracta
   * de la jerarquia del visitor.
   */
  @Override
  public void acceptFormat(FormatoInforme formato) {
    // TODO Auto-generated method stub
    formato.visitFormatoLinea(this);
  }

  @Override
  public void invariante() {
    // TODO Auto-generated method stub
    assert linea != null : "Error la linea no puede ser null";
  }
}
