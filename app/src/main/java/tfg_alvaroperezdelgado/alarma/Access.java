package tfg_alvaroperezdelgado.alarma;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Access extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access);
        findViewById(R.id.btTwitterAccess).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Access.this, addTwitterUser.class));
            }
        });
        findViewById(R.id.btUBUAccess).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Access.this, AddUbuUser.class));
            }
        });
        findViewById(R.id.btCustomMessageAccess).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Access.this, AddMessageUser.class));
            }
        });
        findViewById(R.id.btWeatherAccess).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Access.this, AddWeatherUser.class));
            }
        });

        findViewById(R.id.btAcceptAccess).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Access.this, MainActivity.class));
            }
        });

    }

}
