public class Main {

    public static void main(String[] args) {
        int[] arr = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int[] arr1 = {1, 3, 1};
        System.out.println(findMaxArea(arr));
    }

    public static int findMaxArea(int[] height) {
        int maxArea = 0;

        for (int i = 0; i < height.length - 1; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int length = j - i;
                int height1 = height[i];
                int height2 = height[j];
                int tempArea;
                if (height1 == height2){
                    tempArea = height1 * length;
                    maxArea = Math.max(tempArea, maxArea);
                } else if (height1 > height2){
                    tempArea = height2 * length;
                    maxArea = Math.max(tempArea, maxArea);
                } else {
                    tempArea = height1 * length;
                    maxArea = Math.max(tempArea, maxArea);
                }
            }

        }

        return maxArea;
    }
}
