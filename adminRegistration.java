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

public class adminRegistration extends AppCompatActivity {

    EditText name, location, upid, username, password, pmode;
    Button register;
    DatabaseReference dr;
    HashMap<String,String> dataMap1 =  new HashMap<String, String>();
    HashMap<String,String> dataMap2 =  new HashMap<String, String>();
    int flag=1, val;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_admin);

        name=(EditText)findViewById(R.id.name_admin);
        location=(EditText)findViewById(R.id.location_admin);
        upid=(EditText)findViewById(R.id.upid_admin);
        pmode =(EditText)findViewById(R.id.ptype_admin);

        username=(EditText) findViewById(R.id.username_admin);
        password=(EditText)findViewById(R.id.password_admin);

        register=(Button) findViewById(R.id.register_admin);

        dr= FirebaseDatabase.getInstance().getReference();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataMap1.put("name",name.getText().toString());
                dataMap1.put("location",location.getText().toString());
                dataMap1.put("upid", upid.getText().toString());
                dataMap1.put("parkingmode", pmode.getText().toString());
                /*while(flag==1){
                    dr.child("users").orderByChild("username").equalTo(username.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.exists()){
                                Toast.makeText(adminRegistration.this, "User already exists", Toast.LENGTH_SHORT).show();
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
                dataMap1.put("password",password.getText().toString());

                String str = pmode.getText().toString().toLowerCase();

                if(str.equals("private")){
                    dr.child("slotsnumber").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            val = Integer.parseInt(dataSnapshot.child("private").getValue().toString());
                            val = val + 1;
                            for(DataSnapshot data: dataSnapshot.getChildren()){
                                DatabaseReference dr1 = data.getRef();
                                if(dr1.getKey().equals("private")){
                                    dr1.setValue(val);
                                    dr.child("slots").child("private").child("slot"+val).child("admin").setValue(name.getText().toString());
                                    dr.child("slots").child("private").child("slot"+val).child("phone").setValue(username.getText().toString());
                                    dr.child("slots").child("private").child("slot"+val).child("location").setValue(location.getText().toString());
                                    dr.child("slots").child("private").child("slot"+val).child("rfid").setValue("0");
                                    dr.child("slots").child("private").child("slot"+val).child("status").setValue("Free");
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });
                }
                else if(str.equals("public")){
                    dr.child("slotsnumber").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            val = Integer.parseInt(dataSnapshot.child("public").getValue().toString());
                            val = val + 1;
                            for(DataSnapshot data: dataSnapshot.getChildren()){
                                DatabaseReference dr1 = data.getRef();
                                if(dr1.getKey().equals("public")){
                                    dr1.setValue(val);
                                    dr.child("slots").child("public").child("slot"+val).child("admin").setValue(name.getText().toString());
                                    dr.child("slots").child("public").child("slot"+val).child("phone").setValue(username.getText().toString());
                                    dr.child("slots").child("public").child("slot"+val).child("location").setValue(location.getText().toString());
                                    dr.child("slots").child("public").child("slot"+val).child("rfid").setValue("0");
                                    dr.child("slots").child("public").child("slot"+val).child("status").setValue("Free");
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });
                }
                else{
                    Toast.makeText(adminRegistration.this, "Enter the proper parking mode", Toast.LENGTH_SHORT).show();
                }


                dr.child("admin").push().setValue(dataMap1).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isComplete()) {
                            Toast.makeText(adminRegistration.this, "Registeration successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(adminRegistration.this,Login.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(adminRegistration.this, "Registeration failed.. Try again!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(adminRegistration.this,userNavigation.class);
                            startActivity(intent);
                        }
                    }
                });
            }
        });
    }
}
