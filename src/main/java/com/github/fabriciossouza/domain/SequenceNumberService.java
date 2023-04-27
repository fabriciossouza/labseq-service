package com.github.fabriciossouza.domain;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;


@ApplicationScoped
public class SequenceNumberService {

    private List<Long> cache;

    public SequenceNumberService() {
        cache = new ArrayList<>();
        cache.add(0L);
        cache.add(1L);
        cache.add(0L);
        cache.add(1L);
    }

    public long get(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("number must be non-negative");
        }

        while (number >= cache.size()) {
            int size = cache.size();
            long valueAtNMinus4 = cache.get(size - 4);
            long valueAtNMinus3 = cache.get(size - 3);
            long result = valueAtNMinus4 + valueAtNMinus3;
            cache.add(result);
        }
        return cache.get(number);
    }
}
