package comtfs.example.gamer.carparking;

import android.content.Intent;
import android.support.annotation.NonNull;
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

public class MainPage extends AppCompatActivity {

    Intent intent, intent1;
    TextView name;
    Button book, details, logout, bookingdetails, passwordchange;

    DatabaseReference dr;
    String val;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);

        name = (TextView) findViewById(R.id.welcome_user);
        book = (Button)findViewById(R.id.bookslot_user);
        details = (Button)findViewById(R.id.details_user);
        bookingdetails = (Button)findViewById(R.id.bookingdetails_user);
        passwordchange = (Button)findViewById(R.id.password_update_user);
        logout = (Button)findViewById(R.id.logout_user);

        intent1 = getIntent();
        dr= FirebaseDatabase.getInstance().getReference();

        name.setText(name.getText()+" "+Login.name_db);
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainPage.this,parkingType.class);
                startActivity(intent);
            }
        });

        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainPage.this,userDetails.class);
                startActivity(intent);
            }
        });

        bookingdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dr.child("slots").child("private").orderByChild("rfid").equalTo(Login.rfid_db).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            intent = new Intent(MainPage.this,bookingDetails.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(MainPage.this, "You haven't booked any slot...", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
            }
        });

        passwordchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainPage.this, passwordChange.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainPage.this,Login.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
