package tfg_alvaroperezdelgado.alarma;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddMessageUser extends AppCompatActivity {

    public String customMessage;
    public Button acept;
    public EditText message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_message_user);

        acept=(Button)findViewById(R.id.btAccessMessageAcept);
        message=(EditText)findViewById(R.id.etCustonMessage);
        acept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customMessage=message.getText().toString();
            }
        });

    }


}
