package nucleo;
import android.os.Environment;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Clase encargada de aplicar el formato txt a un informe que hereda de FormatoInforme
 * dentro de la jerarquia del visitor.
 * Con la ayuda del visitor, puede acceder a cada elemento de los que forman un informe
 * para insertarlo con el formato que se este pidiendo.
 * @author Mario, Hector, Edgar
 */

public class FormatoTxt extends FormatoInforme {

  /**
   * @uml.property  name="informeTxt"
   */
  private String informeTxt;

  /**
   * Metodo encargado de inicializar el informe de texto vacio.
   */
  public FormatoTxt() {
    informeTxt = "";
  }
  

  /**
   * Metodo que sirve para guardar el informe generado
   * en formato de texto en la ruta especificada.
   * @param ruta contiene el path del fichero donde queremos generar el informe.
   * @throws FileNotFoundException excepcion por si no encuentra el fichero. 
   */
  @Override
  public void escribirFichero(String ruta) throws FileNotFoundException {
    // TODO Auto-generated method stub
    invariante();
    /*PrintWriter salida = new PrintWriter(ruta);
    salida.println(informeTxt);
    salida.close();*/

    PrintStream salida = new PrintStream(new BufferedOutputStream(new FileOutputStream(new File(Environment.getExternalStorageDirectory().getAbsoluteFile(), ruta))));
    PrintStream printStream = System.out;
    salida.println(informeTxt);
    System.setOut(salida);
    System.setOut(printStream);
    salida.close();
  }
  
  
  /**
   * Metodo para imprimir todo el informe en formato txt por consola.
   */
  public void imprimirInforme() {
    System.out.println(informeTxt);
  }

  
  /**
   * En este metodo, el visitor nos permite acceder al elemento linea,
   * para poder obtener-lo y aplicar el cambio correspondiente al formato.
   * @param linea objeto de la clase Linea utilizado para devolver la linea en formato txt.
   */
  public void visitFormatoLinea(Linea linea) {
    // TODO Auto-generated method stub
    informeTxt += linea.getLinea();
    informeTxt += "\r\n";
  }

  
  /**
  * En este metodo, el visitor nos permite acceder al elemento Titulo,
  * para poder obtenerlo y aplicar el cambio correspondiente al formato.
  * @param titulo objeto de la clase Titulo utilizado para devolver el titulo en formato txt.
  */
  public void visitFormatoTitulo(Titulo titulo) {
    // TODO Auto-generated method stub
    informeTxt += titulo.getTitulo();
    informeTxt += "\r\n";
  }


  /**
  * En este metodo, el visitor nos permite acceder al elemento subtitulo,
  * para poder obtenerlo y aplicar el cambio correspondiente al formato.
  * @param subtitulo objeto de la clase Subtitulo utilizado para devolver el titulo en formato txt.
  */
  public void visitFormatoSubtitulo(Subtitulo subtitulo) {
    // TODO Auto-generated method stub
    informeTxt += subtitulo.getSubtitulo();
    informeTxt += "\r\n";
  }

  
  /**
  * En este metodo, el visitor nos permite acceder al elemento texto,
  * para poder obtenerlo y aplicar el cambio correspondiente al formato.
  * @param texto objeto de la clase Texto utilizado para devolver el titulo en formato txt.
  */
  public void visitFormatoTexto(Texto texto) {
    // TODO Auto-generated method stub
    informeTxt += texto.getTexto();
    informeTxt += "\r\n";
  }

  
  /**
  * En este metodo, el visitor nos permite acceder al elemento tabla,
  * para poder obtenerlo y aplicar el cambio correspondiente al formato.
  * @param tabla objeto de la clase Tabla utilizado para devolver una tabla en formato txt.
  */
  public void visitFormatoTabla(Tabla tabla) {
    // TODO Auto-generated method stub
    ArrayList<?> tablaArray = tabla.getTabla();
    //recorremos la tabla
    for (int i = 0; i < tablaArray.size(); i++) {
      informeTxt += "\r\n";
      for (int j = 0; j < ((ArrayList<?>) tablaArray.get(0)).size(); j++) {
        informeTxt += ((ArrayList<?>) tablaArray.get(i)).get(j);
        informeTxt += "\t\t";
      }
    }
    informeTxt += "\r\n";
  }

  
  /**
   *Por tal de aplicar el diseño por contrato a los metodos de la clase
   *se ha implementado este metodo para establecer las invariantes y
   *las pre y post condiciones necesarias.
   */
  @Override
  public void invariante() {
    // TODO Auto-generated method stub
    assert informeTxt != null : "Error, el informe no puede estar vacio";
  }
}
