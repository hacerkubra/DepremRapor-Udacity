package com.example.android.quakereport;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by KUBRAMEVLUT on 23.04.2018.
 */

public class DepremAdapter extends ArrayAdapter<Deprem> {

    private static final String KONUM_AYIRICI = " of ";

    DecimalFormat formatter = new DecimalFormat("0.0");


    public DepremAdapter(Activity context, ArrayList<Deprem> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView=convertView;
        if(listItemView==null)
        {
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.liste_eleman,parent,false);
        }

        Deprem currentDeprem=getItem(position);

        String orjinalKonum = currentDeprem.getYer();
        String birincilKonum;
        String konumunUzakligi;



        if (orjinalKonum.contains(KONUM_AYIRICI)) {
            String[] parts = orjinalKonum.split(KONUM_AYIRICI);
            konumunUzakligi = parts[0] + KONUM_AYIRICI;
            birincilKonum = parts[1];
        } else {
            konumunUzakligi = getContext().getString(R.string.near_the);
            birincilKonum = orjinalKonum;
        }

        TextView tv_Buyukluk=(TextView)listItemView.findViewById(R.id.textview_buyukluk);
        String output = formatter.format(currentDeprem.getBuyukluk());
        tv_Buyukluk.setText(output);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) tv_Buyukluk.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getBuyuklukRenk(currentDeprem.getBuyukluk());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        TextView tv_Yer=(TextView)listItemView.findViewById(R.id.textview_yer);
        tv_Yer.setText(konumunUzakligi);

        TextView tv_Ulke=(TextView)listItemView.findViewById(R.id.textview_ulke);
        tv_Ulke.setText(birincilKonum);

        // Create a new Date object from the time in milliseconds of the earthquake
        Date dateObject = new Date(currentDeprem.getTarihMilisaniye());

        // Find the TextView with view ID date
        TextView tv_Tarih = (TextView) listItemView.findViewById(R.id.textview_tarih);
        // Format the date string (i.e. "Mar 3, 1984")
        String gosterilecekTarih = formatDate(dateObject);
        // Display the date of the current earthquake in that TextView
        tv_Tarih.setText(gosterilecekTarih);

        // Find the TextView with view ID time
        TextView tv_saat = (TextView) listItemView.findViewById(R.id.textview_saat);
        // Format the time string (i.e. "4:30PM")
        String formattedTime = formatTime(dateObject);
        // Display the time of the current earthquake in that TextView
        tv_saat.setText(formattedTime);

        return listItemView;
    }

    private  String String_Bol1(String cumle){
        if(cumle.contains("of")) {
            String[] parcalar = cumle.split("(?<=of)");
            String parca1 = parcalar[0];
            String parca2 = parcalar[1];
            return parca1;
        }else
            return "Near the";
    }

    private  String String_Bol2(String cumle){
        if(cumle.contains("of")) {
            String[] parcalar = cumle.split("(?<=of)");
            String parca1 = parcalar[0];
            String parca2 = parcalar[1];
            return parca2;
        }else
            return cumle;
    }
    private String bol(String cumle){
        String altcumle;
        if(cumle.contains("of"))
        {
            altcumle=cumle.substring(0,cumle.indexOf("of")+2);
        }else
            altcumle="Near the";

        return altcumle;
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("k:mm");
        return timeFormat.format(dateObject);
    }

    private int getBuyuklukRenk(double buyukluk)
    {
        int buyuklukRenkKaynakId;
        int buyuklukYuvarla=(int)Math.floor(buyukluk);

        switch (buyuklukYuvarla)
        {
            case 0:
            case 1:
                buyuklukRenkKaynakId = R.color.buyukluk1;
                break;
            case 2:
                buyuklukRenkKaynakId = R.color.buyukluk2;
                break;
            case 3:
                buyuklukRenkKaynakId = R.color.buyukluk3;
                break;
            case 4:
                buyuklukRenkKaynakId = R.color.buyukluk4;
                break;
            case 5:
                buyuklukRenkKaynakId = R.color.buyukluk5;
                break;
            case 6:
                buyuklukRenkKaynakId = R.color.buyukluk6;
                break;
            case 7:
                buyuklukRenkKaynakId = R.color.buyukluk7;
                break;
            case 8:
                buyuklukRenkKaynakId= R.color.buyukluk8;
                break;
            case 9:
                buyuklukRenkKaynakId = R.color.buyukluk9;
                break;
            default:
                buyuklukRenkKaynakId = R.color.buyukluk10ustu;
                break;
        }
        return ContextCompat.getColor(getContext(),buyuklukRenkKaynakId);
    }
}
