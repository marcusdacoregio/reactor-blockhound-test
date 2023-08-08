package org.example;

import java.util.UUID;

import reactor.core.publisher.Mono;

public class Generator {

	public Mono<Generated> generate() {
		return Mono.just(new Generated(UUID.randomUUID().toString()));
	}

	public record Generated(String id) {}

}
