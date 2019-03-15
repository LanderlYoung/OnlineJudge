import java.util.LinkedList;
import java.util.List;


class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        LinkedList<String> result = new LinkedList<>();
        TrieNode root = buildTree(words);

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(result, board, i, j, root);
            }
        }
        System.out.println("done");
        return result;
    }

    private void dfs(LinkedList<String> result, char[][] board, int i, int j, TrieNode parent) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return;
        }

        char c = board[i][j];
        if (c == '!') return;

        TrieNode child = parent.children[c - 'a'];
        if (child == null) return;

        if (child.word != null) {
            result.add(child.word);
            child.word = null;
        }

        board[i][j] = '!';
        dfs(result, board, i - 1, j, child);
        dfs(result, board, i + 1, j, child);
        dfs(result, board, i, j - 1, child);
        dfs(result, board, i, j + 1, child);
        board[i][j] = c;
    }

    private TrieNode buildTree(String[] words) {
        TrieNode root = new TrieNode();
        for (String w : words) {
            TrieNode node = root;
            for (int i = 0; i < w.length(); i++) {
                char c = w.charAt(i);
                TrieNode child = node.children[c - 'a'];
                if (child == null) {
                    child = new TrieNode();
                    node.children[c - 'a'] = child;
                }
                node = child;
            }
            node.word = w;
        }
        return root;
    }


    private final class TrieNode {
        public final TrieNode[] children = new TrieNode[26];
        public String word;
    }

    public static void main(String[] args) {
        System.out.println(
                new Solution().findWords(
                        new char[][]{
                                "oaan".toCharArray(),
                                "etae".toCharArray(),
                                "ihkr".toCharArray(),
                                "iflv".toCharArray()
                        },
                        new String[]{
                                "oath", "pea", "eat", "rain"
                        }
                )
        );
    }
}