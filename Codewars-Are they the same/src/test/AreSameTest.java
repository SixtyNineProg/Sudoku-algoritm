package test;

import main.AreSame;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class AreSameTest {

    @Test
    public void test1() {
        int[] a = new int[]{121, 144, 19, 161, 19, 144, 19, 11};
        int[] b = new int[]{121, 14641, 20736, 361, 25921, 361, 20736, 361};
        assertTrue(AreSame.comp(a, b));
    }

    @Test
    public void test2() {
        int[] a = new int[]{};
        int[] b = new int[]{};
        assertTrue(AreSame.comp(a, b));
    }

    @Test
    public void test3() {
        int[] a = new int[]{11};
        int[] b = new int[]{121};
        assertTrue(AreSame.comp(a, b));
    }

    @Test
    public void test4() {
        int[] a = new int[]{121, 144, 19, 161, 19, 144, 19, 11, 5};
        int[] b = new int[]{121, 14641, 20736, 361, 25921, 361, 20736, 361};
        assertFalse(AreSame.comp(a, b));
    }

    @Test
    public void test5() {
        int[] a = new int[]{121, 144, 19, 161, 19, 144, 19, 11};
        int[] b = new int[]{121, 14641, 20736, 361, 25921, 361, 20736, 361, 5};
        assertFalse(AreSame.comp(a, b));
    }

    @Test
    public void test6() {
        int[] a = new int[]{121, 144, 0, 161, 19, 144, 0, 11};
        int[] b = new int[]{121, 14641, 20736, 361, 25921, 0, 20736, 0};
        assertTrue(AreSame.comp(a, b));
    }

    @Test
    public void test7() {
        int[] a = new int[]{-11, 1, -1};
        int[] b = new int[]{121, 1, 1};
        assertTrue(AreSame.comp(a, b));
    }

    @Test
    public void test8() {
        int[] a = new int[]{0, 1, -1};
        int[] b = new int[]{121, 1, 1};
        assertFalse(AreSame.comp(a, b));
    }

    @Test
    public void test9() {
        int[] a = null;
        int[] b = null;
        assertFalse(AreSame.comp(a, b));
    }
}
