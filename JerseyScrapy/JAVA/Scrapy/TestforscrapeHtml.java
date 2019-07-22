//package jsoup;
import org.jsoup.*;
import org.jsoup.helper.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;
import java.io.IOException;
import java.io.*;
public class HELLO2 {
    public static int test(){
        int a=1,b=2,sum=a+b;
        return sum;
    }
    public static void main(String[] args) {
        /*
        System.out.println("Hello! World!!!!");
        System.out.println("Hello! World!!!!");
        */
        Document doc = null;
        try {
            doc = Jsoup.connect("https://www.ptt.cc/bbs/IA/index.html").get(); // URL shortened!
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        //System.out.println(doc);


    }
}
