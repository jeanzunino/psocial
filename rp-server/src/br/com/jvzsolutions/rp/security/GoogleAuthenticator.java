package br.com.jvzsolutions.rp.security;

import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.ws.rs.container.ContainerRequestContext;

import com.google.api.client.extensions.appengine.http.UrlFetchTransport;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.json.jackson2.JacksonFactory;

import br.com.jvzsolutions.rp.model.Usuario;

public class GoogleAuthenticator {

	private final JacksonFactory jacksonFactory = new JacksonFactory();

	// From:
	// https://developers.google.com/identity/sign-in/android/backend-auth#using-a-google-api-client-library
	// If you retrieved the token on Android using the Play Services 8.3 API
	// or newer, set
	// the issuer to "https://accounts.google.com". Otherwise, set the
	// issuer to
	// "accounts.google.com". If you need to verify tokens from multiple
	// sources, build
	// a GoogleIdTokenVerifier for each issuer and try them both.

	GoogleIdTokenVerifier verifierForNewAndroidClients = new GoogleIdTokenVerifier.Builder(
			UrlFetchTransport.getDefaultInstance(), jacksonFactory).setIssuer("https://accounts.google.com")
					.build();

	GoogleIdTokenVerifier verifierForOtherClients = new GoogleIdTokenVerifier.Builder(
			UrlFetchTransport.getDefaultInstance(), jacksonFactory).setIssuer("accounts.google.com").build();

	// Custom Authenticator class for authenticating google accounts
	public Usuario authenticate(ContainerRequestContext requestContext) {

		String token = requestContext.getHeaderString("google_id_token");
		if (token != null) {

			GoogleIdToken idToken = null;
			try {
				idToken = verifierForNewAndroidClients.verify(token);
				if (idToken == null) {
					idToken = verifierForOtherClients.verify(token);
				}
				
				if (idToken != null) {

					GoogleIdToken.Payload payload = idToken.getPayload();

					// Get profile information from payload
					String userId = payload.getSubject();
					String email = payload.getEmail();
					String name = (String) payload.get("name"); 
					
					Usuario usuario = new Usuario();
					usuario.setEmail(email);
					usuario.setNome(name);
					return usuario;

				} else {
					System.out.println("Invalid Google ID token.");
				}

			} catch (GeneralSecurityException e) {
				return null;
			} catch (IOException e) {
				return null;
			}

		}

		return null;
	}

}