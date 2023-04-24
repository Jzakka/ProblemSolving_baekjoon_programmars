class Solution {
    public int solution(String s) {
        int minLen = Integer.MAX_VALUE;
        for (int i = 1; i <= s.length(); i++) {
            minLen = Math.min(minLen, compress(s, i).length());
        }
        return minLen;
    }

    public String compress(String str, int size) {
        StringBuilder compressed = new StringBuilder();

        int i = 0;
        while (i + size < str.length()) {
            int patternCnt = 1;
            String pattern = str.substring(i, i+size);

            while (i + (patternCnt + 1) * size <= str.length() && str.substring(i+patternCnt*size, i + (patternCnt + 1) * size).equals(pattern)) {
                patternCnt++;
            }

            if (patternCnt >= 2) {
                compressed.append(patternCnt).append(pattern);
                i += patternCnt * size;
            } else {
                compressed.append(pattern);
                i += size;
            }
        }
        if (i < str.length()) {
            compressed.append(str.substring(i));
        }

        return compressed.toString();
    }
}