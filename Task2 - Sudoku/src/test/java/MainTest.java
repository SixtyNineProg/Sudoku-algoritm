import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainTest {

    public static final char[][] mas1Test = {
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

    public static final char[][] correctTable = {
            {'1', '5', '9', '3', '4', '2', '7', '8', '6'},
            {'2', '7', '4', '5', '6', '8', '3', '1', '9'},
            {'8', '3', '6', '1', '9', '7', '4', '5', '2'},
            {'7', '1', '8', '9', '2', '6', '5', '4', '3'},
            {'4', '9', '3', '8', '5', '1', '6', '2', '7'},
            {'5', '6', '2', '4', '7', '3', '1', '9', '8'},
            {'3', '2', '1', '6', '8', '5', '9', '7', '4'},
            {'9', '8', '5', '7', '3', '4', '2', '6', '1'},
            {'6', '4', '7', '2', '1', '9', '8', '3', '5'},
    };

    public static final char[][] incorrectTable1 = {
            {'2', '5', '9', '3', '4', '2', '7', '8', '6'},
            {'2', '7', '4', '5', '6', '8', '3', '1', '9'},
            {'8', '3', '6', '1', '9', '7', '4', '5', '2'},
            {'7', '1', '8', '9', '2', '6', '5', '4', '3'},
            {'4', '9', '3', '8', '5', '1', '6', '2', '7'},
            {'5', '6', '2', '4', '7', '3', '1', '9', '8'},
            {'3', '2', '1', '6', '8', '5', '9', '7', '4'},
            {'9', '8', '5', '7', '3', '4', '2', '6', '1'},
            {'6', '4', '7', '2', '1', '9', '8', '3', '5'},
    };

    public static final ArrayList<Integer>[][] listTable1Test = new ArrayList[][]{
            {new ArrayList<>(Collections.singletonList(1)), new ArrayList<>(Collections.singletonList(5)),
                    new ArrayList<>(Collections.singletonList(0)), new ArrayList<>(Collections.singletonList(0)),
                    new ArrayList<>(Collections.singletonList(4)), new ArrayList<>(Collections.singletonList(2)),
                    new ArrayList<>(Collections.singletonList(0)), new ArrayList<>(Collections.singletonList(0)),
                    new ArrayList<>(Collections.singletonList(6))
            },
            {new ArrayList<>(Collections.singletonList(2)), new ArrayList<>(Collections.singletonList(7)),
                    new ArrayList<>(Collections.singletonList(4)), new ArrayList<>(Collections.singletonList(5)),
                    new ArrayList<>(Collections.singletonList(6)), new ArrayList<>(Collections.singletonList(0)),
                    new ArrayList<>(Collections.singletonList(0)), new ArrayList<>(Collections.singletonList(1)),
                    new ArrayList<>(Collections.singletonList(0))
            },
            {new ArrayList<>(Collections.singletonList(0)), new ArrayList<>(Collections.singletonList(0)),
                    new ArrayList<>(Collections.singletonList(6)), new ArrayList<>(Collections.singletonList(0)),
                    new ArrayList<>(Collections.singletonList(0)), new ArrayList<>(Collections.singletonList(7)),
                    new ArrayList<>(Collections.singletonList(4)), new ArrayList<>(Collections.singletonList(0)),
                    new ArrayList<>(Collections.singletonList(2))
            },
            {new ArrayList<>(Collections.singletonList(0)), new ArrayList<>(Collections.singletonList(1)),
                    new ArrayList<>(Collections.singletonList(0)), new ArrayList<>(Collections.singletonList(0)),
                    new ArrayList<>(Collections.singletonList(0)), new ArrayList<>(Collections.singletonList(0)),
                    new ArrayList<>(Collections.singletonList(0)), new ArrayList<>(Collections.singletonList(4)),
                    new ArrayList<>(Collections.singletonList(0))
            },
            {new ArrayList<>(Collections.singletonList(0)), new ArrayList<>(Collections.singletonList(0)),
                    new ArrayList<>(Collections.singletonList(0)), new ArrayList<>(Collections.singletonList(0)),
                    new ArrayList<>(Collections.singletonList(5)), new ArrayList<>(Collections.singletonList(0)),
                    new ArrayList<>(Collections.singletonList(0)), new ArrayList<>(Collections.singletonList(0)),
                    new ArrayList<>(Collections.singletonList(0))
            },
            {new ArrayList<>(Collections.singletonList(0)), new ArrayList<>(Collections.singletonList(6)),
                    new ArrayList<>(Collections.singletonList(0)), new ArrayList<>(Collections.singletonList(4)),
                    new ArrayList<>(Collections.singletonList(0)), new ArrayList<>(Collections.singletonList(3)),
                    new ArrayList<>(Collections.singletonList(1)), new ArrayList<>(Collections.singletonList(9)),
                    new ArrayList<>(Collections.singletonList(0))
            },
            {new ArrayList<>(Collections.singletonList(0)), new ArrayList<>(Collections.singletonList(2)),
                    new ArrayList<>(Collections.singletonList(0)), new ArrayList<>(Collections.singletonList(6)),
                    new ArrayList<>(Collections.singletonList(0)), new ArrayList<>(Collections.singletonList(5)),
                    new ArrayList<>(Collections.singletonList(9)), new ArrayList<>(Collections.singletonList(0)),
                    new ArrayList<>(Collections.singletonList(0))
            },
            {new ArrayList<>(Collections.singletonList(9)), new ArrayList<>(Collections.singletonList(8)),
                    new ArrayList<>(Collections.singletonList(5)), new ArrayList<>(Collections.singletonList(0)),
                    new ArrayList<>(Collections.singletonList(3)), new ArrayList<>(Collections.singletonList(0)),
                    new ArrayList<>(Collections.singletonList(0)), new ArrayList<>(Collections.singletonList(6)),
                    new ArrayList<>(Collections.singletonList(0))
            },
            {new ArrayList<>(Collections.singletonList(0)), new ArrayList<>(Collections.singletonList(4)),
                    new ArrayList<>(Collections.singletonList(0)), new ArrayList<>(Collections.singletonList(2)),
                    new ArrayList<>(Collections.singletonList(1)), new ArrayList<>(Collections.singletonList(9)),
                    new ArrayList<>(Collections.singletonList(8)), new ArrayList<>(Collections.singletonList(3)),
                    new ArrayList<>(Collections.singletonList(0))
            },
    };

    @Test
    public void testConvertChar2ArrayToList2Array() {
        boolean difference = false;
        ArrayList<Integer>[][] actualList = (ArrayList<Integer>[][]) Main.convertChar2ArrayToList2Array(mas1Test);
        for (int i = 0; i < actualList.length; i++) {
            for (int j = 0; j < actualList[i].length; j++) {
                ArrayList<Integer> tmp1 = actualList[i][j];
                ArrayList<Integer> tmp2 = listTable1Test[i][j];
                for (int k = 0; k < tmp1.size(); k++) {
                    if (!tmp1.get(k).equals(tmp2.get(k))) {
                        difference = true;
                        break;
                    }
                }
            }
            Assert.assertFalse(difference);
        }
    }

    @Test
    public void testCount() {
        boolean difference = false;
        ArrayList<Integer>[][] actualList = (ArrayList<Integer>[][]) Main.convertChar2ArrayToList2Array(mas1Test);
        ArrayList<Integer>[][] expectedList = (ArrayList<Integer>[][]) Main.convertChar2ArrayToList2Array(correctTable);
        Main.count(actualList);
        for (int i = 0; i < actualList.length; i++) {
            for (int j = 0; j < actualList[i].length; j++) {
                ArrayList<Integer> tmp1 = actualList[i][j];
                ArrayList<Integer> tmp2 = expectedList[i][j];
                for (int k = 0; k < tmp1.size(); k++) {
                    if (!tmp1.get(k).equals(tmp2.get(k))) {
                        difference = true;
                        break;
                    }
                }
            }
        }
        Assert.assertFalse(difference);
    }

    @Test
    public void whenCorrectDataForCheckSquareThenReturnTrue() {
        List<Integer>[][] actualList = Main.convertChar2ArrayToList2Array(correctTable);
        boolean result = Main.checkSquare(0, 2, 0, 2, actualList);
        Assert.assertTrue(result);
    }

    @Test
    public void whenIncorrectDataForCheckSquareThenReturnFalse() {
        List<Integer>[][] actualList = Main.convertChar2ArrayToList2Array(incorrectTable1);
        boolean result = Main.checkSquare(0, 2, 0, 2, actualList);
        Assert.assertFalse(result);
    }

    @Test
    public void whenCorrectDataForCheckColumnThenReturnTrue() {
        List<Integer>[][] actualList = Main.convertChar2ArrayToList2Array(correctTable);
        boolean result = Main.checkColumn(0,  actualList);
        Assert.assertTrue(result);
    }

    @Test
    public void whenIncorrectDataForCheckColumnThenReturnFalse() {
        List<Integer>[][] actualList = Main.convertChar2ArrayToList2Array(incorrectTable1);
        boolean result = Main.checkColumn(0,  actualList);
        Assert.assertFalse(result);
    }

    @Test
    public void whenCorrectDataForCheckLineThenReturnTrue() {
        List<Integer>[][] actualList = Main.convertChar2ArrayToList2Array(correctTable);
        boolean result = Main.checkLine(0,  actualList);
        Assert.assertTrue(result);
    }

    @Test
    public void whenIncorrectDataForCheckLineThenReturnFalse() {
        List<Integer>[][] actualList = Main.convertChar2ArrayToList2Array(incorrectTable1);
        boolean result = Main.checkLine(0,  actualList);
        Assert.assertFalse(result);
    }
}