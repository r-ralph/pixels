package ms.ralph.minecraft.pixels.tileentity

import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.NetworkManager
import net.minecraft.network.Packet
import net.minecraft.network.play.server.S35PacketUpdateTileEntity
import net.minecraft.tileentity.TileEntity

class TileEntityPixel : TileEntity() {

    var color: Int = 0

    override fun writeToNBT(nbtTagCompound: NBTTagCompound) {
        super.writeToNBT(nbtTagCompound)
        nbtTagCompound.setInteger("color", color)
    }

    override fun readFromNBT(nbtTagCompound: NBTTagCompound) {
        super.readFromNBT(nbtTagCompound)
        color = nbtTagCompound.getInteger("color")
    }

    override fun getDescriptionPacket(): Packet {
        val nbt: NBTTagCompound = NBTTagCompound()
        writeToNBT(nbt);
        return S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, nbt);
    }

    override fun onDataPacket(net: NetworkManager, pkt: S35PacketUpdateTileEntity) {
        readFromNBT(pkt.func_148857_g());
    }
}
