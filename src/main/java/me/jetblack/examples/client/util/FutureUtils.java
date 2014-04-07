package me.jetblack.examples.client.util;

import org.fusesource.restygwt.client.future.Future;
import org.fusesource.restygwt.client.future.FutureImpl;

public class FutureUtils {

    public static <E> Future<ExtendedList<E>> toFutureOfList(ExtendedList<Future<E>> source) {
        FutureImpl<ExtendedList<E>> init = new FutureImpl<>();
        init.completeWithSuccess(new ExtendedArrayList<E>());
        return source.foldLeft(init, (Future<ExtendedList<E>> prev, Future<E> current) -> {
            FutureImpl<ExtendedList<E>> next = new FutureImpl<>();
            current.onComplete(item -> {
                prev.onComplete(items -> {
                    if (!item.isSuccess() || !items.isSuccess()) {
                        next.completeWithFailure(new RuntimeException());
                    } else {
                        ExtendedList<E> result = new ExtendedArrayList<E>();
                        result.addAll(items.get());
                        result.add(item.get());
                        next.completeWithSuccess(result);
                    }
                });
            });
            return next;
        });
    }

    public static <T> Future<T> flatten(Future<Future<T>> source) {
        FutureImpl<T> result = new FutureImpl<>();
        source.forEach(f -> f.onComplete(r -> result.completeWithResult(r)));
        return result;
    }
}
