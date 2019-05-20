package comtfs.example.gamer.carparking;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class parkingType extends AppCompatActivity {

    Intent intent;
    Button privateSlot, publicSlot;
    DatabaseReference dr;
    String val;

    @Override
    protected void onCreate(Bundle savedBundleInstance){
        super.onCreate(savedBundleInstance);
        setContentView(R.layout.parking_type);

        privateSlot = (Button)findViewById(R.id.privatebtn);
        publicSlot = (Button)findViewById(R.id.publicbtn);

        dr= FirebaseDatabase.getInstance().getReference();

        privateSlot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dr.child("slots").child("private").orderByChild("rfid").equalTo(Login.rfid_db).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            intent = new Intent(parkingType.this,bookingDetails.class);
                            startActivity(intent);
                        }
                        else{
                            intent = new Intent(parkingType.this,slotStatusPrivate.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
            }
        });
        publicSlot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(parkingType.this,slotStatusPublic.class);
                startActivity(intent);
            }
        });
    }
}
