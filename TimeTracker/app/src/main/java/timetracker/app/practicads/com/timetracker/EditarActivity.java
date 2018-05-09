package timetracker.app.practicads.com.timetracker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import nucleo.Actividad;

public class EditarActivity extends AppCompatActivity {

    public static final String EDITAR_ACTIVIDAD = "Editar_actividad";
    Actividad actividad = GestorArbreActivitats.getActividadClicada();

    /**
     * Nom de la classe per fer aparèixer als missatges de logging del LogCat.
     * @see Log
     */
    private final String tag = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        EditText nomActivitat = (EditText) findViewById(R.id.editNombre);
        nomActivitat.setText(actividad.getNombre());

        EditText descActivitat = (EditText) findViewById(R.id.editDescripcion);
        descActivitat.setText(actividad.getDescripcion());

        final Button buttonGuardar = (Button) findViewById(R.id.buttonGuardar);


        buttonGuardar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                EditText nuevoNombre = (EditText) findViewById(R.id.editNombre);
                EditText nuevaDescripcion = (EditText) findViewById(R.id.editDescripcion);
                Intent intent = new Intent(EditarActivity.EDITAR_ACTIVIDAD);

                Log.d("tag", "Editando una actividad");

                intent.putExtra("editar_nombre", nuevoNombre.getText().toString());
                intent.putExtra("editar_descrip", nuevaDescripcion.getText().toString());
                sendBroadcast(intent);
                EditarActivity.super.onBackPressed();
            }
        });

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
}
