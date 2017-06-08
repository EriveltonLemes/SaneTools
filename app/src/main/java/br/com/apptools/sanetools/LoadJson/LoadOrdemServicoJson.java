package br.com.apptools.sanetools.LoadJson;

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

import br.com.apptools.sanetools.Response.ResponseOrdemServico;
import br.com.apptools.sanetools.dominio.entidades.OrdemServicoApp;

public class LoadOrdemServicoJson extends AsyncTask<String, Void, ResponseOrdemServico> {

    private static final String TAG = "LoadOrdemServicoJsonMenu";

    public LoadOrdemServicoJson(Listener listener) {

        mListener = listener;
    }

    public interface Listener {

        void onLoaded(List<OrdemServicoApp> androidList);

        void onError();
    }

    private Listener mListener;

    @Override
    protected ResponseOrdemServico doInBackground(String... strings) {
        try {

            String stringResponse = loadJSON(strings[0]);
            Gson gson = new Gson();

            return gson.fromJson(stringResponse, ResponseOrdemServico.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(ResponseOrdemServico responseMenu) {

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
        Log.v(TAG, "Response");
        Log.v(TAG, response.toString());
        in.close();
        return response.toString();
    }
}
