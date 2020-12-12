package br.com.example.a1984;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void exibir(View view) {
        //
        int opcao = view.getId();
        Intent intent;

        switch (opcao) {
            case R.id.button7:
                intent = new Intent(getApplicationContext(), Cliente.class);
                startActivity(intent);
                break;
            case R.id.button8:
                intent = new Intent(getApplicationContext(), SubscribeAsyncExample.class);
                startActivity(intent);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + opcao);
        }
    }
}