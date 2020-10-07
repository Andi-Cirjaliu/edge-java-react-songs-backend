package application.metrics;

import application.datasource.DataSource;
import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;

public class SongsMetrics {
    public static final Counter requests = Counter.build().name("songs_requests").help("Requests made to endpoints").labelNames("path").register();
    public static final Gauge songsStatistics = Gauge.build().name("songs_statistics").help("Song statistics").labelNames("statistic").register();

    public static void collectSongsStatistics() {
        songsStatistics.labels("total_songs").set(DataSource.getInstance().fetchAllSongs().size());
        songsStatistics.labels("most_voted_song").set(1);
        songsStatistics.labels("least_voted_song").set(1);
        songsStatistics.labels("best_rated_song").set(1);
        songsStatistics.labels("lowest_rated_song").set(1);
    }
}
