package com.example.atlanai;

        import androidx.annotation.Nullable;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.core.content.FileProvider;

        import android.content.Intent;
        import android.content.res.Configuration;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.graphics.Matrix;
        import android.media.ExifInterface;
        import android.net.Uri;
        import android.os.Bundle;
        import android.os.Environment;
        import android.os.PersistableBundle;
        import android.provider.MediaStore;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageView;

        import java.io.File;
        import java.io.IOException;
        import java.text.SimpleDateFormat;
        import java.util.Date;

public class MainActivity extends AppCompatActivity {

    String currentImagePath = null;
    ImageView imageView;
    Bitmap bitmap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button camera = findViewById(R.id.cameraBtn);
        imageView = findViewById(R.id.displayImg);
    }


    public void captureImage(View view) {

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            File imageFile = null;

            imageFile = getIMagefile();

            if (imageFile != null) {
                Uri imageUri = FileProvider.getUriForFile(this, "com.example.atlanai.provider", imageFile);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(cameraIntent, 1);
            }
        }
    }

    public File getIMagefile() {
        String timpstamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        String imageName = "jpj_" + timpstamp + " ";
        File stoargeDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        File imageFIle = null;
        try {
            imageFIle = File.createTempFile(imageName, ".jpj", stoargeDir);
        } catch (IOException e) {
            e.printStackTrace();
        }

        currentImagePath = imageFIle.getAbsolutePath();

        return imageFIle;

    }

    public void showImage(View view)
    {
        Bitmap bitmap = BitmapFactory.decodeFile(currentImagePath);
        imageView.setImageBitmap(bitmap);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("imagePath", currentImagePath);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        currentImagePath = savedInstanceState.getString("imagePath");

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        Bitmap bitmap = BitmapFactory.decodeFile(currentImagePath);
        imageView.setImageBitmap(bitmap);
    }

}