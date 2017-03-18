package br.com.jvzsolutions.rp.security;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import br.com.jvzsolutions.rp.model.Usuario;

@Provider
public class AuthenticationHandler implements ContainerRequestFilter {

	private Response createFaultResponse() {
		return Response.status(401).header("WWW-Authenticate", "Basic realm=\"service.com\"").build();
	}

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		String authorization = requestContext.getHeaderString("Authorization");
		if (authorization == null || authorization.isEmpty()) {
			requestContext.abortWith(createFaultResponse());
			return;
		}
		
		Usuario user = new GoogleAuthenticator().authenticate(requestContext);
		
		if (user == null) {
			// authentication failed, request the authetication, add the realm
			// name if needed to the value of WWW-Authenticate
			requestContext.abortWith(Response.status(401).header("WWW-Authenticate", "Basic").build());
		}
	}

}
