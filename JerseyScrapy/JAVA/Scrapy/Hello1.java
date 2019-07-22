import org.jsoup.*;
import org.jsoup.helper.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;
import java.io.IOException;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.*;
import org.json.JSONObject;
import org.json.JSONArray;

public class Hello1 {
    public static void main(String[] args) {

        while (1>0){
            String URL = "https://shopee.tw/api/v2/search_items/?by=ctime&conditions=used&keyword=%E7%90%83%E8%A1%A3&limit=50&newest=0&order=desc&page_type=search&skip_shuffle=1";

            try {
                String stringjson = Jsoup.connect(URL)
                        .header("user-agent","Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36 OPR/58.0.3135.79 (Edition Campaign 34)")
                        .header("referer","https://shopee.tw/search?keyword=%E7%90%83%E8%A1%A3&page=0&skipShuffle=true&sortBy=ctime&usedItem=true")
                        .header("cookie","_gcl_au=1.1.787482709.1551623562; SPC_IA=-1; SPC_EC=-; SPC_F=fMNKJLPSGH7A6J2Nk3G8ph33Wa6UufDL; REC_T_ID=337d8dbe-3dc1-11e9-87d7-b4969130c428; SPC_T_ID=\"z5fNoLujh8XF5qR4mZ4zIiMTtVbC2FBIhOIEht2CGKX1sP9cvnncC+yFvYF9BCAIgx0EDQ2HfeXTbc/YwGpy1uD0v6/u1ScV8x55INEBSR4=\"; SPC_U=-; SPC_T_IV=\"9M6pgKfjbvKTSNq2rBIBIQ==\"; _fbp=fb.1.1551623562719.481596412; _ga=GA1.2.699749640.1551623564; __BWfp=c1551631320061x753350522; cto_lwid=7085ee21-7d2b-4e0e-a388-0126e0114d7d; SPC_SI=ccusa45lcpf8kq8s0tfhu9kl6zj8ds8b; _gid=GA1.2.1530797300.1551964177; AMP_TOKEN=%24NOT_FOUND")
                        .header("x-api-source","pc")
                        .header("x-requested-with","XMLHttpRequest")
                        .header("If-None-Match","b23f164b1a25667580f63ec9ab03dc89;gzip")
                        .header("If-None-Match-","55b03-1482111d36a8b97c54b91761bca4259e")
                        .timeout(10000)
                        .ignoreContentType(true).execute().body();


                System.out.println(stringjson);
                Thread.sleep(1000);
                for (int i = 0; i < 5; i++) {
                    JSONObject empObj = new JSONObject(stringjson);
                    String itname = empObj.getJSONArray("items").getJSONObject(i).getString("name");
                    System.out.println(itname);
//                    System.out.println();
                    Long time = empObj.getJSONArray("items").getJSONObject(i).getLong("ctime");
                    Long price = empObj.getJSONArray("items").getJSONObject(i).getLong("price");
//                    System.out.println(empObj.getJSONArray("items").getJSONObject(i).get("name").getClass().getName());
//                    System.out.println("ctime type ="+ empObj.getJSONArray("items").getJSONObject(i).get("ctime").getClass().getName());
//                    System.out.println("ctime = "+time);
                    System.out.println("$$$ = "+price/100000);
                    double Xtime = System.currentTimeMillis();
                    System.out.println(Xtime/1000);
                    System.out.println();
//                    Thread.sleep(300);
                    //---------------------------- 顯示日期用
                    SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
                    Date current = new Date();
                    System.out.println(sdFormat.format(current));
                    //----------------------------
                }
                System.out.println("-------------------------");
            } catch (Exception ioe) {
                ioe.printStackTrace();
            }
        }


        //JsonParser jp = new JsonParser(); //from gson
        ////Convert the input stream to a json element
        //JsonElement root = jp.parse(new InputStreamReader((InputStream)stringjson.getContent()));
        //JsonObject rootobj = root.getAsJsonObject(); //May be an array, may be an object.

        //System.out.println(doc);
        //System.out.println(doc);

    }
}
