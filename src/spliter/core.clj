(ns spliter.core
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
  "Returns the fname string with the fnumber added between the file extension"
 (str (:name (split-file-name fname)) (str fnumber (:extension (split-file-name fname)))))

(defn add-prefix-suffix [prefix suffix lines]
  "Returns a new list with the prefix and suffix added to it"
  ())

(defn split-file [n fname]
  "Splits a fname file into n other files"
  (let [partitioned-file (partition-all n (read-lines fname))
        files-count (count partitioned-file)]
    (doseq [[idx lines] (map-indexed vector partitioned-file)]
      (write-lines (get-file-name fname (+ idx 1)) lines))))