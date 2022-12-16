
package otoparkproje;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class NewClass {
    public static void main(String[] args) {

           Date girisZamani = new Date();

           GirisAraci arac1 = new GirisAraci("Ford", "Focus", "34 ABC 123", girisZamani);

           arac1.calistir();
   

           arac1.calistir(60);
   

           arac1.calistir(60, "Sag");


        Date cikisZamani = new Date();
        int ucret = arac1.ucretHesapla(cikisZamani);


        Otopark otopark = new Otopark("My Parking Lot", 10);
        Otopark otoparkk = new Otopark("My Parking Lot", 10);

           Arac arac3 = new Arac("Ford", "Focus", "34 ABC 123");
           Arac arac4 = new Arac("Toyota", "Camry", "12 DEF 456");

           otopark.aracGiris(arac3);
           otopark.aracGiris(arac4);

          

           otopark.aracCikis(arac4);

        
          
    }
}
class Otopark implements AracIslemleri{
    private String isim;
    private int kapasite;
    private int mevcutYerler;
    private final ArrayList<Arac> araclar;

    public Otopark(String isim, int kapasite) {
        this.isim = isim;
        this.kapasite = kapasite;
        this.mevcutYerler = kapasite;
        this.araclar = new ArrayList<>();
    }

    public void aracGiris(Arac arac) {
        if (mevcutYerler > 0) {
            araclar.add(arac);
            mevcutYerler--;
            System.out.println("Arac otoparka giris yapti. Mevcut yerler: " + mevcutYerler);
        } else {
            System.out.println("Otopark dolu. Lütfen bekleyiniz.");
        }
    }

    public void aracCikis(Arac arac) {
        if (araclar.contains(arac)) {
            araclar.remove(arac);
            mevcutYerler++;
            System.out.println("Arac otoparktan cikis yapti. Mevcut yerler: " + mevcutYerler);
        } else {
            System.out.println("Arac otoparkta bulunamadi.");
        }
    }
}
    
    class Arac {
    private String marka;
    private String model;
    private String plaka;

    public Arac(String marka, String model, String plaka) {
        this.marka = marka;
        this.model = model;
        this.plaka = plaka;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPlaka() {
        return plaka;
    }

    public void setPlaka(String plaka) {
        this.plaka = plaka;
         if (plaka.length() != 7) {
            throw new IllegalArgumentException("Geçersiz plaka");
        }
    }
     public void calistir() {
        System.out.println("Arac calistirildi.");
    }
    
    public void duraklat() {
        System.out.println("Arac duraklatildi.");
    }
    
    public void hizlandir(int km) {
        System.out.println("Arac " + km + " km hizlandirildi.");
    }
    
    public void yavaslat(int km) {
        System.out.println("Arac " + km + " km yavaslatildi.");
    }
    
    public void durdur() {
        System.out.println("Arac durduruldu.");
    }
}
class GirisAraci extends Arac {
    private Date girisZamani;
    private int ucret;

    public GirisAraci(String marka, String model, String plaka, Date girisZamani) {
        super(marka, model, plaka);
        this.girisZamani = girisZamani;
    }

    public Date getGirisZamani() {
        return new Date(girisZamani.getTime());
    }

    public void calistir() {
        System.out.println("Arac calistirildi.");
    }

    public void calistir(int hiz) {
        System.out.println("Arac " + hiz + " km/s hizla calistirildi.");
    }

    public void calistir(int hiz, String yon) {
        System.out.println("Arac " + hiz + " km/s hizla " + yon + " yonunde calistirildi.");
    }

    public int ucretHesapla(Date cikisZamani) {
        long sure = cikisZamani.getTime() - girisZamani.getTime();
        int dakika = (int) TimeUnit.MILLISECONDS.toMinutes(sure);
        ucret = dakika * 2; // 2 TL per minute
        System.out.println("Aracin otoparktaki sure: " + dakika + " minutes. Ucret: " + ucret + " TL");
        return ucret;
    }
}


 class OtoparkIslemleri {
    private Otopark otopark;

    public OtoparkIslemleri(Otopark otopark) {
        this.otopark = otopark;
    }

    public void aracGiris(GirisAraci arac) {
        otopark.aracGiris(arac);
    }

    public void aracCikis(GirisAraci arac) {
        Date simdi = new Date();
        long fark = simdi.getTime() - arac.getGirisZamani().getTime();
        int dakika = (int) (fark / (60 * 1000));
        int ucret = dakika * 1;
        arac.setUcret(ucret);
        otopark.aracCikis(arac);
    }
}
 public abstract class Arac {
    private String marka;
    private String model;
    private String plaka;

    public Arac(String marka, String model, String plaka) {
        this.marka = marka;
        this.model = model;
        this.plaka = plaka;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPlaka() {
        return plaka;
    }

    public void setPlaka(String plaka) {
        this.plaka = plaka;
    }
    
    public abstract int getUcret();
}
 interface AracIslemleri {
    void aracGiris(Arac arac);
    void aracCikis(Arac arac);
}


