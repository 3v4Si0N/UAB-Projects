package nucleo;
import html.Attribute;
import html.Tag;

import java.util.Collection;
import java.util.Iterator;

/**
 * Clase donde tenemos definidos los tags html y la manera de manejarlos.
 * @author Mario, Hector, Edgar
 */
public class PaginaWeb {

    /**
     * @uml.property name="paginaWeb"
     */
  private Tag paginaWeb = new Tag("html");

  /**
     * @uml.property name="head"
     */
  private Tag head = new Tag("head");

  /**
   * @uml.property name="body"
   */
  private Tag body = new Tag("body");
  
  
  /**
   * Metodo que crea una instancia de pagina web en la que inicializa una etiqueta title
   * y le añade un texto, inserta este en la etiqueta head y dentro de la etiqueta html
   * inserta el contenido de la etiqueta head + el body.
   */
  @SuppressWarnings("unchecked")
  public PaginaWeb() {
    Tag title = new Tag("title");
    title.add("Informe TimeTracker");
    head.add(title);
    paginaWeb.add(head);
    paginaWeb.add(body);
  }
  
  /**
   * 
   * @param str .
   * @param tam .
   * @param centrar .
   */
  @SuppressWarnings("unchecked")
  public void insertarHeader(String str, int tam, boolean centrar) {
    // fa text h1, h2 ... h6
    if (tam >= 1 && tam <= 6) {
      Tag header = new Tag("h" + (new Integer(tam)).toString());
      header.add(str);
      if (centrar) {
        header.addAttribute(new Attribute("style", "text-align: center;"));
      }
      body.add(header);
    }
  }

  @SuppressWarnings("unchecked")
  public void insertarTextoNormal(String str) {
    body.add(str);
  }

  @SuppressWarnings("unchecked")
  public void insertarSaltoDeLinea() {
    body.add(new Tag("br", false));
  }

  /**
   * 
   * @param tabla .
   * @param primeraFilaCabecera .
   * @param primeraColumnaCabecera .
   */
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public void insertarTabla(Collection tabla, 
      boolean primeraFilaCabecera,
         boolean primeraColumnaCabecera) {
    // taula es una llista (files) de llistes (columnes), implementat com un
    // arraylist d'arraylists, encara que aqui per mes generalitat hi posem
    // el tipus generic collection

    /*
     * Exemple : taula amb capsalera a la primera fila
     * 
     * <table style= "text-align: left; width: 842px;" border="1" cellpadding="2" cellspacing="2"> 
     *     <tbody> 
     *     <tr>
     *          <th style="background-color: rgb(102, 255, 255);">No.</th>
     *              <th style="background-color: rgb(102, 255, 255);">Projecte</th>
     *              <th style="background-color: rgb(102, 255, 255);">Data d'inici</th>
     *             <th style="background-color: rgb(102, 255, 255);">Data final</th>
     *              <th style="background-color: rgb(102, 255, 255);">Temps total</th>
     *          </tr> 
     *          <tr> 
     *              <td style="background-color: rgb(204, 255, 255);">1</td>
     *           <td style="background-color: rgb(204, 255, 255);">P&agrave;gina web personal</td> 
     *              <td style="background-color: rgb(204, 255, 255);">15/11/2006, 19:00h</td> 
     *              <td style="background-color: rgb(204, 255, 255);">25/11/2006, 20:00h</td> 
     *              <td style="background-color: rgb(204, 255, 255);">25h 45m 0s</td> 
     *          </tr> 
     *      </tbody> 
     * </table>
     */
    Tag tag = new Tag("table");
    tag.addAttribute(new Attribute("style", "text-align: center; width: 842px;"));
    tag.addAttribute(new Attribute("border", "1"));
    tag.addAttribute(new Attribute("cellpadding", "2"));
    tag.addAttribute(new Attribute("cellspacing", "2"));

    Tag tbody = new Tag("tbody");
    // les cel.les de capsalera tenen fons en blau fosc
    Attribute estilTh = new Attribute("style", "background-color: rgb(102, 255, 255);");
    // les cel.les de dades, fons en blau clar
    Attribute estilTd = new Attribute("style", "background-color: rgb(204, 255, 255);");

    Iterator itFilas = tabla.iterator();
    Iterator itColumnas = null;
    boolean primeraFila = true;
    while (itFilas.hasNext()) {
      Tag tr = new Tag("tr"); // cada fila de la taula
      itColumnas = ((Collection) itFilas.next()).iterator();
      boolean primeraColumna = true;
      while (itColumnas.hasNext()) {
        if ( (primeraFila && primeraFilaCabecera) 
                || (primeraColumna && primeraColumnaCabecera) ) { // th en comptes de td
          Tag th = new Tag("th"); 
          th.addAttribute(estilTh);
          th.add(itColumnas.next().toString());
          tr.add(th);
        } else {
          Tag td = new Tag("td"); 
          td.addAttribute(estilTd); 
          td.add(itColumnas.next().toString());
          tr.add(td);
        }
        primeraColumna = false;
      }
      primeraFila = false;
      tbody.add(tr);
    }
    tag.add(tbody);
    body.add(tag);
  }

  /**
   * Metodo que inserta un <hr> dentro de la pagina web.
   */
  @SuppressWarnings("unchecked")
  public void insertarLineaSeparacion() {
    Tag hr = new Tag("hr");
    hr.addAttribute(new Attribute("style", "width: 100%; height: 2px;"));
    // <hr style="width: 100%; height: 2px;">
    body.add(hr);
  }

  public void escribirPagina() {
    System.out.println(paginaWeb);
  }
}