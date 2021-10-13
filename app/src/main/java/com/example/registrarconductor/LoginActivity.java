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

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class LoginActivity extends AppCompatActivity {
    private Button btn_crearCuenta, btn_iniciar;
    private EditText editText_usuario, editText_contrasenia;
    private AsyncHttpClient cliente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn_crearCuenta = (Button)findViewById(R.id.btn_crearCuenta);
        btn_iniciar = (Button)findViewById(R.id.btn_iniciar);
        editText_usuario = (EditText)findViewById(R.id.editText_usuario);
        editText_contrasenia = (EditText)findViewById(R.id.editText_contraseniaL);
        cliente = new AsyncHttpClient();
        btn_crearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
            }
        });

        botonLogin();
    }
    private void botonLogin(){
        btn_iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editText_usuario.getText().toString().isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(editText_usuario.getText().toString()).matches()) {
                    editText_usuario.setError("Introduzca una dirección de correo electrónico válida");
                    editText_usuario.setText("");
                } else {
                    editText_usuario.setError(null);

                    if (editText_contrasenia.getText().toString().isEmpty()){
                        editText_contrasenia.setError("Introduzca su contraseña");
                    }
                    else
                    {
                        editText_contrasenia.setError(null);
                        String usuario = editText_usuario.getText().toString();
                        String contrasenia = editText_contrasenia.getText().toString();
                        String url = "https://bdparking.000webhostapp.com/consultarConductor.php?Email="+usuario+"&Contrasenia="+contrasenia;
                        cliente.post(url, new AsyncHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                                if(statusCode == 200) {
                                    String respuesta = new String(responseBody);
                                    if (respuesta.equalsIgnoreCase("null")) {
                                        Toast.makeText(LoginActivity.this, "Error De Usuario y/o Contraseña!!", Toast.LENGTH_SHORT).show();
                                        editText_usuario.setText("");
                                        editText_contrasenia.setText("");
                                    } else {
                                        try {
                                            JSONObject jsonObj = new JSONObject(respuesta);
                                            Usuario u = new Usuario();
                                            u.setDni(jsonObj.getInt("dni"));
                                            u.setNombre(jsonObj.getString("nombre"));
                                            u.setApellido(jsonObj.getString("apellido"));
                                            u.setEmail(jsonObj.getString("email"));
                                            u.setFecha_nacimiento(jsonObj.getString("fecha_nacimiento"));
                                            u.setContrasenia(jsonObj.getString("contrasenia"));
                                            u.setNum_matricula(jsonObj.getInt("num_matricula"));
                                            u.setTipo_usuario(jsonObj.getInt("id_tipo_usuario"));
                                            Intent i = null;
                                            switch(u.getTipo_usuario()){
                                                case 1:
                                                    i = new Intent(LoginActivity.this,MenuAdmActivity.class);
                                                    startActivity(new Intent(LoginActivity.this,MenuAdmActivity.class));
                                                    break;
                                                case 2:
                                                    i = new Intent(LoginActivity.this,ConductorActivity.class);
                                                    startActivity(new Intent(LoginActivity.this,ConductorActivity.class));
                                                    break;
                                            }
                                            i.putExtra("u",u);
                                            startActivity(i);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            }

                            @Override
                            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                                Toast.makeText(LoginActivity.this, "Error Desconocido. Intentelo De Nuevo!!", Toast.LENGTH_SHORT).show();
                                editText_usuario.setText("");
                                editText_contrasenia.setText("");
                            }
                        });
                    }

                }
                /*if(editText_usuario.getText().toString().isEmpty() || editText_contrasenia.getText().toString().isEmpty()){
                    Toast.makeText(LoginActivity.this, "Hay Campos En Blanco!!", Toast.LENGTH_SHORT).show();
                    editText_usuario.setText("");
                    editText_contrasenia.setText("");
                }else{
                    String usuario = editText_usuario.getText().toString();
                    String contrasenia = editText_contrasenia.getText().toString();
                    String url = "https://bdparking.000webhostapp.com/consultarConductor.php?Email="+usuario+"&Contrasenia="+contrasenia;
                    cliente.post(url, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            if(statusCode == 200) {
                                String respuesta = new String(responseBody);
                                if (respuesta.equalsIgnoreCase("null")) {
                                    Toast.makeText(LoginActivity.this, "Error De Usuario y/o Contraseña!!", Toast.LENGTH_SHORT).show();
                                    editText_usuario.setText("");
                                    editText_contrasenia.setText("");
                                } else {
                                    try {
                                        JSONObject jsonObj = new JSONObject(respuesta);
                                        Usuario u = new Usuario();
                                        u.setDni(jsonObj.getInt("dni"));
                                        u.setNombre(jsonObj.getString("nombre"));
                                        u.setApellido(jsonObj.getString("apellido"));
                                        u.setEmail(jsonObj.getString("email"));
                                        u.setFecha_nacimiento(jsonObj.getString("fecha_nacimiento"));
                                        u.setContrasenia(jsonObj.getString("contrasenia"));
                                        u.setNum_matricula(jsonObj.getInt("num_matricula"));
                                        u.setTipo_usuario(jsonObj.getInt("id_tipo_usuario"));
                                        Intent i = null;
                                        switch(u.getTipo_usuario()){
                                            case 1:
                                                i = new Intent(LoginActivity.this,MenuAdmActivity.class);
                                                startActivity(new Intent(LoginActivity.this,MenuAdmActivity.class));
                                                break;
                                            case 2:
                                                i = new Intent(LoginActivity.this,ConductorActivity.class);
                                                startActivity(new Intent(LoginActivity.this,ConductorActivity.class));
                                                break;
                                        }
                                        i.putExtra("u",u);
                                        startActivity(i);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                            Toast.makeText(LoginActivity.this, "Error Desconocido. Intentelo De Nuevo!!", Toast.LENGTH_SHORT).show();
                            editText_usuario.setText("");
                            editText_contrasenia.setText("");
                        }
                    });
                }*/
            }
        });
    } // Cierra el metodo botonLogin

}
