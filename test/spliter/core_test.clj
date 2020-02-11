(ns spliter.core-test
  (:require [clojure.test :refer :all]
            [spliter.core :refer :all]))

(deftest a-test
  (testing "FIXME, I fail."
    (is (= 1 1))))

(deftest split-file-name-test
  (testing (is (= {:name "test" :extension ".txt"} (split-file-name "test.txt")))))

(deftest split-file-name-test-1
  (testing (is (= {:name "test" :extension ".txt"} (split-file-name "test.txt")))))

(deftest split-file-name-test-2
  (testing (is (= {:name "test" :extension ""} (split-file-name "test")))))

(deftest get-file-name-test-1
  (testing (is (= "test3.txt" (get-file-name "test.txt" 3)))))

(deftest get-file-name-test-2
  (testing (is (= "test3" (get-file-name "test" 3)))))

(deftest add-prefix-suffix-test
  (testing (is (= (list "begin" "line;" "line;" "commit;" "end;")
                  (add-prefix-suffix (list "begin") (list "commit;" "end;") (list "line;" "line;"))))))

