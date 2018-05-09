package nucleo;
/**
 * Un texto es un elemento descriptivo que forma parte de un informe.
 * En esta classe se crea el elemento texto.
 * @author Mario, Hector, Edgar
 */


public class Texto extends ElementoInforme {

    /**
     * @uml.property  name="texto"
     */
  private String texto;
 
  /**
   * metodo que define como es un texto.
   */
  public Texto(String texto) {
    this.texto = texto;
    invariante();
  }

    /**
     * Getter of the property <tt>texto</tt>
     * @return  Returns the texto.
     * @uml.property  name="texto"
     */
  public String getTexto() {
    return texto;
  }

  /**
   * metodo encargado de recibir y aceptar el visitor.
   * recibe un argumento que hace referencia a la clase abstracta
   * de la jerarquia del visitor.
   */
  @Override
    public void acceptFormat(FormatoInforme formato) {
        // TODO Auto-generated method stub
    formato.visitFormatoTexto(this);
  }
    
  @Override
  public void invariante() {
    // TODO Auto-generated method stub
    assert texto != null : "Error, subtitulo no puede estar vacío";
  }
}
