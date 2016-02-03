/**
 * Created by qiqu on 1/27/16.
 */
public class IntegerWords {
    public String numberToWords(int num) {
        if (num==0) return "Zero";
        int comma = 0, r;
        final int SEPARATE = 1000;
        String word = "", toAdd;
        while (num > 0) {
            r = num % SEPARATE;
            toAdd = translate(r);
            word = toAdd.length() == 0 ? word : (toAdd + " " + translateComma(comma) + " " + word);
            num /= SEPARATE;
            comma++;
        }
        return word.trim();
    }

    private String translateComma(int i) {
        switch (i) {
            case 1:
                return "Thousand";
            case 2:
                return "Million";
            case 3:
                return "Billion";
        }
        return "";
    }

    private String translate(int r) {
        String word = "";
        int hundreds = r / 100;
        if (hundreds > 0) word += translateOnes(hundreds) + " Hundred";
        int tens = (r - 100 * hundreds) / 10;
        if (tens != 1) {
            if (tens > 1) word += " " + translateTens(tens);
            int ones = r % 10;
            word += " " + translateOnes(ones);
        } else {
            tens = r % 100;
            word += " " + translateTens(tens);
        }
        return word.trim();
    }

    private String translateOnes(int ones) {
        switch (ones) {
            case 1:
                return "One";
            case 2:
                return "Two";
            case 3:
                return "Three";
            case 4:
                return "Four";
            case 5:
                return "Five";
            case 6:
                return "Six";
            case 7:
                return "Seven";
            case 8:
                return "Eight";
            case 9:
                return "Nine";
        }
        return "";
    }

    private String translateTens(int tens) {
        switch (tens) {
            case 2:
                return "Twenty";
            case 3:
                return "Thirty";
            case 4:
                return "Forty";
            case 5:
                return "Fifty";
            case 6:
                return "Sixty";
            case 7:
                return "Seventy";
            case 8:
                return "Eighty";
            case 9:
                return "Ninety";
            case 10:
                return "Ten";
            case 11:
                return "Eleven";
            case 12:
                return "Twelve";
            case 13:
                return "Thirteen";
            case 14:
                return "Fourteen";
            case 15:
                return "Fifteen";
            case 16:
                return "Sixteen";
            case 17:
                return "Seventeen";
            case 18:
                return "Eighteen";
            case 19:
                return "Nineteen";
        }
        return "";
    }
}
