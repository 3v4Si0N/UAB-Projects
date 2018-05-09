package timetracker.app.practicads.com.timetracker;

import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import timetracker.app.practicads.com.adapters.AdaptadorActividades;


/**
 * Mostra la llista de projectes i tasques filles del projecte pare actual.
 * Inicialment, en engegar la aplicació per primer cop (o quan s'ha engegat el
 * telèfon) mostra doncs les activitats del primer "nivell", considerant que hi
 * ha un projecte arrel invisible de cara als usuaris que és el seu projecte
 * pare.
 * <p>
 * Juntament amb el nom de projecte o tasca se'n mostra el temps total
 * cronometrat. I mentre que s'està cronometrant alguna tasca d'aquestes, o bé
 * descendent d'un dels projectes mostrats, el seu temps es veu que es va
 * actualitzant. Per tal de mostrar nom i durada mitjançant les
 * <code>ListView</code> d'Android, hem hagut de dotar la classe
 * <code>DadesActivitat</code> d'un mètode <code>toString</code> que és invocat
 * per un objecte de classe <code>Adapter</code>, que fa la connexió entre la
 * interfase i les dades que mostra.
 * <p>
 * També gestiona els events que permeten navegar per l'arbre de projectes i
 * tasques :
 * <ul>
 * <li>un click sobre un element de la llista baixa de nivell: passa a mostrar
 * els seus "fills", la siguin subprojectes i tasques (si era un projecte) o
 * intervals (si era tasca)</li>
 * <li>tecla o botó "back" puja de nivell: anem al projecte para del les
 * activitats de les quals mostrem les dades, o si ja són del primer nivell i no
 * podem pujar més, anem a la pantalla "Home"</li>
 * </ul>
 * I també events per tal de cronometrar una tasca p parar-ne el cronòmetre,
 * mitjançant un click llarg.
 * <p>
 * Totes dues funcions no són dutes a terme efectivament aquí sinó a
 * <code>GestorArbreActivitat</code>, que manté l'arbre de tasques, projectes i
 * intervals en memòria. Cal fer-ho així per que Android pot eliminar (
 * <code>destroy</code>) la instància d'aquesta classe quan no és visible per
 * que estem interactuant amb alguna altra aplicació, si necessita memòria. En
 * canvi, un servei com és <code>GestorArbreActivitats</code> només serà
 * destruït en circumstàncies extremes. La comunicació amb el servei es fa
 * mitjançant "intents", "broadcast" i una classe privada "receiver".
 *
 * @author joans
 * @version 6 febrer 2012
 */

public class MainActivity extends AppCompatActivity {

    /**
     * Nom de la classe per fer aparèixer als missatges de logging del LogCat.
     *
     * @see Log
     */
    private final String tag = this.getClass().getSimpleName();

    /**
     * Grup de vistes (controls de la interfase gràfica) que consisteix en un
     * <code>TextView</code> per a cada activitat a mostrar.
     */
    private ListView arrelListView;
    private ImageView playButton;

    /**
     * Adaptador necessari per connectar les dades de la llista de projectes i
     * tasques filles del projecte pare actual, amb la interfase, segons el
     * mecanisme estàndard d'Android.
     * <p>
     * Per tal de fer-lo servir, cal que la classe <code>DadesActivitat</code>
     * tingui un mètode <code>toString</code> que retornarà l'string a mostrar
     * en els TextView (controls de text) de la llista ListView.
     */
    //private ArrayAdapter<DadesActivitat> aaAct;
    private AdaptadorActividades aaAct;

    /**
     * Llista de dades de les activitats (projectes i tasques) mostrades
     * actualment, filles del (sub)projecte on estem posicionats actualment.
     */
    private List<DadesActivitat> llistaDadesActivitats;

    /**
     * Flag que ens servirà per decidir fer que si premem el botó/tecla "back"
     * quan estem a l'arrel de l'arbre de projectes, tasques i intervals : si és
     * que si, desem l'arbre i tornem a la pantalla "Home", sinó hem d'anar al
     * projecte pare del pare actual (pujar de nivell).
     */
    private boolean activitatPareActualEsArrel;

    /**
     * Rep els "intents" que envia <code>GestorArbreActivitats</code> amb les
     * dades de les activitats a mostrar. El receptor els rep tots (no hi ha cap
     * filtre) per que només se'n n'hi envia un, el "TE_FILLS".
     *
     * @author joans
     * @version 6 febrer 2012
     */
    private class Receptor extends BroadcastReceiver {
        /**
         * Nom de la classe per fer aparèixer als missatges de logging del
         * LogCat.
         *
         * @see Log
         */
        private final String tag = this.getClass().getCanonicalName();

