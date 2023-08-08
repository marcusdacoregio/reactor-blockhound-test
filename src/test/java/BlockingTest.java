import java.time.Duration;
import java.util.UUID;

import org.example.Generator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import reactor.blockhound.BlockHound;
import reactor.core.publisher.Mono;

public class BlockingTest {

	@BeforeAll
	static void beforeAll() {
		BlockHound.install();
	}

	@Test
	void shouldDetectBlockingInline() {
		Mono.delay(Duration.ofSeconds(1))
				.doOnNext(it -> {
					try {
						UUID.randomUUID().toString();
					}
					catch (Exception e) {
						throw new RuntimeException(e);
					}
				})
				.block();
	}

	@Test
	void shouldDetectBlockingInAnotherClass() {
		Generator.Generated generate = new Generator().generate().block();
	}

}
