import org.jsoup.*;
import org.jsoup.helper.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;
import java.io.IOException;
import java.io.*;
import org.json.JSONObject;
import org.json.JSONArray;
import java.lang.Thread;
import java.lang.reflect.Array;
import java.util.Arrays;


public class JerseyScrapy {

    public static JSONArray JsonScrapy(int PAGE) {

        String stringjson = null;
        try {

            String URL=null;
            String URL1 ="https://shopee.tw/api/v2/search_items/?by=ctime&conditions=used&keyword=%E7%90%83%E8%A1%A3&limit=50&newest=0&order=desc&page_type=search&skip_shuffle=1";
            String URL2 ="https://shopee.tw/api/v2/search_items/?by=ctime&conditions=used&keyword=%E7%90%83%E8%A1%A3&limit=50&newest=50&order=desc&page_type=search&skip_shuffle=1";
            String URL3 ="https://shopee.tw/api/v2/search_items/?by=ctime&conditions=used&keyword=%E7%90%83%E8%A1%A3&limit=50&newest=100&order=desc&page_type=search&skip_shuffle=1";
            String URL4 ="https://shopee.tw/api/v2/search_items/?by=ctime&conditions=used&keyword=%E7%90%83%E8%A1%A3&limit=50&newest=150&order=desc&page_type=search&skip_shuffle=1";
            String URL5 ="https://shopee.tw/api/v2/search_items/?by=ctime&conditions=used&keyword=%E7%90%83%E8%A1%A3&limit=50&newest=200&order=desc&page_type=search&skip_shuffle=1";
            //JSONArray
            if(PAGE==1){
                URL=URL1;
            }
            else if(PAGE==2){
                URL = URL2;
            }
            else if(PAGE==3){
                URL = URL3;
            }
            else if(PAGE==4){
                URL = URL4;
            }
            else if(PAGE==5){
                URL = URL5;
            }
            stringjson = Jsoup.connect(URL)
                    .header("user-agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36 OPR/58.0.3135.79 (Edition Campaign 34)")
                    .header("referer", "https://shopee.tw/search?keyword=%E7%90%83%E8%A1%A3&page=0&skipShuffle=true&sortBy=ctime&usedItem=true")
                    .header("cookie", "_gcl_au=1.1.787482709.1551623562; SPC_IA=-1; SPC_EC=-; SPC_F=fMNKJLPSGH7A6J2Nk3G8ph33Wa6UufDL; REC_T_ID=337d8dbe-3dc1-11e9-87d7-b4969130c428; SPC_T_ID=\"z5fNoLujh8XF5qR4mZ4zIiMTtVbC2FBIhOIEht2CGKX1sP9cvnncC+yFvYF9BCAIgx0EDQ2HfeXTbc/YwGpy1uD0v6/u1ScV8x55INEBSR4=\"; SPC_U=-; SPC_T_IV=\"9M6pgKfjbvKTSNq2rBIBIQ==\"; _fbp=fb.1.1551623562719.481596412; _ga=GA1.2.699749640.1551623564; __BWfp=c1551631320061x753350522; cto_lwid=7085ee21-7d2b-4e0e-a388-0126e0114d7d; SPC_SI=ccusa45lcpf8kq8s0tfhu9kl6zj8ds8b; _gid=GA1.2.1530797300.1551964177; AMP_TOKEN=%24NOT_FOUND")
                    .timeout(10000)
                    .ignoreContentType(true).execute().body();
            System.out.println("開始抓Json");
            Thread.sleep(1000);
            JSONObject itemobj = new JSONObject(stringjson);
            JSONArray itarrary = itemobj.getJSONArray("items");
            //JSONObject itobj = itarrary.getJSONObject(1);
            //int itid = itobj.getInt("itemid");
            //System.out.println(itid);
            System.out.println("Jsonarray抓取完畢");
            Thread.sleep(1000);
            return itarrary;

        }catch (Exception ioe) {
            ioe.printStackTrace();
        }return null;
    }
    public static String[] ScrapNewitem (){
        String[] nuitarray=new String[50];//將要儲存新商品名稱的陣列
        String[] nu=new String[50];
        //int itnum=0;//代表在一倫比對中新商品的數量，用這個跟上面的陣列將新商品逐一儲存至陣列中
        try {
            JSONArray outitemarrayP1 = JsonScrapy(1);//抓一個Jarray資料//起始的50商品資料
            JSONArray outitemarrayP2 = JsonScrapy(2);
            JSONArray outitemarrayP3 = JsonScrapy(3);
            JSONArray outitemarrayP4 = JsonScrapy(4);
            JSONArray outitemarrayP5 = JsonScrapy(5);
            System.out.println("抓新的Json比對");
            while (1>0){
                int itnum = 0;
                Thread.sleep(3000);
                JSONArray initemarrayP1 = JsonScrapy(1);
                JSONArray initemarrayP2 = JsonScrapy(2);
                JSONArray initemarrayP3 = JsonScrapy(3);
                JSONArray initemarrayP4 = JsonScrapy(4);
                JSONArray initemarrayP5 = JsonScrapy(5);
                System.out.println("比對中");
                for (int i = 0; i < 50; i++) {
                    JSONObject initobj = initemarrayP1.getJSONObject(i);//比較組的50商品資料
                    int inid = initobj.getInt("itemid");
                    for(int page=1;page<6;page++){//比對頁數1-5
                        JSONArray outitemarrayPX=null;
                        if(page==1){
                            outitemarrayPX = outitemarrayP1;
                            //System.out.println("正在比對第一頁");
                        }
                        else if(page==2){
                            outitemarrayPX = outitemarrayP2;
                            System.out.println("正在比對第二頁");
                        }
                        else if(page==3){
                            outitemarrayPX = outitemarrayP3;
                            System.out.println("正在比對第三頁");
                            Thread.sleep(10000);
                        }
                        else if(page==4){
                            outitemarrayPX = outitemarrayP4;
                            System.out.println("正在比對第四頁");
                            Thread.sleep(100000);
                        }
                        else if(page==5){
                            outitemarrayPX = outitemarrayP5;
                            System.out.println("正在比對第五頁");
                        }
                        for(int j=0;j<50;j++){
                            JSONObject outitobj = outitemarrayPX.getJSONObject(j);//舊的50商品
                            int outid = outitobj.getInt("itemid");
                            Thread.sleep(10);
                            if(inid!=outid && j!=49 && page!=5){ //表示這兩個商品不同，繼續比下去到發現一樣的為止
                                System.out.println("outid="+outid +"inid="+ inid);
                                //Thread.sleep(10);
                            }
                            else if(inid==outid ){//#同一物品
                                System.out.println("outid="+outid +"inid="+ inid);
                                System.out.println("發現為舊商品"+i);
                                //Thread.sleep(100);
                                j=49;
                                page=5;
                            }
                            else if (inid!=outid && j==49 && page==5){ //#表示發現新商品!!!
                                //===在這將先商品的內容做成陣列進行回傳===
                                String newitemname = initobj.getString("name");
                                //int tempnewitemprice = initobj.getInt("price");
                                //int newitemprice = tempnewitemprice/10000;
                                //String Snewitemprice = Integer.toString(newitemprice/10000);
                                //String[] newitarray = {newitemname,Snewitemprice};
                                System.out.println("發現新商品!!!");
                                //System.out.println(newitarray);
                                System.out.println(newitemname);

                                nuitarray[itnum]=newitemname;
                                itnum++;
                                //Thread.sleep(100000000);

                            }
                        }
                    }

                    //System.out.println(outemid);
                }
                System.out.println("比對完畢");

                //Thread.sleep(600000);
                //將新商品名單置入至舊商品名單中
                outitemarrayP1 = initemarrayP1;
                outitemarrayP2 = initemarrayP2;
                outitemarrayP3 = initemarrayP3;
                outitemarrayP4 = initemarrayP4;
                outitemarrayP5 = initemarrayP5;
                if(nuitarray[0]!=null){
//                    System.out.println(Arrays.toString(nuitarray));
//                    Thread.sleep(1000000);
                    for(int k=0;k<50;k++){
                        if(nuitarray[k]!=null){
                            System.out.println(nuitarray[k]);
                        }
                    }

                    if(nuitarray[2]!=null){
                        Thread.sleep(864000000);
                    }
                    nuitarray[0]=null;

                }
                //return nuitarray;
            }


        } catch (Exception ioe) {
            ioe.printStackTrace();
        }return null;
    }
    public static String[]  itnames(){
        return null;

    }
    public static void main(String[] args) {

        ScrapNewitem();
    }
}