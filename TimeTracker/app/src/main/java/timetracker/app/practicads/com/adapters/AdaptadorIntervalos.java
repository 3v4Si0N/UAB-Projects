package timetracker.app.practicads.com.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;
import timetracker.app.practicads.com.timetracker.DadesInterval;
import timetracker.app.practicads.com.timetracker.R;

/**
 * Created by HECTOR DE ARMAS on 01/01/2017.
 * Clase que adapta la listView de los intervalos,
 * las filas están predefinidas en el XML list_row_intervalos.
 * Se mostrarán los datos de los intervalos de cada tarea utilizando
 * el list_row_intervalos. Este adaptador se introducirá en el onCreate
 * de IntervalosActivity en la listView listViewIntervalos.
 */

public class AdaptadorIntervalos extends ArrayAdapter {
    private Activity context;
    private List<DadesInterval> datos;
    private final String tag = this.getClass().getCanonicalName();

    public AdaptadorIntervalos(Activity context, List<DadesInterval> datos) {
        super(context, R.layout.list_row_intervalos, datos);
        this.datos = datos;
        this.context = context;
    }


    @NonNull
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater(); //Obtenemos el layoutInflater del context

        // Creamos la view inflando el layout que queramos mostrar en este caso el de una fila de la lista de intervalos.
        convertView = inflater.inflate(R.layout.list_row_intervalos, null);

        // Inicialización de las variables que se recogerán del layout inflado anteriormente.
        TextView fechaInicial = (TextView)convertView.findViewById(R.id.list_row_initial_date);
        TextView fechaFinal = (TextView)convertView.findViewById(R.id.list_row_final_date);
        TextView duracion = (TextView)convertView.findViewById(R.id.list_row_duracion);

        // Seteo de las variables inicializadas anteriormente utilizando la posición de la List de DadesInterval.
        fechaInicial.setText(datos.get(position).fechaInicialToString());
        fechaFinal.setText(datos.get(position).fechaFinalToString());
        duracion.setText(datos.get(position).duracionToString());

        return convertView; // Devolvemos la view
    }
}
