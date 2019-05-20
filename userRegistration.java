package comtfs.example.gamer.carparking;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class userRegistration extends AppCompatActivity {

    EditText name, rfid, username, password;
    Button register;
    DatabaseReference dr;
    HashMap<String,String> dataMap1 =  new HashMap<String, String>();
    int flag=1;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_user);

        name=(EditText)findViewById(R.id.name_user);
        rfid=(EditText)findViewById(R.id.rfid_user);
        username=(EditText)findViewById(R.id.username_user);
        password=(EditText)findViewById(R.id.password_user);
        register=(Button) findViewById(R.id.register_user);

        dr= FirebaseDatabase.getInstance().getReference();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataMap1.put("name",name.getText().toString());
                dataMap1.put("rfid", rfid.getText().toString());
                /*while(flag==1){
                    dr.child("users").orderByChild("username").equalTo(username.getText().toString()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Toast.makeText(userRegistration.this, "yaaaaaaaaaa", Toast.LENGTH_SHORT).show();
                            if(dataSnapshot.exists()){
                                Toast.makeText(userRegistration.this, "User already exists", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                flag=0;
                                dataMap1.put("username", username.getText().toString());
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }*/
                dataMap1.put("username", username.getText().toString());
                dataMap1.put("password", password.getText().toString());

                dr.child("users").push().setValue(dataMap1).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isComplete()) {
                            Toast.makeText(userRegistration.this, "Registeration successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(userRegistration.this,Login.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(userRegistration.this, "Registeration failed.. Try again!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(userRegistration.this,userNavigation.class);
                            startActivity(intent);
                        }
                    }
                });
            }
        });
    }
}
