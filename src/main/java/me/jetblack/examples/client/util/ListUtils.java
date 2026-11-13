package me.jetblack.examples.client.util;

public class ListUtils {

    public static <E> ExtendedList<E> flatten(ExtendedList<ExtendedList<E>> source) {
        ExtendedList<E> result = new ExtendedArrayList<>();
        source.each(result::addAll);
        return result;
    }

}
