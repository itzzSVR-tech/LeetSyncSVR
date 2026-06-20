function countPrimes(n: number): number {
    const isPrime: boolean[] = new Array(n).fill(true);
    let primeCount: number = 0;
  
    for (let i = 2; i < n; i++) {
        if (isPrime[i]) {
            primeCount++;
            for (let j = i + i; j < n; j += i) {
                isPrime[j] = false;
            }
        }
    }
  
    return primeCount;
};