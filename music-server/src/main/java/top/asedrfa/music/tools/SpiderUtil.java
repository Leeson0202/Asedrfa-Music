package top.asedrfa.music.tools;

import lombok.Data;
import lombok.SneakyThrows;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import top.asedrfa.music.entity.Music;

import java.io.IOException;
import java.util.*;

@Data
public class SpiderUtil {


    @SneakyThrows
    public static List<Music> musicSpider(String url, String input, String type) {

        List<Music> musicList = new ArrayList<>();


        //创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //设置请求地址是：https://www.icourse163.org/search.htm?search=java#/
        //创建URI地址
        String string = "https://api.zhuolin.wang/api.php";
//        ?callback=jQuery111306840550754252452_1637841192164&types=search
//                &count=20&source=netease&pages=1&name=%E5%91%BC%E5%90%B8%E5%86%B3%E5%AE%9A&_=1637841192166";
        URIBuilder uriBuilder = new URIBuilder(string);

        //设置参数
//        String param = "search", value = "java";

        uriBuilder.addParameter("name", input);
        uriBuilder.addParameter("types", "search");
        uriBuilder.addParameter("callback", "jQuery111306840550754252452_1637841192164");
        uriBuilder.addParameter("count", "20");
        uriBuilder.addParameter("source", "netease");
        uriBuilder.addParameter("pages", "1");
        uriBuilder.addParameter("_", "1637841192166");

        //创建HttpPost对象，设置url访问地址
        HttpGet httpPost = new HttpGet(uriBuilder.build());

        //发起请求
        System.out.println("发起请求的信息" + httpPost);

        //try/catch/finally : Ctrl+Alt+T
        CloseableHttpResponse response = null;
        try {
            //使用HttpClient发起请求，获取response
            response = httpClient.execute(httpPost);

            //解析响应
            if (response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(response.getEntity(), "utf8");
                System.out.println(content.length());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭response
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return musicList;
    }


    public static void main(String[] args) {
        SpiderUtil.musicSpider("https://music.liuzhijin.cn/", "呼吸决定", "qq");
    }
}
