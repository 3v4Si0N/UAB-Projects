package nucleo;
import java.io.FileNotFoundException;

/**
 * Aparte de ser breves o detallados, los informes pueden estar en dos formatos distintos:
 * Esta es la Clase abstracta que se encarga de dar formato al informe segun si es html o si es txt.
 * Esta clase forma parte de la jerarquia del visitor.
 * @author Mario, Hector, Edgar
 */

public abstract class FormatoInforme implements Visitor {


  /**
   * Metodo que sirve para guardar el informe generado
   * en formato de textoen la ruta especificada por parametro.
   * @param ruta contiene el path del fichero donde queremos generar el informe.
   * @throws FileNotFoundException excepcion por si no encuentra el fichero.
   */
  public abstract void escribirFichero(String ruta) throws FileNotFoundException;
  
  
  /**
   *Por tal de aplicar el diseño por contrato a los metodos de la clase
   *se ha implementado este metodo para establecer las invariantes y
   *las pre y post condiciones necesarias.
   */
  public abstract void invariante();
}
