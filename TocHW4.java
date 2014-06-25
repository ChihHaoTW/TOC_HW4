import org.json.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.*;

public class TocHW4
{
    public static String read(String urlStr) throws IOException 
    {  
        //URL url = new URL(urlStr);
        //URLConnection connection = url.openConnection();
        //connection.setDoInput(true);
        //InputStream inStream = connection.getInputStream();
        
		BufferedReader in = new BufferedReader(new FileReader("538447a07122e8a77dfe2d86"));
        
		//BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
        String s;  
        StringBuilder sb = new StringBuilder();  

        while ((s = in.readLine()) != null)  
        {
            //URLEncoder.encode(s, "UTF-8");
            sb.append(s + "\n");
        }

        in.close();  
        return sb.toString();  
    }  

    public static void main(String args[]) throws IOException
    {
        JSONArray ary = new JSONArray(read(args[0]));
		
		int longth = 0;
		String address = "";
		String road = "";

		ArrayList<String> add_buf = new ArrayList<String>();
        ArrayList<Integer> start_buf = new ArrayList<Integer>();
		ArrayList<Integer> end_buf = new ArrayList<Integer>();

		for(int i = 0; i < ary.length(); i++)
        {
			address = ary.getJSONObject(i).getString("土地區段位置或建物區門牌"); 

			if(address.indexOf("路") != -1)
			{
				road = address.substring(0, address.indexOf("路") + 1);
				System.out.println(road);
			}
			else if(address.indexOf("街") != -1)
			{
				
			}
			else if(address.indexOf("巷") != -1)
			{

			}
			

			/*try
            {
                sum += ary.getJSONObject(i).getInt("總價元");
            }
            catch(org.json.JSONException e4)
            {
                System.out.println("總價元 error");
                length--;
            }*/

        }
    }
}
