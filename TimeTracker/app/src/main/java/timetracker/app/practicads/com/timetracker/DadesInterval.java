package timetracker.app.practicads.com.timetracker;

import android.util.Log;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import nucleo.Intervalo;



/**
 * Conté les dades d'un interval que poden ser mostrades per la interfase
 * d'usuari. <code>GestorArbreActivitats</code> en fa una llista dels de la
 * tasca pare actual i l'envia a la Activity
 * <code>LlistaIntervalsActivity</code> per que la mostri.
 * <p>
 * Aquesta classe simplifica el passar les dades d'interval a la Activity
 * corresponent que les visualitza. Veure {@link DadesActivitat} per saber
 * perquè.
 *
 * @author joans
 * @version 6 febrer 2012
 */
public class DadesInterval implements Serializable {

    /**
     * Necessari segons checkstyle.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Nom de la classe per fer aparèixer als missatges de logging del LogCat.
     *
     * @see Log
     */
    private final String tag = this.getClass().getSimpleName();

    /**
     * Data inicial d'un interval.
     * @see Intervalo
     */
    private Date dataInicial;

    /**
     * Data final d'un interval.
     * @see Intervalo
     */
    private Date dataFinal;

    /**
     * Durada d'un interval.
     * @see Intervalo
     */
    private long durada;

    /**
     * Extreu les dades de l'interval passat per paràmetre i les copia als
     * atributs propis.
     *
     * @param inter
     *            l'interval
     */
    public DadesInterval(final Intervalo inter) {
        dataInicial = inter.getFechaInicial();
        dataFinal = inter.getFechaFinal();
        durada = inter.getDuracion();
    }

    private String locale = java.util.Locale.getDefault().getCountry();

    public final String fechaInicialToString()
    {

        Log.d(tag, "Localizacion--> " + locale);
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy hh:mm:ss", new Locale(locale.toLowerCase(), locale));

        String strdi = sdf.format(dataInicial);
        return strdi;
    }

    public final String fechaFinalToString()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy hh:mm:ss", new Locale(locale.toLowerCase(), locale));
        String strdf = sdf.format(dataFinal);
        return strdf;
    }

    public final String duracionToString()
    {
        // TODO : aquest codi de conversió de durada en segons a
        // hores, minuts i segons, és redundant amb DadesActivitat.
        // Fer un mètode estàtic en alguna classe a l'efecte.

        /**
         * Factor de conversió
         */
        final long segonsPerHora = 3600;

        final long segonsPerDia = 86400;

        /**
         * Factor de conversió
         */
        final long segonsPerMinut = 60;

        long dias = (long) (durada / segonsPerDia);
        long hores = (long) (durada / segonsPerHora);
        long minuts = (long) ((durada - hores * segonsPerHora)
                / segonsPerMinut);
        long segons = (long) (durada - segonsPerHora * hores
                - segonsPerMinut * minuts);
        // String strdurada = Long.toString(durada);
        String strdurada = dias + "d " + hores + "h " + minuts + "m " + segons + "s";
        return strdurada;
    }

    // Getters

    /**
     * Getter de <code>dataInicial</code>.
     * @return {@link #dataInicial}.
     */
    public final Date getDataInicial() {
        return dataInicial;
    }

    /**
     * Getter de <code>dataFinal</code>.
     * @return {@link #dataFinal}.
     */
    public final Date getDataFinal() {
        return dataFinal;
    }

    /**
     * Getter de <code>durada</code>.
     * @return {@link #durada}.
     */
    public final long getDurada() {
        return durada;
    }
}
