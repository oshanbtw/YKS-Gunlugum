package com.hansheaven.yks_gunlugum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.temporal.TemporalUnit;
import java.util.HashMap;

public class RegisterScreen extends AppCompatActivity {

    ImageView img_registerExit;
    Button btn_registerKayitOl;
    EditText et_registerIsim, et_registerSoyisim, et_registerEmail, et_registerSifre, et_registerSifreTekrar;

    String txt_registerIsim, txt_registerSoyisim, txt_registerEmail, txt_registerSifre, txt_registerSifreTekrar;

    FirebaseAuth mAuth;
    DatabaseReference mReference;
    FirebaseUser mUser;
    HashMap <String, Object> mData, mDataTYT, mDataAYT, mDataYDT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_register);

        init();
        acilincaOlacaklar();
        clickOlaylari();
    }

    public void init(){
        img_registerExit = (ImageView) findViewById(R.id.img_registerExit);

        btn_registerKayitOl = (Button) findViewById(R.id.btn_registerKayitOl);

        et_registerIsim = (EditText) findViewById(R.id.et_registerIsim);
        et_registerSoyisim = (EditText) findViewById(R.id.et_registerSoyisim);
        et_registerEmail= (EditText) findViewById(R.id.et_registerMail);
        et_registerSifre = (EditText) findViewById(R.id.et_registerSifre);
        et_registerSifreTekrar = (EditText) findViewById(R.id.et_registerSifreTekrar);

        mAuth = FirebaseAuth.getInstance();
        mReference = FirebaseDatabase.getInstance().getReference();
    }

    public void acilincaOlacaklar(){
        //Metinleri Temizliyor.
        et_registerIsim.setText("");
        et_registerSoyisim.setText("");
        et_registerEmail.setText("");
        et_registerSifre.setText("");
        et_registerSifreTekrar.setText("");

    }

    public void makeToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void clickOlaylari(){
        img_registerExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go = new Intent(RegisterScreen.this, WelcomeScreen.class);
                startActivity(go);
                finish();
            }
        });

        btn_registerKayitOl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Kayıt ol butonuna basınca bilgileri aktarıyor.
                txt_registerIsim = et_registerIsim.getText().toString();
                txt_registerSoyisim = et_registerSoyisim.getText().toString();
                txt_registerEmail = et_registerEmail.getText().toString();
                txt_registerSifre = et_registerSifre.getText().toString();
                txt_registerSifreTekrar = et_registerSifreTekrar.getText().toString();

                //Kontroller
               if(TextUtils.isEmpty(txt_registerIsim)){
                   makeToast("İsim Kısmı Boş Olamaz!");
               }
               else if(TextUtils.isEmpty(txt_registerSoyisim)){
                   makeToast("Soyisim Kısmı Boş Olamaz!");
               }
               else if(TextUtils.isEmpty(txt_registerEmail)){
                   makeToast("Email Kısmı Boş Olamaz!");
               }
               else if(TextUtils.isEmpty(txt_registerSifre)){
                   makeToast("Şifre Kısmı Boş Olamaz!");
               }
               else if(TextUtils.isEmpty(txt_registerSifreTekrar)){
                   makeToast("Şifre Kısmı Boş Olamaz!");
               }
               else if(!txt_registerSifre.equals(txt_registerSifreTekrar)){
                   makeToast("Şifreler Uyuşmuyor!");
               }
               else{
                   //Her şey tamamsa hesap oluşturuyor.
                    mAuth.createUserWithEmailAndPassword(txt_registerEmail, txt_registerSifre)
                            .addOnCompleteListener(RegisterScreen.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                       if (task.isSuccessful()){
                                           mUser = mAuth.getCurrentUser(); //Mevcut Kullanıcı

                                           mData = new HashMap<>();
                                           mDataTYT = new HashMap<>();
                                           mDataAYT = new HashMap<>();
                                           mDataYDT = new HashMap<>();

                                           mData.put("İsim", txt_registerIsim);
                                           mData.put("Soyisim", txt_registerSoyisim);
                                           mData.put("Email", txt_registerEmail);
                                           mData.put("Şifre", txt_registerSifre);
                                           mData.put("KullaniciID", mUser.getUid());
                                           //10.01.2022 Konuları Ekleme
                                           //Matematik Konularını Datebase'e false olarak ekliyoruz. (23 tane tyt mat konusu varmış)

                                           int temp=1;
                                           for(int i = 0; i<23; i++){
                                               mDataTYT.put("TYT-Mat-"+temp, false);
                                               temp++;
                                           }
                                           temp=1;
                                           for(int i = 0; i<19; i++){
                                               mDataTYT.put("TYT-TUR-"+temp, false);
                                               temp++;
                                           }
                                           temp=1;
                                           for(int i = 0; i<24; i++){
                                               mDataTYT.put("TYT-GEO-"+temp, false);
                                               temp++;
                                           }
                                           temp=1;
                                           for(int i = 0; i<10; i++){
                                               mDataTYT.put("TYT-FIZ-"+temp, false);
                                               temp++;
                                           }
                                           temp=1;
                                           for(int i = 0; i<11; i++){
                                               mDataTYT.put("TYT-KIM-"+temp, false);
                                               temp++;
                                           }
                                           temp=1;
                                           for(int i = 0; i<10; i++){
                                               mDataTYT.put("TYT-BIY-"+temp, false);
                                               temp++;
                                           }
                                           temp=1;
                                           for(int i = 0; i<17; i++){
                                               mDataTYT.put("TYT-TAR-"+temp, false);
                                               temp++;
                                           }
                                           temp=1;
                                           for(int i = 0; i<21; i++){
                                               mDataTYT.put("TYT-COG-"+temp, false);
                                               temp++;
                                           }
                                           temp=1;
                                           for(int i = 0; i<8; i++){
                                               mDataTYT.put("TYT-FEL-"+temp, false);
                                               temp++;
                                           }
                                           temp=1;
                                           for(int i = 0; i<7; i++){
                                               mDataTYT.put("TYT-DIN-"+temp, false);
                                               temp++;
                                           }

                                           //AYT Konuları
                                           temp=1;
                                           for(int i = 0; i<15; i++){
                                               mDataAYT.put("AYT-EDE-"+temp, false);
                                               temp++;
                                           }

                                           temp=1;
                                           for(int i = 0; i<27; i++){
                                               mDataAYT.put("AYT-GEO-"+temp, false);
                                               temp++;
                                           }

                                           temp=1;
                                           for(int i = 0; i<31; i++){
                                               mDataAYT.put("AYT-MAT-"+temp, false);
                                               temp++;
                                           }

                                           temp=1;
                                           for(int i = 0; i<29; i++){
                                               mDataAYT.put("AYT-TAR-"+temp, false);
                                               temp++;
                                           }

                                           temp=1;
                                           for(int i = 0; i<9; i++){
                                               mDataAYT.put("AYT-COG-"+temp, false);
                                               temp++;
                                           }

                                           temp=1;
                                           for(int i = 0; i<8; i++){
                                               mDataAYT.put("AYT-FEL-"+temp, false);
                                               temp++;
                                           }

                                           temp=1;
                                           for(int i = 0; i<11; i++){
                                               mDataAYT.put("AYT-DIN-"+temp, false);
                                               temp++;
                                           }

                                           temp=1;
                                           for(int i = 0; i<20; i++){
                                               mDataAYT.put("AYT-FIZ-"+temp, false);
                                               temp++;
                                           }

                                           temp=1;
                                           for(int i = 0; i<21; i++){
                                               mDataAYT.put("AYT-KIM-"+temp, false);
                                               temp++;
                                           }

                                           temp=1;
                                           for(int i = 0; i<15; i++){
                                               mDataAYT.put("AYT-BIY-"+temp, false);
                                               temp++;
                                           }

                                           temp=1;
                                           for(int i = 0; i<10; i++){
                                               mDataYDT.put("YDT-"+temp, false);
                                               temp++;
                                           }

                                           //Firebase'de yer açıp yerleştiriyor.
                                           mReference.child("Kullanıcılar").child(mUser.getUid()).setValue(mData)
                                                   .addOnCompleteListener(RegisterScreen.this, new OnCompleteListener<Void>() {
                                                       @Override
                                                       public void onComplete(@NonNull Task<Void> task) {
                                                           if(task.isSuccessful()){
                                                               makeToast("Kayıt İşlemi Başarılı!");
                                                               Intent go = new Intent(RegisterScreen.this, LoginScreen.class);
                                                               startActivity(go);
                                                               finish();
                                                           }
                                                           else{
                                                               makeToast(task.getException().getMessage());
                                                           }
                                                       }
                                                   });
                                           mReference.child("Kullanıcılar").child(mUser.getUid()).child("1TYTKonulari").setValue(mDataTYT);
                                           mReference.child("Kullanıcılar").child(mUser.getUid()).child("2AYTKonulari").setValue(mDataAYT);
                                           mReference.child("Kullanıcılar").child(mUser.getUid()).child("3YDTKonulari").setValue(mDataYDT);


                                       }
                                       else{
                                           makeToast(task.getException().getMessage());
                                       }
                                }
                            });
               }
            }
        });
    }
}