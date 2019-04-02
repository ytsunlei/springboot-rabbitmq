# <center>RabbitMQ简单介绍</center>
## 主题交换机（topic）
![topic logo](https://oss.laisitech.com/rabbitmq-topic-model.png)

1.生产者先发送消息到Exchange。

2.Exchange通过绑定的routing-key发送到Queue。

3.消费者只需要监听队列，Queue将消息push到消费者。

## 两种创建方式

### 手动创建
rabbitmq启动后，可以通过http://localhost:15672(端口可以指定)管理后台。添加exchange、route-key,以及routekey对应queue。

配置文件

	spring.rabbitmq.host=localhost
	spring.rabbitmq.port=5672
	spring.rabbitmq.username=guest
	spring.rabbitmq.password=guest
	spring.rabbitmq.template.exchange=laisi-router //指定exchange
	spring.rabbitmq.template.routing-key=balance_measure_raw_queue //指定routing-key
	spring.rabbitmq.template.retry.enabled=true
	spring.rabbitmq.template.retry.initial-interval=2s

### 从代码里创建

创建Exchange、Queue、routing-key，并将队列和routing-key进行绑定
	
	@Configuration
	public class RabbitConfig {
	    @Bean
	    public Queue helloQueue() {
	        return new Queue("hello");
	    }
	
	    @Bean
	    TopicExchange exchange() {
	        return new TopicExchange("hello-router");
	    }
	
	    @Bean
	    Binding bindingExchangeMessage(Queue helloQueue, TopicExchange exchange) {
	        // 将队列helloQueue绑定到名为hello_routing_key的routingKey
	        return BindingBuilder.bind(helloQueue).to(exchange).with("hello_routing_key");
	    }
	
	}

发送消息

	@Component
	public class Sender {
	
	    private final AmqpTemplate amqpTemplate;
	
	    @Autowired
	    public Sender(AmqpAdmin amqpAdmin, AmqpTemplate amqpTemplate) {
	        this.amqpTemplate = amqpTemplate;
	    }
	
	    public void sendMsg(){
	        // Topic
	        amqpTemplate.convertAndSend("hello-router", "hello_routing_key", "hello");
	    };
	
	}
	
消费消息
	
	@Component
	public class ReceivingMessage {
	
	    @RabbitListener(queues = "hello")
	    @RabbitHandler
	    public void process2(String message) {
	        System.out.println("queue:topic.hello,message:" + message);
	    }
	
	}
	
配置文件

	spring.rabbitmq.host=localhost
	spring.rabbitmq.port=5672
	spring.rabbitmq.username=guest
	spring.rabbitmq.password=guest
	spring.rabbitmq.template.retry.enabled=true
	spring.rabbitmq.template.retry.initial-interval=2s



	

