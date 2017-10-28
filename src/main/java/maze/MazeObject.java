package maze;

import java.util.Map;

import static java.util.Map.entry;

/**
 * 迷路オブジェクトを示す列挙型です。
 */
public enum MazeObject {

    START,
    GOAL,
    WALL,
    PASSAGE,

    /*
     * 下記は方向を示す値であり、迷路オブジェクトではないが、
     * 迷路を解く処理の都合上 MazeObject として扱えるほうが
     * 便利なので定義しておく。
     * 対応する文字列表現は存在しないため、
     * MazeObject.toMazeObject() で取得することはできない。
     */
    UP,
    DOWN,
    LEFT,
    RIGHT,
    ;

    private static Map<String, MazeObject> map = Map.ofEntries(
            entry("S", START),
            entry("G", GOAL),
            entry("1", WALL),
            entry(" ", PASSAGE)
    );

    /**
     * {@code s} に対応する {@code MazeObject} を返します。<br>
     * 対応する {@code MazeObject} が存在しない場合は {@code null} を返します。
     *
     * @param s 迷路オブジェクトの文字列表現。
     * @return {@code s} に対応する {@code MazeObject} 。<br>
     * 対応する {@code MazeObject} が存在しない場合は {@code null} 。
     */
    public static MazeObject toMazeObject(String s) {

        return map.get(s);
    }
}
