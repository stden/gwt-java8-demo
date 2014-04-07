package me.jetblack.examples.client.util;

import org.apache.commons.lang.mutable.MutableInt;
import org.fusesource.restygwt.client.future.Future;
import org.fusesource.restygwt.client.future.FutureImpl;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public interface ExtendedList<T> extends List<T> {

    default <R> ExtendedList<R> map(Function<T, R> function) {
        ExtendedList<R> result = new ExtendedArrayList<>();
        this.each(item -> result.add(function.apply(item)));
        return result;
    }


    default void each(Consumer<T> consumer) {
        for (T t : this) {
            consumer.accept(t);
        }
    }

}
