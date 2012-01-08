package campeonato.Persistencia;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DbAdapter {
	
	protected Context contexto;
	protected SQLiteDatabase db;
	protected DbHelper dbHelper;

	protected DbAdapter(Context contexto) {
		this.contexto = contexto;
	}

	protected DbAdapter open() throws SQLException {
		//Inicializa as propriedades
		dbHelper = new DbHelper(contexto);
		db = dbHelper.getWritableDatabase();
		return this;		
	}

	protected void close() {
		dbHelper.close();
	}
}
