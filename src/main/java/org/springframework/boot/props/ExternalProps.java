package org.springframework.boot.props;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * ExternalProps.
 */
@Component
@ConfigurationProperties(prefix = "application.client.external")
@RequiredArgsConstructor
@Getter
@Setter
public class ExternalProps {

  private String oceanUrl;
  private String sealUrl;
}
