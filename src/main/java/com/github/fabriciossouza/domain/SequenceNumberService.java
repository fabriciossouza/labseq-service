package com.github.fabriciossouza.domain;

import io.quarkus.cache.CacheResult;
import jakarta.enterprise.context.ApplicationScoped;

import static java.util.Arrays.copyOf;

@ApplicationScoped
public class SequenceNumberService {
    private long[] cache;

    public SequenceNumberService() {
        cache = new long[]{0, 1, 0, 1};
    }

    @CacheResult(cacheName = "labseq")
    public long calculateLabSeqNumber(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Illegal argument: number must be non-negative");
        }

        if (number < getLength()) {
            return cache[number];
        }

        long[] localCache = copyOf(cache, number + 1);
        for (int i = getLength(); i <= number; i++) {
            long valueAtNMinus4 = localCache[i - 4];
            long valueAtNMinus3 =  localCache[i - 3];
            long newValue = valueAtNMinus4 + valueAtNMinus3;

            localCache[i] = newValue;
        }

        cache = localCache;
        return cache[number];
    }

    private int getLength() {
        return cache.length;
    }
}