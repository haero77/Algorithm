// try1 - fail: 같은 숫자 고려 안했음.

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 변수 선언
        int n = sc.nextInt();
        int[] arr = new int[n];
        int first = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;

        // 배열 입력
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // 첫 번째로 큰 수 구하기
        for (int i = 0; i < n; i++) {
            if (arr[i] > first) {
                first = arr[i];
            }
        }

        // 두 번째로 큰 수 구하기
        for (int i = 0; i < n; i++) {
            if (arr[i] > second && arr[i] < first) {
                second = arr[i];
            }
        }

        // 출력
        System.out.printf("%d %d", first, second);
    }
}

// try2 - pass: 선택 정렬
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];

        // 배열 입력
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // 선택 정렬 (내림차순 정렬)
        for (int i = 0; i < n - 1; i++) {
            int maxIndex = i;

            for (int j = i + 1; j < n; j++) {
                if (arr[maxIndex] < arr[j]) {
                    maxIndex = j;
                }
            }

            // swap
            int tmp = arr[i];
            arr[i] = arr[maxIndex];
            arr[maxIndex] = tmp;
        }

        System.out.printf("%d %d", arr[0], arr[1]);
    }
}

// 해설 1 : 두 번째로 큰 원소 구할 때는 첫 번째 원소 제외하고 구하기
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 변수 선언:
        int[] A = new int[100];
        int n = 0, max1 = 0, max2 = 0, max1Idx = 0;

        // 입력:
        n = sc.nextInt();
        for(int i = 0; i < n; i++)
            A[i] = sc.nextInt();

        // Step 1: max1과 해당 index를 구합니다.
        max1 = A[0];
        max1Idx = 0;

        for (int i = 1; i < n; i++) {
            if (A[i] > max1){
                max1 = A[i];
                max1Idx = i; // 최대 위치를 갱신합니다.
            }
        }

        // Step 2: max1이 골라진 위치를 제외한 곳에서 최댓값을 구합니다.
        boolean isInitialized = false;
        for (int i = 0; i < n; i++) {
            if (i == max1Idx)
                continue; // Case 1 : 1번에서 고른 케이스는 패스합니다.

            if (isInitialized == false) {
                // Case 2: 아직 max2 값을 초기화 하지 못했다면
                //         현재 값으로 초기화 합니다.
                isInitialized = true;
                max2 = A[i];
            }
            else if (A[i] > max2) {
                // Case 3: 지금까지 계산한 값보다 좋다면 갱신합니다.
                max2 = A[i];
            }
        }

        // 출력:
        System.out.print(max1 + " " + max2);
    }
}


// 해설 2 : 배열을 한 번만 순회하며 현재까지의 첫 번째 최대와 두 번째 최대 원소를 계속 갱신
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 변수 선언:
        int[] A = new int[100];
        int n = 0, max1 = 0, max2 = 0;

        // 입력:
        n = sc.nextInt();
        for(int i = 0; i < n; i++)
            A[i] = sc.nextInt();

        // Step 1: 처음 2개의 원소 중 더 큰 값을 max1에
        //                        더 작은 값을 max2에 넣습니다.
        if (A[0] > A[1]){
            max1 = A[0];
            max2 = A[1];
        }
        else{
            max1 = A[1];
            max2 = A[0];
        }

        // Step 2: 3번째 원소부터 보면서 max1과 max2를 갱신합니다.
        for (int i = 2; i < n; i++) {
            if (A[i] >= max1) {
                // Case 1: 지금까지 본 숫자들보다 좋다면
                //         max2, max1 모두 갱신해줍니다.
                max2 = max1;
                max1 = A[i];
            }
            else if (A[i] > max2){
                // Case 2: max2보다만 좋다면 max2를 갱신합니다.
                max2 = A[i];
            }
        }

        // 출력:
        System.out.print(max1 + " " + max2);
    }
}

// 해설 3 : 정렬 메서드 사용
import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 변수 선언:
        int n;

        // 입력:
        n = sc.nextInt();
        int[] A = new int[n];
        for(int i = 0; i < n; i++)
            A[i] = sc.nextInt();

        // 내림차순으로 정렬합니다.
        Integer[] A2 = Arrays.stream(A).boxed().toArray(Integer[]::new);
        Arrays.sort(A2, Collections.reverseOrder());

        // 출력:
        System.out.print(A2[0] + " " + A2[1]);
    }
}