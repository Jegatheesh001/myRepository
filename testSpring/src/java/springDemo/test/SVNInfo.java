package springDemo.test;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import org.tmatesoft.svn.core.SVNLogEntry;
import org.tmatesoft.svn.core.SVNLogEntryPath;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

/**
 * 
 * Get Information About Repository
 * 
 * @author jegatheesh <br>
 * 
 * @Ref <a href=https://wiki.svnkit.com/>Svn kit Tutorials</a>
 *
 */
public class SVNInfo {

	public static void main(String[] args) {
		DAVRepositoryFactory.setup();

		String url;
		String password;
		String name = "jegatheesh";
		
		// url = "https://localhost/svn/medasrepository/";
		// password = "pass";
		url = "https://192.168.0.100/svn/MEDAS-HIS/";
		password = "jegatheesh123";
		
		long startRevision;
		long endRevision; // HEAD (the latest) revision

		SVNRepository repository = null;
		try {
			repository = SVNRepositoryFactory.create(SVNURL.parseURIEncoded(url));
			ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(name, password);
			repository.setAuthenticationManager(authManager);

			Collection<SVNLogEntry> logEntries = null;

			// To get latest Version Details
			endRevision = repository.getLatestRevision();
			// Last 25 Details
			startRevision = endRevision - 25;

			logEntries = repository.log(new String[] { "" }, null, startRevision, endRevision, true, true);

			for (Iterator<SVNLogEntry> entries = logEntries.iterator(); entries.hasNext();) {
				SVNLogEntry logEntry = (SVNLogEntry) entries.next();
				System.out.println("---------------------------------------------");
				System.out.println("revision: " + logEntry.getRevision());
				System.out.println("author: " + logEntry.getAuthor());
				System.out.println("date: " + logEntry.getDate());
				System.out.println("log message: " + logEntry.getMessage());

				if (logEntry.getChangedPaths().size() > 0) {
					System.out.println();
					System.out.println("changed paths " + "(" + logEntry.getChangedPaths().size() + ") :");
					Set changedPathsSet = logEntry.getChangedPaths().keySet();

					for (Iterator<SVNLogEntryPath> changedPaths = changedPathsSet.iterator(); changedPaths.hasNext();) {
						SVNLogEntryPath entryPath = (SVNLogEntryPath) logEntry.getChangedPaths()
								.get(changedPaths.next());
						System.out.println(" " + entryPath.getType() + " " + entryPath.getPath()
								+ ((entryPath.getCopyPath() != null) ? " (from " + entryPath.getCopyPath()
										+ " revision " + entryPath.getCopyRevision() + ")" : ""));
					}
				}
			}
		} catch (Exception e) {

		}

	}

}
