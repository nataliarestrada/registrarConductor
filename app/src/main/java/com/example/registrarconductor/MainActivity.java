package com.example.registrarconductor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.*;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    private EditText editText_nombre, editText_apellido,editText_email, editText_dni, editText_fechaNacimiento, editText_contrasenia, editText_confContrasenia;
    private Button btn_aceptar, btn_inicio;
    //private ListView listView_conductor;
    private AsyncHttpClient cliente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText_nombre = (EditText)findViewById(R.id.editText_nombre);
        editText_apellido = (EditText)findViewById(R.id.editText_apellido);
        editText_email = (EditText)findViewById(R.id.editText_email);
        editText_dni = (EditText)findViewById(R.id.editText_dni);
        editText_fechaNacimiento = (EditText)findViewById(R.id.editText_fechaNacimiento);
        editText_contrasenia= (EditText)findViewById(R.id.editText_contraseniaL);
        editText_confContrasenia = (EditText)findViewById(R.id.editText_confContrasenia);
        btn_aceptar = (Button)findViewById(R.id.btn_aceptar);
        btn_inicio = (Button)findViewById(R.id.btn_inicio);
        btn_inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
            }
        });
       //listView_conductor = (ListView)findViewById(R.id.listView_conductor);

        cliente = new AsyncHttpClient();

        btnRegistrar();

    }
    private void btnRegistrar(){
        btn_aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText_nombre.getText().toString().isEmpty() || editText_apellido.getText().toString().isEmpty() || editText_email.getText().toString().isEmpty() || editText_dni.getText().toString().isEmpty() || editText_contrasenia.getText().toString().isEmpty()||editText_fechaNacimiento.getText().toString().isEmpty()||editText_confContrasenia.getText().toString().isEmpty() ){
                    Toast.makeText(MainActivity.this, "Hay campos vacíos.", Toast.LENGTH_SHORT).show();
                }
                else{
                    Usuario C = new Usuario();
                    C.setNombre(editText_nombre.getText().toString().replaceAll(" ", "%20"));
                    C.setApellido(editText_apellido.getText().toString().replaceAll(" ", "%20"));
                    C.setEmail(editText_email.getText().toString().replaceAll(" ", "%20"));
                    C.setDni(Integer.parseInt(editText_dni.getText().toString()));
                    C.setFecha_nacimiento(editText_fechaNacimiento.getText().toString().replaceAll("/", "-"));
                    C.setContrasenia(editText_contrasenia.getText().toString());
                    C.setTipo_usuario(2);
                    C.setNum_matricula(0);
                    agregarConductor(C);
                }
            }
        });

    }
    private void agregarConductor(Usuario c){
        String url = "https://bdparking.000webhostapp.com/registrarConductor.php?";
        String p = "Nombre="+c.getNombre()+"&Apellido=" + c.getApellido() + "&Email=" + c.getEmail() + "&Dni=" + c.getDni() + "&FechaNacimiento=" + c.getFecha_nacimiento() + "&Contrasenia=" + c.getContrasenia() + "&Matricula=" + c.getNum_matricula() + "&TipoUsuario=" + c.getTipo_usuario();
        cliente.post(url + p, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode == 200){
                    Toast.makeText(MainActivity.this,"Conductor Agregado.", Toast.LENGTH_SHORT).show();
                    editText_nombre.setText("");
                    editText_apellido.setText("");
                    editText_email.setText("");
                    editText_dni.setText("");
                    editText_fechaNacimiento.setText("");
                    editText_contrasenia.setText("");
                    editText_confContrasenia.setText("");
                    startActivity(new Intent(MainActivity.this,LoginActivity.class));
                    finish();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
}
