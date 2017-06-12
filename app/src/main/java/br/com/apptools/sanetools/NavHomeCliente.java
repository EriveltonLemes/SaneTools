package br.com.apptools.sanetools;


import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class NavHomeCliente extends Activity {

    TextView mcpfLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_header_home_cliente);


        mcpfLogin = (TextView) findViewById(R.id.txtNomeLogin);
        mcpfLogin.setText(getIntent().getStringExtra("cpfLogin"));
    }
}


