package presentation;

import entity.Postcard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toSet;

public class SizeMap {
    private final List<Postcard> postcardList;
    private Map<String, Cell> map = new HashMap();
    private Integer[][] result;
    private Integer[] heights;
    private Integer[] widths;

    public SizeMap(List<Postcard> postcardList) {
        this.postcardList = postcardList;
    }

    private void build() {
        for (Postcard postcard : postcardList) {
            String key = postcard.getHeight() + "x" + postcard.getWidth();
            if (map.get(key) == null) {
                map.put(key, new Cell());
            }
            map.get(key).increase();
        }
        heights = postcardList.stream()
                .map(Postcard::getHeight)
                .collect(toSet())
                .stream()
                .sorted()
                .toArray(Integer[]::new);

        widths = postcardList.stream()
                .map(Postcard::getWidth)
                .collect(toSet())
                .stream()
                .sorted()
                .toArray(Integer[]::new);

        result = new Integer[heights.length][widths.length];

        for (int h = 0; h < heights.length; h++) {
            Integer cellHeight = heights[h];
            for (int w = 0; w < widths.length; w++) {
                Integer cellWidth = widths[w];
                result[h][w] = getCell(map, cellHeight, cellWidth);
            }
        }
    }

    private int getCell(Map<String, Cell> map, int height, int wight) {
        Cell cell = map.get(height + "x" + wight);
        return cell != null ? cell.getCount() : 0;
    }

    public Integer[][] get() {
        if (result == null) {
            build();
        }
        return result;
    }

    public int getMaxCount() {
        get();
        return map.values().stream()
                .mapToInt(cell -> cell.getCount())
                .max()
                .getAsInt();
    }

    public Integer[] getHeights() {
        get();
        return heights;
    }

    public Integer[] getWidths() {
        get();
        return widths;
    }

    private class Cell {
        private int count = 0;

        public void increase() {
            count++;
        }

        public int getCount() {
            return count;
        }
    }
}
