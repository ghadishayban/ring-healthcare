(defproject ring-healthcare "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.0-master-SNAPSHOT"]
                 [org.apache.camel/camel-core "2.10.2"]
                 [org.apache.camel/camel-hl7 "2.10.2"]
                 [org.apache.camel/camel-mina2 "2.10.2"] 
                 [org.apache.mina/mina-core "2.0.7"]]  
  :repositories [["sonatype" {:snapshots true
                              :url "https://oss.sonatype.org/content/groups/public/"}]])
