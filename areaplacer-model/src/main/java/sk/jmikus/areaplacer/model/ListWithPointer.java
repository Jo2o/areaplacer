package sk.jmikus.areaplacer.model;

import java.util.ArrayList;
import java.util.List;

public class ListWithPointer {

    private int pointer;
    private List<Shape> shapes;

    public ListWithPointer(int pointer, List<Shape> shapes) {
        this.shapes = shapes;
        validatePointer(pointer);
        this.pointer = pointer;
    }

    public List<Shape> getShapes() {
        return makeDeepCopyOfShapes();
    }

    public int getPointer() {
        return pointer;
    }

    public void setPointer(int pointer) {
        validatePointer(pointer);
        this.pointer = pointer;
    }

    public static ListWithPointerBuilder builder() {
        return new ListWithPointerBuilder();
    }

    public static class ListWithPointerBuilder {
        private int pointer;
        private List<Shape> shapes;
        public ListWithPointerBuilder pointer(int pointer) {
            this.pointer = pointer;
            return this;
        }
        public ListWithPointerBuilder shapes(List<Shape> shapes) {
            this.shapes = shapes;
            return this;
        }
        public ListWithPointer build() {
            return new ListWithPointer(pointer, shapes);
        }
    }

    private void validatePointer(int pointer) {
        if ((pointer < 0) || (pointer >= shapes.size())) {
            throw new IllegalArgumentException("Pointer not within shapes limits: 0 to " + (shapes.size() - 1));
        }
    }

    private List<Shape> makeDeepCopyOfShapes() {
        List<Shape> result = new ArrayList<>();
        for (Shape shape : shapes) {
            result.add(Shape.builder().points(shape.getPoints()).build());
        }
        return result;
    }

}
