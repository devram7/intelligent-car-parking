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

public class slotStatusPublic extends AppCompatActivity {

    TextView slot1,slot2,slot3,slot4,slot5;
    TextView s1,s2,s3,s4,s5;
    DatabaseReference dr;
    String s1_status, s2_status, s3_status, s4_status ,s5_status;
    Intent intent;
    AlertDialog.Builder alertDialogBuilder;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_public_slot);

        slot1=(TextView)findViewById(R.id.slot1_public);
        slot2=(TextView)findViewById(R.id.slot2_public);
        slot3=(TextView)findViewById(R.id.slot3_public);
        slot4=(TextView)findViewById(R.id.slot4_public);
        slot5=(TextView)findViewById(R.id.slot5_public);

        s1=(TextView)findViewById(R.id.s1_public);
        s2=(TextView)findViewById(R.id.s2_public);
        s3=(TextView)findViewById(R.id.s3_public);
        s4=(TextView)findViewById(R.id.s4_public);
        s5=(TextView)findViewById(R.id.s5_public);

        /*alertDialogBuilder = new AlertDialog.Builder(this);

        dr= FirebaseDatabase.getInstance().getReference();

        dr.child("slots").child("public").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data: dataSnapshot.getChildren()) {
                    s1_status = dataSnapshot.child("slot1").getValue().toString();
                    s2_status = dataSnapshot.child("slot2").getValue().toString();
                    s3_status = dataSnapshot.child("slot3").getValue().toString();
                    s4_status = dataSnapshot.child("slot4").getValue().toString();
                    s5_status = dataSnapshot.child("slot5").getValue().toString();
                    slot1.setText(s1_status);
                    slot2.setText(s2_status);
                    slot3.setText(s3_status);
                    slot4.setText(s4_status);
                    slot5.setText(s5_status);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });

        s1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(slot1.getText().toString().equals("free")){
                    alertDialogBuilder.setMessage("Proceed to booking this slot?");
                    alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            intent = new Intent(slotStatusPublic.this,Payment.class);
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
                else if(slot1.getText().toString().equals("occupied")) {
                    Toast.makeText(slotStatusPublic.this, "Slot is occupied", Toast.LENGTH_SHORT).show();
                }
                else if(slot1.getText().toString().equals("booked")) {
                    Toast.makeText(slotStatusPublic.this, "Slot has been booked", Toast.LENGTH_SHORT).show();
                }
            }
        });

        s2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(slot1.getText().toString().equals("free")){
                    alertDialogBuilder.setMessage("Proceed to booking this slot?");
                    alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            intent = new Intent(slotStatusPublic.this,Payment.class);
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
                else if(slot1.getText().toString().equals("occupied")) {
                    Toast.makeText(slotStatusPublic.this, "Slot is occupied", Toast.LENGTH_SHORT).show();
                }
                else if(slot1.getText().toString().equals("booked")) {
                    Toast.makeText(slotStatusPublic.this, "Slot has been booked", Toast.LENGTH_SHORT).show();
                }
            }
        });

        s3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(slot1.getText().toString().equals("free")){
                    alertDialogBuilder.setMessage("Proceed to booking this slot?");
                    alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            intent = new Intent(slotStatusPublic.this,Payment.class);
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
                else if(slot1.getText().toString().equals("occupied")) {
                    Toast.makeText(slotStatusPublic.this, "Slot is occupied", Toast.LENGTH_SHORT).show();
                }
                else if(slot1.getText().toString().equals("booked")) {
                    Toast.makeText(slotStatusPublic.this, "Slot has been booked", Toast.LENGTH_SHORT).show();
                }
            }
        });

        s4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(slot1.getText().toString().equals("free")){
                    alertDialogBuilder.setMessage("Proceed to booking this slot?");
                    alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            intent = new Intent(slotStatusPublic.this,Payment.class);
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
                else if(slot1.getText().toString().equals("occupied")) {
                    Toast.makeText(slotStatusPublic.this, "Slot is occupied", Toast.LENGTH_SHORT).show();
                }
                else if(slot1.getText().toString().equals("booked")) {
                    Toast.makeText(slotStatusPublic.this, "Slot has been booked", Toast.LENGTH_SHORT).show();
                }
            }
        });

        s5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(slot1.getText().toString().equals("free")){
                    alertDialogBuilder.setMessage("Proceed to booking this slot?");
                    alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            intent = new Intent(slotStatusPublic.this,Payment.class);
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
                else if(slot1.getText().toString().equals("occupied")) {
                    Toast.makeText(slotStatusPublic.this, "Slot is occupied", Toast.LENGTH_SHORT).show();
                }
                else if(slot1.getText().toString().equals("booked")) {
                    Toast.makeText(slotStatusPublic.this, "Slot has been booked", Toast.LENGTH_SHORT).show();
                }
            }
        });*/
    }
}