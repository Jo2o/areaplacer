package sk.jmikus.areaplacer.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AreaTest {

    private Area area;

    @BeforeEach
    void setUp() {
        area = Area.builder()
                .points(Arrays.asList(
                        Point.builder().x(0).y(0).build(),
                        Point.builder().x(0).y(1).build(),
                        Point.builder().x(1).y(1).build(),
                        Point.builder().x(0).y(2).build())).build();
    }

    @Test
    void shouldGetLeftBoundary() {
        int result = area.getRightBoundary();
        assertThat(result).isEqualTo(1);
    }

    @Test
    void shouldGetTopBoundary() {
        int result = area.getTopBoundary();
        assertThat(result).isEqualTo(2);
    }

}
