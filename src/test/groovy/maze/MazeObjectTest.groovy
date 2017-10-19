package maze

import spock.lang.Specification
import spock.lang.Unroll

class MazeObjectTest extends Specification {

    @Unroll
    def 'toMazeObject() "#str" に対して #obj を返す'() {

        expect:
        MazeObject.toMazeObject(str) == obj

        where:
        str | obj
        'S' | MazeObject.START
        'G' | MazeObject.GOAL
        '1' | MazeObject.WALL
        ' ' | MazeObject.PASSAGE
        '?' | null
    }
}
