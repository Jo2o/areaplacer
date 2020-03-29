package sk.jmikus.areaplacer.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AreaTest {

    private Area area;

    @BeforeEach
    void setUp() {
        area = new Area();
        area.addPoint(Point.builder().x(0).y(0).build());
        area.addPoint(Point.builder().x(0).y(1).build());
        area.addPoint(Point.builder().x(1).y(1).build());
        area.addPoint(Point.builder().x(0).y(2).build());
    }

    @Test
    void getWidth() {
        // when
        int result = area.getWidth();
        // then
        assertThat(result).isEqualTo(2);
    }

    @Test
    void getHeight() {
        // when
        int result = area.getHeight();
        // then
        assertThat(result).isEqualTo(3);
    }

}
