package com.chila.myapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView txtNombre = findViewById(R.id.tvName);
        final TextView txtTel = findViewById(R.id.tvTelefono);
        final TextView txtMail = findViewById(R.id.tvMail);
        final TextView txtDesc = findViewById(R.id.tvDesc);
        final DatePicker dpDate = findViewById(R.id.dpDate);
        final Button btsig = findViewById(R.id.btSig);


        if(getIntent().getExtras()!=null){
            Bundle parametros = getIntent().getExtras();


            Contacto contacto;
            contacto = new Contacto(
                    parametros.getString(getResources().getString(R.string.pnombre)),
                    parametros.getLong(getResources().getString(R.string.pdate)),
                    parametros.getString(getResources().getString(R.string.ptel)),
                    parametros.getString(getResources().getString(R.string.pmail)),
                    parametros.getString(getResources().getString(R.string.pdesc)));

            txtNombre.setText(contacto.getNombre());


            dpDate.init(contacto.getCalFecha().get(Calendar.YEAR),
                        contacto.getCalFecha().get(Calendar.MONTH),
                        contacto.getCalFecha().get(Calendar.DAY_OF_MONTH),null);
            txtTel.setText(contacto.getTelefono());
            txtMail.setText(contacto.getEmail());
            txtDesc.setText(contacto.getDesc());
        }




        btsig.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Contacto contacto;
                contacto = new Contacto(
                        txtNombre.getText().toString(),
                        getFechaDatePicker(dpDate),
                        txtTel.getText().toString(),
                        txtMail.getText().toString(),
                        txtDesc.getText().toString()
                );

                Intent intent = new Intent(MainActivity.this, ConfirmActivity.class);

                intent.putExtra(getResources().getString(R.string.pnombre), contacto.getNombre());
                intent.putExtra(getResources().getString(R.string.pdate), contacto.getFechaNac());
                intent.putExtra(getResources().getString(R.string.ptel), contacto.getTelefono());
                intent.putExtra(getResources().getString(R.string.pmail), contacto.getEmail());
                intent.putExtra(getResources().getString(R.string.pdesc), contacto.getDesc());
                startActivity(intent);

            }
        });
    }




    public static long getFechaDatePicker(DatePicker datePicker) {
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();

        Calendar fecha = Calendar.getInstance();
        fecha.set(year, month, day);

        return fecha.getTimeInMillis();
    }
}
