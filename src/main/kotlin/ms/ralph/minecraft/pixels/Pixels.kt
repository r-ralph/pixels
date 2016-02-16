package ms.ralph.minecraft.pixels

import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.event.FMLInitializationEvent
import cpw.mods.fml.common.registry.GameRegistry
import ms.ralph.minecraft.pixels.block.BlockPixelFrame

@Mod(modid = Pixels.MODID, version = Pixels.VERSION)
class Pixels {

    @EventHandler
    fun init(event: FMLInitializationEvent) {
        GameRegistry.registerBlock(BlockPixelFrame(), "frame")
    }

    companion object {
        const val MODID = "pixels"
        const val VERSION = "1.0"
    }
}
