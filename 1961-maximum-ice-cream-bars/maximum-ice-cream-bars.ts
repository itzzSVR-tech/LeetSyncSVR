function maxIceCream(costs: number[], coins: number): number {
    costs.sort((a: number, b: number) => a - b);
  
    const totalIceCreams: number = costs.length;
  
    for (let i: number = 0; i < totalIceCreams; i++) {
        if (coins < costs[i]) {
            return i;
        }
        coins -= costs[i];
    }
    
    return totalIceCreams;
};