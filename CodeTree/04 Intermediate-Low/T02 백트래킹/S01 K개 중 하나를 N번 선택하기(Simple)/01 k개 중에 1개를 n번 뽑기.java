import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	static int n, k;
	static List<Integer> list = new ArrayList<>();

	// currNum 번째에 올 숫자를 정하는 함수
	static void choose(int currNum) {
		if (currNum == n + 1) {
			for (int num : list) {
				System.out.print(num + " ");
			}
			System.out.println();
			return;
		}

		for (int i = 1; i <= k; i++) {
			list.add(i);
			choose(currNum + 1);
			list.remove(list.size() - 1);
		}
	}

	public static void main(String[] args) {
		// 입력
		Scanner sc = new Scanner(System.in);
		k = sc.nextInt();
		n = sc.nextInt();

		choose(1);
	}
}

/**
 * 해설
 */

import java.util.Scanner;
import java.util.ArrayList;

public class Main {
	public static int k, n;
	public static ArrayList<Integer> selectedNums = new ArrayList<>();

	// 선택된 원소들을 출력해줍니다.
	public static void printPermutation() {
		for(int i = 0; i < selectedNums.size(); i++)
			System.out.print(selectedNums.get(i) + " ");
		System.out.println();
	}

	public static void findPermutations(int cnt) {
		// n개를 모두 뽑은 경우 답을 출력해줍니다.
		if(cnt == n) {
			printPermutation();
			return;
		}

		// 1부터 k까지의 각 숫자가 뽑혔을 때의 경우를 탐색합니다.
		for(int i = 1; i <= k; i++) {
			selectedNums.add(i);
			findPermutations(cnt + 1);
			selectedNums.remove(selectedNums.size() - 1);
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		k = sc.nextInt();
		n = sc.nextInt();

		findPermutations(0);
	}
}