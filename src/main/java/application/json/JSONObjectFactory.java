package application.json;

import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import application.datasource.DataSource;
import application.models.Song;

public class JSONObjectFactory {
    private static JSONObjectFactory instance = null;

    private JSONObjectFactory() {

    }

    public static JSONObjectFactory getInstance() {
        if ( instance == null ) {
            instance = new JSONObjectFactory();
        }

        return instance;
    }

    public JsonObject generateJSON(Song song) {
        JsonObject jsonObj = new JsonObject();

        jsonObj.addProperty("id", song.getId());
        jsonObj.addProperty("title", song.getTitle());
        jsonObj.addProperty("artist", song.getArtist());
        jsonObj.addProperty("url", song.getUrl());
        jsonObj.addProperty("votes", song.getRatingCount());
        jsonObj.addProperty("rating", song.getRating());

        return jsonObj;
    }

    public JsonArray generateJSONArray(List<Song> songs) {
        JsonArray jsonArray = new JsonArray();

        songs.forEach(song -> jsonArray.add(generateJSON(song)));
        System.out.println("Generated JSON array:"+ jsonArray);

        return jsonArray;
    }

    public String generateJSON(List<Song> songs) {
        JsonArray jsonArray = generateJSONArray(songs);

        String json = jsonArray.toString();
        System.out.println("Generated JSON:"+ json);

        return json;
    }

    public static void main(String[] args) {
        JSONObjectFactory.getInstance().generateJSONArray(DataSource.getInstance().fetchAllSongs());
        JSONObjectFactory.getInstance().generateJSON(DataSource.getInstance().fetchAllSongs());
    }
}
