import Utils.Log;

public class MergeSort {
    public static final String TAG = MergeSort.class.getName();

    public static void bottomUpMergeSort(int[] a) {
        Log.info(TAG, "FUNCTION : bottomUpMergeSort");
        int width;
        boolean isOdd = false;
        if (a.length % 2 == 1) {
            isOdd = true;
            Log.info(TAG, "FUNCTION : bottomUpMergeSort => isOdd");
        }
        for (width = 1; width < a.length; width = 2 * width) {
            // Combine pairs of array a of width
            int i;
            Log.info(TAG, "FUNCTION : bottomUpMergeSort => Width: " + width);
            for (i = 0; i <= a.length; i = i + 2 * width) {
                int left, middle, right;

                left = i;
                middle = i + width;
                right = i + 2 * width;

                if (isOdd == true && width == 1 && right == a.length - 1) {
                    Log.info(TAG, "FUNCTION : bottomUpMergeSort => Calling merge with: left: " + left + ", right: " + right + ", middle: " + middle);
                    merge2(a, left, right);
                } else {
                    Log.info(TAG, "FUNCTION : bottomUpMergeSort => Calling merge with: left: " + left + ", right: " + (right - 1) + ", middle: " + middle);
                    merge2(a, left, right - 1);
                }
            }
        }
        int twoPower = 1;

        while(twoPower-1<a.length/2)
            twoPower *= 2;

        if(twoPower != a.length)
            merge(a,0,twoPower-1,a.length-1);

    }


    static void topDownMergeSort(int arr[], int left, int right) {
        Log.info(TAG, "FUNCTION : topDownMergeSort");
        if (left < right) { //Checking if we have divided arrays enough
            Log.info(TAG, "FUNCTION : topDownMergeSort => Divided arrays before sorting: ");
            //Calculating separation points
            int middle = left + (right - left) / 2;

            for (int i = left; i <= right; i++) {
                System.out.print(arr[i] + ",");
                if (i == middle)
                    System.out.print(" | ");
            }
            System.out.println(" ");

            //Recursively calling this function for two divided arrays
            topDownMergeSort(arr, left, middle);
            topDownMergeSort(arr, middle + 1, right);
            //Merging them
            merge(arr, left, middle, right);
        }
    }

    static void merge(int arr[], int left, int middle, int right) {
        Log.info(TAG, "FUNCTION : merge");
        //Calculating sub arrays
        int i, j, k;
        int n1 = middle - left + 1;
        int n2 = right - middle;

        int L[] = new int[n1];
        int R[] = new int[n2];

        //Copying data to new sub arrays
        for (i = 0; i < n1; i++)
            L[i] = arr[left + i];
        for (j = 0; j < n2; j++)
            R[j] = arr[middle + 1 + j];

        //Sorting and merging
        i = 0;
        j = 0;
        k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        //Copying remained
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    static void merge2(int arr[], int left, int right) {
        Log.info(TAG, "FUNCTION : merge2");
        if (left >= arr.length) {
            Log.info(TAG, "FUNCTION : merge2 => left is greater or equal to size");
            return;
        }
        if (right >= arr.length) {
            Log.info(TAG, "FUNCTION : merge2 => Right is greater or equal to size");
            right = arr.length - 1;
        }
        if (right - left == 1) {
            Log.info(TAG, "FUNCTION : merge2 => case 0 & 1");
            if (arr[left] > arr[right]) {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
            }
        } else if (right - left % 2 == 1) {
            Log.info(TAG, "FUNCTION : merge2 => case odd");
            int middle = left + (right - left) / 2 + 1;
            merge(arr, left, middle, right);
        } else {
            Log.info(TAG, "FUNCTION : merge2 => case even");
            int middle = left + (right - left) / 2;
            merge(arr, left, middle, right);
        }
    }


    public static void main(String[] args) {
        Log.info(TAG, "FUNCTION : main");

        int TDTest1[] = {10, 34, 5, 12, 22, 69, 3, 1, 11, 13, 8};
        topDownMergeSort(TDTest1, 0, TDTest1.length - 1);
        Log.info(TAG, "FUNCTION : main => TDTest1 result: ");
        for (int i = 0; i < TDTest1.length; i++)
            System.out.print(TDTest1[i] + ",");
        System.out.println(" ");

        int BUTest1[] = {3, 18, 11, 13, 2, 33 , 5,15,0,7,4,1};
        bottomUpMergeSort(BUTest1);
        Log.info(TAG, "FUNCTION : main => BUTest1 result: ");
        for (int i = 0; i < BUTest1.length; i++)
            System.out.print(BUTest1[i] + ",");
        System.out.println(" ");
    }

}
