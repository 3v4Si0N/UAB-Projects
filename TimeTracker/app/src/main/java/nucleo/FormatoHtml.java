package nucleo;
import android.os.Environment;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import static android.R.attr.path;

/**
 * Clase encargada de aplicar el formato html a un informe que hereda de FormatoInforme.
 * En el caso de html, usamos la clase PaginaWeb donde tenemos definido el formato.
 * Con la ayuda del visitor, puede acceder a cada elemento de los que forman un informe 
 * para adaptar-lo al formato que se esta pidiendo.
 * @author Mario, Hector, Edgar
 */


public class FormatoHtml extends FormatoInforme {

  private PaginaWeb pw;
  
  public FormatoHtml() {
    pw = new PaginaWeb();
    invariante();
  }
  
  /**
   * Funcion para guardar el informe generado en la
   * ruta especificada a partir de un flujo de datos.
   */
  @Override
  public void escribirFichero(String ruta) throws FileNotFoundException {
    // TODO Auto-generated method stub
    invariante();
    PrintStream salida = new PrintStream(new BufferedOutputStream(new FileOutputStream(new File(Environment.getExternalStorageDirectory().getAbsoluteFile(), ruta))));
    PrintStream printStream = System.out;
    System.setOut(salida);
    pw.escribirPagina();
    System.setOut(printStream);
    salida.close();
  }

  /**
   * En este metodo, el visitor nos permite acceder al elemento linea,
   * para poder insertarlo a traves de la clase PaginaWeb.
   */
  public void visitFormatoLinea(Linea linea) {
    // TODO Auto-generated method stub
    pw.insertarLineaSeparacion();
  }

  /**
   * En este metodo, el visitor nos permite acceder al elemento titulo,
   * para poder insertarlo a traves de la clase PaginaWeb.
   */
  public void visitFormatoTitulo(Titulo titulo) {
    // TODO Auto-generated method stub
    pw.insertarHeader(titulo.getTitulo(), 2, true);
  }

  /**
   * En este metodo, el visitor nos permite acceder al elemento subtitulo,
   * para poder insertarlo a traves de la clase PaginaWeb.
   */
  public void visitFormatoSubtitulo(Subtitulo subtitulo) {
    // TODO Auto-generated method stub
    pw.insertarHeader(subtitulo.getSubtitulo(), 3, false);
  }

  /**
   * En este metodo, el visitor nos permite acceder al elemento texto,
   * para poder insertarlo a traves de la clase PaginaWeb.
   */
  public void visitFormatoTexto(Texto texto) {
    // TODO Auto-generated method stub
    pw.insertarTextoNormal(texto.getTexto());
  }

  /**
   * En este metodo, el visitor nos permite acceder al elemento tabla,
   * para poder insertarlo a traves de la clase PaginaWeb.
   */
  public void visitFormatoTabla(Tabla tabla) {
    // TODO Auto-generated method stub
    pw.insertarTabla(tabla.getTabla(), true, true);
  }

  @Override
  public void invariante() {
    // TODO Auto-generated method stub
    assert pw != null : "Error, la pagina web no puede ser nula";
  }
}