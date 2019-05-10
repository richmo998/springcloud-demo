# springcloud-demo
- 针对springcloud的每种组件进行单独工程测试，拿来开箱即可学习使用；
- 本人服务注册和发现，以及配置中心全部基于zk,所以学习的前提你得弄个zk服务，记得自己调整地址；
- 如果联系使用配置中心，记得先学习一下如何配置信息进zk,这样才能启动的时候获取到配置；
- 集成使用redis,mybatis,druid,activemq还可以有更多工具可集成，学习者可以根据自己的需求去联系；

<br><br>
<b>说明：</b>
<br>
1、sc-register是zookeeper实现的注册中心<br>
2、sc-service与sc-service-jsp是服务提供方，这些服务会注册到sc-register注册中心。另外，从命名上可以看出来，sc-service-jsp支持JSP；<b>注意，这里集成前端仅仅是为了测试方便，在项目中，前端应该在服务的消费方，如在本工程的sc-client模块中集成前端。不过，如果服务消费方希望服务提供方返回html代码片段，那么，你就会发现，这个方式实在是太妙了。</b><br>
3、sc-gateway是微服务网关，通过微服务网关访问sc-service与sc-service-jsp服务。<br>
4、sc-client是服务的消费方。在内部进行服务调用，可通过sc-client访问服务sc-service与sc-service-jsp。<br>
5、sc-trace是<b>链路调用日志</b>收集服务，通过zipkin收集。支持将收集到的链路日志信息保存到MySQL或者是ElasticSearch。当前代码是将调用日志保存到mysql

