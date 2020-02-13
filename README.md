# Text File Splitter

A simple program to split large text files into smaller ones. 

You can choose the amount of lines that each split file will have and also provide two files containing lines that
will be added in the head and foot of each split file(I've called it prefix and suffix files for some reason).

## Usage

The project is set up so you can generate an uberjar, so assuming that you already have Java 8 and [leiningen](https://leiningen.org/) installed, you can just clone the repo, 
navigate into the directory and run the "lein uberjar" command which will generate the jar file containing the program.
The file will be saved in the target directory as "spliter-0.1.0-SNAPSHOT-standalone.jar".

When you run the jar file, you need to pass four arguments, number of lines in each split file, the file to split, the prefix and suffix files. The last two, prefix and sufix,
should be files contains lines that you want to add on the head and footer of each split file.

Here's an example of usage:

java -jar target/spliter-0.1.0-SNAPSHOT-standalone.jar 900 test.txt prefix.txt suffix.txt