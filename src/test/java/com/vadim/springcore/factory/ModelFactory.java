package com.vadim.springcore.factory;

import java.util.ArrayList;
import java.util.List;

public interface ModelFactory<T> {

    Integer defaultListSize = 3;

    T getInstance();

    default List<T> getInstanceList() {
        return getInstanceList(defaultListSize);
    }

    default List<T> getInstanceList(Integer size) {
        size = Math.max(1, size);

        List<T> list = new ArrayList<>(size);

        while (size-- > 0) {
            list.add(getInstance());
        }
        return list;
    }
}
