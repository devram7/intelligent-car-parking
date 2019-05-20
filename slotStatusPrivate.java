package comtfs.example.gamer.carparking;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class slotStatusPrivate extends AppCompatActivity {

    TextView slot1,slot2,s1,s2,a1,a2,slot1loc,slot2loc;
    DatabaseReference dr;
    String s1_status, s2_status, s1_admin, s2_admin;
    Intent intent;
    static String slot_num = "0";
    AlertDialog.Builder alertDialogBuilder;
    AlertDialog alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_private_slot);

        slot1=(TextView)findViewById(R.id.slot1_private);
        slot2=(TextView)findViewById(R.id.slot2_private);
        slot1loc=(TextView)findViewById(R.id.slot1loc_private);
        slot2loc=(TextView)findViewById(R.id.slot2loc_private);
        s1=(TextView)findViewById(R.id.s1_private);
        s2=(TextView)findViewById(R.id.s2_private);
        a1=(TextView)findViewById(R.id.admin1_private);
        a2=(TextView)findViewById(R.id.admin2_private);

        a1.setVisibility(View.INVISIBLE);
        a2.setVisibility(View.INVISIBLE);

        alertDialogBuilder = new AlertDialog.Builder(this);

        dr= FirebaseDatabase.getInstance().getReference();

        dr.child("slots").child("private").child("slot1").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String loc_db=dataSnapshot.child("location").getValue().toString();
                    if(loc_db.equals("Unavailable")){
                        slot1loc.setVisibility(View.INVISIBLE);
                    }
                    else {
                        slot1loc.setText(loc_db);
                    }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        dr.child("slots").child("private").child("slot2").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String loc_db=dataSnapshot.child("location").getValue().toString();
                    if(loc_db.equals("Unavailable")){
                        slot2loc.setVisibility(View.INVISIBLE);
                    }
                    else {
                        slot2loc.setText(loc_db);
                    }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        dr.child("slots").child("private").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data: dataSnapshot.getChildren()) {
                    s1_status = dataSnapshot.child("slot1").child("status").getValue().toString();
                    s1_admin = dataSnapshot.child("slot1").child("admin").getValue().toString();
                    s2_status = dataSnapshot.child("slot2").child("status").getValue().toString();
                    s2_admin = dataSnapshot.child("slot2").child("admin").getValue().toString();
                    slot1.setText(s1_status);
                    slot2.setText(s2_status);
                    a1.setText(s1_admin);
                    a2.setText(s2_admin);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });

        s1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(slot1.getText().toString().equals("Free")){
                    alertDialogBuilder.setMessage("Proceed to booking this slot?");
                    alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            dr.child("slots").child("private").child("slot1").addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    dataSnapshot.getRef().child("status").setValue("Booked");
                                    dataSnapshot.getRef().child("rfid").setValue(Login.rfid_db);
                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {}
                            });
                            slot_num="1";
                            intent = new Intent(slotStatusPrivate.this,bookingDetails.class);
                            intent.putExtra("admin",a1.getText());
                            startActivity(intent);
                            finish();
                        }
                    });
                    alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                    Button nbutton = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
                    nbutton.setTextColor(Color.BLACK);
                    Button pbutton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
                    pbutton.setTextColor(Color.BLACK);

                }
                else if(slot1.getText().toString().equals("Occupied")) {
                    Toast.makeText(slotStatusPrivate.this, "Slot is occupied", Toast.LENGTH_SHORT).show();
                }
                else if(slot1.getText().toString().equals("Booked")) {
                    Toast.makeText(slotStatusPrivate.this, "Slot has been booked", Toast.LENGTH_SHORT).show();
                }
            }
        });

        s2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(slot2.getText().toString().equals("Free")){
                    alertDialogBuilder.setMessage("Proceed to booking this slot?");
                    alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            dr.child("slots").child("private").child("slot2").addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    dataSnapshot.getRef().child("status").setValue("Booked");
                                    dataSnapshot.getRef().child("rfid").setValue(Login.rfid_db);
                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {}
                            });
                            slot_num="2";
                            intent = new Intent(slotStatusPrivate.this,bookingDetails.class);
                            intent.putExtra("admin",a2.getText());
                            startActivity(intent);
                            finish();
                        }
                    });
                    alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                    Button nbutton = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
                    nbutton.setTextColor(Color.BLACK);
                    Button pbutton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
                    pbutton.setTextColor(Color.BLACK);
                }
                else if(slot2.getText().toString().equals("Occupied")) {
                    Toast.makeText(slotStatusPrivate.this, "Slot is occupied", Toast.LENGTH_SHORT).show();
                }
                else if(slot2.getText().toString().equals("Booked")) {
                    Toast.makeText(slotStatusPrivate.this, "Slot has been booked", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
