package ms.ralph.minecraft.pixels

import cpw.mods.fml.client.registry.RenderingRegistry
import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.event.FMLInitializationEvent
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import cpw.mods.fml.common.event.FMLServerStartingEvent
import cpw.mods.fml.common.registry.GameRegistry
import ms.ralph.minecraft.pixels.block.BlockPixel
import ms.ralph.minecraft.pixels.block.BlockPixelFrame
import ms.ralph.minecraft.pixels.tileentity.TileEntityPixel
import java.io.File

@Mod(modid = Pixels.MODID, version = Pixels.VERSION)
class Pixels {
    val injector: ImageInjector = ImageInjector()
    var frame: FrameInfo? = null
    var imageDir: File? = null

    @EventHandler
    fun preInit(event: FMLPreInitializationEvent) {
        imageDir = File(event.modConfigurationDirectory, "images")
        if (!imageDir!!.exists()) {
            imageDir!!.mkdirs()
        }
    }

    @EventHandler
    fun init(event: FMLInitializationEvent) {
        GameRegistry.registerBlock(BlockPixelFrame(), "frame")
        GameRegistry.registerBlock(blockPixel, "pixel")
        GameRegistry.registerTileEntity(TileEntityPixel::class.java, "pixel")
    }

    @EventHandler
    fun serverLoad(event: FMLServerStartingEvent) {
        event.registerServerCommand(CommandFrame())
    }

    companion object {
        const val MODID = "pixels"
        const val VERSION = "1.0"
        @Mod.Instance
        var INSTANCE: Pixels? = null
        var blockPixel: BlockPixel = BlockPixel()
    }
}
