import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력
        int n = sc.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // 동일한 숫자가 나오는 횟수 중 최댓값 구하기
        int cnt = 0, maxCnt = 0;
        for (int i = 0; i < n; i++) {
            if (i == 0 || arr[i] != arr[i - 1]) {
                cnt = 1;
            } else {
                cnt++;
            }

            if (cnt > maxCnt) {
                maxCnt = cnt;
            }
        }

        System.out.println(maxCnt);
    }
}

// 해설
import java.util.Scanner;

public class Main {
    public static final int MAX_N = 1000;

    public static int n;
    public static int[] arr = new int[MAX_N];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력
        n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // 연속하여 동일한 숫자가 나오는 횟수를 구해보며,
        // 그 중 최댓값을 갱신합니다.
        int ans = 0, cnt = 0;
        for (int i = 0; i < n; i++) {
            // Case 1
            if (i >= 1 && arr[i] == arr[i - 1])
                cnt++;
            // Case 2
            else
                cnt = 1;

            ans = Math.max(ans, cnt);
        }

        // 출력
        System.out.print(ans);
    }
}