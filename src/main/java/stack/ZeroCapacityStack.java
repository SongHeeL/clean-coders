package stack;

class ZeroCapacityStack implements Stack {
    @Override
    public Boolean isEmpty() {
        return true;
    }

    @Override
    public Integer getSize() {
        return 0;
    }

    @Override
    public void push(int element) {
        throw new BoundedStack.Overflow();
    }

    @Override
    public int pop() {
        throw new BoundedStack.Underflow();
    }
}
