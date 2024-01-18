import java.util.Scanner

class ArchivesMenu {
    data class Archive(val name: String, val notes: MutableList<Note>)
    data class Note(val name: String, val text: String)

    private val archives = mutableListOf<Archive>()

    private val menu = Menu(
        mutableListOf(
            MenuItem("Создать архив", this::createArchive),
            MenuItem("Просмотреть архивы", this::viewArchives),
            MenuItem("Выход", this::exit)
        )
    )

    fun start() {
        while (true) {
            menu.displayMenu()
            val input = menu.readInput()
            menu.executeMenuItem(input)
        }
    }

    private fun createArchive() {
        var archiveName: String? = null
        while (archiveName.isNullOrBlank()) {
            print("Введите имя архива: ")
            archiveName = readLine()?.trim()
            if (archiveName.isNullOrBlank()) {
                println("Пожалуйста, введите корректное имя архива.")
            }
        }
        archives.add(Archive(archiveName!!, mutableListOf()))
    }

    private fun viewArchives() {
        if (archives.isEmpty()) {
            println("Пока что нет созданных архивов.")
            return
        }

        println("Список доступных архивов:")
        for (i in 0 until archives.size) {
            println("$i. ${archives[i].name}")
        }
        print("Введите номер архива для просмотра или любой другой номер для выхода:")
        val scanner = Scanner(System.`in`)
        if (scanner.hasNextInt()) {
            val input = scanner.nextInt()
            if (input in 0 until archives.size) {
                val selectedArchive = archives[input]
                val notesMenu = NotesMenu(selectedArchive)
                notesMenu.start()
            }
        }
    }

    private fun exit() {
        System.exit(0)
    }
}