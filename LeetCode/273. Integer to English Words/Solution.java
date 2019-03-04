public class Solution {
    private static final int HUNDREN_NUM = 100;
    private static final int THOUSAND_NUM = 1000;
    private static final int MILLION_NUM = 1000 * 1000;
    private static final int BILLION_NUM = 1000 * 1000 * 1000;

    public static final String HUNDRED = "Hundred";
    public static final String THOUSAND = "Thousand";
    public static final String MILLION = "Million";
    public static final String BILLION = "Billion";

    //[0-19]
    private static final String[] NUMBERS = {
            "Zero",
            "One",
            "Two",
            "Three",
            "Four",
            "Five",
            "Six",
            "Seven",
            "Eight",
            "Nine",
            "Ten",
            "Eleven",
            "Twelve",
            "Thirteen",
            "Fourteen",
            "Fifteen",
            "Sixteen",
            "Seventeen",
            "Eighteen",
            "Nineteen",
    };

    //20 ... 90
    private static final String[] TEEIS = {
            null,
            null,
            "Twenty",
            "Thirty",
            "Forty",
            "Fifty",
            "Sixty",
            "Seventy",
            "Eighty",
            "Ninety"
    };

    private int mNumber;
    private StringBuilder mStringBuilder = new StringBuilder();

    //One Thousand And One
    //num less than 1000
    private void numToString(int integer) {
        if (integer >= HUNDREN_NUM) {
            int num = integer / HUNDREN_NUM;
            teensToString(num);
            mStringBuilder.append(HUNDRED).append(' ');
            integer -= num * HUNDREN_NUM;
        }
        if (integer >= 20) {
            teeisToString(integer);
            integer %= 10;
        }
        teensToString(integer);
    }

    //0-19
    private void teensToString(int num) {
        if (num != 0) {
            mStringBuilder.append(NUMBERS[num]).append(' ');
        } else if (mNumber == 0) {
            mStringBuilder.append(NUMBERS[0]).append(' ');
        }
    }

    //20 - 90
    private void teeisToString(int num) {
        mStringBuilder.append(TEEIS[num / 10]).append(' ');
    }

    private int partialIntegerToEnglish(int integer, int threshold, String name) {
        if (integer >= threshold) {
            int num = integer / threshold;
            numToString(num);
            integer = integer - num * threshold;
            mStringBuilder.append(name).append(' ');
        }
        return integer;
    }

    public String numberToWords(int integer) {
        mNumber = integer;
        mStringBuilder.delete(0, mStringBuilder.length());
        integer = partialIntegerToEnglish(integer, BILLION_NUM, BILLION);
        integer = partialIntegerToEnglish(integer, MILLION_NUM, MILLION);
        integer = partialIntegerToEnglish(integer, THOUSAND_NUM, THOUSAND);
        numToString(integer);
        if (mStringBuilder.length() > 0) {
            mStringBuilder.deleteCharAt(mStringBuilder.length() - 1);
        }
        return mStringBuilder.toString();
    }

}