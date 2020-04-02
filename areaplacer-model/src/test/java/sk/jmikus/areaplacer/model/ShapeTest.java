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
        Shape result = shape.moveRightByOne();
        assertThat(result.getPoints().get(0)).isEqualTo(Point.builder().x(1).y(0).build());
        assertThat(result.getPoints().get(1)).isEqualTo(Point.builder().x(2).y(0).build());
        assertThat(result.getPoints().get(2)).isEqualTo(Point.builder().x(1).y(1).build());
    }

    @Test
    void shouldMoveUpByOne() {
        Shape result = shape.moveUpByOne();
        assertThat(result.getPoints().get(0)).isEqualTo(Point.builder().x(0).y(1).build());
        assertThat(result.getPoints().get(1)).isEqualTo(Point.builder().x(1).y(1).build());
        assertThat(result.getPoints().get(2)).isEqualTo(Point.builder().x(0).y(2).build());
    }

    @Test
    void shouldMoveLeftByOne() {
        Shape result = shape.moveLeft(1);
        assertThat(result.getPoints().get(0)).isEqualTo(Point.builder().x(-1).y(0).build());
        assertThat(result.getPoints().get(1)).isEqualTo(Point.builder().x(0).y(0).build());
        assertThat(result.getPoints().get(2)).isEqualTo(Point.builder().x(-1).y(1).build());
    }

    @Test
    void shouldGetLeftBoundary() {
        int result = shape.getLeftBoundary();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldGetRightBoundary() {
        int result = shape.getRightBoundary();
        assertThat(result).isEqualTo(1);
    }

    @Test
    void shouldGetTopBoundary() {
        int result = shape.getTopBoundary();
        assertThat(result).isEqualTo(1);
    }

    @Test
    void shouldGetBottomLeftPoint() {
        Point result = shape.getBottomLeftPoint();
        assertThat(result).isEqualTo(Point.builder().x(0).y(0).build());
    }

}
