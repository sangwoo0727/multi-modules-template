package io.hala.james.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Profiles;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

/**
 * ref) https://goodgid.github.io/Analyzing-the-Feign-Client-and-Use/#custom-yml-%EC%82%AC%EC%9A%A9
 */

public class YamlEnvironmentPostProcessor implements EnvironmentPostProcessor {

  private final String[] propertyUris = {"classpath*:*.yml"};
  private final String[] acceptsProfiles = {"local", "dev"};

  private final YamlPropertySourceLoader yamlPropertySourceLoader = new YamlPropertySourceLoader();
  private final ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

  @Override
  public void postProcessEnvironment(ConfigurableEnvironment environment,
      SpringApplication application) {

    boolean isNotValidProfileActive = !environment.acceptsProfiles(Profiles.of(acceptsProfiles));
    if (isNotValidProfileActive) {
      environment.setActiveProfiles("local");
    }

    try {
      List<Resource> resources = new ArrayList<>();
      for (String uri : propertyUris) {
        resources.addAll(List.of(resourcePatternResolver.getResources(uri)));
      }

      resources.stream().map(this::loadYaml).forEach(them -> {
        if (them != null) {
          for (PropertySource<?> it : them) {
            System.out.println(environment.getPropertySources());
            environment.getPropertySources().addLast(it);
          }
        }
      });
    } catch (Exception e) {
      throw new BeanCreationException(e.getMessage(), e);
    }

  }

  private List<PropertySource<?>> loadYaml(Resource resource) {
    if (!resource.exists()) {
      throw new IllegalArgumentException();
    }
    try {
      return yamlPropertySourceLoader.load(resource.getURL().toString(), resource);
    } catch (IOException e) {
      throw new IllegalStateException();
    }
  }
}
