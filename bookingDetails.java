package comtfs.example.gamer.carparking;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
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

public class bookingDetails extends AppCompatActivity {

    TextView phone, location;
    static TextView name, upid;
    DatabaseReference dr,dr1;
    Button exitbtn,cancelbtn,contactbtn;
    Intent callintent=new Intent(Intent.ACTION_CALL);
    AlertDialog.Builder alertDialogBuilder;
    AlertDialog alertDialog;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking_details);

        name = (TextView)findViewById(R.id.name_detail_booked);
        location = (TextView)findViewById(R.id.username_detail_booked);
        phone = (TextView)findViewById(R.id.phone_booked);
        upid = (TextView)findViewById(R.id.upid_booked);
        cancelbtn = (Button)findViewById(R.id.cancel_booked);
        contactbtn = (Button)findViewById(R.id.contact_booked);
        exitbtn = (Button)findViewById(R.id.exit_booked);

        phone.setVisibility(View.INVISIBLE);
        upid.setVisibility(View.INVISIBLE);

        alertDialogBuilder = new AlertDialog.Builder(this);

        dr= FirebaseDatabase.getInstance().getReference();

        dr.child("slots").child("private").orderByChild("rfid").equalTo(Login.rfid_db).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot datas:dataSnapshot.getChildren()){
                        name.setText(datas.child("admin").getValue().toString());
                        location.setText(datas.child("location").getValue().toString());
                        phone.setText(datas.child("phone").getValue().toString());
                        upid.setText(datas.child("upid").getValue().toString());
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });

        contactbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(bookingDetails.this, phone.getText(), Toast.LENGTH_SHORT).show();
                callintent.setData(Uri.parse("tel:"+phone.getText()));
                if (ActivityCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(bookingDetails.this, new String[]{Manifest.permission.CALL_PHONE}, 101);

                }
                startActivity(callintent);
            }
        });

        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dr.child("slots").child("private").orderByChild("rfid").equalTo(Login.rfid_db).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot datas : dataSnapshot.getChildren()) {
                                String dbval = datas.child("status").getValue().toString();
                                dbval.toLowerCase();
                                if (dbval.equals("Occupied")) {
                                    alertDialogBuilder.setMessage("Cannot cancel an already occupied slot! Exit to make payment").setCancelable(false)
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    //do things
                                                }
                                            });
                                    ;
                                    alertDialog = alertDialogBuilder.create();
                                    alertDialog.show();
                                    Button nbutton = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
                                    nbutton.setTextColor(Color.BLACK);
                                    Button pbutton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
                                    pbutton.setTextColor(Color.BLACK);
                                }
                                else {
                                    dr1 = datas.getRef();
                                    alertDialogBuilder.setMessage("Small charge will be yielded!")
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    dr1.child("phone").setValue("0");
                                                    dr1.child("rfid").setValue("0");
                                                    dr1.child("status").setValue("Free");
                                                    Toast.makeText(bookingDetails.this, "Cancelled", Toast.LENGTH_SHORT).show();
                                                    intent = new Intent(bookingDetails.this, MainPage.class);
                                                    startActivity(intent);
                                                    finish();
                                                }
                                            })
                                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
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
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
            }
        });

        exitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogBuilder.setMessage("Are you sure you want to exit and proceed to payment?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                intent = new Intent(bookingDetails.this, Payment.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
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
        });
    }
}
