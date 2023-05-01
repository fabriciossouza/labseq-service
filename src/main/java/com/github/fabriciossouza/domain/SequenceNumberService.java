package com.github.fabriciossouza.domain;

import io.quarkus.cache.CacheResult;
import jakarta.enterprise.context.ApplicationScoped;

import java.math.BigInteger;

@ApplicationScoped
public class SequenceNumberService {

    private BigInteger[] cache = new BigInteger[]{BigInteger.ZERO, BigInteger.ONE, BigInteger.ZERO, BigInteger.ONE};

    @CacheResult(cacheName = "labseq")
    public BigInteger calculateLabSeqNumber(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Illegal argument: number must be non-negative");
        }

        if (number < getLength()) {
            return cache[number];
        }

        BigInteger[] localCache = new BigInteger[number + 1];
        System.arraycopy(cache, 0, localCache, 0, getLength());

        for (int i = getLength(); i <= number; i++) {
            BigInteger valueAtNMinus4 = localCache[i - 4];
            BigInteger valueAtNMinus3 = localCache[i - 3];
            BigInteger newValue = valueAtNMinus4.add(valueAtNMinus3);

            localCache[i] = newValue;
        }

        cache = localCache;
        return cache[number];
    }

    private int getLength() {
        return cache.length;
    }
}
