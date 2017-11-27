package com.filmdatabase.filmdb.configuration;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * No customizations of {@link AbstractSecurityWebApplicationInitializer} are necessary.
 *
 * @author Rob Winch
 */
public class SecurityWebApplicationInitializer extends
        AbstractSecurityWebApplicationInitializer {

    public SecurityWebApplicationInitializer() {
        super(SecurityJavaConfig.class);
    }
}