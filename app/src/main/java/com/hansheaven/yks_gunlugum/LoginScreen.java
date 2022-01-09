package com.hansheaven.yks_gunlugum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginScreen extends AppCompatActivity {

    ImageView img_loginExit;
    Button btn_loginGirisYap;
    EditText et_loginEmail, et_loginSifre;
    String txt_loginEmail, txt_loginSifre;
    TextView tv_resetSifre;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_login);

        init();
        acilincaOlacaklar();
        clickOlaylari();

    }

    public void init() {
        img_loginExit = (ImageView) findViewById(R.id.img_loginExit);

        et_loginEmail = (EditText) findViewById(R.id.et_loginMail);
        et_loginSifre = (EditText) findViewById(R.id.et_loginSifre);

        btn_loginGirisYap = (Button) findViewById(R.id.btn_loginGirisYap);

        tv_resetSifre = (TextView) findViewById(R.id.tv_loginSifremiUnuttum);

        mAuth = FirebaseAuth.getInstance();
    }

    public void acilincaOlacaklar() {
        et_loginEmail.setText("");
        et_loginSifre.setText("");

    }

    public void makeToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void clickOlaylari() {
        img_loginExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go = new Intent(LoginScreen.this, WelcomeScreen.class);
                startActivity(go);
                finish();
            }
        });

        btn_loginGirisYap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_loginEmail = et_loginEmail.getText().toString();
                txt_loginSifre = et_loginSifre.getText().toString();

                //Kontroller
                if (TextUtils.isEmpty(txt_loginEmail)) {
                    makeToast("Email Alanı Boş Olamaz!");
                } else if (TextUtils.isEmpty(txt_loginSifre)) {
                    makeToast("Şifre Alanı Boş Olamaz!");
                } else {
                    //Giriş Yapma
                    mAuth.signInWithEmailAndPassword(txt_loginEmail, txt_loginSifre)
                            .addOnSuccessListener(LoginScreen.this, new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    //Mevcut kullanıcının bilgisini almak için.
                                    mUser = mAuth.getCurrentUser();
                                    Intent go = new Intent(LoginScreen.this, MainScreen.class);
                                    startActivity(go);
                                    finish();
                                }
                            }).addOnFailureListener(LoginScreen.this, new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            makeToast(e.getMessage());
                        }
                    });
                }
            }

        });

        tv_resetSifre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText et_resetMail = new EditText(view.getContext());
                AlertDialog.Builder sifreYenilemeDialog = new AlertDialog.Builder(view.getContext());

                sifreYenilemeDialog.setTitle("Şifre Yenileme");
                sifreYenilemeDialog.setMessage("Parola Sıfırlama Linkini Gönderebilmemiz İçin Hesabınızın Email Adresinizi Girin");
                sifreYenilemeDialog.setView(et_resetMail);

                sifreYenilemeDialog.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Do
                        String mail = et_resetMail.getText().toString();
                        mAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            public void onSuccess(Void unused) {
                                makeToast("Şifre Yenileme Linki Gönderildi!");
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                makeToast("Şifre Yenileme Linki Gönderilemedi!" + e.getMessage());
                            }
                        });
                    }
                });

                sifreYenilemeDialog.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //
                    }
                });

                sifreYenilemeDialog.setCancelable(false);
                sifreYenilemeDialog.show();
            }
        });


    }
}