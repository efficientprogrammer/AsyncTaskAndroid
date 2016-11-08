package android.efficientprogrammer.com.asynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import static android.efficientprogrammer.com.asynctask.R.id.imview;

public class MainActivity extends AppCompatActivity {

    public Button load,other;
   public  ProgressBar bar;
    public ImageView imview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        load=(Button)findViewById(R.id.load);
        other=(Button)findViewById(R.id.other);
        bar=(ProgressBar)findViewById(R.id.pbar);
        imview=(ImageView)findViewById(R.id.imview);
        bar.setVisibility(ProgressBar.INVISIBLE);
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
    new LoadIconTask().execute(R.drawable.code);
            }
        });

        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "I am responding...", Toast.LENGTH_SHORT).show();
            }
        });


    }


    public class LoadIconTask extends AsyncTask<Integer, Integer, Bitmap> {
        @Override
        protected void onPreExecute() {


            bar.setVisibility(ProgressBar.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(Integer... resId) {

            // getting the bitmap from the res/drawable folder

            Bitmap tmp= BitmapFactory.decodeResource(getResources(),resId[0]);
            for(int i=1;i<11;i++)
            {
                sleep();
                publishProgress(i*10);
            }

            return tmp;
        }

        private void sleep() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        @Override
        protected void onProgressUpdate(Integer... values) {

            bar.setProgress(values[0]);
            super.onProgressUpdate(values);
        }


        @Override
        protected void onPostExecute(Bitmap bitmap) {
            bar.setVisibility(ProgressBar.INVISIBLE);
            imview.setImageBitmap(bitmap);

        }
    }


}
