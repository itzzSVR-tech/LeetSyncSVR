class Solution {
    private static final int MOD = 1_000_000_007;

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;
        
        if (n == 1) return m;

        int states = 2 * m;
        long[][] T = new long[states][states];

        for (int x = 0; x < m; x++) {
            
            for (int y = x + 1; y < m; y++) {
                T[y + m][x] = 1; 
            }
            for (int y = 0; y < x; y++) {
                T[y][x + m] = 1;
            }
        }

        long[] v2 = new long[states];
        for (int a = 0; a < m; a++) {
            for (int b = 0; b < m; b++) {
                if (a < b) {
                    v2[b + m]++;
                } else if (a > b) {
                    v2[b]++;
                }
            }
        }

        long[][] Tpow = matrixPower(T, n - 2, states);

        long totalValidArrays = 0;
        for (int i = 0; i < states; i++) {
            long waysToReachStateI = 0;
            for (int j = 0; j < states; j++) {
                waysToReachStateI = (waysToReachStateI + Tpow[i][j] * v2[j]) % MOD;
            }
            totalValidArrays = (totalValidArrays + waysToReachStateI) % MOD;
        }

        return (int) totalValidArrays;
    }

    private long[][] multiply(long[][] A, long[][] B, int size) {
        long[][] C = new long[size][size];
        for (int i = 0; i < size; i++) {
            for (int k = 0; k < size; k++) {
                if (A[i][k] == 0) continue;
                for (int j = 0; j < size; j++) {
                    C[i][j] = (C[i][j] + A[i][k] * B[k][j]) % MOD;
                }
            }
        }
        return C;
    }

    private long[][] matrixPower(long[][] base, int exp, int size) {
        long[][] res = new long[size][size];
        for (int i = 0; i < size; i++) res[i][i] = 1;

        while (exp > 0) {
            if ((exp & 1) == 1) {
                res = multiply(res, base, size);
            }
            base = multiply(base, base, size);
            exp >>= 1;
        }
        return res;
    }
}