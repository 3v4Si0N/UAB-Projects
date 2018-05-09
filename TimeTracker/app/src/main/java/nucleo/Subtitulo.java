package nucleo;
/**
 * Un subtitulo es un elemento descriptivo que forma parte de un informe.
 * En esta classe se crea el elemento subtitulo
 * @author Mario, Hector, Edgar
 */
public class Subtitulo extends ElementoInforme {
    /**
     * @uml.property  name="subtitulo"
     */
  private String subtitulo;

  /**
   * metodo que define como es un subtitulo.
   */
  public Subtitulo(String subtitulo) {
    this.subtitulo = subtitulo;
    invariante();
  }

  /**
   * Getter of the property <tt>subtitulo</tt>
   * @return  Returns the subtitulo.
   * @uml.property  name="subtitulo"
   */
  public String getSubtitulo() {
    return subtitulo;
  }

  /**
   * metodo encargado de recibir y aceptar el visitor.
   * recibe un argumento que hace referencia a la clase abstracta
   * de la jerarquia del visitor.
   */
  @Override
  public void acceptFormat(FormatoInforme formato) {
    // TODO Auto-generated method stub
    formato.visitFormatoSubtitulo(this);
  }

  @Override
  public void invariante() {
    // TODO Auto-generated method stub
    assert subtitulo != null : "Error, el subtitulo no puede estar vacio";
  }
}
