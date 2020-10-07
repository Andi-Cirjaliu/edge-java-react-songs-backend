package application.json;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import application.models.Song;

public class SongSerializer implements JsonSerializer<Song> {

    @Override
    public JsonElement serialize(Song src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObj = new JsonObject();

        jsonObj.addProperty("id", src.getId());
        jsonObj.addProperty("title", src.getTitle());
        jsonObj.addProperty("author", src.getArtist());
        jsonObj.addProperty("votes", src.getRatingCount());
        jsonObj.addProperty("rating", src.getRating());

        // System.out.println(jsonObj);

        return jsonObj;
    }
}
