import kr.cosine.menu.data.Action
import kr.cosine.menu.data.Sound
import kotlin.test.Test

class TestActionParser {
    @Test
    fun test() {
        val line = "SOUND:minecraft:entity.level.up:1.0:1.0"
        val action = Action.of(line)

        if (action is Sound) {
            println(action.name)
        }
    }
}