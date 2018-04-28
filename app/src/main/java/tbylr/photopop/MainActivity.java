package tbylr.photopop;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int pub;

    private static int RESULT_LOAD_IMG=0;
    private static int REQUEST_READ;
    private byte[] bytes = null;
    String _encoded;
    String[] FILE;

    String ImageDecode;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private static final int MY_PERMISSION_ACCESS_FINE_LOCATION = 11;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }



    public void selectImage(View v){

        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, RESULT_LOAD_IMG);

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        //Log.d("IMG","START");
        super.onActivityResult(requestCode, resultCode, data);
        try {
            int permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

            if (permission != PackageManager.PERMISSION_GRANTED) {
                // We don't have permission so prompt the user
                ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, REQUEST_READ);
            }
            // When an Image is picked
            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK && null != data) {
                // Get the Image from data

                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };

                // Get the cursor
                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String imgString;
                imgString = cursor.getString(columnIndex);
                cursor.close();

/*                File f = new File(imgString);
                FileInputStream fs = new FileInputStream(f);
                byte[] bytes = new byte[(int)f.length()];
                fs.read(bytes);
                String encodedFile = new String(Base64.encode(bytes,Base64.DEFAULT),"UTF-8");
                _encoded = encodedFile;
                Bitmap imgMap = BitmapFactory.decodeFile(imgString);
*/
                Intent i = new Intent (MainActivity.this, ImageEdit.class);
                i.setType("image/*");
                i.putExtra("image_string",imgString);
                startActivity(i);


//                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                //              imgMap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                //            byte[] byted = stream.toByteArray();

                //    Intent i = new Intent(MainActivity.this, ImageEdit.class);
                //    i.putExtra("data", imgMap);
                //    startActivity(i);
            }
/*
        if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK && null != data)
        {
            Uri selectedImage = data.getData();
            String [] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String filePath = cursor.getString(columnIndex);
            selectedphoto = BitmapFactory.decodeFile(filePath);
            cursor.close();
            Intent i = new Intent (MainActivity.this, ImageEdit.class);
            i.putExtra("data",selectedImage);
            startActivity(i);
        }
*/
//    }
        }

        catch (Exception e) {
            Toast.makeText(this, "Could not get image", Toast.LENGTH_LONG).show();
        }
    }
}