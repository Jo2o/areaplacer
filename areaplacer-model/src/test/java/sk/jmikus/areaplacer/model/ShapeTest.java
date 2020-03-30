package sk.jmikus.areaplacer.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShapeTest {

    private Shape shape;

    @BeforeEach
    void setUp() {
        shape = Shape.builder().points(Arrays.asList(
                        Point.builder().x(0).y(0).build(),
                        Point.builder().x(1).y(0).build(),
                        Point.builder().x(0).y(1).build())).build();
    }

    @Test
    void shouldMoveRightByOne() {
        // when
        Shape result = shape.moveRightByOne();
        // then
        assertThat(result.getPoints().get(0)).isEqualTo(Point.builder().x(1).y(0).build());
        assertThat(result.getPoints().get(1)).isEqualTo(Point.builder().x(2).y(0).build());
        assertThat(result.getPoints().get(2)).isEqualTo(Point.builder().x(1).y(1).build());
    }

    @Test
    void shouldMoveUpByOne() {
        // when
        Shape result = shape.moveUpByOne();
        // then
        assertThat(result.getPoints().get(0)).isEqualTo(Point.builder().x(0).y(1).build());
        assertThat(result.getPoints().get(1)).isEqualTo(Point.builder().x(1).y(1).build());
        assertThat(result.getPoints().get(2)).isEqualTo(Point.builder().x(0).y(2).build());
    }

    @Test
    void shouldMoveLeftByOne() {
        // when
        Shape result = shape.moveLeft(1);
        // then
        assertThat(result.getPoints().get(0)).isEqualTo(Point.builder().x(-1).y(0).build());
        assertThat(result.getPoints().get(1)).isEqualTo(Point.builder().x(0).y(0).build());
        assertThat(result.getPoints().get(2)).isEqualTo(Point.builder().x(-1).y(1).build());
    }

    @Test
    void shouldGetLeftBoundary() {
        // when
        int result = shape.getLeftBoundary();
        // then
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldGetRightBoundary() {
        // when
        int result = shape.getRightBoundary();
        // then
        assertThat(result).isEqualTo(1);
    }

    @Test
    void shouldGetTopBoundary() {
        // when
        int result = shape.getTopBoundary();
        // then
        assertThat(result).isEqualTo(1);
    }

    @Test
    void shouldGetBottomLeftPoint() {
        // when
        Point result = shape.getBottomLeftPoint();
        // then
        assertThat(result).isEqualTo(Point.builder().x(0).y(0).build());
    }

}
