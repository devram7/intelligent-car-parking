package comtfs.example.gamer.carparking;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    Button login,register;
    String uname,pswd,pswd_db;
    static String name_db, rfid_db, uname_db;
    EditText username,password;
    Intent intent;
    DatabaseReference dr;
    TextView ctrval;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        login=(Button)findViewById(R.id.login);
        register=(Button)findViewById(R.id.register);
        username =(EditText)findViewById(R.id.username);
        password =(EditText)findViewById(R.id.password);
        ctrval=(TextView)findViewById(R.id.ctrval);
        register.setPaintFlags(register.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent= new Intent(Login.this,userNavigation.class);
                startActivity(intent);
                finish();
            }
        });

        FirebaseApp.initializeApp(this);
        dr= FirebaseDatabase.getInstance().getReference();

        ctrval.setVisibility(View.INVISIBLE);
        ctrval.setText("0");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uname= username.getText().toString();
                pswd= password.getText().toString();

                dr.child("users").orderByChild("username").equalTo(uname).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            ctrval.setText("1");
                            for(DataSnapshot datas:dataSnapshot.getChildren()){
                                //uname_db=datas.child("username").getValue().toString();
                                name_db=datas.child("name").getValue().toString();
                                pswd_db=datas.child("password").getValue().toString();
                                rfid_db=datas.child("rfid").getValue().toString();
                            }
                            if(pswd.equals(pswd_db)) {
                                Toast.makeText(Login.this, "Login Success", Toast.LENGTH_SHORT).show();
                                intent = new Intent(Login.this,MainPage.class);
                                startActivity(intent);
                                finish();
                            }
                            else
                                Toast.makeText(Login.this, "Login Failure", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {}
                });

                if(ctrval.getText().equals("0")){

                    dr.child("admin").orderByChild("username").equalTo(uname).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if(dataSnapshot.exists()){
                                for(DataSnapshot datas:dataSnapshot.getChildren()){
                                    uname_db=datas.child("username").getValue().toString();
                                    name_db=datas.child("name").getValue().toString();
                                    pswd_db=datas.child("password").getValue().toString();
                                }
                                if(pswd.equals(pswd_db)) {
                                    Toast.makeText(Login.this, "Login Success", Toast.LENGTH_SHORT).show();
                                    intent = new Intent(Login.this,MainPageAdmin.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else{
                                    Toast.makeText(Login.this, "Login Failure", Toast.LENGTH_SHORT).show();
                                    username.setText("");
                                    password.setText("");
                                }
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {}
                    });
                }
            }
        });
    }
}
