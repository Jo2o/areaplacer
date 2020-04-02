package sk.jmikus.areaplacer.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

class ListWithPointerTest {

    private ListWithPointer listWithPointer;

    @BeforeEach
    void setUp() {
        listWithPointer = ListWithPointer.builder()
                .pointer(0)
                .shapes(Arrays.asList(
                        Shape.builder()
                                .name("a")
                                .points(Arrays.asList(Point.builder().x(0).y(0).build()))
                                .build()))
                .build();
    }

    @Test
    void shouldGetShapes() {
        List<Shape> result1 = listWithPointer.getShapes();
        List<Shape> result2 = listWithPointer.getShapes();
        assertThat(result1.get(0)).isNotSameAs(result2.get(0));
        assertThat(result1.get(0).getPoints().get(0)).isNotSameAs(result2.get(0).getPoints().get(0));
    }


}
