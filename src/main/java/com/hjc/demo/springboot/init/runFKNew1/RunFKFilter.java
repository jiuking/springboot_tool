package com.hjc.demo.springboot.init.runFKNew1;

import com.hjc.demo.springboot.init.runFKNew.FilterChain;

public interface RunFKFilter {
    void doFilter(RunFKEntity runFKEntity, FilterChain chain);
}
