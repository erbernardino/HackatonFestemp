package br.com.hackpontocerto;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Spinner;

@SuppressLint("SetJavaScriptEnabled") public class MainActivity extends Activity {
	
	private Spinner spinnerSegmento;
	private WebView webview ;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        webview = (WebView) findViewById(R.id.webView1);
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        
        webview.loadUrl("http://www.fisioterapiapirituba.com.br/teste.html");
        
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    
}
