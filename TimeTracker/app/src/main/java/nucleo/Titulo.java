package nucleo;
/**
 * Un titulo es un elemento descriptivo que forma parte de un informe.
 * En esta classe se crea el elemento titulo.
 * @author Mario, Hector, Edgar
 */


public class Titulo extends ElementoInforme {

    /**
     * @uml.property  name="titulo"
     */
  private String titulo;

  /**
   * metodo que define como es un texto.
   */
  public Titulo(String titulo) {
    this.titulo = titulo;
    invariante();
  }

    /**
     * Getter of the property <tt>titulo</tt>
     * @return  Returns the titulo.
     * @uml.property  name="titulo"
     */
  public String getTitulo() {
    return titulo;
  }

  /**
   * metodo encargado de recibir y aceptar el visitor.
   * recibe un argumento que hace referencia a la clase abstracta
   * de la jerarquia del visitor.
   */
  @Override
  public void acceptFormat(FormatoInforme formato) {
    // TODO Auto-generated method stub
    formato.visitFormatoTitulo(this);
  }

  @Override
  public void invariante() {
    // TODO Auto-generated method stub
    assert titulo != null : "Error, titulo no puede estar vacio";
  }
}