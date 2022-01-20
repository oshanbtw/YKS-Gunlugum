package com.hansheaven.yks_gunlugum;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class RegisterScreen extends AppCompatActivity {

    //
    String[] TYTMatematik = {"Sayılar", "Sayı Basamakları", "Bölme ve Bölünebilme", "OBEB-OKEK", "Rasyonel Sayılar", "Basit Eşitsizlikler", "Mutlak Değer",
            "Üslü Sayılar", "Köklü Sayılar", "Çarpanlara Ayırma", "Oran Orantı", "Denklem Çözme", "Problemler", "Kümeler-Kartezyen Çarpım", "Fonskiyonlar", "Permütasyon",
            "Kombinasyon", "Binom", "Olasılık", "İstatistik", "İkinci Dereceden Denklemler", "Karmaşık Sayılar", "Polinomlar"};
    String[] TYTTurkce = {"Sözcük Anlamı", "Söz Yorumu", "Deyim ve Atasözü", "Cümle Anlamı", "Cümle Yorumu", "Paragrafta Anlatım Teknikleri", "Paragrafta Konu-Ana Düşünce",
            "Paragrafta Yapı", "Paragrafta Yardımcı Düşünce", "Paragrafta Yardımcı Düşünce", "Yazım Kuralları", "Noktalama İşaretleri", "Sözcüğün Yapısı", "Sözcük Türleri",
            "Fiiller", "Sözcük Grupları", "Cümlenin Ögeleri", "Cümle Türleri", "Anlatım Bozukluğu"};
    String[] TYTGeometri = {"Doğruda ve Üçgende Açılar", "Dik ve Özel Üçgenler", "Dik Üçgende Trigonemetrik Bağıntılar", "İkizkenar ve Eşkenar Üçgen", "Üçgende Alanlar", "Üçgende Açıortay Bağıntıları",
            "Üçgende Kenarortay Bağıntıları", "Üçgende Eşlik ve Benzerlik", "Üçgende Açı-Kenar Bağıntıları", "Çokgenler", "Dörtgenler", "Yamuk", "Paralelkenar", "Eşkenar Dörtgen – Deltoid",
            "Dikdörtgen", "Çemberde Açılar", "Çemberde Uzunluk", "Daire", "Prizmalar", "Piramitler", "Küre", "Koordinat Düzlemi ve Noktanın Analitiği", "Doğrunun Analitiği", "Tekrar Eden, Dönen ve Yansıyan Şekiller"};
    String[] TYTFizik = {"Fizik Bilimine Giriş", "Madde ve Özellikleri", "Hareket ve Kuvvet", "Enerji", "Isı ve Sıcaklık", "Elektrostatik", "Elektrik ve Manyetizma", "Basınç ve Kaldırma Kuvveti",
            "Dalgalar", "Optik"};
    String[] TYTKimya = {"Kimya Bilimi", "Atom ve Periyodik Sistem", "Modern Atom Teorisi", "Kimyasal Türler Arası Tepkimeler", "Mol Kavramı", "Kimyasal Hesaplamalar",
            "Asit, Baz ve Tuz", "Maddenin Halleri", "Karışımlar", "Endüstride ve Canlılarda Enerji", "Kimya Her Yerde"};
    String[] TYTBiyoloji = {"Biyoloji Bilimi", "Canlıların Yapısında Bulunan Temel Bileşenler", "Hücrenin Yapısı ve İşlevi", "Canlıların Çeşitliliği ve Sınıflandırması",
            "Hücre bölünmesi ve üreme", "Kalıtım", "Modern Genetik Uygulamaları", "Ekosistem Ekolojisi", "Dünyamız", "Kominite ve Popülasyon Ekolojisi"};
    String[] TYTTarih = {"Tarih Bilimi", "Uygarlığın Doğuşu ve İlk Uygarlıklar", "İlk Türk Devletleri", "İslam Tarihi ve Uygarlığı", "Türk-İslam Devletleri", "Türkiye Tarihi",
            "Beylikten Devlete (1300-1453)", "Dünya Gücü: Osmanlı Devleti (1453-1600)", "Arayış Yılları", "Avrupa ve Osmanlı Devleti", "En Uzun Yüzyıl (1800-1922)",
            "Yüzyıl Başlarında Osmanlı Devleti", "Dünya Savaşı – Milli Mücadeleye Hazırlık Dönemi", "Kurtuluş Savaşında Cepheler", "Türk İnkılabı", "Atatürkçülük ve Atatürk İlkeleri",
            "Türk Dış Politikası"};
    String[] TYTCografya = {"Doğa ve İnsan", "Dünya’nın Şekil ve Hareketleri", "Coğrafi Konum", "Harita Bilgisi", "Atmosfer ve İklim", "Sıcaklık", "Basınç ve Rüzgarlar",
            "Nemlilik ve Yağış", "İklim Tipleri ve Bitki Örtüsü", "Türkiye’nin İklimi", "Yerin Şekillenmesi", "İç Kuvvetler", "Dış Kuvvetler", "Su Kaynakları", "Topraklar",
            "Bitkiler", "Nüfus", "Göç", "Yerleşmeler", "Bölgeler ve Ülkeler", "Doğal Afetler"};
    String[] TYTFelsefe = {"Felsefe’nin Konusu", "Bilgi Felsefesi", "Varlık Felsefesi", "Ahlak Felsefesi", "Sanat Felsefesi", "Din Felsefesi", "Siyaset Felsefesi", "Bilim Felsefesi"};
    String[] TYTDin = {"Kuran-ı Kerim-in Anlaşılması ve Kavranması", "İnsan ve Din", "İslam ve İbadetler", "İslam Düşüncesinde Yorumlar, Mezhepler", "Muhammedin Hayatı, Örnekliği ve Onu Anlama",
            "Yaşayan Dinler ve Benzer Özellikleri", "Yaşayan Dinler ve Benzer Özellikleri"};

    String[] AYTMatematik = {"Temel Kavramlar", "Sayı Basamakları", "Rasyonel Sayılar", "Ondalıklı Sayılar", "Basit Eşitsizlikler", "Mutlak Değer", "Üslü Sayılar", "Köklü Sayılar",
            "Çarpanlara Ayırma", "Denklem Çözme", "Oran-Orantı", "Oran-Orantı", "Kümeler", "Fonksiyonlar", "Permütasyon", "Kombinasyon", "Binom", "Olasılık", "İstatistik", "Dereceden Denklemler",
            "Karmaşık Sayılar", "Karmaşık Sayılar", "Polinomlar", "Mantık", "Eşitsizlikler", "Logaritma", "Diziler", "Seriler", "Limit ve Süreklilik", "Türev", "İntegral"};
    String[] AYTEdebiyat = {"Güzel Sanatlar ve Edebiyat", "Coşku ve Heyecan Dile Getiren Metinler", "Olay Çevresinde Oluşan Edebi Metinler", "Öğretici Metinler", "Tarih İçinde Türk Edebiyatı",
            "Destan Dönemi Türk Edebiyatı", "İslam Uygarlığı Çevresinde Gelişen Türk Edebiyatı", "Batı Tesirindeki Türk Edebiyatına Giriş", "Tanzimat Dönemi Edebiyatı", "Servet-i Fünun Edebiyatı ve Fecr-i Ati Topluluğu",
            "Milli Edebiyat Dönemi", "Cumhuriyet Dönemi Türk Edebiyatı", "Cumhuriyet Döneminde Öğretici Metinler", "Cumhuriyet Döneminde Öğretici Metinler", "Cumhuriyet Döneminde Olay Çevresinde Oluşan Edebi Metinler"};
    String[] AYTGeometri = {"Doğruda ve Üçgende Açılar", "Dik ve Özel Üçgenler", "Dik Üçgende Trigonemetrik Bağıntılar", "İkizkenar ve Eşkenar Üçgen", "Üçgende Alanlar", "Üçgende Açıortay Bağıntıları",
            "Üçgende Kenarortay Bağıntıları", "Üçgende Eşlik ve Benzerlik", "Üçgende Açı-Kenar Bağıntıları", "Çokgenler", "Dörtgenler", "Yamuk", "Paralelkenar", "Eşkenar Dörtgen – Deltoid",
            "Dikdörtgen", "Çemberde Açılar", "Çemberde Uzunluk", "Daire", "Prizmalar", "Piramitler", "Küre", "Koordinat Düzlemi ve Noktanın Analitiği", "Doğrunun Analitiği",
            "Tekrar Eden, Dönen ve Yansıyan Şekiller", "Dönüşümlerle Geometri", "Trigonometri", "Çemberin Analitiği"};
    String[] AYTTarih = {"Tarih Bilimi", "Uygarlığın Doğuşu ve İlk Uygarlıklar", "İlk Türk Devletleri", "İslam Tarihi ve Uygarlığı", "Türk-İslam Devletleri", "Türkiye Tarihi",
            "Beylikten Devlete", "Dünya Gücü: Osmanlı Devleti ", "Arayış Yılları ", "Avrupa ve Osmanlı Devleti", "En Uzun Yüzyıl", "1881’den 1919’a Mustafa Kemal", "Milli Mücadele’nin Hazırlık Dönemi",
            "Kurtuluş Savaşı’nda Cepheler", "Türk İnkılabı", "Atatürkçülük ve Atatürk İlkeleri", "Atatürk Dönemi Türk Dış Politikası", "Atatürk’ün Ölümü", "Yüzyılın Başlarında Dünya",
            "İkinci Dünya Savaşı", "Soğuk Savaş Dönemi", "Yumuşama Dönemi ve Sonrası", "Küreselleşen Dünya", "Türklerde Devlet Teşkilatı", "Türklerde Toplum Yapısı", "Türklerde Hukuk",
            "Türklerde Ekonomi", "Türklerde Eğitim", "Türklerde Sanat"};
    String[] AYTCografya = {"Şehirlerin Fonksiyonları", "Türkiye’de Nüfus ve Yerleşme", "Ekonomik Faaliyetler", "Türkiye’de Ekonomik Faaliyetler", "Nüfus",
            "Uluslararası Ulaşım Hatları", "Bölgeler ve Ülkeler", "Bölgeler ve Ülkeler", "Doğal Afetler"};
    String[] AYTFelsefe = {"Felsefe’nin Alanı", "Bilgi Felsefesi", "Bilim Felsefesi", "Varlık Felsefesi", "Ahlak Felsefesi", "Siyaset Felsefesi", "Sanat Felsefesi", "Din Felsefesi"};
    String[] AYTDin = {"Kuran-ı Kerim’in Anlaşılması ve Kavranması", "İnsan ve Din", "İslam ve İbadetler", "İslam Düşüncesinde Yorumlar, Mezhepler", "Muhammedin Hayatı, Örnekliği ve Onu Anlama",
            "İslam ve Bilim, Estetik, Barış", "Yaşayan Dinler ve Benzer Özellikleri", "İslam Düşüncesinde Tasavvu", "Yaşayan Dinler ve Benzer Özellikler", "Hazreti Muhammed",
            "Vahiy ve Akıl Kur’an Yorumları"};
    String[] AYTFizik = {"Vektörler", "Bağıl Hareket", "Newton’ın Hareket Yasaları", "Sabit İvmeli Hareket", "Atışlar", "Enerji ve Hareket", "Enerji ve Hareket",
            "Tork-Denge-Ağırlık Merkezi", "Basit Makineler", "Elektriksel Kuvvet, Potansiyel ve İş", "Elektriksel Kuvvet, Potansiyel ve İş", "Manyetizma ve Elektromanyetik İndükleme",
            "Alternatif Akım ve Transformatörler", "Çembersel Hareket", "Basit Harmonik Hareket", "Dalga Mekaniği", "Atom Modelleri", "Büyük Patlama ve Radyoaktivite", "Modern Fizik",
            "Modern Fiziğin Teknolojideki Uygulamaları"};
    String[] AYTKimya = {"Kuantum Sayıları", "Elektron Dizilimi", "Periyodik Özellik", "Gazlar", "Çözeltilerde Derişim", "Koligatif Özellikler", "Çözünürlük", "Kimyasal Tepkimelerde Enerji",
            "Kimyasal Tepkimelerde Hız", "Kimyasal Denge", "Asit-Baz Dengesi", "Çözünürlük Dengesi (KÇÇ)", "Redoks", "Piller", "Elektroliz", "Karbon Kimyasına Giriş", "Hidrokarbonlar",
            "Alkoller ve Eterler", "Karbonil Bileşikleri", "Karboksilik Asitler ve Esterler", "Enerji Kaynakları ve Bilimsel Gelişmeler"};
    String[] AYTBiyoloji = {"Sinir Sistemi", "Endokrin Sistem", "Duyu Organları", "Destek ve Hareket Sistemi", "Sindirim Sistemi", "Dolaşım ve Bağışıklık Sistemi", "Solunum Sistemi",
            "Boşaltım Sistemi", "Üreme Sistemi ve Embriyonik Gelişim", "Komünite ve Popülasyon Ekolojisi", "Genetik Şifre-Protein Sentezi", "Fotosentez – Kemosentez",
            "Hücresel Solunum", "Bitki Biyolojisi", "Canlılar ve Çevre"};
    //YDT Konulari
    String[] YDTIngilizce = {"Kelime – Phrasal Verb", "Tense – Preposition – Dilbilgisi", "Cloze Test", "Cümle Tamamlama", "Çeviri", "Paragraf", "Diyalog Tamamlama",
            "Yakın Anlamlı Cümle", "Paragraf Tamamlama", "Anlatım Bütünlüğünü Bozan Cümle"};

    Map<String, List<String>> tytDersler = new HashMap<>();
    Map<String, List<String>> aytDersler = new HashMap<>();


    ImageView img_registerExit;
    Button btn_registerKayitOl;
    EditText et_registerIsim, et_registerSoyisim, et_registerEmail, et_registerSifre, et_registerSifreTekrar;

    String txt_registerIsim, txt_registerSoyisim, txt_registerEmail, txt_registerSifre, txt_registerSifreTekrar;

    FirebaseAuth mAuth;
    DatabaseReference mReference;
    FirebaseUser mUser;

    HashMap<String, Object> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_register);

        init();
        acilincaOlacaklar();
        clickOlaylari();
    }

    public void init() {
        img_registerExit = (ImageView) findViewById(R.id.img_registerExit);

        btn_registerKayitOl = (Button) findViewById(R.id.btn_registerKayitOl);

        et_registerIsim = (EditText) findViewById(R.id.et_registerIsim);
        et_registerSoyisim = (EditText) findViewById(R.id.et_registerSoyisim);
        et_registerEmail = (EditText) findViewById(R.id.et_registerMail);
        et_registerSifre = (EditText) findViewById(R.id.et_registerSifre);
        et_registerSifreTekrar = (EditText) findViewById(R.id.et_registerSifreTekrar);

        mAuth = FirebaseAuth.getInstance();
        mReference = FirebaseDatabase.getInstance().getReference();

        tytDersler.put("TYTMatematik", Arrays.asList(TYTMatematik));
        tytDersler.put("TYTTurkce", Arrays.asList(TYTTurkce));
        tytDersler.put("TYTGeometri", Arrays.asList(TYTGeometri));
        tytDersler.put("TYTFizik", Arrays.asList(TYTFizik));
        tytDersler.put("TYTKimya", Arrays.asList(TYTKimya));
        tytDersler.put("TYTBiyoloji", Arrays.asList(TYTBiyoloji));
        tytDersler.put("TYTTarih", Arrays.asList(TYTTarih));
        tytDersler.put("TYTCografya", Arrays.asList(TYTCografya));
        tytDersler.put("TYTFelsefe", Arrays.asList(TYTFelsefe));
        tytDersler.put("TYTDin", Arrays.asList(TYTDin));

        aytDersler.put("AYTMatematik", Arrays.asList(AYTMatematik));
        aytDersler.put("AYTEdebiyat", Arrays.asList(AYTEdebiyat));
        aytDersler.put("AYTGeometri", Arrays.asList(AYTGeometri));
        aytDersler.put("AYTTarih", Arrays.asList(AYTTarih));
        aytDersler.put("AYTCografya", Arrays.asList(AYTCografya));
        aytDersler.put("AYTFelsefe", Arrays.asList(AYTFelsefe));
        aytDersler.put("AYTDin", Arrays.asList(AYTDin));
        aytDersler.put("AYTFizik", Arrays.asList(AYTFizik));
        aytDersler.put("AYTKimya", Arrays.asList(AYTKimya));
        aytDersler.put("AYTBiyoloji", Arrays.asList(AYTBiyoloji));
    }

    public void acilincaOlacaklar() {
        //Metinleri Temizliyor.
        et_registerIsim.setText("");
        et_registerSoyisim.setText("");
        et_registerEmail.setText("");
        et_registerSifre.setText("");
        et_registerSifreTekrar.setText("");

    }

    public void makeToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void clickOlaylari() {
        img_registerExit.setOnClickListener(view -> {
            Intent go = new Intent(RegisterScreen.this, WelcomeScreen.class);
            startActivity(go);
            finish();
        });

        btn_registerKayitOl.setOnClickListener(view -> {
            //Kayıt ol butonuna basınca bilgileri aktarıyor.
            txt_registerIsim = et_registerIsim.getText().toString();
            txt_registerSoyisim = et_registerSoyisim.getText().toString();
            txt_registerEmail = et_registerEmail.getText().toString();
            txt_registerSifre = et_registerSifre.getText().toString();
            txt_registerSifreTekrar = et_registerSifreTekrar.getText().toString();

            //Kontroller
            if (TextUtils.isEmpty(txt_registerIsim)) {
                makeToast("İsim Kısmı Boş Olamaz!");
            } else if (TextUtils.isEmpty(txt_registerSoyisim)) {
                makeToast("Soyisim Kısmı Boş Olamaz!");
            } else if (TextUtils.isEmpty(txt_registerEmail)) {
                makeToast("Email Kısmı Boş Olamaz!");
            } else if (TextUtils.isEmpty(txt_registerSifre)) {
                makeToast("Şifre Kısmı Boş Olamaz!");
            } else if (TextUtils.isEmpty(txt_registerSifreTekrar)) {
                makeToast("Şifre Kısmı Boş Olamaz!");
            } else if (!txt_registerSifre.equals(txt_registerSifreTekrar)) {
                makeToast("Şifreler Uyuşmuyor!");
            } else {
                //Her şey tamamsa hesap oluşturuyor.
                mAuth.createUserWithEmailAndPassword(txt_registerEmail, txt_registerSifre)
                        .addOnCompleteListener(RegisterScreen.this, task -> {
                            if (task.isSuccessful()) {
                                mUser = mAuth.getCurrentUser(); //Mevcut Kullanıcı

                                mData = new HashMap<>();

                                mData.put("İsim", txt_registerIsim);
                                mData.put("Soyisim", txt_registerSoyisim);
                                mData.put("Email", txt_registerEmail);
                                mData.put("Şifre", txt_registerSifre);
                                mData.put("KullaniciID", mUser.getUid());

                                //Konuları veritabanına ekliyoruz
                                for (String s : tytDersler.keySet()) {
                                    for (String ss : Objects.requireNonNull(tytDersler.get(s))) {
                                        mReference.child("Kullanıcılar").child(mUser.getUid()).child("TYTKonulari").child(s).child(ss).setValue(false);
                                    }
                                }
                                for (String s : aytDersler.keySet()) {
                                    for (String ss : Objects.requireNonNull(aytDersler.get(s))) {
                                        mReference.child("Kullanıcılar").child(mUser.getUid()).child("AYTKonulari").child(s).child(ss).setValue(false);
                                    }
                                }
                                for (String s : YDTIngilizce) {
                                    mReference.child("Kullanıcılar").child(mUser.getUid()).child("YDTKonulari").child("Ingilizce").child(s).setValue(false);
                                }

                                //Firebase'de yer açıp yerleştiriyor.
                                mReference.child("Kullanıcılar").child(mUser.getUid()).child("Bilgiler").setValue(mData)
                                        .addOnCompleteListener(RegisterScreen.this, task1 -> {
                                            if (task1.isSuccessful()) {
                                                makeToast("Kayıt İşlemi Başarılı!");
                                                Intent go = new Intent(RegisterScreen.this, LoginScreen.class);
                                                startActivity(go);
                                                finish();
                                            } else {
                                                makeToast(Objects.requireNonNull(task1.getException()).getMessage());
                                            }
                                        });
                            } else {
                                makeToast(Objects.requireNonNull(task.getException()).getMessage());
                            }
                        });
            }
        });
    }
}