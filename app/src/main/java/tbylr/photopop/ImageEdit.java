package tbylr.photopop;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class ImageEdit extends AppCompatActivity {

    ImageView image_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_pic);
        image_view =  (ImageView) findViewById(R.id.pic);

        Intent intent = getIntent();

        String image = intent.getStringExtra("image_string");
        String imgString = image;
        //     Uri imgUri=Uri.parse(image);
        Bitmap imgMap = BitmapFactory.decodeFile(imgString);
        //       image_view.setVisibility(View.VISIBLE);
        //       Uri imgUri=Uri.parse(image);// << get URI from String
        //    byte[] bytes = intent.getByteArrayExtra("BMP");
        //    Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

        image_view.setImageBitmap(imgMap);

    }


}