package TimeTable.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Timer;
import java.util.TimerTask;

public class InternetTest extends TimerTask {

	public static String Data="";
	private static String url="http://www.justcodeon.ir/MyPage.php";
	private static String myS="saeed";
	private static int flag=0;
	
	public static void main(String[] args) throws InterruptedException {
	
mytimer();
	}
	
	static void mytimer() throws InterruptedException{
		 while(true){
System.out.println("ss");
				if(Data!=""){
					System.out.println(Data);
				}
			Thread.sleep(300);
		 }
	 }
	 
	
	

		

		
String data="";
		
		@Override
		public void run() {


			try {
				if (flag == 0) {
					data = URLEncoder.encode("text", "UTF8") + "="
							+ URLEncoder.encode(myS, "UTF8");
				}
				URL url = new URL(this.url);
				URLConnection connect = url.openConnection();
				connect.setDoOutput(true);

				if (flag == 0) {
					OutputStreamWriter wr = new OutputStreamWriter(
							connect.getOutputStream());
					wr.write(data);
					wr.flush();
				}

				String S = convetInputStreamToString(connect.getInputStream());
				data = S;
				
			}  catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		
			
		}
		
		private String convetInputStreamToString(InputStream inputStream) {
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(
						inputStream));
				StringBuilder builder = new StringBuilder();
				String line;

				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}
				return builder.toString();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
	

}
