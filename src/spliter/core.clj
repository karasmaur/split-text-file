(ns spliter.core
  (:gen-class)
  (:require [clojure.string :as str]))

(defn read-lines [fname]
  "Returns a lazy-seq with each line from a fname file"
  (with-open [r (clojure.java.io/reader fname)]
    (doall (line-seq r))))

(defn write-lines [fname lines]
  "Writes a sequence of strings into a file"
  (with-open [w (clojure.java.io/writer fname)]
    (doseq [line lines]
      (.write w line)
      (.newLine w))))

(defn split-file-name [fname]
  "Returns a map with the file name and file extension.
  'test.sql' -> {:name 'test' :extension '.sql'}"
  (if (.contains fname ".")
    (let [split-name (str/split fname #"\.")]
      {:name (nth split-name 0) :extension (str "." (nth split-name 1))})
    {:name fname :extension ""}))

(defn get-file-name [fname fnumber]
  "Returns the fname string with the fnumber added between it and the file extension"
  (str (:name (split-file-name fname)) (str fnumber (:extension (split-file-name fname)))))

(defn add-prefix-suffix [prefix suffix lines]
  "Returns a new list with the prefix and suffix added to it"
  (concat (reverse (concat (reverse lines) prefix)) suffix))

(defn split-file [n fname prefix suffix]
  "Splits a fname file into n other files and add the prefix and suffix in each new file"
  (let [partitioned-file (partition-all n (read-lines fname))]
    (doseq [[idx lines] (map-indexed vector partitioned-file)]
      (write-lines (get-file-name fname (+ idx 1)) (add-prefix-suffix prefix suffix lines)))))

(defn -main [& args]
  (println "File Splitter! Usage(args): java -jar 900 split_file.txt prefix.txt sufix.txt")
  (println "This will split the 'split_file.txt' into other files with 900 lines each with the prefix and sufix")
  (println "Splitting files!"
           (split-file
             (Integer/parseInt (nth args 0))
             (nth args 1)
             (try (read-lines (nth args 2)) (catch java.lang.IndexOutOfBoundsException e (list)))
             (try (read-lines (nth args 3)) (catch java.lang.IndexOutOfBoundsException e (list))))))