//Sort an array of three repeated integers(0, 1, 2)

public class SortArray {
    public static void main(String[] args) {
        int arr[] = new int[]{2, 2, 1, 0, 1};
        int zeroCount=0, oneCount=0, twoCount=0;
        int index=0;
        for(index=0;index<arr.length;index++){
            if(arr[index]==0) zeroCount++;
            else if(arr[index]==1) oneCount++;
            else twoCount++;
        }
        index=0;
        while(zeroCount-- > 0){
            arr[index++]=0;
        }
        while(oneCount-- > 0){
            arr[index++] = 1;
        }
        while(twoCount-- > 0){
            arr[index++] = 2;
        }
        for(index=0;index<arr.length;index++){
            System.out.print(arr[index] + " ");
        }
    }
}
