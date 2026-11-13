package me.jetblack.examples.client.util;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public interface ExtendedList<T> extends List<T> {

    default <R> ExtendedList<R> map(Function<T, R> function) {
        ExtendedList<R> result = new ExtendedArrayList<>();
        this.each(item -> result.add(function.apply(item)));
        return result;
    }

    default <R> R foldLeft(R init, Function2<R, T, R> function) {
        R result = init;
        for (T t : this) {
            result = function.apply(result, t);
        }
        return result;
    }

    default void each(Consumer<T> consumer) {
        for (T t : this) {
            consumer.accept(t);
        }
    }

}
