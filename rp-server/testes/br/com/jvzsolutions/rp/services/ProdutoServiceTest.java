package br.com.jvzsolutions.rp.services;

import static org.junit.Assert.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import br.com.jvzsolutions.rp.model.Produto;

public class ProdutoServiceTest {

	@Test
	public void getAllTest() throws JSONException, JsonParseException, JsonMappingException, IOException {

		Client c = Client.create();
		WebResource r = c.resource("http://psocial.servehttp.com:8080/rp-server/rest/produtos");

		JSONArray response = r.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON).get(JSONArray.class);

		final Collection<Produto> produtos = new ArrayList<>();
		for (int i = 0; i < response.length(); i++) {
			JSONObject jsonObject = response.getJSONObject(i);
			ObjectMapper mapper = new ObjectMapper();
			Produto myPojo = mapper.readValue(jsonObject.toString(), Produto.class);
			produtos.add(myPojo);
		}

		assertEquals(1, produtos.size());
		Produto p = produtos.iterator().next();
		assertTrue((long) p.getId() > 0);
		assertEquals(7891991010023L, p.getCodigoBarras());
		assertNotNull(p.getCategoria());
		assertEquals(1, (long) p.getCategoria().getId());
	}
}
