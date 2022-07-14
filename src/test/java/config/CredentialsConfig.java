package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:credentials.properties"
})
public interface CredentialsConfig extends Config {

    @Key("user")
    String user();

    @Key("password")
    String password();

    @Key("remoteUrl")
    String remoteUrl();
}