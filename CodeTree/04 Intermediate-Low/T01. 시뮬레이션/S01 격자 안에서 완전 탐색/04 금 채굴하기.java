/**
 * try1
 */

import java.util.ArrayList;
import java.util.Scanner;

class Square {
	public int[][] grid;
	public int k;

	public Square(int[][] grid, int k) {
		this.grid = grid;
		this.k = k;
	}
}

public class Main {

    /*
        1. n에 따라 마름모 동적으로 생성
        2. 모든 (x,y) 에 따라 모든 마름모를 적용하여 채굴
            - 손해보지 않은 채굴만 기록
            - 가장 많은 금의 개수 갱신
        3. 출력
    */

	static final int MAX_N = 20;

	static int n, m; // n: grid size, m: gold count
	static int[][] grid = new int[MAX_N][MAX_N];
	static ArrayList<Square> squares = new ArrayList<>();

	// 마름모 동적 생성
	static void createSquare(int n) {
		int maxK = n - 1;

		for (int k = 0; k <= maxK; k++) {

			Square square = new Square(new int[2 * k + 1][2 * k + 1], k);

			// 상삼각형 그리기
			for (int i = 0; i <= k; i++) {
				for (int j = k - i; j <= k + i; j++) {
					square.grid[i][j] = 1;
				}
			}

			// 하삼각형 그리기
			for (int i = k - 1; i >= 0; i--) {
				for (int j = k - i; j <= k + i; j++) {
					square.grid[2 * k - i][j] = 1;
				}
			}

			squares.add(square);
		}
	}

	static void mine() {
		int maxGold = 0;

		// 모든 (x,y)에 대하여
		for (int x = 0; x < n; x++) {
			for (int y = 0; y < n; y++) {
				// 모든 마름모를 적용시켜 채굴 시도

			}
		}
	}

