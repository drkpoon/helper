import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

class Combinatorics {
    /**
     * @param n       number of objects
     * @param r       number of groups
     * @param atLeast objects at least in each group
     * @return arrangement listing
     */
    static int[][] getArrangementList(int n, int r, int atLeast) {
        int[][] result = getArrangementList1(n - r * atLeast, r);
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < r; j++) {
                result[i][j] += atLeast;
            }
        }
        return result;
    }

    /**
     * @param n number of objects
     * @param r number of groups
     * @return arrangement list
     * @deprecated
     */
    static int[][] getArrangementList(int n, int r) {
        ArrayList<int[]> result = new ArrayList<>();
        int[] element = new int[r];
        element[0] = n;
        result.add(Arrays.copyOf(element, r));
        int i = 0;

        while (result.get(i)[r - 1] != n) {
            int j = r - 1;
            int carry = 0;
            element = Arrays.copyOf(result.get(i), r);
            if (element[j] > 0) {
                carry = element[j];
                element[j] = 0;
            }
            while (element[j] == 0) {
                j--;
            }
            element[j] = element[j] - 1;
            element[j + 1] = element[j + 1] + 1 + carry;
            result.add(Arrays.copyOf(element, r));
            i++;
        }
        int[][] output = new int[result.size()][r];
        for (int j = 0; j < result.size(); j++) {
            System.arraycopy(result.get(j), 0, output[j], 0, r);
        }
        return output;
    }

    /**
     * @param n number of objects
     * @param r number of groups
     * @return arrangement list
     */
    static int[][] getArrangementList1(int n, int r) {
        int size = getArrangementListNumber(n, r);
        int[][] result = new int[size][r];
        result[0][0] = n;

        for (int i = 1; i < size; i++) {
            int j = r - 1;
            int carry = 0;
            int[] element = Arrays.copyOf(result[i - 1], r);
            if (element[j] > 0) {
                carry = element[j];
                element[j] = 0;
            }
            while (element[j] == 0) {
                j--;
            }
            element[j] = element[j] - 1;
            element[j + 1] = element[j + 1] + 1 + carry;
            result[i] = Arrays.copyOf(element, r);
        }
        return result;
    }

    /**
     * @param n number of objects
     * @param r number of groups
     * @return number of arrangements
     */
    static int getArrangementListNumber(int n, int r) {
        /*
        Number(n,r) = Number(n-1,r) + Number(n,r-1)
        */
        int[] temp = new int[n];
        for (int i = 0; i < r; i++) {
            temp[0] = i + 1;
            for (int j = 1; j < n; j++) {
                temp[j] += temp[j - 1];
            }
        }
        return temp[temp.length - 1];
    }

    /**
     * @param n number of objects
     * @param r number of each choice
     * @return combination list
     */
    static int[][] getCombinationList(int n, int r) {
        int combination = nCr(n, r);
        int[][] result = new int[combination][r];
        int[] maxPlaceValue = new int[r];
        for (int i = 0; i < r; i++) {
            maxPlaceValue[i] = n - r + 1 + i;
            result[0][i] = i + 1;
        }
        for (int i = 1; i < combination; i++) {
            int changePoint = r - 1;
            if (result[i - 1][r - 1] >= maxPlaceValue[r - 1]) {
                while (result[i - 1][changePoint] >= maxPlaceValue[changePoint]) {
                    changePoint--;
                }
            }
            System.arraycopy(result[i - 1], 0, result[i], 0, changePoint + 1);
            result[i][changePoint] = result[i - 1][changePoint] + 1;
            for (int j = changePoint + 1; j < r; j++) {
                result[i][j] = result[i][j - 1] + 1;
            }
        }
        return result;
    }

    static int[][] getList(int n, int r, ListType listType) {
        ArrayList<int[]> result = new ArrayList<>();
        int[] element = new int[r];
        int[] endElement = new int[r];
        switch (listType) {
            case Permutation, Combination -> {
                for (int i = 0; i < r; i++) {
                    element[i] = i + 1;
                    endElement[i] = n - i;
                }
            }
            case Arrangement -> {
                element[r - 1] = n;
                endElement[0] = n;
            }
        }

        result.add(Arrays.copyOf(element, r));
        while (!Arrays.equals(element, endElement)) {
            int carry = 1;
            for (int i = r - 1; i >= 0; i--) {
                if (element[i] + carry > n) {
                    switch (listType) {
                        case Permutation, Combination -> {
                            element[i] = 1;
                        }
                        case Arrangement -> {
                            element[i] = 0;
                        }
                    }
                } else {
                    element[i]++;
                    break;
                }
            }

            switch (listType) {
                case Permutation, Combination -> {
                    boolean unique = true;
                    for (int i = 0; i < element.length; i++) {
                        for (int j = i + 1; j < element.length; j++) {
                            if (element[i] == element[j]) {
                                unique = false;
                                break;
                            }
                        }
                        if (!unique) {
                            break;
                        }
                    }
                    if (unique) {
                        if (listType == ListType.Permutation) {
                            result.add(Arrays.copyOf(element, r));
                        } else {
                            boolean sorted = true;
                            for (int i = 0; i < element.length - 1; i++) {
                                if (element[i] > element[i + 1]) {
                                    sorted = false;
                                    break;
                                }
                            }
                            if (sorted) {
                                result.add(Arrays.copyOf(element, r));
                            }
                        }
                    }
                }
                case Arrangement -> {
                    if (Arrays.stream(element).sum() == n) {
                        result.add(Arrays.copyOf(element, r));
                    }
                }
            }
        }
        int[][] output = new int[result.size()][r];
        for (int i = 0; i < result.size(); i++) {
            System.arraycopy(result.get(i), 0, output[i], 0, r);
        }
        return output;
    }

    /**
     * @param n number of objects
     * @param r number in each choice
     * @return permutation list
     */
    static int[][] getPermutationList(int n, int r) {
        int permutation = nPr(n, r);
        int[][] result = new int[permutation][r];
        int[] step = new int[r];
        int value = 1;
        ArrayList<Integer> used = new ArrayList<>();
        Stack<Integer> available = new Stack<>();
        for (int i = r - 1; i >= 0; i--) {
            step[i] = value;
            value = (n - i) * value;
        }
        for (int i = 0; i < permutation; i++) {
            if (i % step[0] == 0) {
                value = i / step[0] + 1;
            }
            result[i][0] = value;
        }
        for (int i = 1; i < r; i++) {
            for (int j = 0; j < permutation; j++) {
                if (j % step[i - 1] == 0) {
                    used.clear();
                    for (int k = 0; k < i; k++) {
                        used.add(result[j][k]);
                    }
                    available.clear();
                    for (int k = n; k > 0; k--) {
                        if (!used.contains(k)) {
                            available.add(k);
                        }
                    }
                }
                if (j % step[i] == 0) {
                    value = available.pop();
                }
                result[j][i] = value;
            }
        }
        return result;
    }

    static int nCr(int n, int r) {
        int result = nPr(n, r);
        for (int i = 1; i <= r; i++) {
            result /= i;
        }
        return result;
    }

    static int nPr(int n, int r) {
        int result = 1;
        for (int i = n - r + 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public enum ListType {Permutation, Combination, Arrangement}
}
