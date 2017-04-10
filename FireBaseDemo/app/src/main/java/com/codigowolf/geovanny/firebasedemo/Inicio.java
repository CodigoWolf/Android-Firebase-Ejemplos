package com.codigowolf.geovanny.firebasedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.widget.EditText;
import android.widget.TextView;

import com.codigowolf.geovanny.entidades.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Inicio extends AppCompatActivity {

    private TextView mensajetv;
    private EditText mensajeet;

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();/*Obtener referencia a la raíz*/
    DatabaseReference mensajeRef = ref.child("users");/*Obtener referencia a una rama*/

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
                //String value = dataSnapshot.getValue(String.class);/*Obtenemos el valor del mensaje*/
                //mensajetv.setText(value);
                /*User users = dataSnapshot.getValue(User.class);
                String keys = dataSnapshot.getKey();*/

                List users = new ArrayList();
                for (DataSnapshot child_user : dataSnapshot.getChildren()) {
                    User user = child_user.getValue(User.class);
                    users.add(user);
                }

                Log.d( "LISTAAAAA:", Arrays.asList(users).toString());

                mensajetv.setText(Arrays.asList(users).toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void agregar(View view){
        //String mensaje = mensajeet.getText().toString();
        User user = new User("aabarca", "52");
        String userId = "3";
        //mensajeRef.child("users").child(userId).setValue( user );
        mensajeRef.child(userId).setValue( user );
        Log.d("Agregar: ", user.getUsername().toString());
        /* Si quieres permitir que tus usuarios actualicen sus perfiles puedes
        actualizar el nombre de usuario de la siguiente manera:*/
        //mensajeRef.child("users").child(userId).child("username").setValue("grios");
        //mensajeRef.setValue(mensaje);
        mensajeet.setText("");
    }

    public void modificar(View view){
        String username = mensajeet.getText().toString();
        /*Especificando que campo de la base de datos modificar*/
        //mensajeRef.child("users").child("2").child("username").setValue( username );
        mensajeRef.child("2").child("username").setValue( username );
        mensajeet.setText("");
    }
}
