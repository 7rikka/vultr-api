package nya.nekoneko.vultr;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.time.Duration;
/**
 * @author Ho
 */
public class VultrCall {
    private static final OkHttpClient CLIENT = new OkHttpClient().newBuilder()
            .readTimeout(Duration.ofSeconds(100))
            .connectTimeout(Duration.ofSeconds(100))
            .callTimeout(Duration.ofSeconds(100))
//            .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 7890)))
            .build();
    private static final int OK = 200;
    private static final int CREATED = 201;
    private static final int NO_CONTENT = 204;

    public static Response doCallGetResponse(Request request) {
        try {
            Response response = CLIENT.newCall(request).execute();
            return response;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    public static String doCallGetString(Request request) {
        try {
            Response response = CLIENT.newCall(request).execute();
            String result = response.body().string();
            int code = response.code();
            if (OK != code && CREATED != code && NO_CONTENT != code) {
                throw new VultrException(result);
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}