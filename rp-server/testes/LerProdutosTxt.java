import java.util.List;

import br.com.jvsolutions.rp.facade.RPFacade;
import br.com.jvzsolutions.rp.dao.exception.ExcecaoGenericaDAO;
import br.com.jvzsolutions.rp.model.CategoriaProduto;

public class LerProdutosTxt {

	public CategoriaProduto getCategoria(String nome, String line) throws ExcecaoGenericaDAO {
		CategoriaProduto categoria = null;
		if (nome.toUpperCase().contains("CERVEJA ") || nome.toUpperCase().contains("SKOL ")
				|| nome.toUpperCase().contains("KAISER ") || nome.toUpperCase().contains("ITAIPAVA ") || nome.toUpperCase().startsWith("CERV ")) {
			categoria = buscarcategoria("CERVEJA");
		} else if (nome.toUpperCase().contains("VINHO")) {
			categoria = buscarcategoria("VINHO");
		} else if (nome.toUpperCase().contains("ÁGUA ") || nome.toUpperCase().contains("AGUA ")) {
			categoria = buscarcategoria("ÁGUA");
		} else if (nome.toUpperCase().contains("REFRIGERANTE") || nome.toUpperCase().startsWith("REFR ")
				|| nome.toUpperCase().startsWith("COCA COLA ")) {
			categoria = buscarcategoria("REFRIGERANTE");
		} else if (nome.toUpperCase().contains("WHISKY")) {
			categoria = buscarcategoria("WHISKY");
		} else if (nome.toUpperCase().contains("VODKA")) {
			categoria = buscarcategoria("VODKA");
		} else if (nome.toUpperCase().startsWith("CAFE ")|| nome.toUpperCase().startsWith("CAFÉ ")) {
			categoria = buscarcategoria("CAFE");
		} else if (line.toUpperCase().contains("BEBIDAS ALCOOLICAS")) {
			categoria = buscarcategoria("BEBIDAS");
		}
		return categoria;
	}

	CategoriaProduto buscarcategoria(String nome) throws ExcecaoGenericaDAO {
		List<CategoriaProduto> list = RPFacade.buscarCategoria(nome);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
}
