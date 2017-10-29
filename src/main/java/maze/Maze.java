package maze;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Maze {

    private final int width;
    private final int height;
    private final List<List<MazeObject>> mazeObjs;

    private Maze(List<List<MazeObject>> mazeObjs) {
        this.width = mazeObjs.get(0).size();
        this.height = mazeObjs.size();
        // mazeObjs 内の各 MazeObject を置き換える処理があるため
        // List の実装を ArrayList に変更する
        this.mazeObjs = mazeObjs.stream()
                .map(ArrayList::new)
                // .map(e -> new ArrayList<>(e))
                .collect(Collectors.toList());
    }

    /**
     * 迷路を示す文字列のリストをパースし、{@code Maze} を作成します。<br>
     * <br>
     * リストの 1個目の文字列が迷路の 1行目、2行目の文字列が迷路の 2行目、…、
     * n行目の文字列が迷路の n行目になるように変換します。<br>
     * <br>
     * 変換前の文字と変換後の迷路内のオブジェクトは下記のように対応します。
     * <table border>
     *     <tr><th>文字</th><th>迷路内のオブジェクト</th></tr>
     *     <tr><td>"S"</td> <td>スタート</td></tr>
     *     <tr><td>"G"</td> <td>ゴール</td></tr>
     *     <tr><td>" "</td> <td>通路</td></tr>
     *     <tr><td>"1"</td> <td>壁</td></tr>
     * </table>
     * <br>
     * 指定された文字列のリストから {@code Maze} を作成できない場合、
     * {@code IllegalArgumentException} をスローします。
     *
     * @param mazeStrs 作成する {@code Maze} の元になる文字列のリスト。
     * @return {@code mazeStrs} をパースして作成した {@code Maze} 。
     *
     * @throws NullPointerException {@code mazeStrs} が {@code null} の場合。
     * @throws IllegalArgumentException {@code mazeStrs} が下記のいずれかに該当する場合。
     * <ul>
     *     <li>要素の個数が 0 である(＝迷路を示す文字列が存在しない)。</li>
     *     <li>異なる長さの文字列が含まれている(＝迷路の幅が統一されていない)。</li>
     *     <li>迷路に使用できない文字が含まれている。</li>
     *     <li>スタートの個数が 1 ではない。</li>
     *     <li>ゴールの個数が 1 ではない。</li>
     * </ul>
     */
    public static Maze parse(final List<String> mazeStrs)
            throws NullPointerException, IllegalArgumentException {

        Objects.requireNonNull(mazeStrs);

        if(mazeStrs.isEmpty()) {
            throw new IllegalArgumentException("迷路を示す文字列が存在しません。");
        }

        final int width = mazeStrs.get(0).length();
        final long invalidLines = mazeStrs.stream().filter(s -> s.length() != width).count();
        if(invalidLines > 0) {
            throw new IllegalArgumentException("迷路の幅が統一されていません。");
        }

        final List<List<MazeObject>> list = mazeStrs.stream()
                .map(s -> s.split(""))
                .map(arr -> Arrays.stream(arr)
                        .map(MazeObject::toMazeObject)
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());

        final List<MazeObject> cells = list.stream().flatMap(List::stream).collect(Collectors.toList());
        final long invalidObjs = cells.stream().filter(Objects::isNull).count();
        if(invalidObjs > 0) {
            throw new IllegalArgumentException("迷路に使用できない文字が含まれています。");
        }

        final long starts = cells.stream().filter(MazeObject.START::equals).count();
        if(starts > 1) {
            throw new IllegalArgumentException("スタートが複数存在します。");
        } else if(starts < 1) {
            throw new IllegalArgumentException("スタートが存在しません。");
        }
        final long goals = cells.stream().filter(MazeObject.GOAL::equals).count();
        if(goals > 1) {
            throw new IllegalArgumentException("ゴールが複数存在します。");
        } else if(goals < 1) {
            throw new IllegalArgumentException("ゴールが存在しません。");
        }

        return new Maze(list);
    }

    public int solve() {
        // TODO 実装する
        return 0;
    }

    public void show() {
        // TODO 実装する
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Maze)) return false;
        Maze maze = (Maze) o;
        return width == maze.width &&
                height == maze.height &&
                Objects.equals(mazeObjs, maze.mazeObjs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height, mazeObjs);
    }

}
