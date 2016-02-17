package ms.ralph.minecraft.pixels

import java.util.concurrent.atomic.AtomicBoolean

class PixelTask(private val end: Int, private val interval: Long) {
    val thread: Thread
    val running: AtomicBoolean

    init {
        running = AtomicBoolean(true)

        thread = Thread() {
            run {
                for (i in 1..end) {
                    if (!running.get()) {
                        break
                    }
                    if (Pixels.INSTANCE!!.frame != null) {
                        Pixels.INSTANCE!!.injector.inject(i.toString())
                    }
                    Thread.sleep(interval)
                }
            }
        }
    }

    fun start() {
        thread.start()
    }

    fun cancel() {
        running.set(false)
    }
}
