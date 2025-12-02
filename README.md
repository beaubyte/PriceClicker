# PriceClicker

PriceClicker is a Java program built with the JavaFX framework. It was built as a CS project in October of 2024. It has a basic progression system, where you continue to click a burger and eventually can unlock things that will click for you, and items that will improve the value of each click you do. It can also write and load save data to a file.

### Dependencies
- `openjdk-11-jdk`
- `openjfx`

### Compilation Instructions
- `git clone` the repo into a local folder on your computer
- Ensure that Java 11 is the current set Java version. You may also have to adjust the `openjfx` library location depending on your computer's operating system.
- Run `javac --module-path /usr/share/openjfx/lib --add-modules=javafx.base,javafx.controls,javafx.graphics -d ./ *.java` to compile the program into class files.
- run `java --module-path /usr/share/openjfx/lib --module-path /usr/share/openjfx/lib --add-modules=javafx.base,javafx.controls,javafx.graphics Main` to launch the program.

> Why not compile the files into a .jar?

Because of the virtual machine environment we were working with in the class, the files had to be compiled into classes instead of jars so that the autostart function would work. The virtual machine environment is also why my project does not use the traditional Java project folder structure.
