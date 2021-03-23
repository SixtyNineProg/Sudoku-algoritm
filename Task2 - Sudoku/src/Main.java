import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        char[][] mas4 = {
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
        char[][] mas1 = {
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
        char[][] mas2 = {
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
        print2Mas(mas2);
        List<Integer>[][] listTable = count(mas2);
        System.out.println(checkSolution(listTable));
    }

    public static List<Integer>[][] count(char[][] table) {
        List<Integer>[][] listTable = convertChar2ArrayToList2Array(table);
        printList2Mas(listTable);

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
        return listTable;
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
}