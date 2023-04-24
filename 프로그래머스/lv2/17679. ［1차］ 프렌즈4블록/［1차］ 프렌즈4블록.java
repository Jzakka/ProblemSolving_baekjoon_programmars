import java.util.Arrays;

class Solution {
    Block[][] blocks;
    int n;
    int m;
    int cnt = 0;

    public int solution(int m, int n, String[] board) {
        this.m = m;
        this.n = n;
        blocks = new Block[n][m];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                blocks[j][i] = new Block(board[i].charAt(j));
            }
        }

        boolean colored;
        do {
            colored = false;
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < m - 1; j++) {
                    colored |= color(i, j);
                }
            }
            erase();
        } while (colored);

        return cnt;
    }

    private void erase() {
        for (int i = 0; i < n ; i++) {
            for (int j = 0; j < m ; j++) {
                if (blocks[i][j].character != 'X' && !blocks[i][j].valid) {
                    blocks[i][j].character = 'X';
                    cnt++;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            Arrays.sort(blocks[i]);
        }
    }

    private boolean color(int i, int j) {
        Character mainColor = blocks[i][j].character;
        if (mainColor == 'X') {
            return false;
        }
        if (mainColor == blocks[i + 1][j].character && mainColor == blocks[i][j + 1].character && mainColor == blocks[i + 1][j + 1].character) {
            blocks[i][j].valid = false;
            blocks[i+1][j].valid = false;
            blocks[i][j+1].valid = false;
            blocks[i + 1][j + 1].valid = false;
            return true;
        }
        return false;
    }

    class Block implements Comparable<Block> {
        boolean valid = true;
        Character character;

        public Block(Character character) {
            this.character = character;
        }

        @Override
        public int compareTo(Block o) {
            return character!='X' ? 0 : -1;
        }
    }
}