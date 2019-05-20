package comtfs.example.gamer.carparking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class adminDetails extends AppCompatActivity {

    TextView name, location, parkmode, upid, user;
    DatabaseReference dr;
    String name_db, location_db, parkmode_db, upid_db, user_db;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_details);

        name = (TextView)findViewById(R.id.name_detail_admin);
        location = (TextView)findViewById(R.id.location_detail_admin);
        parkmode = (TextView)findViewById(R.id.parkmode_detail_admin);
        upid = (TextView)findViewById(R.id.upid_detail_admin);
        user = (TextView)findViewById(R.id.user_detail_admin);
        btn = (Button)findViewById(R.id.back_main_admin);

        dr= FirebaseDatabase.getInstance().getReference();

        name.setText(Login.name_db);
        dr.child("admin").orderByChild("username").equalTo(Login.uname_db).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    for (DataSnapshot datas : dataSnapshot.getChildren()) {
                        name_db = datas.child("name").getValue().toString();
                        location_db = datas.child("location").getValue().toString();
                        parkmode_db = datas.child("parkingmode").getValue().toString();
                        user_db = datas.child("username").getValue().toString();
                        upid_db = datas.child("upid").getValue().toString();
                    }
                }
                name.setText(name_db);
                location.setText(location_db);
                parkmode.setText(parkmode_db);
                user.setText(user_db);
                upid.setText(upid_db);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(adminDetails.this,MainPageAdmin.class);
                startActivity(intent);
                finish();
            }
        });

    }
}