package com.github.rye761.unsplash.test;

import com.github.rye761.unsplash.Stats;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class StatsTest {
    
    @Test
    public void totalShouldReturnStats() {
        final Stats stats = Stats.total();
        assertThat(stats, instanceOf(Stats.class));
        assertThat(stats.batchDownloads, instanceOf(int.class));
        assertThat(stats.photoDownloads, instanceOf(int.class));
        System.out.println(stats.batchDownloads);
    }
}
