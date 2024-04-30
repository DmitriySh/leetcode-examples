package ru.shishmakov.my.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * You have:
 * <ul>
 *   <li> a cartesian coordinate system with vertical axis (Y) and horizontal axis (X);</li>
 *   <li> array of `points` with positive integer coordinates (x1, y1) that form a shape</li>
 * </ul>
 * You need to find out if there is a vertical line in the coordinate system dividing the array into two equal (symmetrical) sets of points.
 */
public class VerticalSymmetryLine implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    /**
     * Example of points on coordinate system
     * <pre>
     *   ^ Y
     *  6|             *  |  *
     *   |                |
     *  4|        *   *   |   *   *
     *   |            *   |   *
     *  2|        *       |       *
     *   |                |   *
     *   |___________________________________> X
     * (0,0)  4   8   12  16  20  24  28  32
     *
     * Point {x=20, y=1} - asymmetrical
     * Other points are symmetrical by vertical line
     * </pre>
     */
    public static final Point[] DEFAULT_POINTS = new Point[]{
            new Point(8, 2), new Point(8, 4),
            new Point(12, 3), new Point(12, 4),
            new Point(13, 6),
            // x=16 - vertical line
            new Point(19, 6),
            new Point(20, 1), new Point(20, 3), new Point(20, 4),
            new Point(24, 2), new Point(24, 4),

    };

    private final Point[] points;
    private boolean verticalLine;

    public VerticalSymmetryLine(Point[] points) {
        this.points = points;
    }

    public boolean hasVerticalLine() {
        return verticalLine;
    }

    @Override
    public void run() {
        logger.info("Start searching vertical line between the points...");
        logger.info("Array of points: {}", Arrays.toString(points));

        this.verticalLine = defineVerticalLine(points);
        logger.info("Result. {} vertical symmetry line in an array of points", (this.verticalLine ? "Has" : "No"));
    }

    private boolean defineVerticalLine(Point[] points) {
        int minX = 0, maxX = 0;
        var axisMap = new HashMap<Integer, List<Point>>();

        // find min and max points by horizontal axis (X)
        // accumulate all points by X into HashMap
        for (int i = 0; i < points.length; i++) {
            Point point = points[i];
            if (i == 0) {
                minX = point.x;
                maxX = point.x;
            }
            if (minX > point.x) minX = point.x;
            if (maxX < point.x) maxX = point.x;

            axisMap.merge(point.x, new ArrayList<>(List.of(point)), (old, next) -> {
                old.addAll(next);
                return old;
            });
        }

        // if no shape from array of points
        // if no vertical line in array of points
        if (minX == maxX || (maxX - minX) == 1) {
            return false;
        }

        int lineX = minX + (maxX - minX) / 2;
        logger.info("minX: {}, maxX: {}, calculated lineX: {}", minX, maxX, lineX);
        logger.info("Map of points by X: {}", axisMap);

        // search symmetrical point from left to right by vertical line
        boolean hasVerticalLine = true;
        for (Point point : points) {
            int delta = lineX - point.x;

            // has symmetrical point?
            List<Point> symmetricalPoints = axisMap.get(lineX + delta);
            if (symmetricalPoints != null) {
                if (!hasSymmetricalSubPoint(point, symmetricalPoints)) {
                    // no right point
                    hasVerticalLine = false;
                    break;
                }
            } else {
                // no symmetrical point
                hasVerticalLine = false;
                break;
            }
        }
        return hasVerticalLine;
    }

    private static boolean hasSymmetricalSubPoint(Point mainPoint, List<Point> symmetricalPoints) {
        for (Point symmetricalPoint : symmetricalPoints) {
            if (mainPoint.y == symmetricalPoint.y) {
                return true;
            }
        }
        return false;
    }

    public record Point(int x, int y) {
        @Override
        public String toString() {
            return "{x=" + x + ", y=" + y + "}";
        }
    }

    public static void main(String[] args) {
        new VerticalSymmetryLine(VerticalSymmetryLine.DEFAULT_POINTS).run();
    }
}
