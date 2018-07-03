package com.fibbery.springboot.starter.dubbo;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.utils.NetUtils;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.cluster.loadbalance.RandomLoadBalance;
import com.fibbery.springboot.starter.dubbo.constants.ConfigConstants;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * 偏向ip的测试
 * @author fibbery
 * @date 18/7/2
 */
public class PreferLoadBalance extends RandomLoadBalance {

    @Override
    protected <T> Invoker<T> doSelect(List<Invoker<T>> invokers, URL url, Invocation invocation) {
        String localIP = NetUtils.getLocalAddress().getHostAddress();
        String perferIP = url.getParameter(ConfigConstants.PREFER_IP, localIP);
        Set<String> ipSet = Sets.newHashSet(perferIP.split(";"));
        Optional<Invoker<T>> selected = invokers.stream().filter(invoker -> ipSet.contains(invoker.getUrl().getIp())).findFirst();
        return selected.orElse(super.doSelect(invokers, url, invocation));
    }
}
