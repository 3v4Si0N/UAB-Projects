package timetracker.app.practicads.com.timetracker;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import nucleo.Proyecto;
import nucleo.Actividad;
import nucleo.Intervalo;
import nucleo.Tarea;
import android.util.Log;

/**
 * Factoria d'arbres de projectes, tasques i intervals aleatoris, de mida i
 * contingut depenent dels paràmetres passats al constructor. Són arbres per fer
 * proves per si no tenim la funcionalitat d'afegir i eliminar projectes i
 * tasques implementada a la interfase. També per estalviar-nos d'haver de crear
 * arbres grans manualment.
 * <p>
 * Anàlogament a la classe estàndard de Java <code>Random</code>, que té mètodes
 * <code>nextBoolean</code>, <code>nextInt</code> ... <code>nextDouble</code>
 * que retornen valors aleatoris, aquí tenim el mètode {@link #nextArbre} per
 * obtenir un arbre sencer d'activitats i intervals.
 *
 * @author joans
 * @version 6 febrer 2012
 */
public class ArbreAleatori {

    /**
     * Nom de la classe per fer aparèixer als missatges de logging del LogCat.
     *
     * @see Log
     */
    private final String tag = "ArbreAleatori";

    /**
     * Factor de conversió necessari ja que les durades d'interval i activitat
     * es representen en milisegons.
     */
    private final long milisegonsPerSegon = 1000L;

    /**
     * Retorna valors aleatoris amb distribució uniforme [0,1). El constructor
     * li assigna un seed prefixat, de manera que el constructor sense
     * paràmetres <code>ArbreAleatori</code> sempre farà el mateix arbre, si 
     * els
     * paràmetres són els mateixos.
     */
    private Random rand = new Random();

    /**
     * Retorna una data aleatòria entre les dues dates passades al seu
     * constructor, les mateixes que les del constructor de
     * <code>ArbreAleatori</code>.
     *
     * @see RandomDate
     */
    private RandomDate rd;

    /**
     * Sense seed, aquest constructor sempre generarà el mateix arbre, si la
     * resta de paràmetres és el mateix.
     */
    public ArbreAleatori() {
        this(0L);
        // per generar sempre la mateixa seqüència de valors, amb la idea
        // de generar sempre un mateix arbre, si la resta de paràmetres
        // que fa servir el mètode tenen el mateix valor. Així podrem
        // repetir proves, si convé.
    }

    /**
     * Constructor amb llavor pel generador de números aleatoris, que ens
     * permetria obtenir una seqüència diferent d'arbres aleatoris, encara que
     * això no és necessari ara.
     * 
     * @param seed
     *            llavor del generador de valors aleatoris
     */
    public ArbreAleatori(final long seed) {
        rand.setSeed(seed);
    }

