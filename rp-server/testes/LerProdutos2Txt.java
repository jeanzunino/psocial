
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

import br.com.jvsolutions.rp.facade.RPFacade;
import br.com.jvzsolutions.rp.dao.exception.ExcecaoGenericaDAO;
import br.com.jvzsolutions.rp.model.CategoriaProduto;
import br.com.jvzsolutions.rp.model.Produto;

public class LerProdutos2Txt extends LerProdutosTxt{

	@Test
	public void test() throws FileNotFoundException, IOException, ExcecaoGenericaDAO {
		File textfile = new File("produtos2.txt");

		FileInputStream input = new FileInputStream(textfile);
		byte[] buffer = new byte[(int) textfile.length()];

		input.read(buffer);

		String string = new String(buffer);
		String[] lines = string.split("\n");
		System.out.println("Linhas " + lines.length);
		int campos3 = 0;
		int cadastrados = 0;
		int linhasvazias = 0;
		int dadoInvalido = 0;
		int categoriaNaoTratada = 0;
		for (String line : lines) {
			if (line.length() == 0) {
				linhasvazias++;
			} else {
				String[] campos = line.split("|");
				if (campos.length >= 2) {
					campos3++;
					String codigo = campos[2];
					String nome = campos[1];
					nome = nome.replaceAll("\r", "");
					if (DataUtil.validaEan13(codigo) || DataUtil.validaUPC(codigo) || DataUtil.validaEan14(codigo)) {

						CategoriaProduto categoria = getCategoria(nome, line);

						if (categoria != null) {

							Produto produto = new Produto();
							produto.setNome(nome);
							produto.setCodigoBarras(new Long(codigo));
							produto.setCategoria(categoria);
							try {
								RPFacade.cadastrarProduto(produto);
								cadastrados++;
								System.out.println("Cadastrados: " + cadastrados);
							} catch (Throwable e) {
								System.out.println(codigo + " - " + e.getMessage());
								e.printStackTrace();
								dadoInvalido++;
							}
						}else{
							categoriaNaoTratada++;
							System.out.println("produto não será cadastrado, categoria ainda não tratada: " + codigo +" - "+ line);
						}

					} else {
						dadoInvalido++;
						System.out.println("produto não será cadastrado, código não é EAN 13: " + codigo);

					}
				}
			}

		}
		System.out.println("Linhas vazias: " + linhasvazias);
		System.out.println("Linhas com 3 campos: " + campos3);
		System.out.println("Cadastrados: " + cadastrados);
		System.out.println("Dados inválidos: " + dadoInvalido);
		System.out.println("Categoria Não tratada: " + categoriaNaoTratada);
	}
}
