package comtfs.example.gamer.carparking;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

public class MainPageAdmin extends AppCompatActivity {

    Intent intent, intent1;
    TextView name;
    Button book, details, logout, passwordchange;

    DatabaseReference dr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page_admin);

        name = (TextView) findViewById(R.id.welcome_admin);
        details = (Button)findViewById(R.id.details_admin);
        passwordchange = (Button)findViewById(R.id.password_update_admin);
        logout = (Button)findViewById(R.id.logout_admin);

        intent1 = getIntent();
        dr= FirebaseDatabase.getInstance().getReference();

        name.setText(name.getText()+" "+Login.name_db);
        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainPageAdmin.this,adminDetails.class);
                startActivity(intent);
            }
        });

        passwordchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainPageAdmin.this,passwordChangeAdmin.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainPageAdmin.this,Login.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
