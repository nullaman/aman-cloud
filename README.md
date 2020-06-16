# aman-cloud

##### payment模拟支付模块
##### order模型订单模块

# eureka7001 7002 集群
服务注册与发现、eureka自我保护机制...

# payment8001 8002集群 order80
1.Ribbon负载均衡算法LB(轮询、随机...)
 - com.netflix.loadbalancer.RoundRobinRule 轮询
 - com.netflix.loadbalancer.RandomRule 随机
 - com.netflix.loadbalancer.RetryRule 先按照RoundRobinRule的策略获取服务，如果获取服务失败则再指定时间内重试，获取可用服务
 - WeightResponseTimeRule 对RoundRobinRule的扩展，响应速度越快的实例选择权重越大
 - BestAvailableRule 会先过滤掉由于多次访问故障而处于断路器跳闸状态的服务，然后选择一个并发量最小的服务
 - AvailabilityFilterRule 先过滤掉故障实例，再选择并发较小的实例
 - ZoneAvoidanceRule 默认规则，复合判断server所在区域的性能和server的可用性选择服务器

2.自定义访问策略

3.手写实现【轮询算法】(自旋.)


# feign-order80 open-feign
1.服务间调用

2.超时控制

3.日志级别
# feign-hystrix 断路器

1.服务降级：兜底的方法处处理，作服务降级fallbac

2.服务熔断：配置阈值、失败率...(一段时间之后，默认是5秒，这个断路器是半开状态，会让其中一个请求进行转发，如果成功，断路器会关闭，若失败，继续开启，重复等待判断直至关闭)

3.服务限流：见之后alibaba的sentine


# feign-hystrix-dashboard 服务监控

# gateway 
* Route（路由）
* Predicate（断言）
* Filter（过滤）


# cloudalibaba-provider-payment9001、9002
# cloudalibaba-consumer-nacos-order83
使用： nacos解压(/my-jar/nacos...)
> 1.启动：1.windows下:/nacos/bin/startup.cmd 2.Linux下:/nacos/bin/startup.sh

> 2.开启持久化：运行/nacos/conf/nacos-mysql.sql脚本，配置application.properties的数据库连接

> 3.Linux下nacos集群配置：复制/nacos/conf/cluster.conf.example为cluster.conf 

> 新增多个【{ip}:{port}】的配置，修改startup.sh,执行方式：./startup.sh -p {port}
```shell script
export FUNCTION_MODE="all"
while getopts ":m:f:s:p:" opt  #<<<<<此处
do
    case $opt in
        m)
            MODE=$OPTARG;;
        f)
            FUNCTION_MODE=$OPTARG;;
        s)
            SERVER=$OPTARG;;
        p)                     #<<<<<此处
            PORT=$OPTARG;;     #<<<<<此处
        ?)
        echo "Unknown parameter"
        exit 1;;
    esac
done

#start
echo "$JAVA ${JAVA_OPT}" > ${BASE_DIR}/logs/start.out 2>&1 &
nohup $JAVA -Dserver.port ${JAVA_OPT} nacos.nacos >> ${BASE_DIR}/logs/start.out 2>&1 &   #<<<<<此处
```

* nacos自带轮询测试
* Aop

# cloudalibaba-provider-payment9003
AOP的实现：controller拦截、注解拦截

# cloudalibaba-config-nacos-client3377
读取文件规则：
> ${spring.application.name}-${spring.profile.active}.${file-extension}

# cloudalibaba-sentinel-service8401
* sentinel的使用测试，主要使用 /my-jar/sentinel-dashboard.jar
> java -jar sentinel-dashboard.jar 
>  运行后，访问localhost:8080进入界面

* 。。。。。完善中。。。。。。。
