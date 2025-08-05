package org.springframework.boot.props;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * UserProps.
 */
@Component
@ConfigurationProperties(prefix = "application.data.user")
@RequiredArgsConstructor
@Getter
@Setter
public class UserProps {

  private String name;
  private String surname;
  private String password;
  private String custom;
  private String otpCode;
}
