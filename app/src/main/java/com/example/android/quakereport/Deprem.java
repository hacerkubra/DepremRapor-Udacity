package com.example.android.quakereport;
/**
 * Created by KUBRAMEVLUT on 23.04.2018.
 */

public class Deprem {
    private String mYer;
    private double mBuyukluk;
    private long mTarih;
    private String mWebSayfasi;

    /**
     * Yeni Deprem nesnesi oluştur
     * @param Buyukluk Depremin buyukluğunu geri döndürür.
     * @param Yer Depremin meydana geldiği konum
     * @param TarihMilisaniye Depremin gerçekleştiği zaman
     */
    public Deprem(double Buyukluk,String Yer,long TarihMilisaniye,String websayfasi){
        mBuyukluk=Buyukluk;
        mYer=Yer;
        mTarih=TarihMilisaniye;
        mWebSayfasi=websayfasi;
    }

    /**
     * @return Depremin Büyüklüğü
     */
    public double getBuyukluk(){return mBuyukluk; }

    /**
     *
     * @return Depremin meydana geldiği konum
     */
    public String getYer(){return mYer;}

    /**
     * Depremin meydana geldiği tarih
     */
    public long getTarihMilisaniye(){return mTarih;}

    public String getWebSafyasi(){return mWebSayfasi;}

    @Override
    public String toString() {
        return "Deprem{" +
                "mBuyukluk='" + mBuyukluk + '\'' +
                ", mYer='" + mYer + '\'' +
                ", mTarih='" + mTarih + '\'' +
                '}';
    }
}
