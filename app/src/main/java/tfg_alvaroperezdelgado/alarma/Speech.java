package tfg_alvaroperezdelgado.alarma;
import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.util.Locale;

import Model.Container;

public class Speech extends Activity implements TextToSpeech.OnInitListener
{
    private TextToSpeech textToSpeech;
    private EditText editText;
    private Switch swEsEn;
    private Button btnSpanish;
    private Button btSpeechCustom;
    private Container container;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_speech );

        btnSpanish = ( Button ) findViewById( R.id.btSpeechSpa );
        btSpeechCustom = ( Button ) findViewById( R.id.btSpeechCustom );

        //Instanciamos los componentes de la vista
        textToSpeech = new TextToSpeech( this, this );
        editText = ( EditText ) findViewById( R.id.etEntry );
        swEsEn=(Switch)findViewById(R.id.swESEN);

        //obtenemos una instancia de container
        container=Container.getInstance();


        btnSpanish.setOnClickListener( new View.OnClickListener()
        {
            @Override public void onClick( View v )
            {

                if(swEsEn.isChecked()==false){
                    textToSpeech.setLanguage(new Locale("spa", "ESP"));
                    speak(editText.getText().toString());
                }else{
                    textToSpeech.setLanguage( Locale.ENGLISH );
                    speak( editText.getText().toString() );
                }
            }
        } );

        btSpeechCustom.setOnClickListener( new View.OnClickListener()
        {
            @Override public void onClick( View v )
            {

                if(swEsEn.isChecked()==false){
                    textToSpeech.setLanguage(new Locale("spa", "ESP"));
                    speak(container.getCustomMessage().toString());
                }else{
                    textToSpeech.setLanguage( Locale.ENGLISH );
                    speak( container.getCustomMessage().toString() );
                }
            }
        } );

    }

    @Override
    public void onInit( int status )
    {
        if ( status == TextToSpeech.LANG_MISSING_DATA | status == TextToSpeech.LANG_NOT_SUPPORTED )
        {
            Toast.makeText( this, "ERROR LANG_MISSING_DATA | LANG_NOT_SUPPORTED", Toast.LENGTH_SHORT ).show();
        }
    }

    private void speak( String str )
    {
        textToSpeech.speak( str, TextToSpeech.QUEUE_FLUSH, null );
        textToSpeech.setSpeechRate( 0.0f );
        textToSpeech.setPitch( 0.0f );
    }

    @Override
    protected void onDestroy()
    {
        if ( textToSpeech != null )
        {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }
}