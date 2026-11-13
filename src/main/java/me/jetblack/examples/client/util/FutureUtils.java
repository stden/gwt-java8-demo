package me.jetblack.examples.client.util;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import java.util.ArrayList;
import java.util.List;

public class FutureUtils {
    public static <E> void processListSequentially(ExtendedList<E> source, MethodCallback<ExtendedList<E>> callback) {
        ExtendedList<E> result = new ExtendedArrayList<>();
        processNextItem(source, 0, result, callback);
    }

    private static <E> void processNextItem(ExtendedList<E> source, int index, ExtendedList<E> result, MethodCallback<ExtendedList<E>> callback) {
        if (index >= source.size()) {
            callback.onSuccess(null, result);
            return;
        }

        E item = source.get(index);
        result.add(item);
        processNextItem(source, index + 1, result, callback);
    }

    public static <T> void chain(MethodCallback<T> first, MethodCallback<T> second) {
        first.onSuccess(null, null);
        second.onSuccess(null, null);
    }
}