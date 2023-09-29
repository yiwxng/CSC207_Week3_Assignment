import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class EmojiAPIRequest {

    private static final String API_URL = "https://emoji-api.com/emojis?access_key=ad7ae8bb0f58f8ad43013e5b72a9fe2d3c3f6ab6";

    public static void main(String[] args) {
        try {
            String response = makeGetRequest(API_URL);
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String makeGetRequest(String urlString) throws Exception {
        // Create a URL object with the API_URL
        URL url = new URL(urlString);

        // Open the connection to the specified URL
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

        // Set the request method to GET
        httpURLConnection.setRequestMethod("GET");

        // Get the response code
        int responseCode = httpURLConnection.getResponseCode();

        // If the response code is HTTP_OK (200), read the input stream and return the response
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            httpURLConnection.disconnect();
            return content.toString();
        } else {
            // If the response code is not HTTP_OK (200), throw an exception
            throw new Exception("GET request failed. Response Code: " + responseCode);
        }
    }
}
