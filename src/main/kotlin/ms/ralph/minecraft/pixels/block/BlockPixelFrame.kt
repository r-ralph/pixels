package ms.ralph.minecraft.pixels.block

import ms.ralph.minecraft.pixels.Direction
import ms.ralph.minecraft.pixels.FrameInfo
import ms.ralph.minecraft.pixels.Pixels
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Items
import net.minecraft.util.ChatComponentText
import net.minecraft.world.World

class BlockPixelFrame : Block(Material.rock) {
    init {
        setBlockName(Pixels.MODID + "_pixel_frame")
        setBlockTextureName(Pixels.MODID + ":frame")
        setCreativeTab(CreativeTabs.tabBlock)
    }

    override fun onBlockActivated(world: World?, x: Int, y: Int, z: Int, player: EntityPlayer?, side: Int, fx: Float, fy: Float, fz: Float): Boolean {
        if (!world!!.isRemote && player!!.currentEquippedItem?.item == Items.stick) {
            val success = search(world, x, y, z)
            if (success) {
                val frame = Pixels.INSTANCE?.frame;
                if (frame != null) {
                    val message = java.lang.String.format("Set frame: x=%d, y=%d, z=%d, dir=%s, length=%d", frame.x, frame.y, frame.z, frame.direction.name, frame.length);
                    player.addChatComponentMessage(ChatComponentText(message))
                }
            } else {
                player.addChatComponentMessage(ChatComponentText("Require at least 2 blocks!"))
            }
            return true
        }
        return false
    }

    fun search(world: World, _x: Int, y: Int, _z: Int): Boolean {
        var x = _x
        var z = _z
        if (world.getBlock(x - 1, y, z) == this || world.getBlock(x + 1, y, z) == this) {
            while (world.getBlock(x - 1, y, z) == this) {
                x -= 1
            }
            var length = 0
            var xTmp = x
            while (world.getBlock(xTmp, y, z) == this) {
                length += 1
                xTmp += 1
            }
            Pixels.INSTANCE?.frame = FrameInfo(world, x, y, z, Direction.X, length)
        } else if (world.getBlock(x, y, z - 1) == this || world.getBlock(x, y, z + 1) == this) {
            while (world.getBlock(x, y, z - 1) == this) {
                z -= 1
            }
            var length = 0
            var zTmp = z
            while (world.getBlock(x, y, zTmp) == this) {
                length += 1
                zTmp += 1
            }
            Pixels.INSTANCE?.frame = FrameInfo(world, x, y, z, Direction.Z, length)
        } else {
            return false
        }
        return true
    }
}
