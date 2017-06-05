package br.com.apptools.sanetools;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CadastraUsuario extends AppCompatActivity {

    //Declaração de variaveis para recuperação de dados
    EditText mEdtInserirCPF;
    EditText mEdtInserirNome;
    EditText mEdtInserirSenha;
    EditText mEdtInserirSenha1;
    EditText mEdtInserirEmail;
    EditText mEdtInserirTelefone;
    Button mBtnSalvarCad;
    Button mBtnCancelarCad;


    private RequestQueue requestQueue;
    private static final String URL =  "http://192.168.1.99/apptools/sanetools/registrar2.php"; //Casa
    private StringRequest request;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        mEdtInserirCPF = (EditText) findViewById(R.id.edtInserirCPF);
        mEdtInserirNome = (EditText) findViewById(R.id.edtInserirNome);
        mEdtInserirSenha = (EditText) findViewById(R.id.edtInserirSenha);
        mEdtInserirSenha1 = (EditText) findViewById(R.id.edtInserirSenha1);
        mEdtInserirEmail = (EditText) findViewById(R.id.edtInserirEmail);
        mEdtInserirTelefone = (EditText) findViewById(R.id.edtInserirTelefone);
        mBtnSalvarCad = (Button) findViewById(R.id.btnSalvarCad);
        mBtnCancelarCad = (Button) findViewById(R.id.btnCancelarCad);

        requestQueue = Volley.newRequestQueue(this);


        //Função do botão de gravar o activity_cadastro do usuário

        mBtnSalvarCad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.names().get(0).equals("sucesso")) {
                                Toast.makeText(getApplicationContext(),"Sucesso"+jsonObject.getString("sucesso"), Toast.LENGTH_SHORT).show();

                                startActivity(new Intent(getApplicationContext(), HomeCliente.class));
                                /*Intent logar = new Intent(CadastraUsuario.this, HomeCliente.class);
                                startActivity(logar);
                                finish();*/

                            } else {
                                Toast.makeText(getApplicationContext(), "Erro"+jsonObject.getString("erro"), Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String, String> hashMap = new HashMap<String, String>();
                        hashMap.put("cpf_equipe", mEdtInserirCPF.getText().toString());
                        hashMap.put("nome", mEdtInserirNome.getText().toString());
                        hashMap.put("email", mEdtInserirEmail.getText().toString());
                        hashMap.put("telefone", mEdtInserirTelefone.getText().toString());
                        hashMap.put("senha", mEdtInserirSenha.getText().toString());

                        return hashMap;
                    }
                };

                requestQueue.add(request);
            }
        });

        //Função do botão de cancelar o activity_cadastro do usuário
        mBtnCancelarCad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
