package pizzaMore.utils;

import java.io.*;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map;

public class WebUtils {

    public static String readHTML(String filePath) {
        StringBuilder sb = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line = reader.readLine();
            while (line != null) {
                sb.append(line).append(System.lineSeparator());
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }
    
    public static Map<String, String> getParameters() {
        Map<String, String> parameters = new LinkedHashMap<>();
        
        String paramsLine = "";
        if (WebUtils.isPost()) {

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
                paramsLine = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            paramsLine = System.getProperty("cgi.query_string");
        }
        String[] params = new String[0];
        try {
            params = URLDecoder.decode(paramsLine, "UTF-8").split("&");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        for (String param : params) {
            String[] pair = param.split("=");
            String key = pair[0];
            String value = null;
            if (pair.length > 1) {
                value = pair[1];
            }
            parameters.put(key, value);
        }

        return parameters;
    }

    public static boolean isPost() {
        String requestMethod = System.getProperty("cgi.request_method").toLowerCase();
        return "post".equals(requestMethod);
    }
}
