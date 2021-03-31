import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class MainTest {
    char[][] easy1Test = {
            {'5', '1', '3', '4', '.', '.', '.', '2', '6'},
            {'.', '.', '4', '7', '5', '2', '.', '1', '.'},
            {'.', '7', '.', '3', '1', '6', '.', '9', '5'},
            {'.', '6', '9', '.', '3', '8', '.', '.', '1'},
            {'2', '.', '1', '5', '.', '.', '.', '6', '3'},
            {'7', '3', '5', '.', '9', '1', '.', '.', '.'},
            {'.', '.', '6', '.', '7', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '4', '.', '.', '.'},
            {'3', '.', '.', '.', '.', '.', '2', '.', '9'},
    };

    char[][] easy1Solution = {
            {'5', '1', '3', '4', '8', '9', '7', '2', '6'},
            {'6', '9', '4', '7', '5', '2', '3', '1', '8'},
            {'8', '7', '2', '3', '1', '6', '4', '9', '5'},
            {'4', '6', '9', '2', '3', '8', '5', '7', '1'},
            {'2', '8', '1', '5', '4', '7', '9', '6', '3'},
            {'7', '3', '5', '6', '9', '1', '8', '4', '2'},
            {'9', '2', '6', '8', '7', '3', '1', '5', '4'},
            {'1', '5', '8', '9', '2', '4', '6', '3', '7'},
            {'3', '4', '7', '1', '6', '5', '2', '8', '9'},
    };

    public static final char[][] expert1Test = {
            {'.', '.', '.', '.', '.', '9', '.', '7', '.'},
            {'7', '.', '6', '.', '.', '1', '.', '.', '.'},
            {'.', '.', '.', '4', '.', '.', '5', '.', '.'},
            {'.', '.', '.', '.', '.', '6', '2', '.', '.'},
            {'6', '.', '7', '.', '.', '.', '.', '.', '4'},
            {'5', '.', '.', '.', '2', '.', '.', '.', '3'},
            {'.', '4', '.', '3', '5', '.', '1', '.', '.'},
            {'.', '.', '9', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '8', '.', '.'},
    };

    public static final char[][] expert1Solution = {
            {'4', '5', '1', '2', '6', '9', '3', '7', '8'},
            {'7', '3', '6', '5', '8', '1', '4', '2', '9'},
            {'9', '8', '2', '4', '7', '3', '5', '6', '1'},
            {'8', '1', '3', '9', '4', '6', '2', '5', '7'},
            {'6', '2', '7', '1', '3', '5', '9', '8', '4'},
            {'5', '9', '4', '7', '2', '8', '6', '1', '3'},
            {'2', '4', '8', '3', '5', '7', '1', '9', '6'},
            {'3', '6', '9', '8', '1', '2', '7', '4', '5'},
            {'1', '7', '5', '6', '9', '4', '8', '3', '2'},
    };

    char[][] expert2Test = {
            {'.', '.', '.', '.', '.', '9', '.', '7', '.'},
            {'7', '.', '6', '.', '.', '1', '.', '.', '.'},
            {'.', '.', '.', '4', '.', '.', '5', '.', '.'},
            {'.', '.', '.', '.', '.', '6', '2', '.', '.'},
            {'6', '.', '7', '.', '.', '.', '.', '.', '4'},
            {'5', '.', '.', '.', '2', '.', '.', '.', '3'},
            {'.', '4', '.', '3', '5', '.', '1', '.', '.'},
            {'.', '.', '9', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '8', '.', '.'},
    };

    public static final char[][] expert2Solution = {
            {'4', '5', '1', '2', '6', '9', '3', '7', '8'},
            {'7', '3', '6', '5', '8', '1', '4', '2', '9'},
            {'9', '8', '2', '4', '7', '3', '5', '6', '1'},
            {'8', '1', '3', '9', '4', '6', '2', '5', '7'},
            {'6', '2', '7', '1', '3', '5', '9', '8', '4'},
            {'5', '9', '4', '7', '2', '8', '6', '1', '3'},
            {'2', '4', '8', '3', '5', '7', '1', '9', '6'},
            {'3', '6', '9', '8', '1', '2', '7', '4', '5'},
            {'1', '7', '5', '6', '9', '4', '8', '3', '2'},
    };

    char[][] expert3Test = {
            {'5', '8', '6', '4', '.', '.', '.', '.', '3'},
            {'.', '.', '.', '.', '8', '.', '.', '.', '4'},
            {'.', '.', '.', '9', '.', '.', '.', '.', '7'},
            {'.', '.', '.', '.', '.', '.', '.', '4', '.'},
            {'.', '.', '.', '.', '.', '9', '7', '2', '.'},
            {'.', '3', '.', '.', '5', '.', '.', '.', '1'},
            {'7', '.', '.', '.', '.', '.', '.', '6', '.'},
            {'.', '5', '.', '.', '3', '2', '.', '.', '.'},
            {'2', '.', '.', '.', '6', '.', '.', '.', '.'},
    };

    public static final char[][] expert3Solution = {
            {'5', '8', '6', '4', '2', '7', '9', '1', '3'},
            {'1', '7', '9', '6', '8', '3', '2', '5', '4'},
            {'3', '2', '4', '9', '1', '5', '6', '8', '7'},
            {'9', '6', '2', '8', '7', '1', '3', '4', '5'},
            {'8', '1', '5', '3', '4', '9', '7', '2', '6'},
            {'4', '3', '7', '2', '5', '6', '8', '9', '1'},
            {'7', '4', '3', '5', '9', '8', '1', '6', '2'},
            {'6', '5', '8', '1', '3', '2', '4', '7', '9'},
            {'2', '9', '1', '7', '6', '4', '5', '3', '8'},
    };

    char[][] anRealTest = {
            {'1', '.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
    };

    public static final char[][] anRealSolution = {
            {'1', '2', '3', '4', '5', '6', '7', '8', '9'},
            {'4', '5', '6', '7', '8', '9', '1', '2', '3'},
            {'7', '8', '9', '1', '2', '3', '4', '5', '6'},
            {'2', '3', '1', '6', '7', '4', '8', '9', '5'},
            {'8', '7', '5', '9', '1', '2', '3', '6', '4'},
            {'6', '9', '4', '5', '3', '8', '2', '1', '7'},
            {'3', '1', '7', '2', '6', '5', '9', '4', '8'},
            {'5', '4', '2', '8', '9', '7', '6', '3', '1'},
            {'9', '6', '8', '3', '4', '1', '5', '7', '2'},
    };

    private final Sudoku sudoku = new Sudoku();

    @Test
    public void whenEasy1DataForMainSetSolutionThenReturnCorrectSolution() {
        List<Integer>[][] actualList;
        List<Integer>[][] expectedList = sudoku.convertChar2ArrayToList2Array(easy1Solution);
        sudoku.setSolution(easy1Test);
        actualList = sudoku.getSolution();
        boolean difference = compare2Lists(actualList, expectedList);
        Assert.assertFalse(difference);
    }

    @Test
    public void whenExpert1DataForMainSetSolutionThenReturnCorrectSolution() {
        List<Integer>[][] actualList;
        List<Integer>[][] expectedList = sudoku.convertChar2ArrayToList2Array(expert1Solution);
        sudoku.setSolution(expert1Test);
        actualList = sudoku.getSolution();
        boolean difference = compare2Lists(actualList, expectedList);
        Assert.assertFalse(difference);
    }

    @Test
    public void whenExpert2DataForMainSetSolutionThenReturnCorrectSolution() {
        List<Integer>[][] actualList;
        List<Integer>[][] expectedList = sudoku.convertChar2ArrayToList2Array(expert2Solution);
        sudoku.setSolution(expert2Test);
        actualList = sudoku.getSolution();
        boolean difference = compare2Lists(actualList, expectedList);
        Assert.assertFalse(difference);
    }

    @Test
    public void whenExpert3DataForMainSetSolutionThenReturnCorrectSolution() {
        List<Integer>[][] actualList;
        List<Integer>[][] expectedList = sudoku.convertChar2ArrayToList2Array(expert3Solution);
        sudoku.setSolution(expert3Test);
        actualList = sudoku.getSolution();
        boolean difference = compare2Lists(actualList, expectedList);
        Assert.assertFalse(difference);
    }

    @Test
    public void whenAnRealDataForMainSetSolutionThenReturnCorrectSolution() {
        List<Integer>[][] actualList;
        List<Integer>[][] expectedList = sudoku.convertChar2ArrayToList2Array(anRealSolution);
        sudoku.setSolution(anRealTest);
        actualList = sudoku.getSolution();
        boolean difference = compare2Lists(actualList, expectedList);
        Assert.assertFalse(difference);
    }

    private boolean compare2Lists(List<Integer>[][] actualList, List<Integer>[][] expectedList) {
        for (int i = 0; i < actualList.length; i++) {
            for (int j = 0; j < actualList[i].length; j++) {
                List<Integer> tmp1 = actualList[i][j];
                List<Integer> tmp2 = expectedList[i][j];
                for (int k = 0; k < tmp1.size(); k++) {
                    if (!tmp1.get(k).equals(tmp2.get(k)))
                        return true;
                }
            }
        }
        return false;
    }
}