	public static void main(String[] args) {
		// 입력
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();

		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				grid[i][j] = sc.nextInt();

		// 마름모 만들기
		createSquare(n);

		for (Square square : squares) {
			int[][] grid = square.grid;
			for (int i = 0; i < grid.length; i++) {
				for (int j = 0; j < grid[i].length; j++) {
					System.out.print(grid[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println("--------------");
		}

		// 채굴

		// 출력
	}
}

/**
 * 해설 1 - 모든 격자 탐색
 */
import java.util.Scanner;

public class Main {
	public static final int MAX_NUM = 20;

	public static int n, m;
	public static int[][] grid = new int[MAX_NUM][MAX_NUM];

	// 주어진 k에 대하여 마름모의 넓이를 반환합니다.
	public static int getArea(int k) {
		return k * k + (k+1) * (k+1);
	}

	// 주어진 k에 대하여 채굴 가능한 금의 개수를 반환합니다.
	public static int getNumOfGold(int row, int col, int k) {
		int numOfGold = 0;

		for(int i = 0; i < n; i++)
			for(int j = 0; j < n; j++)
				if(Math.abs(row - i) + Math.abs(col - j) <= k)
					numOfGold += grid[i][j];

		return numOfGold;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int maxGold = 0;

		// 입력:
		n = sc.nextInt();
		m = sc.nextInt();
		for(int row = 0; row < n; row++)
			for(int col = 0; col < n; col++)
				grid[row][col] = sc.nextInt();

		// 격자의 각 위치가 마름모의 중앙일 때 채굴 가능한 금의 개수를 구합니다.
		for(int row = 0; row < n; row++) {
			for(int col = 0; col < n; col++) {
				for(int k = 0; k <= 2 * (n-1); k++) {
					int numOfGold = getNumOfGold(row, col, k);

					// 손해를 보지 않으면서 채굴할 수 있는 최대 금의 개수를 저장합니다.
					if(numOfGold * m >= getArea(k))
						maxGold = Math.max(maxGold, numOfGold);
				}
			}
		}

		System.out.print(maxGold);
	}
}

/**
 * 해설 2 - 마름모 범위 안만 탐색. 마름모 범위를 경계의 합으로 계산
 */
import java.util.Scanner;

public class Main {
	public static final int MAX_NUM = 20;

	public static int n, m;
	public static int[][] grid = new int[MAX_NUM][MAX_NUM];

	// 주어진 k에 대하여 마름모의 넓이를 반환합니다.
	public static int getArea(int k) {
		return k * k + (k+1) * (k+1);
	}

	// 주어진 좌표가 격자에 포함되는지 여부를 반환합니다.
	public static boolean inRange(int x, int y) {
		return x >= 0 && x < n && y >= 0 && y < n;
	}

	// 주어진 k에 대하여 채굴 가능한 금의 개수를 반환합니다.
	public static int getNumOfGold(int row, int col, int k) {
		int numOfGold = 0;
		int[] dx = new int[]{1, 1, -1, -1};
		int[] dy = new int[]{-1, 1, 1, -1}; // 방향에 따라 바뀌는 x와 y의 변화량을 정의합니다.

		numOfGold += grid[row][col]; // k=0 일 때 처리

		for(int currK = 1; currK <= k; currK++) {
			int currX = row - currK, currY = col; // 순회 시작점 설정

			for(int currDir = 0; currDir < 4; currDir++) {
				for(int step = 0; step < currK; step++) {
					if(inRange(currX, currY)) {
						numOfGold += grid[currX][currY];
					}
					currX = currX + dx[currDir];
					currY = currY + dy[currDir];
				}
			}
		}

		return numOfGold;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int maxGold = 0;

		n = sc.nextInt();
		m = sc.nextInt();
		for(int row = 0; row < n; row++)
			for(int col = 0; col < n; col++)
				grid[row][col] = sc.nextInt();


		for(int row = 0; row < n; row++) {
			for(int col = 0; col < n; col++) {
				for(int k = 0; k <= 2 * (n-1); k++) {
					int numOfGold = getNumOfGold(row, col, k);

					if(numOfGold * m >= getArea(k))
						maxGold = Math.max(maxGold, numOfGold);
				}
			}
		}

		System.out.print(maxGold);
	}
}

/**
 * 해설 3 - 현재 마름모 넓이 = 이전 마름모 넓이 + 현재 마름모의 경계
 */

import java.util.Scanner;

public class Main {
	public static final int MAX_NUM = 20;

	public static int n, m;
	public static int[][] grid = new int[MAX_NUM][MAX_NUM];

	public static int getArea(int k) {
		return k * k + (k+1) * (k+1);
	}

	public static boolean inRange(int x, int y) {
		return x >= 0 && x < n && y >= 0 && y < n;
	}

	// 주어진 k에 대하여 모서리에서 채굴 가능한 금의 개수를 반환합니다.
	public static int getNumOfGoldInBorder(int row, int col, int k) {
		int numOfGold = 0;
		int[] dx = new int[]{1, 1, -1, -1};
		int[] dy = new int[]{-1, 1, 1, -1}; // 방향에 따라 바뀌는 x와 y의 변화량을 정의합니다.

		if(k == 0)
			return grid[row][col];

		int currX = row - k, currY = col; // 순회 시작점 설정

		for(int currDir = 0; currDir < 4; currDir++) {
			for(int step = 0; step < k; step++) {
				if(inRange(currX, currY)) {
					numOfGold += grid[currX][currY];
				}
				currX = currX + dx[currDir];
				currY = currY + dy[currDir];
			}
		}

		return numOfGold;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int maxGold = 0;

		n = sc.nextInt();
		m = sc.nextInt();
		for(int row = 0; row < n; row++)
			for(int col = 0; col < n; col++)
				grid[row][col] = sc.nextInt();


		for(int row = 0; row < n; row++) {
			for(int col = 0; col < n; col++) {
				int numOfGold = 0;
				for(int k = 0; k <= 2 * (n-1); k++) {
					// 이전 k까지 구한 금의 개수에
					// 해당 k의 모서리에 존재하는 금의 개수를 더해줍니다.
					numOfGold += getNumOfGoldInBorder(row, col, k);

					if(numOfGold * m >= getArea(k))
						maxGold = Math.max(maxGold, numOfGold);
				}
			}
		}

		System.out.print(maxGold);
	}
}

/**
 * 23.04.12
 */

import java.util.Scanner;

public class Main {

	/*
	 	- 채굴 1번만 마름모 모양으로 가능
	 		- 마름모 모양: 중심점 기준으로 K번 이동가능 위치
	 		- 격자를 벗어난 모양도 가능
	 		- k = 2(n-1) 일 때 모든 격자 커버 가능
		- 금 1개 가격이 m일 때, 손해보지 않으면서 캘 수 있는 최대 금 개수?
			- 채굴 비용 = k^2 + (k+1)^2
		- 모든 중심점에 대해
			- k <- 0 to 2n - 1
				- k에 대하여 gold 추출
				- 손해 안 봤으면 gold 개수 갱신
	 */

	public static int MAX_N = 20;

	static int[][] grid = new int[MAX_N][MAX_N];
	static int n, m;

	static int calcMineCost(int k) {
		return (k * k) + (k + 1) * (k + 1);
	}

	static int calcDist(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}

	static int mine(int row, int col, int k) {
		// (row, col) : 중심점
		int goldCnt = 0;

		// 모든 격자에 대해
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {

				// 중심점으로부터 거리가 k이내일 때, 금 채굴
				if (calcDist(i, j, row, col) <= k) {
					goldCnt += grid[i][j];
				}
			}
		}

		return goldCnt;
	}

	static int simulate() {
		int maxGoldCnt = 0;

		// 모든 중심점에 대하여
		for (int row = 0; row < n; row++) {
			for (int col = 0; col < n; col++) {

				// 마름모 모양으로 채굴 시도
				for (int k = 0; k <= 2 * n - 1; k++) {
					int goldCnt = mine(row, col, k);

					// 전체 금 가격 >= 채굴비용 && 기존 금 개수 보다 많으면 갱신
					if (goldCnt * m >= calcMineCost(k) && goldCnt > maxGoldCnt) {
						maxGoldCnt = goldCnt;
					}
				}
			}
		}

		return maxGoldCnt;
	}

	public static void main(String[] args) {
		// 입력
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt(); // n by n grid
		m = sc.nextInt(); // 금 1개의 가격

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				grid[i][j] = sc.nextInt();
			}
		}

		// 최대 금 개수 구하기
		int maxGoldCnt = simulate();

		// 출력
		System.out.println(maxGoldCnt);
	}
}

/*
	23.05.09: 해설 3과 같은 풀이
 */

import java.util.Scanner;

public class Main {

