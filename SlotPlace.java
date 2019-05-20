package comtfs.example.gamer.carparking;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SlotPlace extends AppCompatActivity {
EditText t1;
Button button,pick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slot_place);
        final Bundle bundle = getIntent().getExtras();
        final String typemode=bundle.getString("type");

t1=(EditText) findViewById(R.id.place1);
button=(Button) findViewById(R.id.button);
pick=(Button)findViewById(R.id.button1);
button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String data=t1.getText().toString();


            Intent i = new Intent(SlotPlace.this, SlotBuilding.class);

            i.putExtra("place", data);
            startActivity(i);


    }

});
pick.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
       String location= bundle.getString("location");
        Toast.makeText(getApplicationContext(),location,Toast.LENGTH_LONG).show();
        Intent i = new Intent(SlotPlace.this, SlotBuilding.class);
        i.putExtra("type",typemode);
        i.putExtra("place", location);
        startActivity(i);
    }
});
}}
