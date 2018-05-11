package com.chila.myapplication;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button btsig = (Button) findViewById(R.id.btSig);
        final TextView txtNombre = (TextView)findViewById(R.id.tvName);
        final TextView txtTel = (TextView)findViewById(R.id.tvTelefono);
        final TextView txtMail =(TextView)findViewById(R.id.tvMail);
        final TextView txtDesc =(TextView)findViewById(R.id.tvDesc);
        final DatePicker dpDate = (DatePicker)findViewById(R.id.dpDate);

        Bundle parametros = getIntent().getExtras();

        if(parametros.get(getResources().getString(R.string.pnombre))!=null){
            Contacto contacto = contacto = new Contacto(
                    parametros.getString(getResources().getString(R.string.pnombre)),
                    parametros.getLong(getResources().getString(R.string.pdate)),
                    parametros.getString(getResources().getString(R.string.ptel)),
                    parametros.getString(getResources().getString(R.string.pmail)),
                    parametros.getString(getResources().getString(R.string.pdesc)));

            txtNombre.setText(contacto.getNombre());
            dpDate.init(contacto.getCalFecha().YEAR,
                    contacto.getCalFecha().MONTH,
                    contacto.getCalFecha().DAY_OF_MONTH,
                    null);
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

                Intent intent= new Intent(MainActivity.this, ConfirmActivity.class);

                intent.putExtra(getResources().getString(R.string.pnombre), contacto.getNombre());
                intent.putExtra(getResources().getString(R.string.pdate), contacto.getFechaNac());
                intent.putExtra(getResources().getString(R.string.ptel), contacto.getTelefono());
                intent.putExtra(getResources().getString(R.string.pmail), contacto.getEmail());
                intent.putExtra(getResources().getString(R.string.pdesc), contacto.getDesc());

                startActivity(intent);

            }
        });
    }

    public static long getFechaDatePicker(DatePicker datePicker){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        Calendar fecha = Calendar.getInstance();
        fecha.set(year, month, day);

        return fecha.getTimeInMillis();
    }
}
