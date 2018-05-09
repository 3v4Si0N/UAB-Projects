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
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import timetracker.app.practicads.com.adapters.AdaptadorIntervalos;

public class IntervalosActivity extends AppCompatActivity {

    /**
     * Estableix com a intervals a visualitzar els de la tasca
     * <code>tascaPare</code>. Aquest mètode és invocat just a l'inici del cicle
     * de vida de la Activity.
     *
     * @param savedInstanceState
     *            de tipus Bundle, però no el fem servir ja que el pas de
     *            paràmetres es fa via l'objecte aplicació
     *            <code>TimeTrackerApplication</code>.
     * @see MainActivity#onCreate
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intervalos);

        Log.i(tag, "onCreate intervalos");

        setContentView(R.layout.activity_intervalos);
        /*
      Grup de vistes (controls de la interfase gràfica) que consisteix en un
      <code>TextView</code> per a cada interval a mostrar.
     */
        ListView intervalsListView = (ListView) this.findViewById(R.id.listViewIntervalos);

        /*
      Llista de dades dels intervals de la tasca però havent fet un cast a la
      classe abstracta <code>List</code> per tal de fer servir aquest atribut
      conjuntament amb un <code>Adapter</code> d'Android.
     */
        List<DadesInterval> llistaDadesIntervals = new ArrayList<DadesInterval>();
        aaAct = new AdaptadorIntervalos(this, llistaDadesIntervals);

