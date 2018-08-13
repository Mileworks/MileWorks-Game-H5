package com.mileworks.game.h5.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.lmax.disruptor.spring.boot.DisruptorTemplate;
import com.lmax.disruptor.spring.boot.event.DisruptorBindEvent;

@Configuration
@EnableScheduling
public class DisruptorConfig {

	@Autowired	
	protected DisruptorTemplate disruptorTemplate;

	@Scheduled(fixedDelay = 1000) // 每1s执行1次
	public void send() throws Exception {
		
		DisruptorBindEvent event = new DisruptorBindEvent(this, "我是小龙 " + Math.random());
		
		event.setEvent("Event-Output");
		event.setTag("TagC-Output");
		event.setKey("id-" + Math.random());
		
		disruptorTemplate.publishEvent(event);
		
	}
	
	@Scheduled(fixedDelay = 1000) // 每1s执行1次
	public void send2() throws Exception {
		
		DisruptorBindEvent event = new DisruptorBindEvent(this, "我是徐龙 " + Math.random());
		
		event.setEvent("Event-Output");
		event.setTag("TagB-Output");
		event.setKey("id-" + Math.random());
		
		disruptorTemplate.publishEvent(event);
		
	}
	
}
