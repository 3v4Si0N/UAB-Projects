package nucleo;
/**
 * Un informe necessita una serie de operaciones, 
 * para no tener que defnir-las en cada formato, 
 * creamos la classe visitor que nos permitira acceder a estas operaciones.
 * Esta classe contiene todos los metodos para "visitar" 
 * las operaciones que necessitan nuestros informes.
 * Cada una de las operaciones tendra un accept() concreto para recibir al visitor.
 * @author Mario, Hector, Edgar
 */


public interface Visitor {
  public void visitFormatoLinea(Linea linea);
  
  public void visitFormatoTitulo(Titulo titulo);
  
  public void visitFormatoSubtitulo(Subtitulo subtitulo);
  
  public void visitFormatoTexto(Texto texto);
  
  public void visitFormatoTabla(Tabla tabla);
}
