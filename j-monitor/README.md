# j-monitor
  admin

# 
  zipkin以jar包形式启动
  直接打开命令行，执行以下命令，访问localhost:9411即可
  java -jar j-monitor/zipkin/zipkin.jar
  
  注：
      如果zipkin jar包版本过低，请执行以下两条命令重新下载最新包使用
      # 下载zipkin 的jar包
      curl -sSL https://zipkin.io/quickstart.sh | bash -s 
      # 启动zipkin
      java -jar zipkin.jar
      

     