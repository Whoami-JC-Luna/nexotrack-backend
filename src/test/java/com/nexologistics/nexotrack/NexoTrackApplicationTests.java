package com.nexologistics.nexotrack;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Protege que el contexto de Spring arranca con la configuración por defecto.
 * Si una dependencia o un bean mal configurado rompe el arranque, este test falla
 * antes de que el problema llegue a cualquier entorno.
 */
@SpringBootTest
class NexoTrackApplicationTests {

    @Test
    void contextLoads() {
    }
}
