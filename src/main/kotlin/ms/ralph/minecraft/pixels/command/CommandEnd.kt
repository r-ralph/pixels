package ms.ralph.minecraft.pixels.command

import ms.ralph.minecraft.pixels.Pixels
import net.minecraft.command.ICommand
import net.minecraft.command.ICommandSender
import java.util.*

class CommandEnd : ICommand {
    var aliases: ArrayList<String>

    init {
        aliases = ArrayList();
        aliases.add("pixel-end")
        aliases.add("pe")
    }

    override fun getCommandName(): String? {
        return "pixel-end"
    }

    override fun getCommandUsage(sender: ICommandSender?): String? {
        return "pixel-end"
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
        Pixels.INSTANCE!!.currentTask?.cancel()
    }
}