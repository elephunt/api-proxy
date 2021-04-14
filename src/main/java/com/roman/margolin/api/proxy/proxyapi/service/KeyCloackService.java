package com.roman.margolin.api.proxy.proxyapi.service;

import com.roman.margolin.api.proxy.proxyapi.model.User;
import lombok.extern.slf4j.Slf4j;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class KeyCloackService {

    private final KeyCloakConfiguration keyCloakConfiguration;
    private final Keycloak keycloak;

    public KeyCloackService(KeyCloakConfiguration keyCloakConfiguration){
        this.keyCloakConfiguration = keyCloakConfiguration;
        keycloak =  KeycloakBuilder.builder()
                .serverUrl(keyCloakConfiguration.getServerUrl())
                .realm(keyCloakConfiguration.getRealm())
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .clientId(keyCloakConfiguration.getClientId())
                .clientSecret(keyCloakConfiguration.getClientSecret())
                .resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10).build())
                .build();

    }

    public UserRepresentation addUser(User user){
        UsersResource users = keycloak.realm(keyCloakConfiguration.getRealm()).users();
        CredentialRepresentation passwordCredentials = createPasswordCredentials(user.getPassword());
        UserRepresentation kcUser = new UserRepresentation();
        kcUser.setUsername(user.getEmail());
        kcUser.setCredentials(Collections.singletonList(passwordCredentials));
        kcUser.setFirstName(user.getFirstName());
        kcUser.setLastName(user.getLastName());
        kcUser.setEmail(user.getEmail());
        kcUser.setEnabled(true);
        kcUser.setEmailVerified(false);
        Response response = users.create(kcUser);
        List<UserRepresentation> search = users.search(user.getEmail());
        UserRepresentation userRepresentation = search.get(0);
        log.info("Created user : {}",userRepresentation.getId());
        return userRepresentation;
    }


    private CredentialRepresentation createPasswordCredentials(String password) {
        CredentialRepresentation passwordCredentials = new CredentialRepresentation();
        passwordCredentials.setTemporary(false);
        passwordCredentials.setType(CredentialRepresentation.PASSWORD);
        passwordCredentials.setValue(password);
        return passwordCredentials;
    }
}
