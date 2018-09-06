package tech.imranpasha.fingerprint_auth.android_fingerprint_auth;

import android.Manifest;
import android.app.KeyguardManager;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView msg;
    private ImageView fingerprint_img;
    private FingerprintManager fingerpringManager;
    private KeyguardManager keyguardManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            fingerpringManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);
            keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);
            if(!fingerpringManager.isHardwareDetected()){
                msg.setText("Fingerprint scanner not detected in Device");
            }else if(ContextCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED){
                msg.setText("PERMISSION NOT GRANTED");
            }else if(keyguardManager.isKeyguardSecure()){
                msg.setText("you should add atleast");
            }else {
                msg.setText("please place your finger");
            }
        }

    }

    private void init() {
        msg=(TextView)findViewById(R.id.label);
        fingerprint_img=(ImageView)findViewById(R.id.fingerprint_img);
    }
}
