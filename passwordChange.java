package comtfs.example.gamer.carparking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class passwordChange extends AppCompatActivity {

    EditText t1,t2,t3,t4;
    Button b;
    DatabaseReference dr,dr1;

    String uname,opswd,npswd,cpswd,pswd_db;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_change);

        t1 = (EditText) findViewById(R.id.username);
        t2 = (EditText) findViewById(R.id.oldpswd);
        t3 = (EditText) findViewById(R.id.newpswd);
        t4 = (EditText) findViewById(R.id.newpswd_cnf);
        b = (Button) findViewById(R.id.change);

        dr = FirebaseDatabase.getInstance().getReference();

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                uname = t1.getText().toString();
                opswd = t2.getText().toString();
                npswd = t3.getText().toString();
                cpswd = t4.getText().toString();

                dr.child("users").orderByChild("username").equalTo(uname).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot datas : dataSnapshot.getChildren()) {
                                pswd_db = datas.child("password").getValue().toString();
                                dr1 = datas.child("password").getRef();
                            }
                            if (opswd.equals(pswd_db)) {
                                if (npswd.equals(cpswd)) {
                                    dr1.setValue(npswd);
                                    Toast.makeText(passwordChange.this, "Password updated", Toast.LENGTH_SHORT).show();
                                    intent = new Intent(passwordChange.this, Login.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(passwordChange.this, "Confirm your new password correctly!!!", Toast.LENGTH_SHORT).show();
                                    t1.setText("");
                                    t2.setText("");
                                    t3.setText("");
                                    t4.setText("");
                                }
                            } else
                                Toast.makeText(passwordChange.this, "Invalid Password!!!", Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(passwordChange.this, "Invalid Credentials!!!", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });
    }
}
