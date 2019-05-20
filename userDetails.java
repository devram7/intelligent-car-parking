package comtfs.example.gamer.carparking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class userDetails extends AppCompatActivity {

    TextView name, username, rfid, status;
    DatabaseReference dr;
    String name_db, username_db, rfid_db, status_db;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_details);

        name = (TextView)findViewById(R.id.name_detail);
        username = (TextView)findViewById(R.id.username_detail);
        rfid = (TextView)findViewById(R.id.rfid_detail);
        status = (TextView)findViewById(R.id.status_detail);
        btn = (Button)findViewById(R.id.back_main);

        dr= FirebaseDatabase.getInstance().getReference();

        name.setText(Login.name_db);
        dr.child("users").orderByChild("name").equalTo(Login.name_db).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    for (DataSnapshot datas : dataSnapshot.getChildren()) {
                        name_db = datas.child("name").getValue().toString();
                        username_db = datas.child("username").getValue().toString();
                        rfid_db = datas.child("rfid").getValue().toString();
                        status_db = datas.child("slot").getValue().toString();
                    }
                }
                name.setText(name_db);
                username.setText(username_db);
                rfid.setText(rfid_db);
                status.setText(status_db);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(userDetails.this,MainPage.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