	/*
		모든 중심점 (row, col)을 기준으로 채굴 시도
		(row, col) 에 대해 k가 0부터 2n-2 까지 증가하며 마름모 모양으로 채굴
		순이익이 날 때, 최대 금 개수 업데이트
	 */

	static final int MAX_N = 20;
	static final int DIR_NUM = 4;

	static int[][] grid = new int[MAX_N][MAX_N];
	static int n; // n by n grid
	static int m; // 금 1개의 가격

	static int[] dx = {1, 1, -1, -1};
	static int[] dy = {-1, 1, 1, -1};

	static int maxGoldCnt;

	static boolean inRange(int x, int y) {
		return x >= 0 && x < n && y >= 0 && y < n;
	}

	// k에 대해, 모서리만 계산
	static int mine(int k, int row, int col) {

		int goldCnt = 0;

		if (k == 0)
			return grid[row][col];

		int cx = row - k, cy = col;

		// 모서리 순회하면서 채굴
		for (int dir = 0; dir < DIR_NUM; dir++) {
			for (int step = 1; step <= k; step++) {
				cx += dx[dir];
				cy += dy[dir];

				if (inRange(cx, cy)) {
					goldCnt += grid[cx][cy];
				}
			}
		}
		return goldCnt;
	}

	static int cost(int k) {
		return (k * k) + (k + 1) * (k + 1);
	}

	static void simulate() {
		// 모든 row, col 에 대해 완전탐색
		for (int row = 0; row < n; row++) {
			for (int col = 0; col < n; col++) {

				// 마름모 모양으로 채굴
				// row, col에 대해 k 늘려가며 마름모 모양으로 채굴
				int goldCnt = 0;
				for (int k = 0; k <= 2 * n - 2; k++) {

					goldCnt += mine(k, row, col); // 모서리만 계산

					// k 마름모에 대해 채굴 해서, 순이익이 날 때, 최대 금 개수 업데이트
					if (goldCnt * m >= cost(k)) {
						maxGoldCnt = Math.max(goldCnt, maxGoldCnt);
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		// 입력
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				grid[i][j] = sc.nextInt();
			}
		}

		simulate();

		// 출력
		System.out.println(maxGoldCnt);
	}
}
