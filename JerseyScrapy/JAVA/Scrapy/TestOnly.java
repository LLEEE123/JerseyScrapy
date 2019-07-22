import org.jsoup.*;
import org.jsoup.helper.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;
import java.io.IOException;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.*;
import org.json.JSONObject;
import org.json.JSONArray;

public class TestOnly {
    public static void main(String[] args){

            String URL = "https://shopee.tw/search?keyword=球衣&page=0&skipShuffle=true&sortBy=ctime&usedItem=true";

            try {

                long x=1964632001;
                long mx;
                if(x<0){
                    mx=x*(-1);
                }
                else {
                    mx=x;
                }

                int a=1,z=1;
                for(int i=0;i<z;i++){
                    a = a*10;
                    if(mx%a==0&&mx!=0){
                        z++;//z-1等於她後面的0的數量
                    }
                }
                a=1;
                if(z!=1&&mx!=0){
                    for(int i=0;i<z-1;i++){
                        a=a*10;
                    }
                    mx=mx/a;
                }
                System.out.println("x="+mx);

                String stringx = Long.toString(mx);
                System.out.println("1");
                String[] xx = stringx.split("");
                System.out.println(Arrays.toString(xx));
                String[] xxx = new String[xx.length];
                System.out.println(xxx.length);
                System.out.println("2");
                //////////////////////OOOOOOOOOOOKKKKKKKKKKKKK
                int zzz=0;
                for (int i=xx.length-1;i>-1;i--){
                    System.out.println("i="+i+"xx[i]="+xx[i]);
                    System.out.println("zzz="+zzz);
                    xxx[zzz]= xx[i];
                    zzz++;
                }
                System.out.println(Arrays.toString(xxx));
                int[] couseNumbers = new int[xxx.length];
                for(int i = 0; i <xxx.length; i++){
                    couseNumbers[i]=Integer.parseInt(xxx[i]);
                }
                StringBuilder strNum = new StringBuilder();

                for (long num : couseNumbers)
                {
                    strNum.append(num);
                }
                long finalInt = Integer.parseInt(strNum.toString());
                if(x<0){
                    finalInt=finalInt*(-1);
                }
                else if(x==0){
                    finalInt=0;
                }
                System.out.println("");
                System.out.println("x="+x);
                System.out.println("finalInt="+finalInt);


////                ArrayList Alist = new ArrayList<>();
//                List<String> Alist = new ArrayList<>();
//                System.out.println("3");
//                int z=0;
//                for (int i=xx.length-1;i<0;i--){
//                    if(Integer.valueOf(xx[i])==0 && z==0){
//
//                    }
//                    if(Integer.valueOf(xx[i])!=0){
//                        Alist.add(xx[i]);
//                        z=1;
//                    }
//                }
//                String[] AlistS = new String[Alist.size()];
//                for(int i=0;i<AlistS.length;i++){
//                    System.out.println("CCC");
//                    System.out.println(Alist.contains(i));
////                    myList.contains(s)
//                }
//
//                System.out.println("4");
//                String[]XX = Alist.toArray(new String[Alist.size()]);
//                int[] arr = new int[XX.length];
//                for (int i = 0; i < XX.length; i++){
//                    arr[i]= Integer.parseInt(XX[i]);
//                }
//                System.out.println("5");
//                System.out.println(Arrays.toString(arr));
//                StringBuilder strNum = new StringBuilder();
//                for (int num : arr)
//                {
//                    strNum.append(num);
//                }
//                System.out.println("6");
//                int finalInt = Integer.parseInt(strNum.toString());
//                System.out.println(finalInt);
//                System.out.println("7");



//                System.out.println(Arrays.toString(X));
//                String stringjson = Jsoup.connect(URL)
//                        .header("user-agent","Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36 OPR/58.0.3135.79 (Edition Campaign 34)")
//                        .header("referer","https://shopee.tw/search?keyword=%E7%90%83%E8%A1%A3&page=0&skipShuffle=true&sortBy=ctime&usedItem=true")
//                        .header("cookie","_gcl_au=1.1.787482709.1551623562; SPC_IA=-1; SPC_EC=-; SPC_F=fMNKJLPSGH7A6J2Nk3G8ph33Wa6UufDL; REC_T_ID=337d8dbe-3dc1-11e9-87d7-b4969130c428; SPC_T_ID=\"z5fNoLujh8XF5qR4mZ4zIiMTtVbC2FBIhOIEht2CGKX1sP9cvnncC+yFvYF9BCAIgx0EDQ2HfeXTbc/YwGpy1uD0v6/u1ScV8x55INEBSR4=\"; SPC_U=-; SPC_T_IV=\"9M6pgKfjbvKTSNq2rBIBIQ==\"; _fbp=fb.1.1551623562719.481596412; _ga=GA1.2.699749640.1551623564; __BWfp=c1551631320061x753350522; cto_lwid=7085ee21-7d2b-4e0e-a388-0126e0114d7d; SPC_SI=ccusa45lcpf8kq8s0tfhu9kl6zj8ds8b; _gid=GA1.2.1530797300.1551964177; AMP_TOKEN=%24NOT_FOUND")
//                        .timeout(10000)
//                        .ignoreContentType(true).execute().body();
//                //.get();
//                System.out.println(stringjson);

        }catch (Exception ioe) {
            ioe.printStackTrace();
        }

    }






}