        /**
         * Gestiona tots els intents enviats, de moment només el de la
         * acció TE_FILLS. La gestió consisteix en actualitzar la llista
         * de dades que s'està mostrant mitjançant el seu adaptador.
         *
         * @param context
         * @param intent
         * objecte Intent que arriba per "broadcast" i del qual en fem
         * servir l'atribut "action" per saber quina mena de intent és
         * i els extres per obtenir les dades a mostrar i si el projecte
         * actual és l'arrel de tot l'arbre o no
         *
         */
        @Override
        public void onReceive(final Context context, final Intent intent) {
            Log.i(tag, "onReceive");
            if (intent.getAction().equals(GestorArbreActivitats.TE_FILLS)) {
                activitatPareActualEsArrel = intent.getBooleanExtra(
                        "activitat_pare_actual_es_arrel", false);
                // obtenim la nova llista de dades d'activitat que ve amb
                // l'intent
                @SuppressWarnings("unchecked")
                ArrayList<DadesActivitat> llistaDadesAct =
                        (ArrayList<DadesActivitat>) intent
                                .getSerializableExtra("llista_dades_activitats");
                aaAct.clear();
                for (DadesActivitat dadesAct : llistaDadesAct) {
                    aaAct.add(dadesAct);
                }
                // això farà redibuixar el ListView
                aaAct.notifyDataSetChanged();
                Log.d(tag, "mostro els fills actualitzats");
            } else {
                // no pot ser
                assert false : "intent d'acció no prevista";
            }
        }
    }

    /**
     * Objecte únic de la classe {@link Receptor}.
     */
    private Receptor receptor;

    // Aquests són els "serveis", identificats per un string, que demana
    // aquesta classe a la classe Service GestorArbreActivitats, en funció
    // de la interacció de l'usuari:

    /**
     * String que defineix l'acció de demanar a <code>GestorActivitats</code> la
     * llista de les dades dels fills de l'activitat actual, que és un projecte.
     * Aquesta llista arribarà com a dades extres d'un Intent amb la "acció"
     * TE_FILLS.
     *
     * @see GestorArbreActivitats.Receptor
     */
    public static final String DONAM_FILLS = "Donam_fills";

    /**
     * String que defineix l'acció de demanar a <code>GestorActivitats</code>
     * que engegui el cronòmetre de la tasca clicada.
     */
    public static final String ENGEGA_CRONOMETRE = "Engega_cronometre";

    /**
     * String que defineix l'acció de demanar a <code>GestorActivitats</code>
     * que pari el cronòmetre de la tasca clicada.
     */
    public static final String PARA_CRONOMETRE = "Para_cronometre";

    /**
     * String que defineix l'acció de demanar a <code>GestorActivitats</code>
     * que escrigui al disc l'arbre actual.
     */
    public static final String DESA_ARBRE = "Desa_arbre";

    /**
     * String que defineix l'acció de demanar a <code>GestorActivitats</code>
     * que el projecte pare de les activitats actuals sigui el projecte que
     * l'usuari ha clicat.
     */
    public static final String BAIXA_NIVELL = "Baixa_nivell";

    /**
     * String que defineix l'acció de demanar a <code>GestorActivitats</code>
     * que el projecte pare passi a ser el seu pare, o sigui, pujar de nivell.
     */
    public static final String PUJA_NIVELL = "Puja_nivell";

    /**
     * En voler pujar de nivell quan ja som a dalt de tot vol dir que l'usuari
     * desitja "deixar de treballar del tot" amb la aplicació, així que "parem"
     * el servei <code>GestorActivitats</code>, que vol dir parar el cronòmetre
     * de les tasques engegades, si n'hi ha alguna, desar l'arbre i parar
     * (invocant <code>stopSelf</code>) el servei. Tot això es fa a
     * {@link GestorArbreActivitats#paraServei}.
     */
    public static final String PARA_SERVEI = "Para_servei";

    /**
     * String que define la acción de pedir a GestorActivitats
     * que devuelva la actividad en la que se ha hecho click.
     */
    public static final String ACTIVIDAD_CLICADA = "Actividad_clicada";

