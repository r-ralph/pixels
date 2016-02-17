package ms.ralph.minecraft.pixels.command

import ms.ralph.minecraft.pixels.PixelTask
import ms.ralph.minecraft.pixels.Pixels
import net.minecraft.command.ICommand
import net.minecraft.command.ICommandSender
import net.minecraft.util.ChatComponentText
import java.util.*

class CommandStart : ICommand {
    var aliases: ArrayList<String>

    init {
        aliases = ArrayList();
        aliases.add("pixel-start")
        aliases.add("ps")
    }

    override fun getCommandName(): String? {
        return "pixel-start"
    }

    override fun getCommandUsage(sender: ICommandSender?): String? {
        return "pixel-start <end> <interval>"
    }

    override fun getCommandAliases(): MutableList<String>? {
        return aliases
    }

    override fun compareTo(other: Any?): Int {
        return commandName?.compareTo((other as? ICommand)?.commandName!!) ?: 0
    }

    override fun addTabCompletionOptions(sender: ICommandSender?, args: Array<out String>?): MutableList<Any?>? {
        return null
    }

    override fun canCommandSenderUseCommand(sender: ICommandSender?): Boolean {
        return true
    }

    override fun isUsernameIndex(args: Array<out String>?, i: Int): Boolean {
        return false
    }

    override fun processCommand(sender: ICommandSender, args: Array<out String>) {
        if (args.size != 2) {
            sender.addChatMessage(ChatComponentText("Invalid argument"))
            return
        }
        Pixels.INSTANCE!!.currentTask?.cancel()
        Pixels.INSTANCE!!.currentTask = PixelTask(Integer.parseInt(args[0]), Integer.parseInt(args[1]).toLong())
        Pixels.INSTANCE!!.currentTask!!.start()
    }
}