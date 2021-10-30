/**
This two pointer approach helps in evaluating the number of pairs
(i, j) where 0<=i<=j<=array.length, such that A[i]+A[j] = target.
Using naive approach, two nest loop accounts to O(n^2) time complexity
however using two pointer approach we can obtain result in O(n) time.
**/
public class Main{

  public static int pairSum(int A[], int N, int target){
    int i = 0;
    int j = N - 1;

    while (i < j) {
      if (A[i] + A[j] == target) return 1;
      else if (A[i] + A[j] < target) i++;
      else j--;
      }
    return 0;
  }

  public static void main(String[]Args){
      int [] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};
      int target = 10;
      System.out.println(pairSum(array, array.length, target));
  }

}
