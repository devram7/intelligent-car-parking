package comtfs.example.gamer.carparking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class userNavigation extends AppCompatActivity {

    Intent intent;
    Button admin,user;

    @Override
    protected void onCreate(Bundle savedBundleInstance){

        super.onCreate(savedBundleInstance);
        setContentView(R.layout.user_type);

        admin=(Button)findViewById(R.id.adminbtn);
        user=(Button)findViewById(R.id.userbtn);

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(userNavigation.this,adminRegistration.class);
                startActivity(intent);
            }
        });

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(userNavigation.this,userRegistration.class);
                startActivity(intent);
            }
        });
    }
}
