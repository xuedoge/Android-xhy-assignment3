package mg.studio.weatherappdesign;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

//import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new DownloadUpdate().execute();
    }

    public void btnClick(View view) {
        new DownloadUpdate().execute();
    }


    private class DownloadUpdate extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... strings) {
            String stringUrl = "http://apis.juhe.cn/simpleWeather/query?city=%E9%87%8D%E5%BA%86&key=680fde858ff5aafd4c4534948e10963f";
            HttpURLConnection urlConnection = null;
            BufferedReader reader;

            try {
                URL url = new URL(stringUrl);

                // Create the request to get the information from the server, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();

                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Mainly needed for debugging
                    Log.d("TAG", line);
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                //The temperature
                return buffer.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String weather) {
            //Update the temperature displayed
            if(weather==null){
                Toast showToast=Toast.makeText(MainActivity.this, "网络连接失败，请检查您的网络", Toast.LENGTH_LONG);
                showToast.setGravity(Gravity.BOTTOM, 0, 0);
                showToast.show();
                return;
            }
            JSONObject json_weather= JSON.parseObject(weather);
            String result=json_weather.getString("result");
            JSONObject json_result= JSON.parseObject(result);
            String city = json_result.getString( "city");
            String realtime = json_result.getString( "realtime");

            JSONObject json_realtime = JSON.parseObject(realtime);
            String realtime_temp = json_realtime.getString("temperature");
            ((TextView) findViewById(R.id.temperature_of_the_day)).setText(realtime_temp);
            ((TextView) findViewById(R.id.tv_location)).setText(city);


            String future = json_result.getString( "future");
            JSONArray Array = JSON.parseArray(future);
            JSONObject json_futrue[] = new JSONObject[5];
            int size = Array.size();
            for (int i = 0; i < size; i++){
                json_futrue[i] = Array.getJSONObject(i);
            }
            ((TextView) findViewById(R.id.tv_date)).setText(json_futrue[0].getString("date"));
            ((TextView) findViewById(R.id.tv_weather)).setText(json_futrue[0].getString("weather"));

            ((TextView) findViewById(R.id.id_day1)).setText(json_futrue[1].getString("date"));
            ((TextView) findViewById(R.id.id_day2)).setText(json_futrue[2].getString("date"));
            ((TextView) findViewById(R.id.id_day3)).setText(json_futrue[3].getString("date"));
            ((TextView) findViewById(R.id.id_day4)).setText(json_futrue[4].getString("date"));

            ((TextView) findViewById(R.id.id_tmp1)).setText(json_futrue[1].getString("temperature"));
            ((TextView) findViewById(R.id.id_tmp2)).setText(json_futrue[2].getString("temperature"));
            ((TextView) findViewById(R.id.id_tmp3)).setText(json_futrue[3].getString("temperature"));
            ((TextView) findViewById(R.id.id_tmp4)).setText(json_futrue[4].getString("temperature"));

            ((TextView) findViewById(R.id.id_weather1)).setText(json_futrue[1].getString("weather"));
            ((TextView) findViewById(R.id.id_weather2)).setText(json_futrue[2].getString("weather"));
            ((TextView) findViewById(R.id.id_weather3)).setText(json_futrue[3].getString("weather"));
            ((TextView) findViewById(R.id.id_weather4)).setText(json_futrue[4].getString("weather"));

            //Toast.makeText(getApplicationContext(), "This is a Toast", Toast.LENGTH_SHORT).show();
            Toast showToast=Toast.makeText(MainActivity.this, "天气已更新", Toast.LENGTH_LONG);
            showToast.setGravity(Gravity.BOTTOM, 0, 0);
            showToast.show();
        }
    }
}
