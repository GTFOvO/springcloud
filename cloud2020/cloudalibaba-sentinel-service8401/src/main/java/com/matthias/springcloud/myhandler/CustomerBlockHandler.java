package com.matthias.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.matthias.springcloud.entities.CommonResult;

public class CustomerBlockHandler {

    public static CommonResult customerHandler(BlockException e) {
        return new CommonResult(404, "客户自定义：global==========1", null);
    }

    public static CommonResult customerHandler2(BlockException e) {
        return new CommonResult(404, "客户自定义：global==========2", null);
    }
}
