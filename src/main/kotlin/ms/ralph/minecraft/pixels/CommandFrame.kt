package ms.ralph.minecraft.pixels

import net.minecraft.command.ICommand
import net.minecraft.command.ICommandSender
import net.minecraft.util.ChatComponentText
import java.util.*

class CommandFrame : ICommand {
    var aliases: ArrayList<String>

    init {
        aliases = ArrayList();
        aliases.add("frame")
        aliases.add("f")
    }

    override fun getCommandName(): String? {
        return "frame"
    }

    override fun getCommandUsage(sender: ICommandSender?): String? {
        return "frame <name>"
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
        if (args.size == 0) {
            sender.addChatMessage(ChatComponentText("Invalid argument"))
            return
        }
        if (Pixels.INSTANCE!!.frame == null) {
            sender.addChatMessage(ChatComponentText("Frame hasn't be selected"))
            return
        }
        Pixels.INSTANCE!!.injector.inject(args[0])
    }
}
