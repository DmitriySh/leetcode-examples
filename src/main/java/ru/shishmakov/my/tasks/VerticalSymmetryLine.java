package ru.shishmakov.my.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Array of points on vertical axis (Y) and horizontal axis (X) make a shape.
 * Vertical line on coordinate system could separate shape on two equal (symmetrical) parts.
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
     * </pre>
     */
    public static final Point[] DEFAULT_POINTS = new Point[]{
            new Point(8, 2), new Point(8, 4),
            new Point(12, 3), new Point(12, 4),
            new Point(13, 6),
            // 16 - vertical line
            new Point(19, 6),
            new Point(20, 1), new Point(20, 3), new Point(20, 4),
            new Point(24, 2), new Point(24, 4),

    };

    private final Point[] points;
    private boolean hasVerticalLine;

    public VerticalSymmetryLine(Point[] points) {
        this.points = points;
    }

    @Override
    public void run() {
        logger.info("Start searching vertical line between the points...");
        logger.info("Array of points: {}", Arrays.toString(points));

        this.hasVerticalLine = defineVerticalLine(points);
        logger.info("Result. {} vertical symmetry line in an array of points", (this.hasVerticalLine ? "Has" : "No"));
    }

    private boolean defineVerticalLine(Point[] points) {
        int minX = 0, maxX = 0;
        var axisMap = new HashMap<Integer, List<Point>>();

        // find min and max points by horizontal axis (X)
        // accumulate all points by x into hashMap
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

        // no shape of points
        // no vertical line
        if (minX == maxX || (maxX - minX) == 1) {
            return false;
        }

        int lineX = minX + (maxX - minX) / 2;
        logger.info("minX: {}, maxX: {}, lineX: {}", minX, maxX, lineX);
        logger.info("Map: {}", axisMap);

        // search symmetrical point from left to right by vertical line
        boolean hasVerticalLine = true;
        for (int i = 0; i < points.length; i++) {
            Point point = points[i];
            int delta = lineX - point.x;

            // has right point?
            List<Point> rightPoints = axisMap.get(lineX + delta);
            if (rightPoints != null) {
                if (!hasSymmetricalSubPoint(point, rightPoints)) {
                    // no right point
                    hasVerticalLine = false;
                    break;
                }
            } else {
                // no right point
                hasVerticalLine = false;
                break;
            }
        }
        return hasVerticalLine;
    }

    private static boolean hasSymmetricalSubPoint(Point leftPoint, List<Point> rightPoints) {
        for (Point rightPoint : rightPoints) {
            if (leftPoint.y == rightPoint.y) {
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
