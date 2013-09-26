package br.com.hackpontocerto;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ActivityMapa extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_mapa);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_mapa, menu);
		return true;
	}

}
