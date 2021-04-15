package com.jq.mall.component;

import com.jq.mall.dto.QueueEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CancelOrderSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendMessage(Long orderId, final long delayTime) {
        // 给延迟队列发送消息
        amqpTemplate.convertAndSend(
                QueueEnum.QUEUE_TTL_ORDER_CANCEL.getExchange(),
                QueueEnum.QUEUE_TTL_ORDER_CANCEL.getRouteKey(),
                orderId, message -> {
                    // 给消息设置延迟毫秒值
                    message.getMessageProperties().setExpiration(String.valueOf(delayTime));
                    return message;
                });
        log.info("Send delay message orderId: {}", orderId);
    }
}
