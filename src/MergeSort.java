import Utils.Log;

import java.util.Arrays;

public class MergeSort {
    public static final String TAG = MergeSort.class.getName();

    private static void mergeWithAux(int[] array, int left, int middle, int right) {
        int[] aux = new int[array.length];
        for (int i = left; i <= middle; i++)
            aux[i] = array[i];

        for (int j = middle+1; j <= right; j++)
            aux[j] = array[right-j+middle+1];

        int i = left, j = right;
        for (int k = left; k <= right; k++)
            if (aux[j] < aux[i])
                array[k] = aux[j--];
            else
                array[k] = aux[i++];
    }

    public static void merge(int[] array, int[] aux, int left, int middle, int right) {
        int i, j, z = left;
        if(array[middle] <= array[middle+1])return;

        for(i=left, j = middle+1; i!=middle+1 || j!=right+1;){
            if(i==middle+1)
                while(j!=right+1)
                    aux[z++] = array[j++];
            else if(j==right+1)
                while(i!=middle+1)
                    aux[z++] = array[i++];
            else if(array[i]<=array[j])
                aux[z++] = array[i++];
            else
                aux[z++] = array[j++];
        }
        System.out.println(Arrays.toString(array));
        System.out.println("start = "+left+" mid = "+middle+" end = "+right);
        System.out.println(Arrays.toString(aux)+"\n");
        System.arraycopy(aux, left, array, left, right-left+1);
    }

    public static void bottomUpMergeSort(int[] array) {
        int N = array.length;
        int aux[] = new int[array.length];
        for (int i = 1; i < N; i *= 2) {
            for (int j = 0; j < N - i; j += i + i) {
                merge(array, aux, j, j + i - 1, Math.min(j + i + i - 1, N-1));
            }
        }
    }

    static void topDownMergeSort(int array[], int left, int right) {
        Log.info(TAG, "FUNCTION : topDownMergeSort");
        if (left < right) { //Checking if we have divided arrays enough
            Log.info(TAG, "FUNCTION : topDownMergeSort => Divided arrays before sorting: ");
            //Calculating separation points
            int middle = left + (right - left) / 2;

            for (int i = left; i <= right; i++) {
                System.out.print(array[i] + ",");
                if (i == middle)
                    System.out.print(" | ");
            }
            System.out.println(" ");

            //Recursively calling this function for two divided arrays
            topDownMergeSort(array, left, middle);
            topDownMergeSort(array, middle + 1, right);
            //Merging them
            merge(array, left, middle, right);
        }
    }

    static void topDownMergeSortWithAux(int array[], int left, int right) {
        Log.info(TAG, "FUNCTION : topDownMergeSort");
        if (left < right) { //Checking if we have divided arrays enough
            Log.info(TAG, "FUNCTION : topDownMergeSort => Divided arrays before sorting: ");
            //Calculating separation points
            int middle = left + (right - left) / 2;

            for (int i = left; i <= right; i++) {
                System.out.print(array[i] + ",");
                if (i == middle)
                    System.out.print(" | ");
            }
            System.out.println(" ");

            //Recursively calling this function for two divided arrays
            topDownMergeSort(array, left, middle);
            topDownMergeSort(array, middle + 1, right);
            //Merging them
            mergeWithAux(array, left, middle, right);
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
