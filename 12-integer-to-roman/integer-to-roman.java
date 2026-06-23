class Solution {
    public String intToRoman(int num) {
        List<String> romanSymbols = List.of(
            "M",   // 1000
            "CM",  // 900
            "D",   // 500
            "CD",  // 400
            "C",   // 100
            "XC",  // 90
            "L",   // 50
            "XL",  // 40
            "X",   // 10
            "IX",  // 9
            "V",   // 5
            "IV",  // 4
            "I"    // 1
        );
      
        List<Integer> values = List.of(
            1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1
        );
      
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < romanSymbols.size(); i++) {
            while (num >= values.get(i)) {
                num -= values.get(i);
                result.append(romanSymbols.get(i));
            }
        }
        return result.toString();
    }
}