package timetracker.app.practicads.com.timetracker;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Clase que crea una nueva tarea o proyecto
 * según lo que el usuario elija en un spinner.
 */
public class NuevaActividad extends AppCompatActivity {

    private Button btnAceptar;
    private EditText nombre;
    private EditText descripcion;
    private Spinner spinnerActividades;


    private final String tag = this.getClass().getCanonicalName();
    public static final String CREAR_TASCA = "Crear_tasca";
    public static final String CREAR_PROYECTO = "Crear_proyecto";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_actividad);
        addListenerOnButton();

        // Muestra la <-- en el action bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
    }


    /**
     * Al presionar el boton de atrás del action bar
     * se ejecutará el método onBackPressed()
     * @return false
     */
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }


    private void addListenerOnButton() {

        // Inicialización de las variables a utilizar para recoger los datos del layout
        spinnerActividades = (Spinner) findViewById(R.id.spinnerNuevaActividad);
        spinnerActividades.setSelection(1); // Seleccion item por default
        btnAceptar = (Button) findViewById(R.id.buttonAceptar);

        // Si el usuario hace click en el botón aceptar, se realizará lo siguiente:
        btnAceptar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // Inicialización de variables
                nombre = (EditText) findViewById(R.id.nombre);
                descripcion = (EditText) findViewById(R.id.descripcion);
                int tipoActividad = spinnerActividades.getSelectedItemPosition();

                switch (tipoActividad)
                {
                    case 0:
                        if (!nombre.getText().toString().isEmpty()) { // Comprobamos que el usuario haya introducido un nombre para el proyecto que va a crear
                            Intent inte = new Intent(NuevaActividad.CREAR_PROYECTO); // Creamos un intent con la acción CREAR_PROYECTO
                            inte.putExtra("nombre_proyecto", nombre.getText().toString()); // Enviamos por el intent el nombre del proyecto
                            inte.putExtra("descripcion_proyecto", descripcion.getText().toString()); // Enviamos por el intent la descripcion del proyecto
                            sendBroadcast(inte); // Enviamos el intent por broadcast
                            Log.d(tag, "enviado intent CREAR_PROYECTO " + nombre.getText().toString());

                            Intent intent = new Intent(NuevaActividad.this, MainActivity.class); // Creamos un intent para movernos a la activity principal
                            startActivity(intent); // Nos trasladamos a la activity principal
                        } else {
                            Context context = getApplicationContext();
                            Toast.makeText(context, "Introduce nombre del proyecto", Toast.LENGTH_SHORT).show(); // Mostramos un dialogo para que el usuario introduzca un nombre al proyecto.
                        }
                        break;
                    case 1:
                        if (!nombre.getText().toString().isEmpty()) { // Comprobamos que el usuario ha introducido un nombre para la tarea que va a crear
                            Intent inte = new Intent(NuevaActividad.CREAR_TASCA); // Creamos un intent con la acción de CREAR_TASCA
                            inte.putExtra("nombre_tarea", nombre.getText().toString()); // Enviamos por el intent el nombre de la tarea
                            inte.putExtra("descripcion_tarea", descripcion.getText().toString()); // Enviamos por el intent la descripcion de la tarea
                            sendBroadcast(inte); // Enviamos el intent por broadcast
                            Log.d(tag, "enviado intent CREAR_TASCA " + nombre.getText().toString());
                            Intent intent = new Intent(NuevaActividad.this, MainActivity.class); // Creamos un intent nuevo para trasladarnos a la activity principal
                            startActivity(intent); // Nos trasladamos a la activity principal
                        } else {
                            Context context = getApplicationContext();
                            Toast.makeText(context, "Introduce nombre de la tarea", Toast.LENGTH_SHORT).show(); // Mostramos un dialogo para que el usuario introduzca un nombre a la tarea.
                        }
                        break;
                    case 2:
                        Toast.makeText(NuevaActividad.this,"Comming soon", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(NuevaActividad.this,"Comming soon", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        assert false : "No existen más opciones posibles";
                        break;
                }
            }

        });

    }
}
