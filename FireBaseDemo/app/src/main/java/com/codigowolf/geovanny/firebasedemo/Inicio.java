package com.codigowolf.geovanny.firebasedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Inicio extends AppCompatActivity {

    private TextView mensajetv;
    private EditText mensajeet;

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();/*Obtener referencia a la raíz*/
    DatabaseReference mensajeRef = ref.child("mensaje");/*Obtener referencia a una rama*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        mensajetv = (TextView) findViewById(R.id.mensajetv);
        mensajeet = (EditText) findViewById(R.id.mensajeet);
    }

    @Override
    protected void onStart() {
        super.onStart();
        /*Leer desde la base de datos*/
        mensajeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);/*Obtenemos el valor del mensaje*/

                mensajetv.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void modificar(View view){
        String mensaje = mensajeet.getText().toString();
        mensajeRef.setValue(mensaje);
        mensajeet.setText("");
    }
}
