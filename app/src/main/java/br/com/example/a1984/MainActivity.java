package br.com.example.a1984;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import static br.com.example.a1984.R.id;
import static br.com.example.a1984.R.layout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
    }

    public void exibir(View view) {
        //
        try {
            int opcao = view.getId();
            Intent intent;

            if (opcao == id.postar) {
                intent = new Intent(getApplicationContext(), Cliente.class);
                startActivity(intent);
            } else if (opcao == id.visualizar) {
                intent = new Intent(getApplicationContext(), SubscribeAsyncExample.class);
                startActivity(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Cara, deu um probleminha...\n" + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }
}