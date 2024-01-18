class NotesMenu(private val archive: ArchivesMenu.Archive) {

    private val menu = Menu(
        mutableListOf(
            MenuItem("Создать заметку", this::createNote),
            *archive.notes.mapIndexed { index, note ->
                MenuItem(
                    "Заметка $index (${note.name})",
                    { this.viewNote(index) })
            }.toTypedArray(),
            MenuItem("Назад", this::goBack)
        )
    )

    fun start() {
        while (true) {
            println("Список заметок в архиве '${archive.name}':")
            // Обновляем пункты меню с заметками перед каждой итерацией
            menu.menuItems.clear()
            menu.menuItems.add(MenuItem("Создать заметку", this::createNote))
            menu.menuItems.addAll(archive.notes.mapIndexed { index, note ->
                MenuItem("Заметка (${note.name})", { this.viewNote(index) })
            })
            menu.menuItems.add(MenuItem("Назад", this::goBack))

            menu.displayMenu()
            val input = menu.readInput()
            menu.executeMenuItem(input)
        }
    }

    private fun createNote() {
        var noteName: String? = null
        var noteText: String? = null
        while (noteName.isNullOrBlank() || noteText.isNullOrBlank()) {
            print("Введите имя заметки: ")
            noteName = readLine()?.trim()
            if (noteName.isNullOrBlank()) {
                println("Пожалуйста, введите корректное имя заметки.")
            } else {
                print("Введите текст заметки: ")
                noteText = readLine()?.trim()
                if (noteText.isNullOrBlank()) {
                    println("Пожалуйста, введите текст заметки.")
                }
            }
        }
        archive.notes.add(ArchivesMenu.Note(noteName!!, noteText!!))
    }

    private fun viewNote(index: Int) {
        val note = archive.notes[index]
        println("Просмотр заметки '${note.name}'")
        println("Текст заметки: ${note.text}")
    }

    private fun goBack() {
        println("Выход в предыдущее меню.")
        val archivesMenu = ArchivesMenu()
        archivesMenu.start()
    }
}