        intervalsListView.setAdapter(aaAct); // Se establece el adaptador a la lista intervalsListView
    }

    /**
     * Adaptador necessari per connectar les dades de la llista d'intervals de
     * la tasca pare actual, amb la interfase, segons el mecanisme estàndard de
     * <code>ListView</code> d'Android.
     * <p>
     * Per tal de fer-lo servir, he hagut d'afegir a la classe
     * <code>DadesInterval</code> tingui un mètode <code>toString</code> que
     * retornarà l'string a mostrar en els <code>TextView</code> (controls de
     * text) de la llista <code>ListView</code>.
     */
    private AdaptadorIntervalos aaAct;

    /**
     * Nom de la classe per fer aparèixer als missatges de logging del LogCat.
     *
     * @see Log
     */
    private final String tag = this.getClass().getSimpleName();


    // Aquests són els "serveis" que demana aquesta classe
    // a la classe Service GestorArbreActivitats

    /**
     * Sol·licita les dades dels intervals fills d'una tasca que és la activitat
     * pare actual.
     */
    public static final String DONAM_FILLS = "Donam_fills";

    /**
     * Demana que l'activitat pare actual passi a ser el projecte pare de la
     * tasca que és ara l'activitat actual.
     */
    public static final String PUJA_NIVELL = "Puja_nivell";

    /**
     * Rep els "intents" que envia <code>GestorArbreActivitats</code> amb les
     * dades de intervals a mostrar. El receptor els rep tots (no hi ha cap
     * filtre) per que només se'n n'hi envia un, el "TE_FILLS".
     *
     * @author joans
     * @version 6 febrer 2012
     */
    public class Receptor extends BroadcastReceiver {
        /**
         * Nom de la classe per fer aparèixer als missatges de logging del
         * LogCat.
         *
         * @see Log
         */
        private final String tag = this.getClass().getCanonicalName();

        /**
         * Gestiona tots els intents enviats, de moment només el de la acció
         * TE_FILLS. La gestió consisteix en actualitzar la llista de dades que
         * s'està mostrant mitjançant el seu adaptador.
         *
         * @param context
         *            el context (classe) des del qual s'ha llençat l'intent.
         * @param intent
         *            objecte Intent que arriba per "broadcast" i del qual en
         *            fem servir l'atribut "action" per saber quina mena de
         *            intent és i els extres per obtenir les dades a mostrar.
         */
        @Override
        public final void onReceive(final Context context,
                                    final Intent intent) {
            Log.d(tag, "onReceive Receptor LlistaIntervals");
            if (intent.getAction().equals(GestorArbreActivitats.TE_FILLS)) {
                ArrayList<DadesInterval> llistaDadesInter =
                        (ArrayList<DadesInterval>) intent
                                .getSerializableExtra("llista_dades_intervals");
                aaAct.clear();
                for (DadesInterval dadesInter : llistaDadesInter) {
                    aaAct.add(dadesInter);
                }
                aaAct.notifyDataSetChanged();
            }
            Log.i(tag, "final de onReceive LlistaIntervals");

            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setDisplayShowHomeEnabled(true);
            }
        }
    }

    /**
     * Objecte únic de la classe {LlistaIntervalsActivity.Receptor}.
     */
    private Receptor receptor;

    /**
     * Cuando se presiona el botón de atrás se envia la acción PUJA_NIVELL
     * para subir un nivel en el árbol y así poder refrescar la interfaz gráfica.
     */
    @Override
    public final void onBackPressed() {
        Log.i(tag, "onBackPressed");
        sendBroadcast(new Intent(IntervalosActivity.PUJA_NIVELL));
        Log.d(tag, "enviat intent PUJA_NIVELL");
        super.onBackPressed();
    }


    /**
     * Método el cual dependiendo del item
     * que se seleccione realizará una acción u otra.
     * @param item elemento que se podrá seleccionar.
     * @return devuelve el item que se ha seleccionado.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            sendBroadcast(new Intent(IntervalosActivity.PUJA_NIVELL));
            Log.d(tag, "enviat intent PUJA_NIVELL");
            super.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Quan aquesta Activity es mostra per primer cop o després d'haver estat
     * ocultada per alguna altra Activity cal tornar a fer receptor i el seu
     * filtre per que atengui als intents que es redifonen (broadcast). I
     * després, demanar la llista de dades d'interval a mostrar.
     */
    @Override
    public final void onResume() {
        Log.i(tag, "onResume intervals");

        IntentFilter filter;
        filter = new IntentFilter();
        filter.addAction(GestorArbreActivitats.TE_FILLS);
        receptor = new IntervalosActivity.Receptor();
        registerReceiver(receptor, filter);

        sendBroadcast(new Intent(IntervalosActivity.DONAM_FILLS));
        Log.d(tag, "enviat intent DONAM_FILLS");

        super.onResume();
    }

    /**
     * Just abans de quedar "oculta" aquesta Activity per una altra, anul·lem el
     * receptor de intents.
     */
    @Override
    public final void onPause() {
        Log.i(tag, "onPause intervals");

        unregisterReceiver(receptor);

        super.onPause();
    }

    // D'aqui en avall els mètodes que apareixen són simplement sobrecàrregues
    // de mètodes de Activity per tal que es mostri un missatge de logging i
    // d'aquesta manera puguem entendre el cicle de vida d'un objecte d'aquesta
    // classe i depurar errors de funcionament de la interfase (on posar què).

    /**
     * Mostra un missatge de log per entendre millor el cicle de vida d'una
     * Activity.
     */
    @Override
    public final void onDestroy() {
        Log.i(tag, "onDestroy intervals");
        super.onDestroy();
    }

    /**
     * Mostra un missatge de log per entendre millor el cicle de vida d'una
     * Activity.
     */
    @Override
    public final void onStart() {
        Log.i(tag, "onStart intervals");
        super.onStart();
    }

    /**
     * Mostra un missatge de log per entendre millor el cicle de vida d'una
     * Activity.
     */
    @Override
    public final void onStop() {
        Log.i(tag, "onStop intervals");
        super.onStop();
    }

    /**
     * Mostra un missatge de log per entendre millor el cicle de vida d'una
     * Activity.
     */
    @Override
    public final void onRestart() {
        Log.i(tag, "onRestart intervals");
        super.onRestart();
    }

    /**
     * Mostra un missatge de log per entendre millor el cicle de vida d'una
     * Activity.
     *
     * @param savedInstanceState
     *            Bundle que de fet no es fa servir.
     */
    @Override
    public final void onSaveInstanceState(final Bundle savedInstanceState) {
        Log.i(tag, "onSaveInstanceState");
        super.onSaveInstanceState(savedInstanceState);
    }

    /**
     * Mostra un missatge de log per entendre millor el cicle de vida d'una
     * Activity.
     *
     * @param savedInstanceState
     *            Bundle que de fet no es fa servir.
     */
    @Override
    public final void onRestoreInstanceState(final Bundle savedInstanceState) {
        Log.i(tag, "onRestoreInstanceState");
        super.onRestoreInstanceState(savedInstanceState);
    }

    /**
     * Mostra un missatge de logging en rotar 90 graus el dispositiu (o
     * simular-ho en l'emulador).
     *
     * @param newConfig
     *            nova configuració {@link Configuration}
     * LlistaActivitatsActivity#onConfigurationChanged
     */
    @Override
    public final void onConfigurationChanged(final Configuration newConfig) {
        Log.i(tag, "onConfigurationChanged");
        if (Log.isLoggable(tag, Log.VERBOSE)) {
            Log.v(tag, newConfig.toString());
        }
    }
}
