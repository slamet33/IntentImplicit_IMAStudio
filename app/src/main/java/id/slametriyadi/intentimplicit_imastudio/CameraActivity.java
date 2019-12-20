package id.slametriyadi.intentimplicit_imastudio;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CameraActivity extends AppCompatActivity {

    @BindView(R.id.img_result)
    ImageView imgResult;
    @BindView(R.id.btn_galery)
    Button btnGalery;
    @BindView(R.id.btn_kamera)
    Button btnKamera;

    Uri fileLocation;
    String filePath;
    private final int requestGallery = 10;
    private final int requestCamera = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        ButterKnife.bind(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                    && checkSelfPermission(Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(
                        new String[]{Manifest.permission.CAMERA},
                        10);
            }
            return;
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                    && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        107);
            }
            return;
        }
    }

    @OnClick({R.id.btn_galery, R.id.btn_kamera})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_galery:
                Intent iGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(iGallery, requestGallery);
                break;
            case R.id.btn_kamera:
//                StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
//                StrictMode.setVmPolicy(builder.build());
//
//                String namaFolder = "foto_implicit_intent";
//                File file = new File(Environment.getExternalStorageDirectory(), namaFolder);
//                if (!file.exists()){
//                    file.mkdir();
//                }
//
//                File isiFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+namaFolder+"/PIC"+currentDate()+".jpg");
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                fileLocation = Uri.fromFile(isiFile);
//
//                camera.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                camera.putExtra(MediaStore.EXTRA_OUTPUT, fileLocation);
//
//                startActivityForResult(camera, 2);
                File photoFile = null;
                try {
                    photoFile = createImageFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (photoFile != null) {
                    Uri photoURI = FileProvider.getUriForFile(this,
                            "id.slametriyadi.intentimplicit_imastudio",
                            photoFile);
                    camera.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    //Start the camera application
                    startActivityForResult(camera, 2);
                }

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == requestGallery) {
            if (resultCode == RESULT_OK) {
                Uri uri = data.getData();
                imgResult.setImageURI(uri);
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Canceled Take a Picture"  , Toast.LENGTH_SHORT).show();
            }
        }

        if (requestCode == 2) {
            if (resultCode == RESULT_OK){
                BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                bmOptions.inSampleSize = 4;
                Bitmap bitmap = BitmapFactory.decodeFile(filePath, bmOptions);
                imgResult.setImageBitmap(bitmap);
            }
            else if (resultCode == RESULT_CANCELED){
                Toast.makeText(this, "Canceled Operation", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private String currentDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy_HH:mm:ss");
        Date date = new Date();
        return formatter.format(date);
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        filePath = image.getAbsolutePath();
        return image;
    }
}