    /**
     * String que define la acción de pedir a GestorActivitats
     * que elimine todas las actividades que hay actualmente en el árbol.
     */
    public static final String ELIMINAR_TODO = "Eliminar_todo";

    public static final String PARAR_TODO = "Parar_todo";


    /**
     * Quan aquesta Activity es mostra després d'haver estat ocultada per alguna
     * altra Activity cal tornar a fer receptor i el seu filtre per que atengui
     * als intents que es redifonen (broadcast). I també engegar el servei
     * <code>GestorArbreActivitats</code>, si és la primera vegada que es mostra
     * aquesta Activity. En fer-ho, el servei enviarà la llista de dades de les
     * activitats filles del projecte arrel actual.
     */
    @Override
    public final void onResume() {
        Log.i(tag, "onResume");

        IntentFilter filter;
        filter = new IntentFilter();
        filter.addAction(GestorArbreActivitats.TE_FILLS);
        receptor = new Receptor();
        registerReceiver(receptor, filter);

        // Crea el servei GestorArbreActivitats, si no existia ja. A més,
        // executa el mètode onStartCommand del servei, de manera que
        // *un cop creat el servei* = havent llegit ja l'arbre si es el
        // primer cop, ens enviarà un Intent amb acció TE_FILLS amb les
        // dades de les activitats de primer nivell per que les mostrem.
        // El que no funcionava era crear el servei (aquí mateix o
        // a onCreate) i després demanar la llista d'activiats a mostrar
        // per que startService s'executa asíncronament = retorna de seguida,
        // i la petició no trobava el servei creat encara.
        startService(new Intent(this, GestorArbreActivitats.class));

        super.onResume();
        Log.i(tag, "final de onResume");
    }

    /**
     * Just abans de quedar "oculta" aquesta Activity per una altra, anul·lem el
     * receptor de intents.
     */
    @Override
    public final void onPause() {
        Log.i(tag, "onPause");

        unregisterReceiver(receptor);

        super.onPause();
    }

    /**
     * Estableix com a activitats a visualitzar les filles del projecte
     * arrel, així com els dos listeners que gestionen els
     * events de un click normal i un click llarg. El primer serveix per navegar
     * "cap avall" per l'arbre, o sigui, veure els fills d'un projecte o els
     * intervals d'una tasca. El segon per cronometrar, en cas que haguem clicat
     * sobre una tasca.
     *
     * @param savedInstanceState
     *            de tipus Bundle, però no el fem servir ja que el pas de
     *            paràmetres es fa via l'objecte aplicació
     *            <code>TimeTrackerApplication</code>.
     */

    //private MyCustomAdapter mAdapter;

    @Override
    public final void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(tag, "onCreate");

