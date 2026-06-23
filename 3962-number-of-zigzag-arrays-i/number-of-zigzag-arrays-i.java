class Solution {
    public int zigZagArrays(int n, int l, int r) {
        final int MOD = 1_000_000_007; // 10^9 + 7
        r -= l;

        int[][] dp = new int[n][r + 1];

        for (int j = 0; j <= r; j++) {
            dp[0][j] = 1;
        }

        for (int i = 1; i < n; i++) {
            long prev = 0;

            if (i % 2 == 1) {
                for (int j = 0; j <= r; j++) {
                    dp[i][j] = (int) prev;
                    prev = (prev + dp[i - 1][j]) % MOD;
                }
            } else {
                for (int j = r; j >= 0; j--) {
                    dp[i][j] = (int) prev;
                    prev = (prev + dp[i - 1][j]) % MOD;
                }
            }
        }
        long sum = 0;
        for (int j = 0; j <= r; j++) {
            sum = (sum + dp[n - 1][j]) % MOD;
        }

        return (int) ((sum * 2) % MOD);
    }
}