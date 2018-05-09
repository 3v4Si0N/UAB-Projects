package timetracker.app.practicads.com.timetracker;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;

public class GenerarInformeActivity extends AppCompatActivity {

    private TextView desde, hasta;
    private Button buttonGenerar;
    private RadioGroup radioInforme, radioFormato;
    public static final String GENERAR_INFORME = "Generar_informe";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generar_informe);
        desde = (TextView) findViewById(R.id.fecha_desde);
        hasta = (TextView) findViewById(R.id.fecha_hasta);
        buttonGenerar = (Button) findViewById(R.id.buttonGenerar);

        onClickEditText(desde);
        onClickEditText(hasta);
        onClickGenerarInforme(buttonGenerar);

        // Muestra la <-- en el action bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        verifyStoragePermissions(GenerarInformeActivity.this);
    }


    private void onClickEditText(final TextView editText)
    {
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //To show current date in the datepicker
                Calendar mcurrentDate=Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker=new DatePickerDialog(GenerarInformeActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        // TODO Auto-generated method stub
                        /*      Get date and time    */
                        editText.setText("");
                        int d = selectedday;
                        int m = selectedmonth + 1;
                        int y = selectedyear;
                        String c = d + "-" + m + "-" + y;
                        editText.append(c);
                    }
                },mYear, mMonth, mDay);
                mDatePicker.show();
            }
        });
    }

    private void onClickGenerarInforme(final Button buttonGenerar)
    {
        radioInforme = (RadioGroup) findViewById(R.id.radioInforme);
        radioFormato = (RadioGroup) findViewById(R.id.radioFormato);

        buttonGenerar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // get selected radio button from radioGroup
                int selectedIdInforme = radioInforme.getCheckedRadioButtonId();
                int selectedIdFormato = radioFormato.getCheckedRadioButtonId();


                if (selectedIdInforme == R.id.radioBreve) {
                    selectedIdInforme = 0;
                } else {
                    selectedIdInforme = 1;
                }

                if (selectedIdFormato == R.id.radioTxt) {
                    selectedIdFormato = 0;
                } else {
                    selectedIdFormato = 1;
                }

                Intent intent = new Intent(GenerarInformeActivity.GENERAR_INFORME);
                intent.putExtra("tipo_informe", selectedIdInforme); // Enviamos por el intent el nombre del proyecto
                intent.putExtra("tipo_formato", selectedIdFormato); // Enviamos por el intent la descripcion del proyecto
                sendBroadcast(intent);
            }
        });
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

    // Storage Permissions variables
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    /**
     * Persmission method.
     * Método necesario para moviles con version +6.0 ya que
     * a partir de esta versión se pide una confirmación de permisos
     * antes de usar ciertas funcionalidades.
     */
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have read or write permission
        int writePermission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int readPermission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (writePermission != PackageManager.PERMISSION_GRANTED || readPermission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }
}
