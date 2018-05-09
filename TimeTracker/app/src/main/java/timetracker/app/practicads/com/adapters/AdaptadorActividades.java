package timetracker.app.practicads.com.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

import timetracker.app.practicads.com.timetracker.DadesActivitat;
import timetracker.app.practicads.com.timetracker.MainActivity;
import timetracker.app.practicads.com.timetracker.R;

/**
 * Created by HECTOR DE ARMAS on 31/12/2016.
 * Clase que adapta la listView de las actividades a 2 tipos de filas diferentes,
 * una fila es la de una tarea y otra la de un proyecto.
 * Dependiendo de qué tipo de actividad es la que se inserta en la listView se mostrará
 * un layout u otro. Si se realiza un click sobre el botón play de algún elemento del listView (alguna tarea),
 * se obtiene su posición y se encenderá la tarea o se apagará según su posición.
 */

public class AdaptadorActividades extends ArrayAdapter {
    private Activity context;
    private List<DadesActivitat> datos;
    private final String tag = this.getClass().getCanonicalName();

    public AdaptadorActividades(Activity context, List<DadesActivitat> datos) {
        super(context, R.layout.list_row_proyectos, datos);
        this.datos = datos;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater(); // Obtenemos el LayoutInflater de nuestro context
        TextView nombreActividad;
        TextView duracion;

        // Comprobamos si es un proyecto o una tarea
        if (datos.get(position).isProjecte()) {
            convertView = inflater.inflate(R.layout.list_row_proyectos, null); //Inflamos el layout de un proyecto

            //Inicializacion las variables que vamos a recoger del layout inflado anteriormente
            nombreActividad = (TextView)convertView.findViewById(R.id.list_row_title_proyecto);
            ImageView img = (ImageView)convertView.findViewById(R.id.list_row_image);
            duracion = (TextView) convertView.findViewById(R.id.list_row_duracion_proyecto);

            //Seteo del nombre del proyecto, imagen y duración del proyecto
            nombreActividad.setText(datos.get(position).getNom());
            img.setImageResource(datos.get(position).getImg());
            duracion.setText(datos.get(position).toString());
        }
        else
        {
            convertView = inflater.inflate(R.layout.list_row_tareas, null); // inflamos el layout de una tarea

            // Inicializacion las variables que vamos a recoger del layout inflado anteriormente
            nombreActividad = (TextView)convertView.findViewById(R.id.list_row_title_tarea);
            duracion = (TextView) convertView.findViewById(R.id.list_row_duracion_tarea);
            final ImageView buttonPlay = (ImageView) convertView.findViewById(R.id.play_button);

            // Seteo del nombre de la tarea que se mestrará en la lista + duración
            nombreActividad.setText(datos.get(position).getNom());
            duracion.setText(datos.get(position).toString());

            // Comprobamos si alguna tarea se está cronometrando, si es así se introduce una imagen de pause en el botón.
            if (datos.get(position).isCronometreEngegat()) {
                buttonPlay.setImageResource(R.drawable.ic_pause_white_24dp);
            }

            /**
             * Si hacemos click en el boton play de una tarea,
             * se cronometrará una tarea o se parará según el estado de la misma.
             */
            buttonPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent inte;
                    if (!datos.get(position).isCronometreEngegat()) {
                        buttonPlay.setImageResource(R.drawable.ic_pause_white_24dp);
                        inte = new Intent(
                                MainActivity.ENGEGA_CRONOMETRE);
                        Log.d(tag, "enviat intent ENGEGA_CRONOMETRE de "
                                + datos.get(position).getNom());
                    } else {
                        inte = new Intent(
                                MainActivity.PARA_CRONOMETRE);
                        Log.d(tag, "enviat intent PARA_CRONOMETRE de "
                                + datos.get(position).getNom());
                    }
                    inte.putExtra("posicio", position);
                    context.sendBroadcast(inte); // Se realiza un broadcast receiver con la accion ENGEGA_CRONOMETRE o PARA_CRONOMETRE
                }
            });
        }

        return convertView;
    }
}
