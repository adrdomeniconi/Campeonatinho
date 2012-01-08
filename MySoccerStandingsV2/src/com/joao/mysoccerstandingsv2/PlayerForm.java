package com.joao.mysoccerstandingsv2;

import com.joao.mysoccerstandingsv2.Negocio.Jogador;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PlayerForm extends Activity {
private Button btnSavePlayer;
private EditText edtPlayerName;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.playerform);
	    btnSavePlayer = (Button)findViewById(R.id.btnSavePlayer);
	    edtPlayerName = (EditText)findViewById(R.id.edtPLayerName);
	    btnSavePlayer.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				
				String nome = edtPlayerName.getText().toString();
				
				Jogador novoJogador = new Jogador(0, (byte)0, nome);
				novoJogador.Salvar(PlayerForm.this);
				
			}
		});
	}

	
	
	
}
