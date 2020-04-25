package pl.haladyj.springsecurity11.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {


    @Autowired
    private AuthenticationManager authenticationManager;
    /*
    * authorization_code / pkce
    * password ---> deprecated
    * client_credentials
    * refresh token
    * implicit ----> deprecated
    * */


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                .inMemory()
                    .withClient("client1")
                    .secret("secret1")
                    .scopes("read")
                    .authorizedGrantTypes("password")
                .and()
                    .withClient("client")
                    .secret("secret2")
                    .scopes("read")
                    .authorizedGrantTypes("authorization_code")
                    .redirectUris("http://locallhost:9090");


    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);

    }
}
