package com.example.asus.labs3m.ItemMenu;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.labs3m.R;

import java.util.ArrayList;

public class MenuBaseAdapter extends BaseAdapter implements OnloadCompleImg {

    private ArrayList<ItemMenuStructure> list;
    private ArrayList<TextView> counter;
    private Context context;

    public  MenuBaseAdapter (Context context, ArrayList<ItemMenuStructure> list){
        this.list= list;
        this.context = context;
        counter = new ArrayList<TextView>();
    }
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.itemfood, null);
        }
        TextView txt1= convertView.findViewById(R.id.textView2);
        TextView txt2 = convertView.findViewById(R.id.textView3);
        ImageView img = convertView.findViewById(R.id.imageView2);
        txt1.setText(this.list.get(position).getFoodname());
        txt2.setText(this.list.get(position).getQuantity()+"");
        counter.add(txt2);
        if (this.list.get(position).getImg() == null){
            LoaderImg loader = new LoaderImg();
            loader.setOnLoadCompleteImg(img, position, this);
            loader.execute(this.list.get(position).getUrlimg());
        }else{
            img.setImageBitmap(this.list.get(position).getImg());
        }
        return  convertView;
    }
    @Override
    public void OnloadCompleteImgResult(ImageView img, int position, Bitmap imgsourceimg){
        this.list.get(position).setImg(imgsourceimg);
        img.setImageBitmap(imgsourceimg);
    }
}
