package com.cid.jquick_test;

public interface Testable<T extends Testable<T>> {
    void ensure_equal(Test of, T other) throws RuntimeException;
}
