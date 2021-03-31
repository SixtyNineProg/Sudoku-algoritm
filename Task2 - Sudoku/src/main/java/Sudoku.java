import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sudoku {
    private List<Integer>[][] solution;
    private boolean isSolutionFind = false;

    public void setSolution(char[][] mas) {
        List<Integer>[][] listTable = convertChar2ArrayToList2Array(mas);
        count(listTable);
        if (checkSolution(listTable)) {
            solution = listTable;
            return;
        }
        countNext(listTable);
    }

    public void count(List<Integer>[][] listTable) {
        startCount(listTable);
        continueCount(listTable);
    }

    private void startCount(List<Integer>[][] listTable) {
        for (int i = 0; i < listTable.length; i++) {
            for (int j = 0; j < listTable[i].length; j++) {

                if (listTable[i][j].size() == 1) {
                    if (listTable[i][j].get(0) == 0) {
                        ArrayList<Integer> nums = new ArrayList<>(
                                List.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                        );
                        checkNearSquare(i, j, listTable, nums);
                        if (nums.size() != 1) checkColumn(j, listTable, nums);
                        if (nums.size() != 1) checkLine(i, listTable, nums);
                        listTable[i][j] = nums;
                    }
                }
            }
        }
    }

    private void continueCount(List<Integer>[][] listTable) {
        boolean difference;
        boolean differenceMain;
        do {
            differenceMain = false;
            do {
                difference = false;
                if (makeOptions(listTable)) {
                    difference = true;
                    differenceMain = true;
                }
            } while (difference);

            do {
                difference = false;
                if (checkAllHavingOne(listTable)) {
                    difference = true;
                    differenceMain = true;
                }
            } while (difference);

            do {
                difference = false;
                if (findIdenticalElementsWithSize2(listTable)) {
                    difference = true;
                    differenceMain = true;
                }
            } while (difference);

        } while (differenceMain);
    }

    private void countNext(List<Integer>[][] listTable) {
        int[] minLen = findMinLenNums(listTable);
        int x = minLen[0];
        int y = minLen[1];
        List<Integer> tmpList = listTable[x][y];
        List<Integer>[][] tempListTable = cloneStructure(listTable);
        for (Integer num : tmpList) {
            if (isSolutionFind) return;
            listTable = cloneStructure(tempListTable); //return step

            ArrayList<Integer> oneSizeList = new ArrayList<>(Collections.singletonList(num));
            listTable[minLen[0]][minLen[1]] = oneSizeList;

            count(listTable);

            if (!checkAll(listTable))
                continue;

            if (isExistMoreThanOne(listTable)) {
                List<Integer>[][] listTableClone = cloneStructure(listTable);
                countNext(listTableClone);
            } else if (checkSolution(listTable)) {
                solution = listTable;
                isSolutionFind = true;
            }
        }
    }


    private List<Integer>[][] cloneStructure(List<Integer>[][] listTable) {
        List<Integer>[][] listTabClone = new ArrayList[listTable.length][listTable[0].length];
        for (int i = 0; i < listTable.length; i++) {
            for (int j = 0; j < listTable[i].length; j++) {
                ArrayList<Integer> tmpList = (ArrayList<Integer>) listTable[i][j];
                listTabClone[i][j] = (List<Integer>) tmpList.clone();
            }
        }
        return listTabClone;
    }

    private boolean isExistMoreThanOne(List<Integer>[][] listTable) {
        for (List<Integer>[] lists : listTable)
            for (List<Integer> list : lists)
                if (list.size() > 1) return true;
        return false;
    }

    private int[] findMinLenNums(List<Integer>[][] listTable) {
        int[] minLen = {0, 0};
        int minLength = 10;
        for (int i = 0; i < listTable.length; i++) {
            for (int j = 0; j < listTable[i].length; j++) {
                int tmpLen = listTable[i][j].size();
                if (minLength > tmpLen && tmpLen > 1) {
                    minLength = tmpLen;
                    minLen[0] = i;
                    minLen[1] = j;
                }
            }
        }
        return minLen;
    }

    public List<Integer>[][] convertChar2ArrayToList2Array(char[][] table) {
        ArrayList<Integer>[][] listTab = new ArrayList[table.length][table[0].length];
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                if (table[i][j] == '.') {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(0);
                    listTab[i][j] = list;
                } else {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(Integer.parseInt(String.valueOf(table[i][j])));
                    listTab[i][j] = list;
                }
            }
        }
        return listTab;
    }

    private int[] searchSquareCoordinates(int line, int column) {
        //0-startI, 1-endI, 2-startJ, 3-endJ
        int[] coordinates = new int[4];

        int startI = -1;
        int endI = -1;
        int startJ = -1;
        int endJ = -1;

        if (line <= 2) {
            startI = 0;
            endI = 2;
            if (column <= 2) {
                startJ = 0;
                endJ = 2;
            }
            if (column >= 3 && column <= 5) {
                startJ = 3;
                endJ = 5;
            }
            if (column >= 6 && column <= 8) {
                startJ = 6;
                endJ = 8;
            }
        }
        if (line >= 3 && line <= 5) {
            startI = 3;
            endI = 5;
            if (column <= 2) {
                startJ = 0;
                endJ = 2;
            }
            if (column >= 3 && column <= 5) {
                startJ = 3;
                endJ = 5;
            }
            if (column >= 6 && column <= 8) {
                startJ = 6;
                endJ = 8;
            }
        }
        if (line >= 6 && line <= 8) {
            startI = 6;
            endI = 8;
            if (column <= 2) {
                startJ = 0;
                endJ = 2;
            }
            if (column >= 3 && column <= 5) {
                startJ = 3;
                endJ = 5;
            }
            if (column >= 6 && column <= 8) {
                startJ = 6;
                endJ = 8;
            }
        }

        coordinates[0] = startI;
        coordinates[1] = endI;
        coordinates[2] = startJ;
        coordinates[3] = endJ;

        return coordinates;
    }


    public void print2Mas(char[][] mas) {
        for (char[] ma : mas) {
            for (char c : ma) {
                System.out.print(c + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void printList2Mas(List<Integer>[][] listTable) {
        for (List<Integer>[] lists : listTable) {
            for (List<Integer> list : lists) {
                StringBuilder stringBuilder = new StringBuilder();
                for (Integer num : list) {
                    stringBuilder.append(num);
                }
                System.out.format("%15s", stringBuilder);
            }
            System.out.println();
        }
        System.out.println();
    }

    public void printList2MasSolution(List<Integer>[][] listTable) {
        for (List<Integer>[] lists : listTable) {
            for (List<Integer> list : lists) {
                StringBuilder stringBuilder = new StringBuilder();
                for (Integer num : list) {
                    stringBuilder.append(num);
                }
                System.out.print(stringBuilder + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }


    private boolean makeOptions(List<Integer>[][] listTable) {
        boolean difference = false;
        for (int i = 0; i < listTable.length; i++) {
            for (int j = 0; j < listTable[i].length; j++) {
                int tempSize = listTable[i][j].size();
                if (tempSize != 1) {
                    ArrayList<Integer> nums = (ArrayList<Integer>) listTable[i][j];
                    checkNearSquare(i, j, listTable, nums);
                    checkColumn(j, listTable, nums);
                    checkLine(i, listTable, nums);
                    listTable[i][j] = nums;
                    if (tempSize != nums.size())
                        difference = true;
                }
            }
        }
        return difference;
    }

    public void checkNearSquare(int line, int column, List<Integer>[][] listTable, ArrayList<Integer> list) {
        int[] coordinates = searchSquareCoordinates(line, column);
        checkSquare(coordinates[0], coordinates[1], coordinates[2], coordinates[3], listTable, list);
    }

    public void checkSquare(int startI, int endI, int startJ, int endJ,
                                   List<Integer>[][] listTable, ArrayList<Integer> list) {
        mainNum:
        for (int k = 0; k < list.size(); k++) {
            if (list.size() != 1) {
                Integer tempInt = list.get(k);
                for (int i = startI; i <= endI; i++) {
                    for (int j = startJ; j <= endJ; j++) {
                        if (listTable[i][j].size() == 1) {
                            if (tempInt.equals(listTable[i][j].get(0))) {
                                list.remove(tempInt);
                                k--;
                                continue mainNum;
                            }
                        }
                    }
                }
            } else return;
        }
    }

    public void checkColumn(int column, List<Integer>[][] listTable, ArrayList<Integer> list) {
        mainNum:
        for (int k = 0; k < list.size(); k++) {
            if (list.size() != 1) {
                Integer tempInt = list.get(k);
                for (List<Integer>[] lists : listTable) {
                    if (lists[column].size() == 1) {
                        for (Integer num : lists[column]) {
                            if (tempInt.equals(num)) {
                                list.remove(tempInt);
                                k--;
                                continue mainNum;
                            }
                        }
                    }
                }
            } else return;
        }
    }

    public void checkLine(int line, List<Integer>[][] listTable, ArrayList<Integer> list) {
        mainNum:
        for (int k = 0; k < list.size(); k++) {
            if (list.size() != 1) {
                Integer tempInt = list.get(k);
                for (int i = 0; i < listTable[line].length; i++) {
                    if (listTable[line][i].size() == 1) {
                        for (Integer num : listTable[line][i]) {
                            if (tempInt.equals(num)) {
                                list.remove(tempInt);
                                k--;
                                continue mainNum;
                            }
                        }
                    }
                }
            } else return;
        }
    }


    public boolean checkSolution(List<Integer>[][] lists) {
        int sizeBlock = 3;
        int startI = 0;
        int endI = sizeBlock;
        int startJ = 0;
        int endJ = sizeBlock;
        ArrayList<Integer> nums;

        for (int n = 0; n < sizeBlock; n++) {
            for (int l = 0; l < sizeBlock; l++) {
                nums = new ArrayList<>(
                        List.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                );
                checkSquareSolution(startI, endI, startJ, endJ, lists, nums);
                if (nums.size() != 0) return false;
                startJ += sizeBlock;
                endJ += sizeBlock;
            }
            startI += sizeBlock;
            endI += sizeBlock;
            startJ = 0;
            endJ = sizeBlock;
        }

        for (int i = 0; i < lists[0].length; i++) {
            nums = new ArrayList<>(
                    List.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
            );
            checkColumnSolution(i, lists, nums);
            if (nums.size() != 0) return false;
        }

        for (int i = 0; i < lists.length; i++) {
            nums = new ArrayList<>(
                    List.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
            );
            checkLineSolution(i, lists, nums);
            if (nums.size() != 0) return false;
        }

        return true;
    }

    public void checkSquareSolution(int startI, int endI, int startJ, int endJ,
                                           List<Integer>[][] listTable, ArrayList<Integer> list) {
        mainNum:
        for (int k = 0; k < list.size(); k++) {
            Integer tempInt = list.get(k);
            for (int i = startI; i < endI; i++) {
                for (int j = startJ; j < endJ; j++) {
                    if (listTable[i][j].size() == 1) {
                        if (tempInt.equals(listTable[i][j].get(0))) {
                            list.remove(tempInt);
                            k--;
                            continue mainNum;
                        }
                    } else return;
                }
            }
        }
    }

    public void checkColumnSolution(int column, List<Integer>[][] listTable, ArrayList<Integer> list) {
        mainNum:
        for (int k = 0; k < list.size(); k++) {
            Integer tempInt = list.get(k);
            for (List<Integer>[] lists : listTable) {
                if (lists[column].size() == 1) {
                    for (Integer num : lists[column]) {
                        if (tempInt.equals(num)) {
                            list.remove(tempInt);
                            k--;
                            continue mainNum;
                        }
                    }
                } else return;
            }
        }
    }

    public void checkLineSolution(int line, List<Integer>[][] listTable, ArrayList<Integer> list) {
        mainNum:
        for (int k = 0; k < list.size(); k++) {
            Integer tempInt = list.get(k);
            for (int i = 0; i < listTable[line].length; i++) {
                if (listTable[line][i].size() == 1) {
                    for (Integer num : listTable[line][i]) {
                        if (tempInt.equals(num)) {
                            list.remove(tempInt);
                            k--;
                            continue mainNum;
                        }
                    }
                } else return;
            }
        }
    }


    public boolean checkAll(List<Integer>[][] lists) {
        for (int i = 0; i < lists.length; i++) {
            if (!checkLine(i, lists))
                return false;
        }

        for (int i = 0; i < lists[0].length; i++) {
            if (!checkColumn(i, lists))
                return false;
        }

        for (int i = 0; i < lists.length; i++) {
            for (int j = 0; j < lists[i].length; j++) {
                if (!checkSquare(i, j, lists))
                    return false;
            }
        }
        return true;
    }

    public boolean checkSquare(int line, int column, List<Integer>[][] listTable) {
        int[] coordinates = searchSquareCoordinates(line, column);

        ArrayList<Integer> actualList = new ArrayList<>();
        for (int i = coordinates[0]; i <= coordinates[1]; i++) {
            for (int j = coordinates[2]; j <= coordinates[3]; j++) {
                if (listTable[i][j].size() == 0) return false;
                if (listTable[i][j].size() == 1) {
                    Integer tmpNum = listTable[i][j].get(0);
                    if (actualList.indexOf(tmpNum) == -1) {
                        actualList.add(tmpNum);
                    } else return false;
                }
            }
        }
        return true;
    }

    public boolean checkColumn(int column, List<Integer>[][] listTable) {
        ArrayList<Integer> actualList = new ArrayList<>();
        for (List<Integer>[] lists : listTable) {
            if (lists[column].size() == 0) return false;
            if (lists[column].size() == 1) {
                Integer tmpNum = lists[column].get(0);
                if (actualList.indexOf(tmpNum) == -1) {
                    actualList.add(tmpNum);
                } else return false;
            }
        }
        return true;
    }

    public boolean checkLine(int line, List<Integer>[][] listTable) {
        ArrayList<Integer> actualList = new ArrayList<>();
        for (int i = 0; i < listTable[line].length; i++) {
            if (listTable[line][i].size() == 0) return false;
            if (listTable[line][i].size() == 1) {
                Integer tmpNum = listTable[line][i].get(0);
                if (actualList.indexOf(tmpNum) == -1) {
                    actualList.add(tmpNum);
                } else return false;
            }
        }
        return true;
    }


    public boolean checkAllHavingOne(List<Integer>[][] lists) {
        for (int i = 0; i < lists.length; i++) {
            if (checkLineHavingOne(i, lists))
                return true;
        }

        for (int i = 0; i < lists[0].length; i++) {
            if (checkColumnHavingOne(i, lists))
                return true;
        }

        int sizeBlock = 3;
        int startI = 0;
        int endI = sizeBlock;
        int startJ = 0;
        int endJ = sizeBlock;

        for (int n = 0; n < sizeBlock; n++) {
            for (int l = 0; l < sizeBlock; l++) {
                if (checkSquareHavOne(startI, endI, startJ, endJ, lists))
                    return true;
                startJ += sizeBlock;
                endJ += sizeBlock;
            }
            startI += sizeBlock;
            endI += sizeBlock;
            startJ = 0;
            endJ = sizeBlock;
        }
        return false;
    }

    public boolean checkSquareHavOne(int startI, int endI, int startJ, int endJ, List<Integer>[][] listTable) {
        int matches;
        int x, y;

        mainLoop:
        for (int i = 1; i <= 9; i++) {
            matches = 0;
            x = -1;
            y = -1;
            for (int j = startI; j < endI; j++) {
                for (int k = startJ; k < endJ; k++) {
                    List<Integer> tmpList = listTable[j][k];
                    boolean exist = tmpList.stream().anyMatch(Integer.valueOf(i)::equals);
                    if (exist) {
                        matches++;
                        if (matches > 1)
                            continue mainLoop;
                        x = j;
                        y = k;
                    }
                }
            }
            if (matches == 1) {
                List<Integer> tmpList = listTable[x][y];
                if (tmpList.size() == 1 && tmpList.get(0) == i) continue;
                ArrayList<Integer> oneSizeList = new ArrayList<>(Collections.singletonList(i));
                listTable[x][y] = oneSizeList;
                return true;
            }
        }
        return false;
    }

    public boolean checkColumnHavingOne(int column, List<Integer>[][] listTable) {
        int matches;
        int x, y;

        mainLoop:
        for (int i = 1; i <= 9; i++) {
            matches = 0;
            x = -1;
            y = -1;
            for (int j = 0; j < listTable.length; j++) {
                List<Integer> tmpList = listTable[j][column];
                boolean exist = tmpList.stream().anyMatch(Integer.valueOf(i)::equals);
                if (exist) {
                    matches++;
                    if (matches > 1)
                        continue mainLoop;
                    x = j;
                    y = column;
                }
            }
            if (matches == 1) {
                List<Integer> tmpList = listTable[x][y];
                if (tmpList.size() == 1 && tmpList.get(0) == i) continue;
                ArrayList<Integer> oneSizeList = new ArrayList<>(Collections.singletonList(i));
                listTable[x][y] = oneSizeList;
                return true;
            }
        }
        return false;
    }

    public boolean checkLineHavingOne(int line, List<Integer>[][] listTable) {
        int matches;
        int x, y;

        mainLoop:
        for (int i = 1; i <= 9; i++) {
            matches = 0;
            x = -1;
            y = -1;
            for (int j = 0; j < listTable[line].length; j++) {
                List<Integer> tmpList = listTable[line][j];
                boolean exist = tmpList.stream().anyMatch(Integer.valueOf(i)::equals);
                if (exist) {
                    matches++;
                    if (matches > 1)
                        continue mainLoop;
                    x = line;
                    y = j;
                }
            }
            if (matches == 1) {
                List<Integer> tmpList = listTable[x][y];
                if (tmpList.size() == 1 && tmpList.get(0) == i) continue;
                ArrayList<Integer> oneSizeList = new ArrayList<>(Collections.singletonList(i));
                listTable[x][y] = oneSizeList;
                return true;
            }
        }
        return false;
    }


    private boolean findIdenticalElementsWithSize2(List<Integer>[][] listTable) {
        for (int i = 0; i < listTable[0].length; i++)
            if (findIdenticalElementsWithSize2Column(i, listTable))
                return true;

        for (int i = 0; i < listTable.length; i++)
            if (findIdenticalElementsWithSize2Line(i, listTable))
                return true;


        int sizeBlock = 3;
        int startI = 0;
        int endI = sizeBlock;
        int startJ = 0;
        int endJ = sizeBlock;

        for (int n = 0; n < sizeBlock; n++) {
            for (int l = 0; l < sizeBlock; l++) {
                if (findIdenticalElementsWithSize2Square(startI, endI, startJ, endJ, listTable))
                    return true;
                startJ += sizeBlock;
                endJ += sizeBlock;
            }
            startI += sizeBlock;
            endI += sizeBlock;
            startJ = 0;
            endJ = sizeBlock;
        }
        return false;
    }

    private boolean findIdenticalElementsWithSize2Square(int startI, int endI, int startJ, int endJ,
                                                                List<Integer>[][] listTable) {
        boolean difference = false;
        for (int i = startI; i < endI; i++) {
            for (int j = startJ; j < endJ; j++) {
                if (listTable[i][j].size() == 2) {
                    List<Integer> tmpList1 = listTable[i][j];
                    for (int k = i; k < endI; k++) {
                        for (int l = j; l < endJ; l++) {
                            if (i == k && l == j) continue;
                            if (listTable[k][l].size() == 2) {
                                List<Integer> tmpList2 = listTable[k][l];
                                if (tmpList1.equals(tmpList2))
                                    if (removeDuplicatesInGroupSquare(startI, endI, startJ, endJ, tmpList1, listTable,
                                            i, j, k, l))
                                        difference = true;
                            }
                        }
                    }
                }
            }
        }
        return difference;
    }

    private boolean findIdenticalElementsWithSize2Column(int column, List<Integer>[][] listTable) {
        boolean difference = false;
        mainLoop:
        for (int i = 0; i < listTable.length; i++) {
            if (listTable[i][column].size() == 2) {
                List<Integer> tmpList1 = listTable[i][column];
                for (int k = i + 1; k < listTable.length; k++) {
                    if (listTable[k][column].size() == 2) {
                        List<Integer> tmpList2 = listTable[k][column];
                        if (tmpList1.equals(tmpList2)) {
                            if (removeDuplicatesInGroupColumn(column, tmpList1, listTable, i, k))
                                difference = true;
                            continue mainLoop;
                        }
                    }
                }
            }
        }
        return difference;
    }

    private boolean findIdenticalElementsWithSize2Line(int line, List<Integer>[][] listTable) {
        boolean difference = false;
        mainLoop:
        for (int i = 0; i < listTable[line].length; i++) {
            if (listTable[line][i].size() == 2) {
                List<Integer> tmpList1 = listTable[line][i];
                for (int l = i + 1; l < listTable[line].length; l++) {
                    if (listTable[line][l].size() == 2) {
                        List<Integer> tmpList2 = listTable[line][l];
                        if (tmpList1.equals(tmpList2)) {
                            if (removeDuplicatesInGroupLine(line, tmpList1, listTable, i, l))
                                difference = true;
                            continue mainLoop;
                        }
                    }
                }
            }
        }
        return difference;
    }


    private boolean removeDuplicatesInGroupSquare(int startI, int endI, int startJ, int endJ,
                                                         List<Integer> nums, List<Integer>[][] listTable,
                                                         int save1I, int save1J, int save2I, int save2J) {
        boolean difference = false;
        for (int i = startI; i < endI; i++) {
            for (int j = startJ; j < endJ; j++) {
                if ((i == save1I && j == save1J) || (i == save2I && j == save2J)) continue;
                for (Integer num : nums)
                    if (listTable[i][j].remove(num))
                        difference = true;
            }
        }
        return difference;
    }

    private boolean removeDuplicatesInGroupLine(int line, List<Integer> nums, List<Integer>[][]
            listTable,
                                                       int saveNum1, int saveNum2) {
        boolean difference = false;
        for (int i = 0; i < listTable[line].length; i++) {
            if (i == saveNum1 || i == saveNum2) continue;
            for (Integer num : nums)
                if (listTable[line][i].remove(num))
                    difference = true;
        }
        return difference;
    }

    private boolean removeDuplicatesInGroupColumn(int column, List<Integer> nums, List<Integer>[][]
            listTable,
                                                         int saveNum1, int saveNum2) {
        boolean difference = false;
        for (int i = 0; i < listTable.length; i++) {
            if (i == saveNum1 || i == saveNum2) continue;
            for (Integer num : nums)
                if (listTable[i][column].remove(num))
                    difference = true;
        }
        return difference;
    }

    public List<Integer>[][] getSolution() {
        return solution;
    }
}