package application.datasource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonSerializer;

import application.json.JSONObjectFactory;
import application.json.SongSerializer;
import application.models.Song;

public class DataSource {
    private static DataSource instance = null;
    private List<Song> songs = new ArrayList<Song>();

    private DataSource() {
        songs.add(new Song(1, "Shallow (From A Star Is Born)", "Lady Gaga, Bradley Cooper", "https://www.youtube.com/watch?v=bo_efYhYU2A"));
        songs.add(new Song(2, "Rain On Me", "Lady Gaga, Ariana Grande", "https://www.youtube.com/watch?v=AoAm4om0wTs"));
        songs.add(new Song(3, "Do It", "Chloe x Halle", "https://www.youtube.com/watch?v=a9HIaGcBocc"));
        songs.add(new Song(4, "Don't Start Now", "Dua Lipa", "https://www.youtube.com/watch?v=oygrmJFKYZY"));
        songs.add(new Song(5, " POPSTAR", "DJ Khaled ft. Drake", "https://www.youtube.com/watch?v=3CxtK7-XtE0"));
        songs.add(new Song(6, "No Time To Die", "Billie Eilish", "https://www.youtube.com/watch?v=2I1ZU5g1QNo"));
        songs.add(new Song(7, "ME!", "Taylor Swift", "https://www.youtube.com/watch?v=_zUCMfYkgj0"));
        songs.add(new Song(8, "Beautiful People (feat. Khalid)", "Ed Sheeran", "https://www.youtube.com/watch?v=74yb9E3WY1I"));
        songs.add(new Song(9, "Say Something", "Justin Timberlake", "https://www.youtube.com/watch?v=tMkwQFlAhMA"));
        songs.add(new Song(10, "In My Blood", "Shawn Mendes", "https://www.youtube.com/watch?v=36tggrpRoTI"));
    }

    public static DataSource getInstance() {
        if ( instance == null ) {
            instance = new DataSource();
        }

        return instance;
    }

    public List<Song> fetchAllSongs() {
        List<Song> list = this.songs;

        System.out.println("List of songs:"+ list);

        return list;
    }

    public Song rateSong(int id, int rating) throws Exception {
        System.out.println("Rate song "+ id + " rating " + rating);

        Song foundSong = findSong(id);
        if (foundSong != null) {
            foundSong.rate(rating);
        } else {
            throw new Exception("Song not found");
        }

        return foundSong;
    }

    public Song findSong(int id) {
        List<Song> list = this.songs;

        Song song = null;

        for(int i=0; i< list.size();i++){
            if ( list.get(i).getId() == id ) {
                song = list.get(i);
                break;
            }
        }

        return song;
    }

    public static void main(String[] args) {
        Gson gson = new Gson();

        System.out.println(gson.toJson(getInstance().fetchAllSongs()));

        Map<String, List<Song>> a = new HashMap<String, List<Song>>();
        a.put("songs", getInstance().fetchAllSongs());
        System.out.println(gson.toJson(a));

        JsonArray array = JSONObjectFactory.getInstance().generateJSONArray(getInstance().fetchAllSongs());
        HashMap<String, JsonArray> map = new HashMap<String, JsonArray>();
        map.put("songs", array);
        System.out.println(gson.toJson(map));

        GsonBuilder gsonBuilder = new GsonBuilder();
        JsonSerializer<Song> serializer = new SongSerializer();
        gsonBuilder.registerTypeAdapter(Song.class, serializer);

        Gson customGson = gsonBuilder.create();
        System.out.println(customGson.toJson(a)); 
    }

}
