package ms.ralph.minecraft.pixels.block

import ms.ralph.minecraft.pixels.Pixels
import ms.ralph.minecraft.pixels.tileentity.TileEntityPixel
import net.minecraft.block.BlockContainer
import net.minecraft.block.material.Material
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.tileentity.TileEntity
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World

class BlockPixel() : BlockContainer(Material.rock) {
    init {
        setBlockName(Pixels.MODID + "_pixel")
        setBlockTextureName(Pixels.MODID + ":pixel")
        setCreativeTab(CreativeTabs.tabBlock)
    }

    override fun createNewTileEntity(world: World, metadata: Int): TileEntity {
        return TileEntityPixel()
    }

    override fun colorMultiplier(world: IBlockAccess, x: Int, y: Int, z: Int): Int {
        return (world.getTileEntity(x, y, z) as TileEntityPixel ).color
    }
}
