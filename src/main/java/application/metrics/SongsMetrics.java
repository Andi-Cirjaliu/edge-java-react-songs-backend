package application.metrics;

import application.datasource.DataSource;
import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;

public class SongsMetrics {
    public static final Counter requests = Counter.build().name("songs_requests").help("Requests made to endpoints").labelNames("path").register();
    public static final Gauge songsStatistics = Gauge.build().name("songs_statistics").help("Song statistics").labelNames("statistic").register();

    public static void collectSongsStatistics() {
        songsStatistics.labels("total_songs").set(DataSource.getInstance().fetchAllSongs().size());

        songsStatistics.labels("most_voted_song").set(DataSource.getInstance().findMostVotedSong().getId());
        songsStatistics.labels("max_votes_song").set(DataSource.getInstance().findMostVotedSong().getRatingCount());

        songsStatistics.labels("least_voted_song").set(DataSource.getInstance().findLeastVotedSong().getId());
        songsStatistics.labels("min_votes_song").set(DataSource.getInstance().findLeastVotedSong().getRatingCount());

        songsStatistics.labels("best_rated_song").set(DataSource.getInstance().findBestRatedSong().getId());
        songsStatistics.labels("max_rating_song").set(DataSource.getInstance().findBestRatedSong().getRating());

        songsStatistics.labels("lowest_rated_song").set(DataSource.getInstance().findLowestRatedSong().getId());
        songsStatistics.labels("min_rating_song").set(DataSource.getInstance().findLowestRatedSong().getRating());
    }
}
