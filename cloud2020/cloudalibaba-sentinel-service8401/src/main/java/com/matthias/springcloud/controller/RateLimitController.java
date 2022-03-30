package com.matthias.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.matthias.springcloud.entities.CommonResult;
import com.matthias.springcloud.entities.Payment;
import com.matthias.springcloud.myhandler.CustomerBlockHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RateLimitController {

    @GetMapping("/byResource")
    @SentinelResource(value = "byResource", blockHandler = "handleException")
    public CommonResult byResource() {
        return new CommonResult(200, "按资源名称测试ok", new Payment(2020l, "2020"));
    }
    public CommonResult handleException(BlockException be) {
        return new CommonResult(400,  "按资源名称测试error\t"
                + be.getClass().getCanonicalName() , null);
    }

    @GetMapping("/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler", blockHandlerClass = CustomerBlockHandler.class,
    blockHandler = "customerHandler2")
    public CommonResult customerBlockHandler() {
        return new CommonResult(200, "customerBlockHandler测试ok", new Payment(2021l, "2021"));
    }

}
