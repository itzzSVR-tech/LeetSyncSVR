class Solution {
    public int maxNumberOfBalloons(String text) {
        int[] charFrequency = new int[26];
      
        for (int i = 0; i < text.length(); i++) {
            charFrequency[text.charAt(i) - 'a']++;
        }      
        charFrequency['l' - 'a'] /= 2;
        charFrequency['o' - 'a'] /= 2;
      
        int maxBalloons = Integer.MAX_VALUE;
        for (char c : "balon".toCharArray()) {
            maxBalloons = Math.min(maxBalloons, charFrequency[c - 'a']);
        }
      
        return maxBalloons;
    }
}