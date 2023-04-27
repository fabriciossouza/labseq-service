package com.github.fabriciossouza.domain;

import java.util.HashMap;
import java.util.Map;

public class SequenceNumberService {

    private Map<Integer, Long> memo;

    public SequenceNumberService() {
        memo = new HashMap<>();
        memo.put(0, 0L);
        memo.put(1, 1L);
        memo.put(2, 0L);
        memo.put(3, 1L);
    }

    public long get(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be non-negative");
        }

        if (!memo.containsKey(n)) {
            for (int i = 4; i <= n; i++) {
                if (!memo.containsKey(i)) {
                    long result = memo.get(i - 4) + memo.get(i - 3);
                    memo.put(i, result);
                }
            }
        }
        return memo.get(n);
    }
}
