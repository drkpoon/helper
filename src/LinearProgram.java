import java.awt.geom.Point2D;
import java.util.ArrayList;

public class LinearProgram {

    private Point2D.Float[] boundedRegion;

    public LinearProgram(Inequality[] inequalities) {
        int[][] combinationList = Combinatorics.getCombinationList(inequalities.length, 2);
        ArrayList<Point2D.Float> vertices = new ArrayList<>();
        for (int[] ints : combinationList) {
            int eq1 = ints[0] - 1;
            int eq2 = ints[1] - 1;
            float det = inequalities[eq1].xCoefficient * inequalities[eq2].yCoefficient -
                    inequalities[eq2].xCoefficient * inequalities[eq1].yCoefficient;
            if (det != 0) {
                float x = (inequalities[eq2].yCoefficient * inequalities[eq1].constant -
                        inequalities[eq1].yCoefficient * inequalities[eq2].constant) / det;
                float y = (inequalities[eq1].xCoefficient * inequalities[eq2].constant -
                        inequalities[eq2].xCoefficient * inequalities[eq1].constant) / det;
                boolean match = true;
                for (int j = 0; j < inequalities.length; j++) {
                    if (j != eq1 && j != eq2) {
                        float distance = inequalities[j].xCoefficient * x + inequalities[j].yCoefficient * y;
                        switch (inequalities[j].sign) {
                            case GREATER -> {
                                if (distance < inequalities[j].constant) {
                                    match = false;
                                }
                            }
                            case LESS -> {
                                if (distance > inequalities[j].constant) {
                                    match = false;
                                }
                            }
                        }
                    }
                    if (!match) break;
                }
                if (match) vertices.add(new Point2D.Float(x, y));
            }
        }
        boundedRegion = new Point2D.Float[vertices.size()];
        boundedRegion = vertices.toArray(boundedRegion);
    }

    public Point2D.Float[] getBoundedRegion() {
        return boundedRegion;
    }

    public int getBoundedSize() {
        return boundedRegion.length;
    }

    float getOptimizeValue(float optX, float optY, Inequality.optimize optimize) {
        if (getBoundedSize() == 0) return 0;
        float result = 0;
        switch (optimize) {
            case MAXIMISE -> {
                result = Float.MIN_VALUE;
            }
            case MINIMISE -> {
                result = Float.MAX_VALUE;
            }
        }
        for (Point2D.Float aFloat : boundedRegion) {
            float res = optX * aFloat.x + optY * aFloat.y;
            switch (optimize) {
                case MAXIMISE -> {
                    if (res > result) {
                        result = res;
                    }
                }
                case MINIMISE -> {
                    if (res < result) {
                        result = res;
                    }
                }
            }
        }
        return result;
    }
}
