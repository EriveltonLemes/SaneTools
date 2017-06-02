package br.com.apptools.sanetools;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by Erivelton on 30/05/2017.
 */

public class LoadImoveisJson extends AsyncTask<String, Void, ResponseImoveis> {

    private static final String TAG = "LoadEnderecoJsonMenu";

    public LoadImoveisJson(Listener listener) {

        mListener = listener;
    }

    public interface Listener {

        void onLoaded(List<ImoveisApp> androidList);

        void onError();
    }

    private Listener mListener;

    @Override
    protected ResponseImoveis doInBackground(String... strings) {
        try {

            String stringResponse = loadJSON(strings[0]);
            Gson gson = new Gson();

            return gson.fromJson(stringResponse, ResponseImoveis.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(ResponseImoveis responseMenu) {

        if (responseMenu != null) {

            mListener.onLoaded(responseMenu.getMenu());

        } else {

            mListener.onError();
        }
    }

    private String loadJSON(String jsonURL) throws IOException {

        URL url = new URL(jsonURL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000);
        conn.setConnectTimeout(15000);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        conn.connect();

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        StringBuilder response = new StringBuilder();


        while ((line = in.readLine()) != null) {
            Log.v(TAG, "Line:");
            Log.v(TAG, line);
            response.append(line);
        }
        Log.v(TAG, "response");
        Log.v(TAG, response.toString());
        in.close();
        return response.toString();
    }
}
