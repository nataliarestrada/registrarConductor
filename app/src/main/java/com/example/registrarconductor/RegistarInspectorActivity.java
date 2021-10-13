package com.example.registrarconductor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class RegistarInspectorActivity extends AppCompatActivity {
    private EditText editText_nombre, editText_apellido,editText_email, editText_dni, editText_fechaNacimiento, editText_contrasenia, editText_confContrasenia,editText_num_matricula;
    private Button btn_aceptar;
    private AsyncHttpClient cliente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registar_inspector);
        editText_nombre = (EditText)findViewById(R.id.editText_nombre);
        editText_apellido = (EditText)findViewById(R.id.editText_apellido);
        editText_email = (EditText)findViewById(R.id.editText_email);
        editText_dni = (EditText)findViewById(R.id.editText_dni);
        editText_fechaNacimiento = (EditText)findViewById(R.id.editText_fechaNacimiento);
        editText_contrasenia= (EditText)findViewById(R.id.editText_contraseniaL);
        editText_confContrasenia = (EditText)findViewById(R.id.editText_confContrasenia);
        editText_num_matricula = (EditText)findViewById(R.id.editText_num_matricula);
        btn_aceptar = (Button)findViewById(R.id.btn_aceptar);

        cliente = new AsyncHttpClient();

        btnRegistrar();

    }
    private void btnRegistrar(){
        btn_aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText_nombre.getText().toString().isEmpty() || editText_apellido.getText().toString().isEmpty() || editText_email.getText().toString().isEmpty() || editText_dni.getText().toString().isEmpty() || editText_contrasenia.getText().toString().isEmpty()||editText_fechaNacimiento.getText().toString().isEmpty()||editText_confContrasenia.getText().toString().isEmpty()||editText_num_matricula.getText().toString().isEmpty() ){
                    Toast.makeText(RegistarInspectorActivity.this, "Hay campos vacíos.", Toast.LENGTH_SHORT).show();
                }
                else{
                    Usuario I = new Usuario();
                    I.setNombre(editText_nombre.getText().toString().replaceAll(" ", "%20"));
                    I.setApellido(editText_apellido.getText().toString().replaceAll(" ", "%20"));
                    I.setEmail(editText_email.getText().toString().replaceAll(" ", "%20"));
                    I.setDni(Integer.parseInt(editText_dni.getText().toString()));
                    I.setFecha_nacimiento(editText_fechaNacimiento.getText().toString().replaceAll("/", "-"));
                    I.setContrasenia(editText_contrasenia.getText().toString());
                    I.setTipo_usuario(3);
                    I.setNum_matricula(Integer.parseInt(editText_num_matricula.getText().toString()));
                    agregarConductor(I);
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
                    Toast.makeText(RegistarInspectorActivity.this,"Conductor Agregado.", Toast.LENGTH_SHORT).show();
                    editText_nombre.setText("");
                    editText_apellido.setText("");
                    editText_email.setText("");
                    editText_dni.setText("");
                    editText_fechaNacimiento.setText("");
                    editText_contrasenia.setText("");
                    editText_confContrasenia.setText("");
                    editText_num_matricula.setText("");
                    startActivity(new Intent(RegistarInspectorActivity.this,LoginActivity.class));
                    finish();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
}
