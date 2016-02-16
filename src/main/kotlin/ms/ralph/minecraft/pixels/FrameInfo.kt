package ms.ralph.minecraft.pixels

import net.minecraft.world.World

data class FrameInfo(val world: World, val x: Int, val y: Int, val z: Int, val direction: Direction, val length: Int)

enum class Direction {
    X, Z
}