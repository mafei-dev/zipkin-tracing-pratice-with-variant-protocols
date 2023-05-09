package com.example.service6;

import brave.http.HttpTracing;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.web.embedded.netty.NettyServerCustomizer;
import org.springframework.cloud.sleuth.instrument.web.TraceWebFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

