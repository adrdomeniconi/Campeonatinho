package campeonato.Negocio.Persistencia;

import android.content.Context;
import campeonato.Negocio.Campeonato;
import campeonato.Persistencia.DbAdapter;

public class CarregarCampeonato extends DbAdapter {
	
	protected CarregarCampeonato(Context contexto) {
		super(contexto);
	}

	//public Campeonato CarregarCampeonato(long idCampeonato) {
		//return new Campeonato("", false);
	//}
	
}
