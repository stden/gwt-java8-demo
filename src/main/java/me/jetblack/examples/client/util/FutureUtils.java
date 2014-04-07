package me.jetblack.examples.client.util;

import org.fusesource.restygwt.client.future.Future;

public class FutureUtils {

    public static <E> Future<ExtendedList<E>> toFutureOfList(ExtendedList<Future<E>> source) {
        return null;
    }

    public static <T> Future<T> flatten(Future<Future<T>> source) {
        return null;
    }
}
