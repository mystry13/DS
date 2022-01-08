package graph;

/*
Given a world map. Containing either water bodies or continents. Device and implement a means, to get the size of the largest water body.

Input: 2D dimensional array with 1 and 0.
	* 1 representing water-body
	* 0 representing land-mass

0 0 0 0 0
0 0 0 0 0
0 0 0 0 1

Ans: 1

1 2 0 0 0
0 3 0 0 0
0 4 0 0 1


there?

*/
public class WMap {
    int[][] map;
    int rows;
    int columns;

    WMap(int[][] wMap) {
        map = wMap;
        rows = wMap.length;
        columns = wMap[0].length;
    }

    public int findLargestWaterBody() {
        int[][] myWorld = getCopyOfMap();
        int[][] result = new int[rows][columns];
        // iterating over map
        for (int i = 0; i < myWorld.length; i++) {
            for (int j = 0; j < myWorld[0].length; j++) {
                if (myWorld[i][j] == 1) {
                    if (checkRowColValid(i, j, "RC")) {
                        result[i][j] = Math.max(result[i - 1][j], result[i][j - 1]) + 1;
                    } else if (checkRowColValid(i, j, "C")) {
                        result[i][j] = result[i][j - 1] + 1;
                    } else if (checkRowColValid(i, j, "R")) {
                        result[i][j] = result[i - 1][j] + 1;
                    } else {
                        result[i][j] = myWorld[i][j];
                    }
                } else if (myWorld[i][j] == 0) {
                    result[i][j] = 0;
                }
            }
        }
        printMap(result);
        int maxWbody = findMax(result);
        return maxWbody;
    }

    private boolean checkRowColValid(int row, int col, String state) {
        boolean isValid = false;
        switch (state) {
            case "RC":
                isValid = !isOutOfBoundsRow(row - 1) && !isOutOfBoundsCol(col - 1);
                break;
            case "R":
                isValid = !isOutOfBoundsRow(row - 1) && isOutOfBoundsCol(col - 1);
                break;
            case "C":
                isValid = isOutOfBoundsRow(row - 1) && !isOutOfBoundsCol(col - 1);
                break;
        }
        return isValid;
    }

    private int findMax(int[][] myRes) {
        int max = 0;

        for (int i = 0; i < myRes.length; i++) {
            for (int j = 0; j < myRes[0].length; j++) {
                max = Math.max(myRes[i][j], max);
            }
        }
        return max;
    }

    private boolean isOutOfBoundsRow(int row) {
        if (row >= 0 && row < rows) {
            return false;
        }
        return true;
    }

    private boolean isOutOfBoundsCol(int col) {
        if (col >= 0 && col < columns) {
            return false;
        }
        return true;
    }

    private void printMap(int[][] inp) {
        for (int i = 0; i < inp.length; i++) {
            for (int j = 0; j < inp[0].length; j++) {
                System.out.print(inp[i][j] + " ");
            }
            System.out.println();
        }
    }

    private int[][] getCopyOfMap() {
        //checks for map
        int[][] world = new int[rows][columns];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                world[i][j] = map[i][j];
            }
        }
        return world;
    }

    public static void main(String[] args) {
        int[][] mat = {{1,1,0,0},
                       {0,1,0,0}};
        WMap mapObj = new WMap(mat);
        System.out.println("Largest water-body is of size : " + mapObj.findLargestWaterBody());
    }
}
