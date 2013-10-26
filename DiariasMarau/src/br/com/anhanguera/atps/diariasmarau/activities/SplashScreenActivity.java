package br.com.anhanguera.atps.diariasmarau.activities;


import br.com.anhanguera.atps.diariasmarau.R;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
//import android.view.Window;


public class SplashScreenActivity extends Activity implements Runnable{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_splash);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		Handler handler = new Handler();
		handler.postDelayed(this, 3000);
			
		return true;
	}

	@Override
	public void run() {
		startActivity(new Intent(this, ListaHospedesActivity.class));
		finish();
		
	}

}
