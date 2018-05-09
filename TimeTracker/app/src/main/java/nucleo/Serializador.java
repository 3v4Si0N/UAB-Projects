package nucleo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Clase encargada de guardar la ejecucion del arbol en un archivo a partir de su raiz,
 * tambien se encarga de cargar dicho archivo para seguir con la ejecucion.
 * @author Mario, Hector, Edgar
 */

public class Serializador {
  /**
   * Inicializacion para la definicion de los niveles de depuracion.
   * @uml.property  name="logger"
   */
  private static Logger logger = LoggerFactory.getLogger(Actividad.class);
    /**
     * Metodo que guarda en el disco un archivo con la ejecucion actual.
     * @param proyecto: proyecto desde el cual se va a guardar el arbol
     * @param fileName: nombre del fichero que se guardara en el disco
     */
  public void guardar(Proyecto proyecto, String fileName) {
    try {
      FileOutputStream fileOut = new FileOutputStream(fileName);
      ObjectOutputStream out = new ObjectOutputStream(fileOut);
      out.writeObject(proyecto);
      out.close();
      fileOut.close();
      logger.info("Los datos serializados se han guardado en " + fileName);
    } catch (IOException e) {
      e.printStackTrace();
      logger.error("Error al guardar el arbol");
    }
  }
  /**
   * Metodo que carga el archivo guardado anteriormente en el
   * disco con el arbol y la informacion necesaria.
   * @param fileName: nombre del fichero que se cargara del disco
   */
  public Proyecto cargar(String fileName) {
    Proyecto root = null;
    try {
      FileInputStream fileIn = new FileInputStream(fileName);
      ObjectInputStream in = new ObjectInputStream(fileIn);
      root = (Proyecto) in.readObject();
      in.close();
      fileIn.close();
      logger.info(fileName + " cargado correctamente");
    } catch (IOException e) {
      logger.error("Error al cargar " + fileName);
      e.printStackTrace();
    } catch (ClassNotFoundException c) {
      logger.debug(fileName + " no encontrado");
      c.printStackTrace();
    }
    return root;
  }
}