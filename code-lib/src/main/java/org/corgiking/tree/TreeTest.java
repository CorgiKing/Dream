package org.corgiking.tree;

public class TreeTest {


    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9 , 0, 123, 1234, 23};


        heapSort(arr);

    }

    private static void heapSort(int[] arr) {
        heapify(arr);
        print(arr);
        System.out.println();
        System.out.println("start sort");
        for (int i = arr.length - 1; i > 0; --i){
            int tmp = arr[0];
            arr[0] = arr[i];
            arr[i] = tmp;
            heapifyDown(arr, i);
            System.out.println(i);
        }


        print(arr);
    }


    /**
     * 将无序数组转化成大堆
     *
     * @param arr 数组
     */
    public static void heapify(int[] arr){

        for (int i = 1; i < arr.length; ++i){
            heapifyUp(arr, i);
        }
    }


    /**
     * 第i个元素加入堆
     * 
     * @param arr 数组
     * @param i
     */
    public static void heapifyUp(int[] arr, int i){
        int tmp = arr[i];
        int k = i;
        while (k > 0){
            //如果当前节点比父节点大，上移
            if (tmp > arr[(k-1)/2]){
                arr[k] = arr[(k-1)/2];
                //当前节点设置为父节点
                k = (k-1)/2;
            }else {
                break;
            }
        }
        arr[k] = tmp;
    }

    /**
     *
     *
     * @param arr
     * @param size
     */
    public static void heapifyDown(int[] arr, int size){
        int tmp = arr[0];
        int k = 0;
        while (k < size){
            int lIndex = 2*k + 1;
            int rIndex = 2*k + 2;
            Integer left = lIndex < size? arr[lIndex]:null;
            Integer right = rIndex < size? arr[rIndex]:null;

            Integer max;
            int maxIndex;
            if (left == null && right == null){
                break;
            }else if (right == null){
                max = left;
                maxIndex = lIndex;
                if (tmp < max){
                    arr[k] = arr[maxIndex];
                    k = maxIndex;
                }
                break;
            }else {
                max = left > right ? left: right;
                maxIndex = left > right ? lIndex: rIndex;
            }

            if (tmp < max){
                arr[k] = arr[maxIndex];
                k = maxIndex;
            }
        }
        arr[k] = tmp;
    }


    public static void print(int[] arr){
        int k = 0;

        end:
        for (int i = 0;; ++i){
            for (int j = (int) Math.pow(2, i), m = 0;m < j; ++m){
                System.out.print(arr[k++] + "\t");
                if (k == arr.length){
                    break end;
                }
            }
            System.out.println();
        }
    }


}