    /**
     * Crea un arbre de projectes, tasques i intervals, on les dates inicial i
     * fi, les durades, els noms i les descripcions de tots ells són aleatoris,
     * si bé consistents. La mida i contingut de l'arbre depèn dels següents
     * paràmetres :
     * 
     * @param nNivells
     *            nombre de nivells de l'arbre, comptant tant el node arrel que
     *            no veu l'usuari, però no els intervals, que en són les fulles.
     * @param nMaximActivitatsFilles
     *            Maxim nombre d'activitats filles d'un projecte. El nombre
     *            efectiu s'escull aleatòriament.
     * @param nMaximIntervalsFills
     *            Maxim nombre d'intervals fills d'una tasca. El nombre efectiu
     *            s'escull aleatòriament.
     * @param ratio
     *            Proporció entre projectes i tasques filles d'un projecte, en
     *            promig. Es un numero entre 0 i 1 (0= cap projecte, 1=cap
     *            tasca).
     * @param dataInici
     *            data mínima de començament d'un interval.
     * @param dataFi
     *            data màxima de final d'interval.
     * @param duradaMinimaInterval
     *            en segons.
     * @param duradaMaximaInterval
     *            en segons.
     * @return Arrel de l'arbre creat.
     */
    public final Proyecto nextArbre(final int nNivells,
                                    final int nMaximActivitatsFilles, final int nMaximIntervalsFills,
                                    final double ratio, final Date dataInici, final Date dataFi,
                                    final long duradaMinimaInterval, final long duradaMaximaInterval) {
        /**
         * Arrel de l'arbre creat que retornem com a resultat.
         */
        Proyecto arrel;

        /**
         * Llista d'activitats del nivell superior a l'actual, en generar
         * l'arbre.
         */
        ArrayList<Actividad> llistaActivitatsPare = new ArrayList<Actividad>();

        rd = new RandomDate(dataInici, dataFi);

        arrel = new Proyecto("root", "arrel de projectes", null);
        llistaActivitatsPare.add(arrel);
        for (int i = 0; i < nNivells; i++) {
            Log.d(tag, "******* NIVELL " + (i + 1) + " *********");
            for (Actividad act : llistaActivitatsPare) {
                if (act.getClass().getName().endsWith("Proyecto")) {
                    // li pengem més projectes i tasques
                    int nActivitats = (int) Math.round(nMaximActivitatsFilles
                            * rand.nextDouble());
                    Proyecto projPare = (Proyecto) act;
                    for (int j = 0; j < nActivitats; j++) {
                        fesActivitatsFilles(projPare, ratio,
                                nMaximIntervalsFills, dataFi,
                                duradaMinimaInterval, duradaMaximaInterval);
                    }
                }
            }
            // actualitzem la llista d'activitats pare amb les activitats
            // filles del nivell actual
            int nact = llistaActivitatsPare.size();
            for (int k = 0; k < nact; k++) {
                Actividad act = llistaActivitatsPare.get(k);
                if (act.getClass().getName().endsWith("Proyecto")) {
                    Proyecto proj = (Proyecto) act;
                    llistaActivitatsPare.addAll(proj.getActividades());
                    llistaActivitatsPare.remove(0);
                }
            }
        }
        // Aquest arbre no es consistent: les activitats no tenen una
        // data inicial ni final ni durada, cal calcular-les a partir
        // dels intervals descendents.
        fesConsistencia(arrel);

        return arrel;
    }

    /**
     * Mètode auxiliar a <code>nextArbre</code> per reduir-ne la complexitat,
     * que provoca un alt nivell d'aniuament.
     * 
     * @param projPare
     *            Projecte del qual penjarem les activitats filles. No és final
     *            per que en afegir-li fills es modifica.
     * @param nMaximIntervalsFills
     *            Maxim nombre d'intervals fills d'una tasca. El nombre efectiu
     *            s'escull aleatòriament.
     * @param ratio
     *            Proporció entre projectes i tasques filles d'un projecte, en
     *            promig. Es un numero entre 0 i 1 (0= cap projecte, 1=cap
     *            tasca).
     * @param dataFi
     *            Data màxima de final d'interval.
     * @param duradaMinimaInterval
     *            En segons.
     * @param duradaMaximaInterval
     *            En segons.
     * @see #nextArbre
     */
    private void fesActivitatsFilles(Proyecto projPare, final double ratio,
            final int nMaximIntervalsFills, final Date dataFi,
            final long duradaMinimaInterval, final long duradaMaximaInterval) {
        final int selectorXifresNom = 1000; // noms de 3 xifres
        String nom = Integer.toString((int) Math.abs(selectorXifresNom
                * rand.nextDouble()));
        String descr = Long.toString(Math.abs(rand.nextLong()));
        if (rand.nextDouble() < ratio) {
            // faig un projecte fill
            Proyecto p = new Proyecto("P " + nom, descr, projPare);
            Log.d(tag,
                    "faig projecte " + p.getNombre() + " fill de "
                            + projPare.getNombre());
        } else {
            // faig una tasca filla i els seus intervals
            Tarea tasca = new Tarea("T " + nom, descr, projPare);
            Log.d(tag,
                    "faig tasca " + tasca.getNombre() + " filla de "
                            + projPare.getNombre());
            int nIntervals = (int) Math.round(nMaximIntervalsFills
                    * rand.nextDouble());
            for (int k = 0; k < nIntervals; k++) {
                Intervalo inter = new Intervalo(tasca);
                long durada = Math.round(duradaMinimaInterval
                        + (duradaMaximaInterval - duradaMinimaInterval)
                        * rand.nextDouble());
                Log.d(tag, "durada = " + durada + " entre "
                        + duradaMinimaInterval + " i " + duradaMaximaInterval);
                Date di = rd.nextDate();
                Date df = new Date(di.getTime() + durada * milisegonsPerSegon);
                if (df.after(dataFi)) {
                    // escurcem l'interval per que quedi entre data inici i fi
                    df = dataFi;
                    // i actualitzem conseqüentment la durada
                    durada = (long) Math
                            .round(((double) df.getTime() - (double) di
                                    .getTime()) / milisegonsPerSegon);
                }
                tasca.addIntervalo(inter);
                Log.d(tag, "faig interval fill de " + tasca.getNombre());
            }
        }
    }

