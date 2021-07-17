package problem68;

import java.util.ArrayList;
import java.util.List;

public class Solution {


    private static String justify(String[] words, int start, int wordCount, int totalLineLength, int maxWidth) {
        final StringBuilder sb = new StringBuilder(maxWidth);

        //gap number
        int gapCount = wordCount - 1;
        int totalSpaceNum = maxWidth - totalLineLength;
        for (int i = 0; i < wordCount; i++) {
            sb.append(words[start + i]);
            if (gapCount > 0) {
                //append space
                int spaceNum;
                if (totalSpaceNum % gapCount != 0) {
                    spaceNum = totalSpaceNum / gapCount + 1;
                } else {
                    spaceNum = totalSpaceNum / gapCount;
                }
                gapCount--;
                totalSpaceNum -= spaceNum;
                appendSpace(sb, spaceNum);
            } else if (gapCount == 0) {
                appendSpace(sb, totalSpaceNum);
            }
        }
        return sb.toString();
    }

    private static String plainify(String[] words, int start, int wordCount, int maxWidth) {
        final StringBuilder sb = new StringBuilder(maxWidth);
        for (int i = 0; i < wordCount; i++) {
            sb.append(words[start + i]).append(' ');
        }
        final int len = sb.length();
        if (len <= maxWidth) {
            appendSpace(sb, maxWidth - len);
        } else {
            sb.delete(len - 1, len);
        }
        return sb.toString();
    }

    public static List<String> fullJustify(String[] words, int maxWidth) {
        final List<String> result = new ArrayList<>();

        int lineLength = 0;
        int start = 0;
        int end = 0;
        while (end < words.length) {
            lineLength += words[end].length();
            int wordCount = end - start + 1;

            /*take space BETWEEN word into account*/
            final int lineWithSpace = lineLength + wordCount - 1;

            final boolean meetGreedyCondition = lineWithSpace > maxWidth;
            final boolean noWordLeft = end >= words.length - 1;
            if ((meetGreedyCondition || noWordLeft)) {
                if (meetGreedyCondition) {
                    //back one word
                    lineLength -= words[end--].length();
                    wordCount--;
                }

                if (meetGreedyCondition) {
                    result.add(justify(words, start, wordCount, lineLength, maxWidth));
                } else {
                    result.add(plainify(words, start, wordCount, maxWidth));
                }

                lineLength = 0;
                start = ++end;
            } else {
                ++end;
            }
        }
        return result;
    }

    private static void appendSpace(StringBuilder sb, int spaceNum) {
        for (int i = 0; i < spaceNum; i++) {
            sb.append(' ');
        }
    }

}