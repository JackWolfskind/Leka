package org.gso.leka;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.gso.leka.data.Student;

import com.unboundid.ldap.sdk.LDAPConnection;
import com.unboundid.ldap.sdk.LDAPException;
import com.unboundid.ldap.sdk.LDAPSearchException;
import com.unboundid.ldap.sdk.SearchResult;
import com.unboundid.ldap.sdk.SearchResultEntry;
import com.unboundid.ldap.sdk.SearchScope;

public class LDAPHelper {
	
	LDAPHelper() throws LDAPException{
		klassenAbteilung();
	}

	private HashMap<String, String> klassenAbteilungen = new HashMap<String, String>();

	/**
	 * @return the klassenAbteilungen
	 */
	public HashMap<String, String> getKlassenAbteilungen() {
		return klassenAbteilungen;
	}

	
	
	// create LDAP Connenction
	public static LDAPConnection getConnection() throws LDAPException {
		
		return new LDAPConnection(AppConfig.getConfig().getFromPath("credentials/ldap/host"), AppConfig.getConfig().getIntFromPath("credentials/ldap/port"));
	}

	// filter result
	public static List<SearchResultEntry> getResults(LDAPConnection connection, String baseDN, String filter)
			throws LDAPSearchException {
		SearchResult searchResult;

		if (connection.isConnected()) {
			searchResult = connection.search(baseDN, SearchScope.ONE, filter);

			return searchResult.getSearchEntries();
		}

		return null;
	}


	// stellt eine Anfrage an LDAP und liefert die dazugehörigen Abteilungen
	public List<SearchResultEntry> getAlleAbteilungen() throws LDAPException {
		String baseDN = "ou=schueler,ou=benutzer,dc=gso-neu";
		String filter = "(&(|(objectClass=organizationalUnit)))";

		LDAPConnection connection = getConnection();
		return getResults(connection, baseDN, filter);
	}

	// stellt eine Anfrage an LDAP unter Angabe einer Abtelung und liefert die
	// dazugehörigen Klassen
	public List<SearchResultEntry> klassenEinerAbteilung(String ouAbteilung) throws LDAPException {
		String baseDN = "ou=" + ouAbteilung + ",ou=schueler,ou=benutzer,dc=gso-neu";
		String filter = "(&(|(objectClass=organizationalUnit)))";

		LDAPConnection connection = getConnection();
		return getResults(connection, baseDN, filter);
	}

	// stellt eine Anfrage an LDAP unter Angabe einer Abtelung, Klasse und
	// liefert die dazugehörigen Schüler
	public List<SearchResultEntry> schuelerEinerKlasse(String ouKlasse, String ouAbteilung) throws LDAPException {
		String baseDN = "ou=" + ouKlasse + ",ou=" + ouAbteilung + ",ou=schueler,ou=benutzer,dc=gso-neu";
		String filter = "(&(|(uid=*)))";

		LDAPConnection connection = getConnection();
		return getResults(connection, baseDN, filter);
	}

	// Abteilungen zu Liste aus SearchResultEntry
	public List<String> abteilungen() throws LDAPException {
		List<String> abteilungList = new ArrayList<String>();

		for (SearchResultEntry sre : getAlleAbteilungen()) {
			abteilungList.add(sre.getAttributeValue("ou"));
		}

		return abteilungList;
	}

	//füllt eine HashMap mit Klassen(key) und deren Abteilungen(value)
	public void klassenAbteilung() throws LDAPException {

		for (String abteilung : abteilungen()) {

			for (SearchResultEntry sre : klassenEinerAbteilung(abteilung)) {
					klassenAbteilungen.put(sre.getAttributeValue("ou"),abteilung);
			}
		}

	}
	//liefert Liste von Schüler Objekten unter eingabe der Klasse
	public List<Student> studentsOfClass(String klasse) throws LDAPException{
		List<Student> schoolClass = new ArrayList<Student>();
		
		for(SearchResultEntry sre:schuelerEinerKlasse(klasse, klassenAbteilungen.get(klasse))){
			schoolClass.add(new Student(sre.getAttributeValue("uid"),sre.getAttributeValue("cn")));
		}
		return schoolClass;
	}
	

	
//	public static void main(String[] args) throws LDAPException {
//		LDAPHelper ldapHelper = new LDAPHelper();
//		ldapHelper.studentsOfClass("fia51");
//	}
}
