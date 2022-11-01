public class Main {
    public static void main(String[] args) {
        int n = 5;
        int r = 3;
        int atLeast = 1;

//        int[][] output = Combinatorics.getList(n, r, Combinatorics.ListType.Arrangement);
//        for (int[] value : output) {
//            System.out.println(Arrays.toString(value));
//        }
//        System.out.println(output.length);
//        System.out.println();
//        output = Combinatorics.getList(n, r, Combinatorics.ListType.Combination);
//        for (int[] value : output) {
//            System.out.println(Arrays.toString(value));
//        }

//        System.out.printf("nPr = %d%n", Helper.nPr(n, r));
//        System.out.println("Permutation list is");
//        int[][] output = Helper.getPermutationList(n, r);
//        for (int[] value : output) {
//            System.out.println(Arrays.toString(value));
//        }
//        System.out.println();
//
//        System.out.printf("nCr = %d%n", Helper.nCr(n, r));
//        System.out.println("Combination list is");
//        output = Helper.getCombinationList(n, r);
//        for (int[] value : output) {
//            System.out.println(Arrays.toString(value));
//        }
//        System.out.println();
//
//        output = Helper.getArrangementList(n, r, atLeast);
//        int total = 0;
//        for (int[] ints : output) {
//            System.out.println(Arrays.toString(ints));
//            int object = n;
//            int subtotal = 1;
//            int partial;
//            for (int i = 0; i < ints.length; i++) {
//                partial = Helper.nCr(object, ints[i]);
//                System.out.printf("%dC%d = %d", object, ints[i], partial);
//                if (i < ints.length - 1) {
//                    System.out.print(" * ");
//                }
//                object -= ints[i];
//                subtotal *= partial;
//            }
//            System.out.printf(" = %d%n", subtotal);
//            total += subtotal;
//        }
//        System.out.printf("Total: %d%n", total);
//        System.out.println();
//
//        System.out.printf("Arrangement number of n in r = %d%n", Helper.getArrangementListNumber(n, r));
//        System.out.println("Arrangement list is");
//        output = Helper.getArrangementList1(n, r);
//        for (int[] outs : output) {
//            System.out.println(Arrays.toString(outs));
//        }

//        System.out.println(Arrays.toString(Fibonacci.getFibonacciList(0, 19)));
//        System.out.println(Fibonacci.getFibonacciNumber( 10000));

        System.out.println(ContinuedFraction.getRepeatedFraction(new int[]{3, 7, 15, 1, 292}, new int[]{1, 1, 1, 1}));
    }
}
