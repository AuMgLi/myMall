package com.jq.mall.service;

import com.jq.mall.common.CommonResult;
import com.jq.mall.dto.OrderParam;
import org.springframework.transaction.annotation.Transactional;

/**
 * 前台订单管理Service
 */
public interface OmsPortalOrderService {

    @Transactional
    CommonResult<String> generateOrder(OrderParam orderParam);

    @Transactional
    void cancelOrder(Long orderId);

}