    /**
     * Fem que els projectes i tasques tinguin uns valors de data inicial, final
     * i durada consistent amb els seus descendents. Cal recórrer l'arbre en
     * postordre i assignar a cada activitat com a data inicial el mínim de les
     * dates inicials dels seus fills, com a final el maxim de dates finals, i
     * com a durada la suma de les seves durades.
     * 
     * @param actArrel
     *            arrel del subarbre actual, que pot ser un projecte o una
     *            tasca, no un interval. No pot ser final per que en modificar
     *            les dades dels descendents per que siguin consistents, les
     *            seves (es un projecte més) també ho seran.
     */
    private void fesConsistencia(Actividad actArrel) {
        Date di = new Date(Long.MAX_VALUE);
        Date df = new Date(Long.MIN_VALUE);
        int durada = 0;
        if (actArrel.getClass().getName().endsWith("Proyecto")) {
            // posem com a data inicial la mínima de les dates inicials
            // de les activitats filles, data final la màxima de les
            // dates finals, i la durada com la suma de durades, però
            // es clar, un cop els subarbres fills siguin ja consistents!
            Proyecto proj = (Proyecto) actArrel;
            for (Actividad act : proj.getActividades()) {
                fesConsistencia(act);
                if (di.after(act.getFechaInicial())) {
                    di = act.getFechaInicial();
                }
                if (df.before(act.getFechaFinal())) {
                    df = act.getFechaFinal();
                }
                durada += act.getDuracion();
            }
        } else {
            // es una tasca => calculem la seva data inicial com el
            // mínim de les dates inicials dels seus intervals, la data
            // final com el màxim de les dated finals dels intervals, i
            // la durada com la suma de les durades dels intervals.
            Tarea tasca = (Tarea) actArrel;
            for (Intervalo inter : tasca.getIntervalos()) {
                if (inter.getFechaInicial().before(di)) {
                    di = inter.getFechaInicial();
                }
                if (inter.getFechaFinal().after(df)) {
                    df = inter.getFechaFinal();
                }
                durada += inter.getDuracion();
            }
        }
        /*actArrel.setFechaInicial(di);
        actArrel.setDataFinal(df);
        actArrel.setDurada(durada);*/
    }

    /**
     * Classe d'utilitat que ens dona una data Date aleatòria entre una data
     * inicial i una final passades al constructor. El funcionament volem que
     * sigui anàleg a <code>Math.Random</code>, i per això té un mètode
     * <code>nextDate</code>.
     * 
     * @author joans
     * @version 6 febrer 2012
     */
    private class RandomDate {
        /**
         * Data inicial en format de milisegons.
         */
        private long valInici;

        /**
         * Data final en format de milisegons.
         */
        private long valFi;

        /**
         * Variable de la que extreiem valors aleatoris amb distribució uniforme
         * [0,1), i que convertim a una data.
         */
        private Random rand;

        /**
         * Constructor sense paràmetre Random.
         * 
         * @param dataInici
         *            data a partir de la qual obtindrem dates aleatòries
         * @param dataFi
         *            màxima data possible
         */
        public RandomDate(final Date dataInici, final Date dataFi) {
            this(dataInici, dataFi, new Random());
        }

        /**
         * Constructor amb tots els paràmetres.
         * 
         * @param dataInici
         *            data a partir de la qual obtindrem dates aleatòries
         * @param dataFi
         *            màxima data possible
         * @param r
         *            variable de tipus Random, que necessitem passar si és que
         *            volem generar la mateixa seqüència de dates aleatòries
         *            sempre.
         */
        public RandomDate(final Date dataInici, final Date dataFi,
                final Random r) {
            rand = r;

            // getTime() retorna el nombre de milisegons des de
            // l'1 de gener de 1970.
            valInici = dataInici.getTime();
            valFi = dataFi.getTime();

            assert (valFi >= valInici) : "data inicial posterior a data final";
        }

        /**
         * Anàlogament a la classe Math.Random, per obtenir una data aleatòria
         * demanem la següent.
         * 
         * @return data aleatòria
         */
        public final Date nextDate() {
            long timeStamp = (long) (rand.nextDouble() * (valFi - valInici))
                    + valInici;
            Date d = new Date(timeStamp);
            return d;
        }
    }
}
