package org.gso.leka;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.ldap.LdapContext;

public class LDAPHelper {
	Context ldapCtx;

	public LDAPHelper() throws NamingException {
		Hashtable<String, Object> env = new Hashtable<String, Object>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://ldap-dc1.gso:389");
		try {
			ldapCtx = new InitialContext(env);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		NamingEnumeration list = ldapCtx.list("ou=klassen,ou=gruppen,ou=benutzer,dc=gso-neu");
		DirContext klasse = (DirContext) ldapCtx.lookup("cn=fia51,ou=klassen,ou=gruppen,ou=benutzer,dc=gso-neu");
		//list = ldapCtx.getAttributes("cn=fia51,ou=klassen,ou=gruppen,ou=benutzer,dc=gso-neu").getAll();
		while (list.hasMore()) {
			NameClassPair nc;
			nc = (NameClassPair) list.next();
			System.out.println(nc);
		}
	}
}
