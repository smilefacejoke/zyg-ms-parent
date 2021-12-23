package com.zyg.user.client.factory;

import com.zyg.common.utils.R;
import com.zyg.user.client.ManagerClient;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author 涛哥
 * @date 2021/12/22
 */
@Component
public class ManagerClientFallbackFactory implements FallbackFactory<ManagerClient> {
    @Override
    public ManagerClient create(Throwable throwable) {
        return new ManagerClient() {
            @Override
            public R findAll() {
                return R.error("zyg-manager dead by factory");
            }
        };
    }
}
