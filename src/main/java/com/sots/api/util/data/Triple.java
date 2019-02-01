package com.sots.api.util.data;

public class Triple<A, B, C> {

    private final A first;
    private final B secnd;
    private final C third;

    public Triple(A first, B secnd, C third) {
        this.first = first;
        this.secnd = secnd;
        this.third = third;
    }

    public A getFirst() {
        return first;
    }

    public B getSecnd() {
        return secnd;
    }

    public C getThird() {
        return third;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof Tuple<?, ?>)) {
            return false;
        }
        Triple<?, ?, ?> other = (Triple<?, ?, ?>) obj;

        if (!(first.equals(other.getFirst()))) {
            return false;
        }

        if (!(secnd.equals(other.getSecnd()))) {
            return false;
        }

        return third.equals(other.getThird());
    }

    @Override
    public int hashCode() {
        return first.hashCode() + secnd.hashCode() + third.hashCode() * 17;
    }
}
