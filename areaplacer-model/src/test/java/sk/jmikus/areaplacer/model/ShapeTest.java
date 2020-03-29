package sk.jmikus.areaplacer.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
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
    void shouldMoveLeft() {
        // when
        shape.moveLeft();
        // then
        assertThat(shape.getPoints().get(0)).isEqualTo(Point.builder().x(1).y(0).build());
        assertThat(shape.getPoints().get(1)).isEqualTo(Point.builder().x(2).y(0).build());
        assertThat(shape.getPoints().get(2)).isEqualTo(Point.builder().x(1).y(1).build());
    }

    @Test
    void shouldMoveUp() {
        // when
        shape.moveUp();
        // then
        assertThat(shape.getPoints().get(0)).isEqualTo(Point.builder().x(0).y(1).build());
        assertThat(shape.getPoints().get(1)).isEqualTo(Point.builder().x(1).y(1).build());
        assertThat(shape.getPoints().get(2)).isEqualTo(Point.builder().x(0).y(2).build());
    }

}
