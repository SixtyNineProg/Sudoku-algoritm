import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        char[][] masEasy = {
                {'1', '5', '.', '.', '4', '2', '.', '.', '6'},
                {'2', '7', '4', '5', '6', '.', '.', '1', '.'},
                {'.', '.', '6', '.', '.', '7', '4', '.', '2'},
                {'.', '1', '.', '.', '.', '.', '.', '4', '.'},
                {'.', '.', '.', '.', '5', '.', '.', '.', '.'},
                {'.', '6', '.', '4', '.', '3', '1', '9', '.'},
                {'.', '2', '.', '6', '.', '5', '9', '.', '.'},
                {'9', '8', '5', '.', '3', '.', '.', '6', '.'},
                {'.', '4', '.', '2', '1', '9', '8', '3', '.'},
        };
        char[][] masHard = {
                {'.', '.', '.', '5', '.', '6', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '6', '2'},
                {'7', '.', '4', '.', '1', '.', '.', '.', '.'},
                {'9', '.', '.', '.', '4', '3', '.', '.', '7'},
                {'.', '2', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '8', '.', '.', '9', '.', '.', '5', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'3', '.', '.', '.', '.', '8', '7', '.', '.'},
                {'.', '5', '.', '3', '.', '4', '.', '.', '1'},
        };
        char[][] masMedium = {
                {'8', '6', '.', '.', '.', '.', '7', '.', '.'},
                {'.', '.', '.', '2', '7', '.', '6', '9', '.'},
                {'7', '.', '2', '6', '.', '4', '.', '.', '.'},
                {'3', '.', '.', '7', '.', '9', '.', '8', '.'},
                {'.', '7', '6', '.', '1', '3', '5', '.', '.'},
                {'.', '.', '.', '4', '.', '.', '3', '.', '.'},
                {'9', '2', '7', '3', '8', '5', '.', '.', '.'},
                {'.', '.', '8', '.', '.', '.', '.', '3', '5'},
                {'.', '.', '3', '.', '.', '.', '.', '.', '.'},
        };

        print2Mas(masMedium);
        List<Integer>[][] listTable = convertChar2ArrayToList2Array(masMedium);
        count(listTable);
        List<Integer>[][] listTableClone = cloneStructure(listTable);
        ArrayList<Integer> oneSizeList = new ArrayList<>(Collections.singletonList(55));
        listTableClone[0][0] = oneSizeList;
        printList2Mas(listTable);
        printList2Mas(listTableClone);
        if (checkSolution(listTable)) return;
        countNext(listTable);
        printList2Mas(listTable);
    }

    private static void countNext(List<Integer>[][] listTable) {
        int[] minLen = findMinLenNums(listTable);
        int x = minLen[1];
        int y = minLen[2];
        List<Integer> tmpList = listTable[x][y];
        for (Integer num : tmpList) {
            ArrayList<Integer> oneSizeList = new ArrayList<>(Collections.singletonList(num));
            listTable[minLen[1]][minLen[2]] = oneSizeList;
            count(listTable);
            if (isExistMoreThanOne(listTable)) {
                List<Integer>[][] listTableClone = cloneStructure(listTable);
                countNext(listTableClone);
            } else if (!checkSolution(listTable))
                return;
        }
    }

    private static List<Integer>[][] cloneStructure(List<Integer>[][] listTable) {
        List<Integer>[][] listTabClone = new ArrayList[listTable.length][listTable[0].length];
        for (int i = 0; i < listTable.length; i++) {
            for (int j = 0; j < listTable[i].length; j++) {
                ArrayList<Integer> tmpList = (ArrayList<Integer>) listTable[i][j];
                listTabClone[i][j] = (List<Integer>) tmpList.clone();
            }
        }
        return listTabClone;
    }

    private static boolean isExistMoreThanOne(List<Integer>[][] listTable) {
        for (List<Integer>[] lists : listTable)
            for (List<Integer> list : lists)
                if (list.size() > 1) return true;
        return false;
    }

    //minLen: 0 - length, 1 - i, 2 - j
    private static int[] findMinLenNums(List<Integer>[][] listTable) {
        int[] minLen = {10, 0, 0};
        for (int i = 0; i < listTable.length; i++) {
            for (int j = 0; j < listTable[i].length; j++) {
                int tmpLen = listTable[i][j].size();
                if (minLen[0] > tmpLen && tmpLen > 1) {
                    minLen[0] = tmpLen;
                    minLen[1] = i;
                    minLen[2] = j;
                }
            }
        }
        return minLen;
    }

    public static void count(List<Integer>[][] listTable) {
        printList2Mas(listTable);
        startCount(listTable);
        printList2Mas(listTable);

        boolean difference;
        startWork:
        while (true) {
            difference = false;
            for (int i = 0; i < listTable.length; i++) {
                for (int j = 0; j < listTable[i].length; j++) {
                    int tempSize = listTable[i][j].size();
                    if (tempSize != 1) {
                        ArrayList<Integer> nums = (ArrayList<Integer>) listTable[i][j];
                        checkNearSquare(i, j, listTable, nums);
                        if (nums.size() != 1) checkColumn(j, listTable, nums);
                        if (nums.size() != 1) checkLine(i, listTable, nums);
                        listTable[i][j] = nums;
                        if (tempSize != nums.size()) difference = true;
                    }
                }
            }
            if (!difference) break;
            printList2Mas(listTable);

            for (List<Integer>[] lists : listTable) {
                for (List<Integer> list : lists) {
                    if (list.size() != 1) continue startWork;
                }
            }
        }
    }

    private static void startCount(List<Integer>[][] listTable) {
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


    public static void print2Mas(char[][] mas) {
        for (char[] ma : mas) {
            for (char c : ma) {
                System.out.print(c + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void printMas(int[] mas) {
        for (int c : mas) {
            System.out.print(c + "\t");
        }
        System.out.println();
    }

    public static void printList2Mas(List<Integer>[][] listTable) {
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


    public static List<Integer>[][] convertChar2ArrayToList2Array(char[][] table) {
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


    public static void checkNearSquare(int line, int column, List<Integer>[][] listTable, ArrayList<Integer> list) {
        if (line <= 2) {
            if (column <= 2) checkSquare(0, 2, 0, 2, listTable, list); //1
            if (column >= 3 && column <= 5) checkSquare(0, 2, 3, 5, listTable, list); //2
            if (column >= 6 && column <= 8) checkSquare(0, 2, 6, 8, listTable, list); //3
        }
        if (line >= 3 && line <= 5) {
            if (column <= 2) checkSquare(3, 5, 0, 2, listTable, list); //4
            if (column >= 3 && column <= 5) checkSquare(3, 5, 3, 5, listTable, list); //5
            if (column >= 6 && column <= 8) checkSquare(3, 5, 6, 8, listTable, list); //6
        }
        if (line >= 6 && line <= 8) {
            if (column <= 2) checkSquare(6, 8, 0, 2, listTable, list); //7
            if (column >= 3 && column <= 5) checkSquare(6, 8, 3, 5, listTable, list); //8
            if (column >= 6 && column <= 8) checkSquare(6, 8, 6, 8, listTable, list); //9
        }
    }

    public static void checkSquare(int startI, int endI, int startJ, int endJ,
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

    public static void checkColumn(int column, List<Integer>[][] listTable, ArrayList<Integer> list) {
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

    public static void checkLine(int line, List<Integer>[][] listTable, ArrayList<Integer> list) {
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


    public static boolean checkSolution(List<Integer>[][] lists) {
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

    public static void checkSquareSolution(int startI, int endI, int startJ, int endJ,
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

    public static void checkColumnSolution(int column, List<Integer>[][] listTable, ArrayList<Integer> list) {
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

    public static void checkLineSolution(int line, List<Integer>[][] listTable, ArrayList<Integer> list) {
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

    public static boolean checkSquareAndColumnAndLine(int line, int column, List<Integer>[][] lists) {
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

        if (!checkSquare(startI, endI, startJ, endJ, lists)) return false;
        if (!checkColumn(column, lists)) return false;
        return checkLine(line, lists);
    }

    public static boolean checkSquare(int startI, int endI, int startJ, int endJ, List<Integer>[][] listTable) {
        ArrayList<Integer> actualList = new ArrayList<>();
        for (int i = startI; i <= endI; i++) {
            for (int j = startJ; j <= endJ; j++) {
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

    public static boolean checkColumn(int column, List<Integer>[][] listTable) {
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

    public static boolean checkLine(int line, List<Integer>[][] listTable) {
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
}