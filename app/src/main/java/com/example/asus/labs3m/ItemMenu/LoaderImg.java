package com.example.asus.labs3m.ItemMenu;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class LoaderImg extends AsyncTask<String, Bitmap, Bitmap>{
    private OnloadCompleImg event;
    private ImageView containering ;
    private int position;
    public void setOnLoadCompleteImg (ImageView container, int position, MenuBaseAdapter event){
        this.event = event;
        this.containering = container;
        this.position = position;

    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        String url = strings[0];
        try{
            URL imgurl = new URL(url);
            InputStream file = imgurl.openConnection().getInputStream();
            Bitmap img = BitmapFactory.decodeStream(file);
            return img;
        }catch (MalformedURLException exp){
            Log.i("ERROR", exp.getMessage());
        }catch (IOException exp){
            Log.i("ERROR", exp.getMessage());
        }
        return null;
    }
    protected void onPostExecute(Bitmap img){
        this.event.OnloadCompleteImgResult(this.containering, this.position, img);
    }
}
