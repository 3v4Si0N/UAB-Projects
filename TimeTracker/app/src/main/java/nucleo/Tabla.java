package nucleo;
import java.util.ArrayList;

/**
 * Una tabla es un elemento contenedor que forma parte de un informe.
 * En esta classe se crea el elemento tabla.
 * @author Mario, Hector, Edgar
 */
public class Tabla extends ElementoInforme {

  @Override
  public void acceptFormat(FormatoInforme formato) {
      // TODO Auto-generated method stub
    formato.visitFormatoTabla(this);
  }
  
  private int nfilas;

  public int getNfilas() {
    return nfilas;
  }

  protected void setNfilas(int nfilas) {
    this.nfilas = nfilas;
  }

  private int ncolumnas;

  public int getNcolumnas() {
    return ncolumnas;
  }

  protected void setNcolumnas(int ncolumnas) {
    this.ncolumnas = ncolumnas;
  }

  @SuppressWarnings("rawtypes")
  private ArrayList tabla = null;

  @SuppressWarnings("rawtypes")
  public ArrayList getTabla() {
    return tabla;
  }
  
  @SuppressWarnings("rawtypes")
  public void setTaula(ArrayList tabla) {
    this.tabla = tabla;
  }

  /**
   * 
   * @param nfilas .
   * @param ncolumnas .
   */
  @SuppressWarnings({ "rawtypes", "unchecked" })

  public Tabla(int nfilas, int ncolumnas) {
    setNfilas(nfilas);
    setNcolumnas(ncolumnas);
    ArrayList tabla = new ArrayList();
    for (int i = 0 ; i < nfilas ; i++) {
      ArrayList fila = new ArrayList();
      for (int j = 0; j < ncolumnas ; j++) {
            // fila.add(new String());
        fila.add(null);
      }
      tabla.add(fila);
    }
    setTaula(tabla);
  }

  /**
   * Metodo que inserta una fila nula dentro de la tabla.
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public void insertarFila() {
    int ncolumnas = getNcolumnas();
    ArrayList fila = new ArrayList();
    for (int j = 0; j < ncolumnas ; j++) {
      // fila.add(new String());
      fila.add(null);
    }
    getTabla().add(fila);
    setNfilas(getNfilas() + 1);
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  
  public void insertarFila(ArrayList listaStrings) {
    getTabla().add(listaStrings);
    setNfilas(getNfilas() + 1);
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  public void setPosicion(int fila, int columna, String str) { 
      // numerem de 1 ... n i no de 0 ... n-1
    ((ArrayList) getTabla().get(fila - 1)).set(columna - 1,str);
  }

  @SuppressWarnings("rawtypes")
  public String getPosicion(int fila, int columna) {
    return (String) ((ArrayList) getTabla().get(fila - 1)).get(columna - 1);
  }

  public void imprimir() {
    System.out.println(this.getTabla());
    invariante();
  }
  
  @Override
  public void invariante() {
    // TODO Auto-generated method stub
    assert tabla != null : "Error, tabla no puede ser estar vacia";
  }
}