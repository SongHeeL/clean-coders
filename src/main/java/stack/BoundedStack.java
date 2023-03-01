package stack;

public class BoundedStack implements Stack {
    private int size = 0;
    private int capacity;
    private int [] elements;

    public BoundedStack(int capacity) {
        this.capacity = capacity;
        elements = new int [capacity];
    }

    @Override
    public Boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Integer getSize() {
        return size;
    }

    @Override
    public void push(int element) {
        if(size == capacity)
            throw new Overflow();
        this.elements[size++] = element;
    }

    @Override
    public int pop() {
        if(size == 0)
            throw new Underflow();
        return elements[--size];
    }

    public static Stack make(int capacity) {
        if(capacity < 0)
            throw new IllegalCapacity();
        if(capacity == 0){
            return new ZeroCapacityStack();
        }
        return new BoundedStack(capacity);
    }

    public static class Overflow extends RuntimeException {
    }

    public static class IllegalCapacity extends RuntimeException {
    }

    public static class Underflow extends RuntimeException {
    }

}