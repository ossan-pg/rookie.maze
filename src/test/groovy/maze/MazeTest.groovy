package maze

import spock.lang.Specification
import spock.lang.Unroll

import static maze.MazeObject.*

class MazeTest extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    def "parse() パース元の文字列が存在しない場合、IllegalArgumentException をスローする"() {
        given:
        final List<String> strs = []

        when:
        Maze.parse(strs)

        then:
        IllegalArgumentException e = thrown()
    }

    @Unroll
    def "parse() パース元の各文字列の長さが異なる場合、IllegalArgumentException をスローする"() {
        when:
        Maze.parse(strs)

        then:
        IllegalArgumentException e = thrown()

        where:
        _ | strs
        _ | [ 'SG', '111' ]
        _ | [ ' 1 ', 'SG' ]
        _ | [ '   ', 'S1G', ' 1'  ]
    }

    def "parse() パース元の文字列に迷路に使用できない文字が含まれている場合、IllegalArgumentException をスローする"() {
        given:
        final List<String> strs = [ 'SG2' ]

        when:
        Maze.parse(strs)

        then:
        IllegalArgumentException e = thrown()
    }

    def "parse() パース元の文字列にスタートが存在しない場合、IllegalArgumentException をスローする"() {
        given:
        final List<String> strs = [ 'G' ]

        when:
        Maze.parse(strs)

        then:
        IllegalArgumentException e = thrown()
    }

    def "parse() パース元の文字列にスタートが複数存在する場合、IllegalArgumentException をスローする"() {
        given:
        final List<String> strs = [ 'SSG' ]

        when:
        Maze.parse(strs)

        then:
        IllegalArgumentException e = thrown()
    }

    def "parse() パース元の文字列にゴールが存在しない場合、IllegalArgumentException をスローする"() {
        given:
        final List<String> strs = [ 'S' ]

        when:
        Maze.parse(strs)

        then:
        IllegalArgumentException e = thrown()
    }

    def "parse() パース元の文字列にゴールが複数存在する場合、IllegalArgumentException をスローする"() {
        given:
        final List<String> strs = [ 'SGG' ]

        when:
        Maze.parse(strs)

        then:
        IllegalArgumentException e = thrown()
    }

    @Unroll
    def "parse() 迷路用の文字列をパースし、Maze を作成する"() {
        when:
        final Maze maze = Maze.parse(strs)

        then:
        maze.width == strs[0].size()
        maze.height == strs.size()
        maze.mazeObjs == mazeObjs

        where:
        strs | mazeObjs
        [ 'SG' ] | [ [ START, GOAL ] ]
        [ 'GS' ] | [ [ GOAL, START  ] ]
        [ 'S', 'G' ] | [ [ START ], [ GOAL ] ]
        [ 'G', 'S' ] | [ [ GOAL ], [ START ] ]
        [ 'S G', '111' ] | [ [ START, PASSAGE, GOAL ], [ WALL, WALL, WALL ] ]
        [ '111', 'S G', '1 1' ] | [ [ WALL, WALL, WALL ], [ START, PASSAGE, GOAL ], [ WALL, PASSAGE, WALL ] ]
    }

}
