package com.mileworks.game.h5.dicruptor;

import org.springframework.stereotype.Component;

import com.lmax.disruptor.spring.boot.annotation.EventRule;
import com.lmax.disruptor.spring.boot.event.DisruptorBindEvent;
import com.lmax.disruptor.spring.boot.event.handler.DisruptorHandler;
import com.lmax.disruptor.spring.boot.event.handler.chain.HandlerChain;

@EventRule("/Event-Output/TagB-Output/**")
@Component("smsHandler")
public class SmsDisruptorHandler implements DisruptorHandler<DisruptorBindEvent> {

	@Override
	public void doHandler(DisruptorBindEvent event, HandlerChain<DisruptorBindEvent> handlerChain) throws Exception {
		System.out.println(event.toString());
	}

}
