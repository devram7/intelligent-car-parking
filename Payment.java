package comtfs.example.gamer.carparking;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Payment extends AppCompatActivity {
    EditText cardname,carid;
    Button payment;
    DatabaseReference dr;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        cardname=(EditText)findViewById(R.id.name);
        carid=(EditText)findViewById(R.id.carid);
        payment=(Button)findViewById(R.id.button);

        dr= FirebaseDatabase.getInstance().getReference();

        cardname.setText(bookingDetails.name.getText());
        carid.setText(bookingDetails.upid.getText());

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dr.child("slots").child("private").orderByChild("rfid").equalTo(Login.rfid_db).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot datas : dataSnapshot.getChildren()) {
                                datas.getRef().child("rfid").setValue("0");
                                datas.getRef().child("phone").setValue("0");
                                datas.getRef().child("status").setValue("Free");
                                intent = new Intent(Payment.this, MainPage.class);
                                startActivity(intent);
                                Toast.makeText(Payment.this, "Visit Again!", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });

                dr.child("slots").child("public").orderByChild("rfid").equalTo(Login.rfid_db).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot datas : dataSnapshot.getChildren()) {
                                datas.getRef().child("rfid").setValue("0");
                                datas.getRef().child("phone").setValue("0");
                                datas.getRef().child("status").setValue("Free");
                                intent = new Intent(Payment.this, MainPage.class);
                                startActivity(intent);
                                Toast.makeText(Payment.this, "Visit Again!", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
            }
        });
    }
}
