import java.net.*;
import java.util.*;
import java.io.*;
import com.google.gson.*;

public class Test{

    public static String[] getData(String field){
        String query = "https://api.themoviedb.org/3/search/movie?&api_key=7fae79308cccbe2e9a5c2356e568cf05&query=";
        String[] movieList;
        try{

            URL url = new URL(query+field);
            StringBuilder result = new StringBuilder();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            String response = result.toString();
            JsonParser parser = new JsonParser();
            JsonObject json = parser.parse(response).getAsJsonObject();

            JsonArray movies = json.getAsJsonArray("results");
            movieList = new String[movies.size()];

            for(int i = 0; i < movies.size(); i++){
                JsonObject movie = movies.get(i).getAsJsonObject();
                movieList[i] = String.valueOf(movie.get("title"));
            }
            for(int i = 0; i < movieList.length; i++){
                System.out.println(movieList[i]);
            }


        }catch(Exception e){
            System.out.println("Exception");
            return null;
        }


        return movieList;
    }

    public static void main(String[] args){
        getData("superman");
    }


}