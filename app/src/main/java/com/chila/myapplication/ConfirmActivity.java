package com.chila.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

public class ConfirmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        final Bundle parametros = getIntent().getExtras();
        final Contacto contacto;
        Contacto contacto1;

        contacto1 = new Contacto(
                parametros.getString(getResources().getString(R.string.pnombre)),
                parametros.getLong(getResources().getString(R.string.pdate)),
                parametros.getString(getResources().getString(R.string.ptel)),
                parametros.getString(getResources().getString(R.string.pmail)),
                parametros.getString(getResources().getString(R.string.pdesc)));

        contacto = contacto1;
        final TextView tvName = findViewById(R.id.tvName);
        final TextView tvFechaNac = findViewById(R.id.tvFechaNac);
        final TextView tvTel = findViewById(R.id.tvTel);
        final TextView tvMail = findViewById(R.id.tvMailConfirm);
        final TextView tvDesc = findViewById(R.id.tvDescripcion);


        tvName.setText(contacto.getNombre());

        Calendar fecha = Calendar.getInstance();
        fecha.setTimeInMillis(contacto.getFechaNac());
        tvFechaNac.setText(String.valueOf(contacto.getCalFecha().get(Calendar.DAY_OF_MONTH)) + "/" + String.valueOf(contacto.getCalFecha().get(Calendar.MONTH) + 1) + "/" + String.valueOf(contacto.getCalFecha().get(Calendar.YEAR)));

        tvTel.setText(contacto.getTelefono());
        tvMail.setText(contacto.getEmail());
        tvDesc.setText(contacto.getDesc());

        Button btEdit = findViewById(R.id.btEdit);
        btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(ConfirmActivity.this, MainActivity.class);

                intent  .putExtra(getResources().getString(R.string.pnombre), contacto.getNombre())
                        .putExtra(getResources().getString(R.string.pdate), contacto.getFechaNac())
                        .putExtra(getResources().getString(R.string.ptel), contacto.getTelefono())
                        .putExtra(getResources().getString(R.string.pmail), contacto.getEmail())
                        .putExtra(getResources().getString(R.string.pdesc), contacto.getDesc());


                startActivity(intent);
                finish();
            }
        });


    }

    public void onBackPressed() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Light_Dialog_Alert);
        builder.setTitle("Cancelar carga")
                .setMessage("Desea cancelar la carga del contacto?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();

                    }
                })

                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }


}
