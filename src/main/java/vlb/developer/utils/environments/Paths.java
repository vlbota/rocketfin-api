package vlb.developer.utils.environments;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class Paths {

    @ConfigProperty(name = "env.path.front-end")
    public String FRONT_URL;

    @ConfigProperty(name = "env.path.back-end")
    public String BACK_URL;
}

