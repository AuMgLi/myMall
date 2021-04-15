package com.jq.mall.service.impl;

import com.jq.mall.common.CommonResult;
import com.jq.mall.component.CancelOrderSender;
import com.jq.mall.dto.OrderParam;
import com.jq.mall.service.OmsPortalOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 前台订单管理Service
 */
@Service
@Slf4j
public class OmsPortalOrderServiceImpl implements OmsPortalOrderService {

    @Autowired
    private CancelOrderSender cancelOrderSender;


    @Override
    public CommonResult<String> generateOrder(OrderParam orderParam) {
        // Todo 执行一系类下单操作，具体参考mall项目
        log.info("Process generateOrder");
        // 下单完成后开启一个延迟消息，用于当用户没有付款时取消订单（orderId应该在下单后生成）
        sendDelayMessageCancelOrder(11L);
        return CommonResult.success(null, "下单成功");
    }

    @Override
    public void cancelOrder(Long orderId) {
        // Todo 执行一系类取消订单操作，具体参考mall项目
        log.info("Process cancelOrder orderId: {}", orderId);
    }

    private void sendDelayMessageCancelOrder(Long orderId) {
        // 获取订单超时时间
        long delayTime = 30 * 1000;  // 30 sec.
        // 发送延迟消息
        cancelOrderSender.sendMessage(orderId, delayTime);
    }
}
