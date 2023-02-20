package google_api.me;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class GooglePlaceTest2 {
	public static final String GOOGLE_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?"
			+ "location=24.9577267,121.2246804&"
			+ "radius=1000&"
			+ "types=food&"
			+ "name=bar&"
			+ "language=zh-TW&"
			+ "key=AIzaSyDeePRPPIrCXlI_ETMPHfF7sJS-naoIh9k";

	public static void main(String[] args) throws IOException {
		URL url = new URL(GOOGLE_URL);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setUseCaches(false);
//		System.out.println(con.getResponseCode());
//
		if (con.getResponseCode() == 200) {
			InputStream is = con.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			FileWriter fw2=new FileWriter("src\\main\\java\\google_api\\store.json");
			BufferedWriter br2=new BufferedWriter(fw2);
			PrintWriter ps2=new PrintWriter(br2);
			System.out.println("IO通道均已開啟");
			String str;
			ps2.println("[");
			while ((str = br.readLine()) != null){
				System.out.println(str);
				ps2.println(str);
				System.out.println("=======================================");
			}
			ps2.println("]");

			ps2.close();
			br2.close();
			fw2.close();
			br.close();
			isr.close();
			is.close();
			System.out.println("IO通道均已關閉");


		} else {
			System.out.println("Failed...");
		}
	}
	
}
