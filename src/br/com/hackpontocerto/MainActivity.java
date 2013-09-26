package br.com.hackpontocerto;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.LinkedList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import br.com.hackpontocerto.GeocodeResponse2.Geocode;

import com.google.gson.Gson;

public class MainActivity extends Activity {
	/** Called when the activity is first created. */

	private WebView webView;
	private EditText editText;
	boolean espera = true;
	private String s1;
	LinkedList<ListView> listviews = new LinkedList();
	LinkedList<String> enderecos = new LinkedList<String>();
	String loc = "-23.5150231,-46.6418381";
	String lat = "-23.5150231";
	String lng = "-46.6418381";
	Spinner spinnerseg;
	Spinner spinnerRaio;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_principal);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		editText = (EditText) findViewById(R.id.editText1);

		 spinnerseg = (Spinner) findViewById(R.id.spinnerSeg);
		Spinner spinnerRaio = (Spinner) findViewById(R.id.spinner1);

		editText.addTextChangedListener(new TextWatcher() {

			public void afterTextChanged(Editable s) {
				if (s.length() > 4) {
					s1 = s.toString();
					espera = true;
					for (ListView lv : listviews) {
						lv.setVisibility(View.INVISIBLE);
					}
					System.out.println("Maior q 4 !!!!@@##$%%%");
					Handler handler = new Handler();
					handler.postDelayed(new Runnable() {
						public void run() {
							espera = false;

							try {
								if (!espera && s1 != null) {

									String content = getURLContent("http://maps.google.com/maps/api/geocode/json?address="
											+ URLEncoder.encode(s1)
											+ "&sensor=false&region=br&language=pt-BR");
									s1 = null;

									Gson gson = new Gson();
									System.out.println(content);
									GeocodeResponse2 geo = gson.fromJson(
											content, GeocodeResponse2.class);

									System.out.println(geo);
									if (geo.getResults().size() == 1) {
										enderecos = new LinkedList<String>();
										String ende = geo.getResults().get(0)
												.getFormatted_address();
										enderecos.add(ende);
										criaLista(new String[] { ende });

									} else if (geo.getResults().size() > 1) {
										enderecos = new LinkedList<String>();

										for (Geocode result : geo.getResults()) {
											enderecos.add(result
													.getFormatted_address());

										}
										String[] array = new String[enderecos
												.size()];

										criaLista(enderecos.toArray(array));
										espera = true;
									}
								}

							} catch (Exception e) {

								e.printStackTrace();
							}
						}
					}, 2000);

				}

			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

			}

		});

		spinnerRaio
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						disparaMapa(null, null, null);

					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub.

					}

				});

		spinnerseg
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						disparaMapa(null, null, null);

					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub.

					}

				});

		disparaMapa(null, null, null);

		criaLista(new String[] {});

	}

	public String getURLContent(String urlString) throws Exception {
		URL url = new URL(urlString);
		URLConnection yc = url.openConnection();

		StringBuffer sb = new StringBuffer();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				yc.getInputStream()));
		String inputLine;
		while ((inputLine = in.readLine()) != null)
			sb.append(inputLine);
		in.close();

		return sb.toString();
	}

	public void disparaMapa(String endereco, String raio, String segmento) {
		if (raio == null) {
			if (spinnerRaio != null) {
				raio = String.valueOf(spinnerRaio.getSelectedItem());
			}
		}
		if (segmento == null) {
			if (spinnerseg != null) {
				segmento = String.valueOf(spinnerseg.getSelectedItem());
			}else{
				segmento = "cafe";
			}
		}
		if (endereco == null) {
			if (enderecos.size() > 0) {
				endereco = enderecos.getFirst();
			} else {
				endereco = "Anhembi Parque, Sao Paulo";
			}
		}

		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%");

		webView = (WebView) findViewById(R.id.webView1);
		WebSettings webSettings = webView.getSettings();
		webSettings.setJavaScriptEnabled(true);

		webView.loadUrl("http://www.fisioterapiapirituba.com.br/teste.html?endereco=" + URLEncoder.encode(endereco) + "&raio=1000&segmento="+ URLEncoder.encode(segmento) +"&latitude=-23.5150231&longitude=-46.6418381");

	}

	public void criaLista(String[] lista) {

		if (lista.length > 0) {
			ListView list = new ListView(this);

			listviews.add(list);
			list.setAdapter(new MyAdapter(this, lista));

			list.setY(100);
			list.setBackgroundColor(Color.GRAY);

			list.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> arg0, View v,
						int position, long id) {
					System.out.println("********************** " + (id));
					// editText = (EditText) findViewById(R.id.editText1);
					// editText.setText(enderecos.get((int)id));
					String endereco = enderecos.get((int) id);
					enderecos = new LinkedList<String>();
					enderecos.add(endereco);

					for (ListView lv : listviews) {
						lv.setVisibility(View.INVISIBLE);
					}

					// TODO:Criar chamada a url
				}
			});

			RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.mainLayout);
			relativeLayout.addView(list);
		}
	}

	private class MyAdapter extends ArrayAdapter<String> {

		public MyAdapter(Context context, String[] strings) {
			super(context, -1, -1, strings);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			LinearLayout listLayout = new LinearLayout(MainActivity.this);
			listLayout.setLayoutParams(new AbsListView.LayoutParams(
					AbsListView.LayoutParams.WRAP_CONTENT,
					AbsListView.LayoutParams.WRAP_CONTENT));
			listLayout.setId(5000);

			TextView listText = new TextView(MainActivity.this);
			listText.setId(5001);
			listText.setTextColor(Color.WHITE);
			listText.setTextSize(20);

			listLayout.addView(listText);

			listText.setText(super.getItem(position));

			return listLayout;
		}
		
		
	}
}
