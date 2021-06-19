cd ../../../../target

java -agentlib:jdwp=transport=dt_socket,address=6006,server=y,suspend=n -Duser.timezone=GMT -XX:+HeapDumpOnOutOfMemoryError -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.port=1198 -Dcom.sun.management.jmxremote.rmi.port=55558 -Dspring.profiles.active=default -jar europeangateway-app-exe.jar