        setContentView(R.layout.activity_main); // Inflamos el layout de MainActivity
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar); // Inicializamos la barra de herramientas de mainActivity
        setSupportActionBar(toolbar); // Insertamos la barra de herramientas
        getSupportActionBar().setIcon(R.drawable.ic_access_alarm_white_24dp);
        arrelListView = (ListView) findViewById(R.id.listView1); //Inicializamos en la lista del root, listView1

        // Sacamos mensaje de ayuda por pantalla la primera vez que el usuario entra en la aplicacion.
        primeraVez();

        llistaDadesActivitats = new ArrayList<DadesActivitat>();
        aaAct = new AdaptadorActividades(this, llistaDadesActivitats); // Creación del adaptador para las actividades
        arrelListView.setAdapter(aaAct); // Seteamos el adapter en el arrelListView

        // Registra un contextMenu por si se realiza un long press
        registerForContextMenu(arrelListView);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab); // Creación del FloatingActionButton (botón de creación de una tarea o proyecto)
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * Si se hace click en el botón nos envia a la activity
                 * con nombre NuevaActividad
                 */
                Intent intent = new Intent(MainActivity.this, NuevaActividad.class);
                startActivity(intent);

            }
        });

        // Comprobamos si se hace click en algún elemento de la lista arrelListView
        arrelListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> arg0, final View arg1,
                                    final int pos, final long id) {
                Log.i(tag, "onItemClick");
                Log.d(tag, "pos = " + pos + ", id = " + id);

                Intent inte = new Intent(MainActivity.BAIXA_NIVELL);
                inte.putExtra("posicio", pos); // Enviamos la posición del elemento clicado
                sendBroadcast(inte); // Enviamos el intent con la acción BAIXA_NIVELL por broadcast
                if (llistaDadesActivitats.get(pos).isProjecte()) {
                    sendBroadcast(new Intent(
                            MainActivity.DONAM_FILLS)); // Si el elemento clicado es un proyecto envia la acción DONAM_FILLS por broadcast
                    setTitle(llistaDadesActivitats.get(pos).getNom());
                    Log.d(tag, "enviat intent DONAM_FILLS");
                } else if (llistaDadesActivitats.get(pos).isTasca()) {
                    startActivity(new Intent(MainActivity.this,
                            IntervalosActivity.class)); // Si el elemento clicado es una tarea, nos envía a la activity IntervalosActivity
                    // en aquesta classe ja es demanara la llista de fills
                } else {
                    // no pot ser!
                    assert false : "activitat que no es projecte ni tasca";
                }
            }
        });

    }

    /**
     * Método que sirve para sacar por pantalla un diálogo
     * si el usuario ha entrado a la aplicación por primera vez.
     */
    private void primeraVez() {
        final String PREFS_NAME = "MyPrefsFile";
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        if (settings.getBoolean("my_first_time", true)) {
            // first time task
            newDialog(); // Método que crea el mensaje de alerta en el primer inicio de la app
            // record the fact that the app has been started at least once
            settings.edit().putBoolean("my_first_time", false).commit();
        }
    }


    /**
     * Metodo para mostrar un menu en específico para una tarea o un proyecto
     * si se hace un long press sobre alguno de ellos.
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        if (v.getId() == R.id.listView1) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo; // A partir de esta variable se saca información del elemento clicado.
            Intent intent = new Intent(MainActivity.ACTIVIDAD_CLICADA); // Creación del intent con la acción a realizar.

            intent.putExtra("posicion_actividad", info.position); // Obtenemos la posición de la actividad en la que se ha hecho un long click
            sendBroadcast(intent); // Envio del intent por broadcast con la acción a realizar + posicion_actividad
            MenuInflater inflater = getMenuInflater(); // Obtenemos el inflater del menú.

            if (llistaDadesActivitats.get(info.position).isTasca()) {
                inflater.inflate(R.menu.menu_preferences_tarea, menu); // Inflamos el menú de preferencias de una tarea
            } else {
                inflater.inflate(R.menu.menu_preferences_proyecto, menu); // Inflamos el menú de preferencias de un proyecto
            }
        }
    }


    /**
     * Método para realizar una acción en específico según el
     * item que se haya seleccionado en el menú.
     * @param item opción que se elige en el menú.
     * @return true or false.
     */
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.action_editar_tarea:
                intent = new Intent(MainActivity.this, EditarActivity.class);
                startActivity(intent);
                break;

            case R.id.action_editar_proyecto:
                intent = new Intent(MainActivity.this, EditarActivity.class);
                startActivity(intent);
                break;

            case R.id.action_borrar_tarea:
                Toast.makeText(getApplicationContext(), "Comming soon", Toast.LENGTH_LONG).show();
                break;

            case R.id.action_borrar_proyecto:
                Toast.makeText(getApplicationContext(), "Comming soon", Toast.LENGTH_LONG).show();
                break;

            case R.id.action_detalles:
                Toast.makeText(getApplicationContext(), "Comming soon", Toast.LENGTH_LONG).show();
                break;

            default:
                return super.onContextItemSelected(item);
        }
        return true;
    }


    public void newDialog() {
        // Creación de un mensaje al iniciar la app
        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this); // Creación del diálogo
        //dialogo1.setTitle("Importante");
        dialogo1.setMessage("Para crear una tarea hacer click en +");
        dialogo1.setCancelable(false); // No seteamos un botón de cancelar
        dialogo1.setPositiveButton("Aceptar", null); // Seteo del botón de aceptar
        dialogo1.show(); // Se muestra el diálogo
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Intent intent;
        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_informe:
                intent = new Intent(MainActivity.this, GenerarInformeActivity.class);
                startActivity(intent);
                break;

            case R.id.action_borrar_todo:
                //Toast.makeText(getApplicationContext(),"Comming soon",Toast.LENGTH_LONG).show();
                //mAdapter.deleteAll();

                intent = new Intent(MainActivity.ELIMINAR_TODO);
                sendBroadcast(intent);

                //Refresco de la activity
                intent = getIntent();
                finish();
                startActivity(intent);
                break;

            case R.id.action_parar_todo:
                intent = new Intent(MainActivity.PARAR_TODO);
                sendBroadcast(intent);
                //Toast.makeText(getApplicationContext(), "Se tiene que hacer", Toast.LENGTH_LONG).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Gestor de l'event de prémer la tecla 'enrera' del D-pad. El que fem es
     * anar "cap amunt" en l'arbre de tasques i projectes. Si el projecte pare
     * de les activitats que es mostren ara no és nul (n'hi ha), 'pugem' per
     * mostrar-lo a ell i les seves activitats germanes. Si no n'hi ha, paro el
     * servei, deso l'arbre (equivalent a parar totes les tasques que s'estiguin
     * cronometrant) i pleguem de la aplicació.
     */
    @Override
    public final void onBackPressed() {
        Log.i(tag, "onBackPressed");
        if (activitatPareActualEsArrel) {
            Log.d(tag, "parem servei");
            sendBroadcast(new Intent(MainActivity.PARA_SERVEI));
            super.onBackPressed();
        } else {
            sendBroadcast(new Intent(MainActivity.PUJA_NIVELL));
            Log.d(tag, "enviat intent PUJA_NIVELL");
            sendBroadcast(new Intent(MainActivity.DONAM_FILLS));
            Log.d(tag, "enviat intent DONAM_FILLS");

            if (GestorArbreActivitats.getActivitatPareActual().getNombre().equals("root")) {
                setTitle("TimeTracker");
            } else {
                setTitle(GestorArbreActivitats.getActivitatPareActual().getNombre());
            }
        }
    }

    // D'aqui en avall els mètodes que apareixen són simplement sobrecàrregues
    // de mètodes de Activity per tal que es mostri un missatge de logging i
    // d'aquesta manera puguem entendre el cicle de vida d'un objecte d'aquesta
    // classe i depurar errors de funcionament de la interfase (on posar què).

    /**
     * Mostra un missatge de log per entendre millor el cicle de vida d'una
     * Activity.
     *
     * @param savedInstanceState
     *            objecte de classe Bundle, que no fem servir.
     */
    @Override
    public final void onSaveInstanceState(final Bundle savedInstanceState) {
        Log.i(tag, "onSaveInstanceState");
        super.onSaveInstanceState(savedInstanceState);
    }

    /**
     * Aquesta funció es crida després de <code>onCreate</code> quan hi ha un
     * canvi de configuració = rotar el mòbil 90 graus, passant de "portrait" a
     * apaisat o al revés.
     *
     * @param savedInstanceState
     *            Bundle que de fet no es fa servir.
     *
     * onConfigurationChanged
     */
    @Override
    public final void onRestoreInstanceState(final Bundle savedInstanceState) {
        Log.i(tag, "onRestoreInstanceState");
        super.onRestoreInstanceState(savedInstanceState);
    }

    /**
     * Mostra un missatge de log per entendre millor el cicle de vida d'una
     * Activity.
     */
    @Override
    public final void onStop() {
        Log.i(tag, "onStop");
        super.onStop();
    }

    /**
     * Mostra un missatge de log per entendre millor el cicle de vida d'una
     * Activity.
     */
    @Override
    public final void onDestroy() {
        Log.i(tag, "onDestroy");
        super.onDestroy();
    }

    /**
     * Mostra un missatge de log per entendre millor el cicle de vida d'una
     * Activity.
     */
    @Override
    public final void onStart() {
        Log.i(tag, "onStart");
        super.onStart();
    }

    /**
     * Mostra un missatge de log per entendre millor el cicle de vida d'una
     * Activity.
     */
    @Override
    public final void onRestart() {
        Log.i(tag, "onRestart");
        super.onRestart();
    }

    /**
     * Mostra un missatge de logging en rotar 90 graus el dispositiu (o
     * simular-ho en l'emulador). L'event <code>configChanged</code> passa quan
     * girem el dispositiu 90 graus i passem de portrait a landscape (apaisat) o
     * al revés. Això fa que les activitats siguin destruïdes (
     * <code>onDestroy</code>) i tornades a crear (<code>onCreate</code>). En
     * l'emulador del dispositiu, això es simula fent Ctrl-F11.
     *
     * @param newConfig
     *            nova configuració {@link Configuration}
     */
    @Override
    public final void onConfigurationChanged(final Configuration newConfig) {
        Log.i(tag, "onConfigurationChanged");
        if (Log.isLoggable(tag, Log.VERBOSE)) {
            Log.v(tag, newConfig.toString());
        }
    }
}
