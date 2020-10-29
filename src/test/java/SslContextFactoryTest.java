import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class SslContextFactoryTest {
    @Test
    public void testDumpExcludedProtocols() throws Exception {
        SslContextFactory.Server cf = new SslContextFactory.Server();
        cf.setKeyStorePassword("storepwd");
        cf.setKeyManagerPassword("keypwd");
        cf.setExcludeProtocols("SSL.*", "TLSv1", "TLSv1\\.[01]");
        cf.start();

        // Confirm behavior in engine
        String[] enabledProtocols = cf.newSSLEngine().getEnabledProtocols();
        assertThat(enabledProtocols).doesNotContain("TLSv1.1");
    }
}
