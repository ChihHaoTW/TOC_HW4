import org.json.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class TocHW3 
{
    public static String read(String urlStr) throws IOException 
    {  
        URL url = new URL(urlStr);
        URLConnection connection = url.openConnection();
        connection.setDoInput(true);
        InputStream inStream = connection.getInputStream();
        
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

    public static void main(String args[]) throws IOException
    {
        JSONArray ary = new JSONArray(read(args[0]));

        long sum = 0;
        int length = 0;
        for(int i = 0; i < ary.length(); i++)
        {
            try
            {
                if (!ary.getJSONObject(i).getString("鄉鎮市區").equals(args[1])) 
                    continue;
                //System.out.println(ary.getJSONObject(i).getString("鄉鎮市區"));
            }
            catch(org.json.JSONException e1)
            {
                //System.out.println("鄉鎮市區 error");
                continue;
            }

            try
            {
                if (ary.getJSONObject(i).getString("土地區段位置或建物區門牌").indexOf(args[2]) == -1) 
                    continue;
            }
            catch(org.json.JSONException e2)
            {
                //System.out.println("土地區段位置或建物區門牌 error");
                continue;
            }

            try
            {
                if (ary.getJSONObject(i).getInt("交易年月") < (Integer.parseInt(args[3])*100)) 
                    continue;
            }
            catch(org.json.JSONException e3)
            {
                //System.out.println("交易年月 error");
                continue;
            }


            try
            {
                sum += ary.getJSONObject(i).getInt("總價元");
            }
            catch(org.json.JSONException e4)
            {
                System.out.println("總價元 error");
                length--;
            }

            length++;
        }
        if (length == 0) 
            System.out.println("No result!");
        else
            System.out.println(sum / length);
    }
}
