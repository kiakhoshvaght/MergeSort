import Utils.Log;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


public class MergeSort {
    public static final String TAG = MergeSort.class.getName();

    public static boolean isSorted(int[] array) {
        for (int i = 1; i < array.length; i += 1) {
            if (array[i - 1] > array[i]) {
                return false;
            }
        }
        return true;
    }

    private static void naturalMergeSort(int[] array) {
        int left = 0;
        int right = array.length;
        int i = left;
        int j = 0;

        while (true) {
            i = 0;
            while (i < array.length) {
                if (i == array.length - 1) {
                    break;
                } else if (array[i] > array[i + 1]) {
                    break;
                }
                i++;
            }

            j = i + 1;

            while (j < array.length) {
                if (j == array.length - 1) {
                    break;
                } else if (array[j]>array[j + 1]) {
                    break;
                }
                j++;
            }
            merge(array, left, i, j);
            left = 0;

            if (isSorted(array)) {
                break;
            }
        }
    }

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
        if (left < right) { //Checking if we have divided arrays enough
            //Calculating separation points
            int middle = left + (right - left) / 2;

            //Recursively calling this function for two divided arrays
            topDownMergeSort(array, left, middle);
            topDownMergeSort(array, middle + 1, right);
            //Merging them
            merge(array, left, middle, right);
        }
    }

    static void topDownMergeSortWithAux(int array[], int left, int right) {
        if (left < right) { //Checking if we have divided arrays enough
            //Calculating separation points
            int middle = left + (right - left) / 2;

            //Recursively calling this function for two divided arrays
            topDownMergeSortWithAux(array, left, middle);
            topDownMergeSortWithAux(array, middle + 1, right);
            //Merging them
            mergeWithAux(array, left, middle, right);
        }
    }

    static void merge(int arr[], int left, int middle, int right) {
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

    static void stringToIntArray(String string, int[] array){
        for (int i = 0; i < string.length(); i++) {
            array [i] = (int) string.charAt(i);
        }
    }

    static void mergedQueue (Queue<Integer> q1, Queue<Integer> q2, Queue<Integer> result){
            System.out.print(q1.peek() + ",");
        while (!q1.isEmpty() || !q2.isEmpty()){
            Log.info(TAG,"in while");
            if(q1.peek() < q2.peek()){
                Log.info(TAG,"in q1<q2");
                result.add(q1.poll());
            } else {
                Log.info(TAG,"in else");
                result.add(q2.poll());
            }
        }
    }

    public static void main(String[] args) {
        Log.info(TAG, "FUNCTION : main");

        int TDTest1[] ={3, 18, 11, 13, 2, 33 , 5,15,0,7,4,1,16};
        topDownMergeSort(TDTest1, 0, TDTest1.length - 1);
        Log.info(TAG, "FUNCTION : main => Top Down Sort result: ");
        for (int i = 0; i < TDTest1.length; i++)
            System.out.print(TDTest1[i] + ",");
        System.out.println(" ");

        String name = "NILOOFARKALANTARI";
        int TDStringTest1[] = new int[name.length()];
        stringToIntArray(name,TDStringTest1);
        topDownMergeSort(TDStringTest1, 0, TDStringTest1.length - 1);
        Log.info(TAG, "FUNCTION : main => Top Down Sort for string result: ");
        for (int i = 0; i < TDStringTest1.length; i++)
            System.out.print((char)TDStringTest1[i] + ",");
        System.out.println(" ");

        int BUTest1[] = {3, 18, 11, 13, 2, 33 , 5,15,0,7,4,1,16};
        bottomUpMergeSort(BUTest1);
        Log.info(TAG, "FUNCTION : main => Bottom Up Test result: ");
        for (int i = 0; i < BUTest1.length; i++)
            System.out.print(BUTest1[i] + ",");
        System.out.println(" ");

        int TDUTest1[] = {3, 18, 11, 13, 2, 33 , 5,15,0,7,4,1,16};
        topDownMergeSortWithAux(TDUTest1,0,TDUTest1.length-1);
        Log.info(TAG, "FUNCTION : main => Top Down With Aux result: ");
        for (int i = 0; i < BUTest1.length; i++)
            System.out.print(TDUTest1[i] + ",");
        System.out.println(" ");

        int NMTest1[] = {3, 18, 11, 13, 2, 33 , 5,15,0,7,4,1,16};
        naturalMergeSort(NMTest1);
        Log.info(TAG, "FUNCTION : main => Natural merge sort result: ");
        for (int i = 0; i < NMTest1.length; i++)
            System.out.print(NMTest1[i] + ",");
        System.out.println(" ");

        Queue<Integer> q1 = new LinkedList<Integer>();
        q1.add(8);
        q1.add(12);
        q1.add(20);
        q1.add(34);
        q1.add(55);
        Queue<Integer> q2 = new LinkedList<Integer>();
        q1.add(5);
        q1.add(14);
        q1.add(33);
        q1.add(38);
        q1.add(51);
        Queue<Integer> result = new LinkedList<Integer>();
        mergedQueue(q1,q2,result);
        Log.info(TAG,"FUNCTION : main => merge queue result: ");
        for (int i = 0; i < result.size(); i++)
            System.out.print(result.poll() + ",");
        System.out.println(" ");



    }

}
