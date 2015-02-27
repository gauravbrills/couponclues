/**
 * 
 */
package com.sapient.couponclues.predictor;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

/**
 * @author grawat
 */

public class MatrixOfObjects { // class declaration should not have a "()"
    @Getter
    private Map<String, Map<String, Integer>> map_use;

    public MatrixOfObjects() {

        map_use = new HashMap<>();
    }

    /*
     * public void emptyCellContents(final Integer row, final Integer col) { // unlike lists, arrays do not have a "set()" method.
     * You have to use standard array assignment map_use[row][col] = null; }
     */

    public Integer getCellContents(final String row, final String col) {

        Map<String, Integer> map = map_use.get(row);
        return map != null ? map.get(col) != null ? map.get(col) : 0 : 0;
    }

    public Integer getRowCount(final String row) {

        Integer count = 0;
        Map<String, Integer> map = map_use.get(row);
        if (map == null)
            return 0;
        for (String col : map.keySet()) {
            count += map.get(col);
        }
        return count;
    }

    public Boolean isCellUsed(final String row, final String col) {

        Map<String, Integer> map = map_use.get(row);
        return map.get(col) != null;
    }

    public void setCellContents(final Integer e, final String row, final String col) {

        Map<String, Integer> mapSub = map_use.get(row);
        if (mapSub == null)
            mapSub = new HashMap<String, Integer>();
        mapSub.put(col, e);
        map_use.put(row, mapSub);
    }
}
