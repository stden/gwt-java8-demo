package me.jetblack.examples.client.util;

import org.fusesource.restygwt.client.future.Future;
import org.fusesource.restygwt.client.future.FutureImpl;
import org.fusesource.restygwt.client.future.util.Try;

import java.util.function.Consumer;

public class FutureUtils {

    public static <E> Future<ExtendedList<E>> toFutureOfList(ExtendedList<Future<E>> source) {
        FutureImpl<ExtendedList<E>> init = new FutureImpl<>();
        init.completeWithSuccess(new ExtendedArrayList<E>());
        return source.foldLeft(init, new Function2<Future<ExtendedList<E>>, Future<E>, Future<ExtendedList<E>>>() {
            @Override
            public Future<ExtendedList<E>> apply(Future<ExtendedList<E>> prev, Future<E> current) {
                FutureImpl<ExtendedList<E>> next = new FutureImpl<>();
                current.onComplete(new Consumer<Try<E>>() {
                    @Override
                    public void accept(Try<E> item) {
                        prev.onComplete(new Consumer<Try<ExtendedList<E>>>() {
                            @Override
                            public void accept(Try<ExtendedList<E>> items) {
                                if (!item.isSuccess() || !items.isSuccess()) {
                                    next.completeWithFailure(new IllegalArgumentException());
                                } else {
                                    ExtendedList<E> result = new ExtendedArrayList<E>();
                                    result.addAll(items.get());
                                    result.add(item.get());
                                    next.completeWithSuccess(result);
                                }
                            }
                        });
                    }
                });
                return next;
            }
        });
    }

    public static <T> Future<T> flatten(Future<Future<T>> source) {
        FutureImpl<T> result = new FutureImpl<>();
        source.forEach(new Consumer<Future<T>>() {
            @Override
            public void accept(Future<T> f) {
                f.onComplete(new Consumer<Try<T>>() {
                    @Override
                    public void accept(Try<T> t) {
                        result.completeWithResult(t);
                    }
                });
            }
        });
        return result;
    }
}
