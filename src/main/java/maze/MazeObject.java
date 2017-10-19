package maze;

import java.util.Map;

import static java.util.Map.entry;

/**
 * 迷路内のオブジェクトを示す列挙型です。
 */
public enum MazeObject {

    START,
    GOAL,
    WALL,
    PASSAGE,
    ;

    private static Map<String, MazeObject> map =Map.ofEntries(
            entry("S", START),
            entry("G", GOAL),
            entry("1", WALL),
            entry(" ", PASSAGE));

    /**
     * {@code s} に対応する {@code MazeObject} を返します。<br>
     * 対応する {@code MazeObject} が存在しない場合は {@code null} を返します。
     *
     * @param s 迷路内のオブジェクトの文字列表現。
     * @return {@code s} に対応する {@code MazeObject} 。<br>
     * 対応する {@code MazeObject} が存在しない場合は {@code null} 。
     */
    public static MazeObject toMazeObject(String s) {

        return map.get(s);
    }
}
