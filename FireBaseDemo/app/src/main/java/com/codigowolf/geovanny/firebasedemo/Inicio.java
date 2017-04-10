package com.codigowolf.geovanny.firebasedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
    private EditText useridEt, usernameEt, edadEt;

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();/*Obtener referencia a la raíz*/
    DatabaseReference mensajeRef = ref.child("users");/*Obtener referencia a una rama*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        mensajetv = (TextView) findViewById(R.id.mensajetv);
        useridEt = (EditText) findViewById(R.id.useridEt);
        usernameEt = (EditText) findViewById(R.id.usernameEt);
        edadEt = (EditText) findViewById(R.id.edadEt);
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

                List<User> users = new ArrayList<User>();
                for (DataSnapshot child_user : dataSnapshot.getChildren()) {
                    User user = child_user.getValue(User.class);
                    users.add(user);
                }
                Log.d("Valores desde Firebase", Arrays.asList(users).toString());
                mensajetv.setText(Arrays.asList(users).toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void agregar(View view){
        mensajeRef.child( getUserCompleteWithData().getUserid() ).setValue( getUserCompleteWithData() );
        Log.d("Agregar: ", getUserCompleteWithData().toString());
        limpiarCajasEntrada();
    }

    public void modificar(View view){
        mensajeRef.child(getUserCompleteWithData().getUserid()).setValue( getUserCompleteWithData());
        Log.d("Modificar: ", getUserCompleteWithData().getUsername());
        limpiarCajasEntrada();
    }

    public User getUserCompleteWithData(){
        /*Obteniendo los valores de las cajas de entrada*/
        String userid = useridEt.getText().toString(),
                username = usernameEt.getText().toString(),
                edad = edadEt.getText().toString();
        /*Instanciar una variable de la clase User y pasar los parámetros correspondientes.*/
        User user = new User(userid, username, edad);
        return user;
    }

    public void limpiarCajasEntrada(){
        useridEt.setText("");
        usernameEt.setText("");
        edadEt.setText("");
    }
}
