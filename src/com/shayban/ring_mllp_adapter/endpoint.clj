(ns com.shayban.ring-mllp-adapter.endpoint
  (:import [org.apache.camel Processor Exchange]
           [org.apache.camel.builder RouteBuilder]
           [org.apache.camel.impl SimpleRegistry DefaultCamelContext]
           org.apache.camel.component.hl7.HL7MLLPCodec))

(defn- proxy-processor
  [handler]
  (proxy [Processor] []
    (process [^Exchange excg]
      (let [input (.. excg .getIn (.getBody String))
            output (handler input)] 
        (.. excg .getOut (.setBody output))))))

(defn- build-url [port]
  (format "mina2:tcp://0.0.0.0:%s?sync=true&codec=#hl7mllp" port))

(defn- route-builder [url handler]
  (proxy [RouteBuilder] []
    (configure []
      (.. this
        (from url)
        (process (proxy-processor handler))))))

(defn- start-camel-context [routebuilder]
  (let [r (SimpleRegistry.)
        ctx (doto (DefaultCamelContext. r))]
    (.put r "hl7mllp" (HL7MLLPCodec.))
    (.addRoutes ctx  routebuilder)
    (.start ctx)
    ctx))

(defn run-listener [handler {:keys [port]}]
  (let [rb (route-builder (build-url port) handler)]
    (start-camel-context rb)))
