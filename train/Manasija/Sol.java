import java.util.Arrays;

class Sol{
    public static void main(String[] args) {
        int[] arr = new int[]{1,2,2,3,4,4,5};
        int[] res = new int[arr.length];
        int j = 1;

        Arrays.sort(arr);
        res[0] = arr[0];
        for(int i = 1; i < arr.length; i++){
            if(arr[i] != arr[i - 1]){
                res[j] = arr[i];
                j++;
            }
        }

        for(int i = 0; i < res.length; i++)
            if(res[i] != 0)
                System.out.print(res[i] + " ");
    }
}