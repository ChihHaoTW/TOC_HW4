import org.json.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.*;
import java.lang.Math.*;

public class TocHW4
{
    public static String read(String urlStr) throws IOException 
    {  
        URL url = new URL(urlStr);
        URLConnection connection = url.openConnection();
        connection.setDoInput(true);
        InputStream inStream = connection.getInputStream();
        
		//BufferedReader in = new BufferedReader(new FileReader("5385b69de7259bb37d925971"));
        
		BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
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

 	public static void show_max_min(JSONArray ary, String str)
	{
		int max = -1, min = Integer.MAX_VALUE;
		
		for(int i = 0; i < ary.length(); i++)
		{
			String address = ary.getJSONObject(i).getString("土地區段位置或建物區門牌"); 

			if(address.indexOf(str) != -1)
			{
				if(ary.getJSONObject(i).getInt("總價元") > max)
					max = ary.getJSONObject(i).getInt("總價元");

				if(ary.getJSONObject(i).getInt("總價元") < min)
					min = ary.getJSONObject(i).getInt("總價元");
			}
		}

		System.out.print("最高成交價:" + max + ", 最低成交價:" + min);
	}

	public static void main(String args[]) throws IOException
    {
        JSONArray ary = new JSONArray(read(args[0]));
		
		int idx = -1;
		String temp;
		String address = "";
		String road = "";

		ArrayList<String> add_buf = new ArrayList<String>();
        ArrayList<String> month = new ArrayList<String>();

		for(int i = 0; i < ary.length(); i++)
        {
			address = ary.getJSONObject(i).getString("土地區段位置或建物區門牌"); 

			if(address.indexOf("路") != -1)
			{
				road = address.substring(0, address.indexOf("路") + 1);
				//System.out.println(road);
			}
			else if(address.indexOf("街") != -1)
			{
				road = address.substring(0, address.indexOf("街") + 1);
			}
			else if(address.indexOf("大道") != -1)
			{
				road = address.substring(0, address.indexOf("大道") + 1);
			}
			else if(address.indexOf("巷") != -1)
			{
				road = address.substring(0, address.indexOf("巷") + 1);
			}
			else
				continue;
			
			if(!add_buf.contains(road))
			{
				add_buf.add(road);
				
				temp = Integer.toString(ary.getJSONObject(i).getInt("交易年月"));
				
				if(temp.length() < 5) temp = temp + "0";

				month.add(temp + "!");
			}
			else
			{
				idx = add_buf.indexOf(road);
				temp = Integer.toString(ary.getJSONObject(i).getInt("交易年月"));
				
				if(temp.length() < 5) temp = temp + "0";

				if(month.get(idx).indexOf(temp) == -1)
					month.set(idx, month.get(idx) + temp + "!");
			}
		}

		int longth = -1;

		for(int i = 0; i < add_buf.size(); i++)
		{
			if(month.get(i).length() > longth)
				longth = month.get(i).length();
		}

		for(int i = 0; i < add_buf.size(); i++)
		{
			if(month.get(i).length() == longth)
			{
				System.out.print(add_buf.get(i) + ", ");
				show_max_min(ary, add_buf.get(i));
				System.out.println();
			}
			//System.out.println(add_buf.get(i) + " !! " + month.get(i));
		}
			
		/*int longth = -1, i;
			
		for(i = 0; i < start_buf.size(); i++)
		{
			if((int)Math.floor(end_buf.get(i) / 100) * 12 + end_buf.get(i) % 100 - (int)Math.floor(start_buf.get(i) / 100) * 12 - start_buf.get(i) % 100 > longth)
				longth = (int)Math.floor(end_buf.get(i) / 100) * 12 + end_buf.get(i) % 100 - (int)Math.floor(start_buf.get(i) / 100) * 12 - start_buf.get(i) % 100;
		}

		for(i = 0; i < start_buf.size(); i++)
		{
			if((int)Math.floor(end_buf.get(i) / 100) * 12 + end_buf.get(i) % 100 - (int)Math.floor(start_buf.get(i) / 100) * 12 - start_buf.get(i) % 100 == longth)
				System.out.println(add_buf.get(i));
			//System.out.println(add_buf.get(i) + " !! " + start_buf.get(i) + " !! " + end_buf.get(i));
		}*/

    }
}
