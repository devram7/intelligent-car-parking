package comtfs.example.gamer.carparking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SlotBuilding extends AppCompatActivity {

    String[] thrissur={"confident group","ace tower","kalayan tower"};
    String[] aluva={"royal plaza","najadh tower","hill tower"};
    TextView place1,place2,place3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slot_building);
        place1=(TextView)findViewById(R.id.place1);
        place2=(TextView)findViewById(R.id.place2);
        place3=(TextView)findViewById(R.id.place3);

        Bundle extras = getIntent().getExtras();
        final String typemode=extras.getString("type");
        String   place= extras.getString("place");
        Log.e("place",place+" "+typemode);

        if(place.equals("Thrissur")){
        place1.setText(thrissur[0]);
        place2.setText(thrissur[1]);
        place3.setText(thrissur[2]);

        }else if(place.equals("Aluva")){

                place1.setText(aluva[0]);
                place2.setText(aluva[1]);
                place3.setText(aluva[2]);

            }else{

            Toast.makeText(getApplicationContext(),"Server busy :contact Admin", Toast.LENGTH_LONG).show();
        }

        place1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SlotBuilding.this, slotStatusPublic.class);
                String strName = place1.getText().toString();
                i.putExtra("place", strName);i.putExtra("type",typemode);
                startActivity(i);
            }
        });
        place2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SlotBuilding.this, slotStatusPublic.class);
                String strName = place2.getText().toString();
                i.putExtra("type",typemode);
                i.putExtra("place", strName);
                startActivity(i);
            }
        });
        place3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SlotBuilding.this, slotStatusPublic.class);
                String strName = place3.getText().toString();
                i.putExtra("type",typemode);
                i.putExtra("place", strName);
                startActivity(i);
            }
        });


        }
    }