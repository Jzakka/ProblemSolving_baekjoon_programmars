class Solution {
    public String solution(String new_id) {
        if (accept(new_id)) {
            return new_id;
        }
        return getRecommendId(new_id);
    }

    private String getRecommendId(String new_id) {
        new_id = new_id
                .toLowerCase()
                .replaceAll("[^a-z\\d\\-_\\.]", "")
                .replaceAll("\\.{2,}", ".")
                .replaceAll("^\\.", "").replaceAll("\\.$", "")
                .replaceAll("^$", "a");
        if (new_id.length() > 15) {
            new_id = new_id.substring(0, 15)
                    .replaceAll("\\.$", "");
        }

        while (new_id.length() <= 2) {
            new_id += new_id.charAt(new_id.length() - 1);
        }

        return new_id;
    }

    private boolean accept(String new_id) {
        return lenConstrain(new_id) && compConstrain(new_id) && dotConstrain(new_id);
    }

    private boolean lenConstrain(String new_id) {
        int strLen = new_id.length();
        return strLen >= 3 && strLen <= 15;
    }

    private boolean compConstrain(String new_id) {
        return new_id.matches("[\\da-z\\-\\_\\.]+");
    }

    private boolean dotConstrain(String new_id) {
        for (int i = 0; i < new_id.length() - 1; i++) {
            int letter = new_id.charAt(i);
            if (i == 0 && letter == '.') {
                return false;
            }
            if (letter == '.' && new_id.charAt(i + 1) == '.') {
                return false;
            }
        }
        if (new_id.charAt(new_id.length() - 1) == '.') {
            return false;
        }
        return true;
    }
}