package com.example.demoac_circulante;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Splash_login extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;
    boolean passwordVisible;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        // Aquí puedes configurar cualquier lógica adicional para la actividad de inicio de sesión

        // Por ejemplo, puedes obtener referencias a los EditText para el usuario y la contraseña
        editTextUsername = findViewById(R.id.Users);
        editTextPassword = findViewById(R.id.passwords);

        editTextPassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right = 2;
                if ( event.getAction()==MotionEvent.ACTION_UP ){
                    if (event.getRawX() >= editTextPassword.getRight() - editTextPassword.getCompoundDrawables()[Right].getBounds().width()){
                        int selection = editTextPassword.getSelectionEnd();
                        if (passwordVisible){

                            //establecer imagen dibujable aquí
                            editTextPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ocultar, 0);

                            //ocultar contraseña
                            editTextPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible = false;

                        }else {

                            //establecer imagen dibujable aquí
                            editTextPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.visualizar, 0);

                            //mostrar contraseña
                            editTextPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible = true;

                        }
                        editTextPassword.setSelection(selection);

                    }
                }
                return false;
            }
        });
    }

    public void loginClicked(View view) {
        // Aquí se llama cuando se hace clic en el botón de inicio de sesión

        // Verifica las credenciales (por ejemplo, usuario: "admin", contraseña: "password")
        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();

        if (isValidCredentials(username, password)) {
            // Credenciales válidas, iniciar MainActivity
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish(); // Opcional: finaliza LoginActivity para que el usuario no pueda volver atrás
        } else {
            // Credenciales inválidas, muestra un mensaje de error (puedes usar Toast o Snackbar)
            Toast.makeText(this, "Credenciales inválidas", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isValidCredentials(String username, String password) {
        // Aquí puedes implementar la lógica para verificar las credenciales
        // Por ejemplo, puedes comparar con credenciales almacenadas en tu base de datos o en algún otro lugar seguro
        return username.equals("admin") && password.equals("12345");
    }
}


