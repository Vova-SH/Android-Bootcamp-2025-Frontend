package ru.sicampus.bootcamp2025.ui.map;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class ImageService {
    public void setImage(ImageView imageView, String path){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Image").whereMatches("name", path);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if(object != null){
                    ParseFile file = object.getParseFile("image");
                    if(file != null){
                        file.getDataInBackground(new GetDataCallback() {
                            @Override
                            public void done(byte[] data, ParseException e) {
                                if(e == null && data.length > 0){
                                    Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                                    imageView.setImageBitmap(bitmap);
                                }
                                Log.e("ImageError", e.getLocalizedMessage());
                            }
                        });
                    }
                    else Log.e("ImageError", e.getLocalizedMessage());
                }
                else{
                    Log.e("ImageError", e.getLocalizedMessage());
                }
            }
        });
    }
}
