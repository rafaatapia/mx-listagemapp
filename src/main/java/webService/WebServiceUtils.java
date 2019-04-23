package webService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

public class WebServiceUtils {
	
	public static void logarWs() {
		try {
			CookieManager cookieManager = new CookieManager();
			CookieHandler.setDefault(cookieManager);
			
			URL url = new URL("http://servicosflex.rpinfo.com.br:9000/v1.0/auth");
            HttpURLConnection conexao;
            InputStream is;
            String body = "{ \"usuario\": \"100000\", \"senha\": \"123456\"}";
            
            conexao = (HttpURLConnection) url.openConnection();
            conexao.setConnectTimeout(15000);
            conexao.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conexao.setDoOutput(true);
            conexao.setDoInput(true);
            conexao.setRequestMethod("POST");
            
            OutputStream os = conexao.getOutputStream();
            os.write(body.getBytes("UTF-8"));
            os.close(); 
            
            conexao.connect();
            is = conexao.getInputStream();
            
            is.close();
            conexao.disconnect();     
            
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static String retornaJSON(String url) {
        try {
            URL urlApi = new URL(url);
            HttpURLConnection conexao;

            conexao = (HttpURLConnection) urlApi.openConnection();
            conexao.setRequestMethod("GET");
            conexao.setReadTimeout(15000);
            conexao.setConnectTimeout(15000);

            conexao.connect();

            BufferedReader input = new BufferedReader(
                    new InputStreamReader(conexao.getInputStream(), "UTF-8")); 
            StringBuilder retorno = new StringBuilder();
            String str;
            while (null != (str = input.readLine())) {
            	retorno.append(str).append("\r\n"); 
            }
            
            conexao.disconnect();

            return retorno.toString();
            
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        
    }

}
