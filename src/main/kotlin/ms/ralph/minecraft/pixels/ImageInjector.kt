package ms.ralph.minecraft.pixels

import ms.ralph.minecraft.pixels.tileentity.TileEntityPixel
import net.minecraft.world.World
import java.awt.Image
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class ImageInjector {

    fun inject(name: String) {
        val file: File = File(Pixels.INSTANCE!!.imageDir!!, name + ".png")
        val image: BufferedImage = ImageIO.read(file)

        // Calc size
        val frame: FrameInfo = Pixels.INSTANCE!!.frame!!
        val ratio = image.height.toFloat() / image.width.toFloat()
        val width = frame.length
        val height = Math.round(width * ratio)

        // Create scaled image
        val scaled: BufferedImage = BufferedImage(width, height, image.type)
        scaled.graphics.drawImage(image.getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING), 0, 0, width, height, null);

        // Reflect image into block
        val world: World = frame.world
        if (frame.direction == Direction.X) {
            val z = frame.z
            for (x in frame.x..(frame.x + width - 1)) {
                for (h in 0..(height - 1)) {
                    val y = frame.y + height - h
                    if (world.getBlock(x, y, z) != Pixels.blockPixel) {
                        world.setBlock(x, y, z, Pixels.blockPixel)
                    }
                    val tileEntity: TileEntityPixel = world.getTileEntity(x, y, z) as TileEntityPixel
                    tileEntity.color = scaled.getRGB(x - frame.x, h)
                    world.markBlockForUpdate(x, y, z)
                }
            }
        } else {
            val x = frame.x
            for (z in frame.z..(frame.z + width - 1)) {
                for (h in 0..(height - 1)) {
                    val y = frame.y + height - h
                    if (world.getBlock(x, y, z) != Pixels.blockPixel) {
                        world.setBlock(x, y, z, Pixels.blockPixel)
                    }
                    val tileEntity: TileEntityPixel = world.getTileEntity(x, y, z) as TileEntityPixel
                    tileEntity.color = scaled.getRGB(z - frame.z, h)
                    world.markBlockForUpdate(x, y, z)
                }
            }
        }
    }
}
