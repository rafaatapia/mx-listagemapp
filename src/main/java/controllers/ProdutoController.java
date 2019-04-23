package controllers;

import java.lang.reflect.Type;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import models.Produto;
import webService.WebServiceUtils;

public class ProdutoController {
	
	public ArrayList<Produto> buscaProdutos() {
		WebServiceUtils.logarWs();
		ArrayList<Produto> listaProdutos = new ArrayList<Produto>();

		boolean isDone = true;
		int last = 0;		
		
		while(isDone) {
			String URL = "http://servicosflex.rpinfo.com.br:9000/v1.1/produtounidade/listaprodutos/$CODIGO/unidade/00000000000000/semcalcularestoque";
			URL = URL.replace("$CODIGO", String.valueOf(last));
			String json = WebServiceUtils.retornaJSON(URL);						
			ArrayList<Produto> listaAxuliar;
			listaAxuliar = JSON2String(json);
			
			last = listaAxuliar.get(listaAxuliar.size()-1).getCodigo();
			
			for(Produto p : listaAxuliar) {
				listaProdutos.add(p);
			}
			
			isDone = validaFimLista(json, last);
			
		}
		
		return listaProdutos;
	}
	
	private ArrayList<Produto> JSON2String(String json) {
		try {
			JSONObject jsonObject = new JSONObject(json);
			JSONObject response = jsonObject.getJSONObject("response");
			JSONArray produtos = response.getJSONArray("produtos");
			
			ArrayList<Produto> lista = new ArrayList<Produto>();
			Gson gson = new Gson();		
			Type listType = new TypeToken<ArrayList<Produto>>(){}.getType();

			String jsonProdutos = produtos.toString();
			
			lista = gson.fromJson(jsonProdutos, listType);
			
			return lista;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private boolean validaFimLista(String json, int last) {
		try {
			JSONObject jsonObject = new JSONObject(json);
			int max = jsonObject.getInt("maxCod");
			
			if(last < max) {
				return true;
			}
			
			return false;
			
		}catch (JSONException e) {
			e.printStackTrace();
			return false;
		}		
	}
}
