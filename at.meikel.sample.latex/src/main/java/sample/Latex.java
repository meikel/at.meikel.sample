package sample;

import java.io.File;
import java.util.Collections;

import org.apache.maven.BuildFailureException;
import org.apache.maven.artifact.resolver.ArtifactNotFoundException;
import org.apache.maven.artifact.resolver.ArtifactResolutionException;
import org.apache.maven.cli.ConsoleDownloadMonitor;
import org.apache.maven.embedder.MavenEmbedder;
import org.apache.maven.embedder.MavenEmbedderConsoleLogger;
import org.apache.maven.embedder.MavenEmbedderException;
import org.apache.maven.embedder.PlexusLoggerAdapter;
import org.apache.maven.lifecycle.LifecycleExecutionException;
import org.apache.maven.monitor.event.DefaultEventMonitor;
import org.apache.maven.monitor.event.EventMonitor;
import org.apache.maven.project.DuplicateProjectException;
import org.apache.maven.project.MavenProject;
import org.apache.maven.project.ProjectBuildingException;
import org.codehaus.plexus.util.dag.CycleDetectedException;

// See: http://codehaus.org/~jvanzyl/maven2/guides/mini/guide-embedding-m2.html
// See: http://mojo.codehaus.org/latex-maven-plugin/

public class Latex {

	public static void main(String[] args) {
		Latex latex = new Latex();
		latex.clean();
		latex.pdflatex();
		latex.cleanPdflatex();
	}

	private void clean() {
		maven("clean");
	}

	private void pdflatex() {
		maven("latex:latex");
	}

	private void cleanPdflatex() {
		maven(new String[] { "clean", "latex:latex" });
	}

	private void maven(String goal) {
		maven(new String[] { goal });
	}

	private void maven(String[] goals) {
		if (goals == null) {
			return;
		}

		MavenEmbedder maven = new MavenEmbedder();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		maven.setClassLoader(classLoader);
		maven.setLogger(new MavenEmbedderConsoleLogger());
		for (String goal : goals) {
			try {
				maven.start();
				File targetDirectory = new File(System.getProperty("user.dir"), "work");
				File pomFile = new File(targetDirectory, "pom.xml");
				MavenProject pom;
				pom = maven.readProjectWithDependencies(pomFile);
				EventMonitor eventMonitor = new DefaultEventMonitor(new PlexusLoggerAdapter(
				        new MavenEmbedderConsoleLogger()));
				maven.execute(pom, Collections.singletonList(goal), eventMonitor, new ConsoleDownloadMonitor(), null,
				        targetDirectory);
			} catch (MavenEmbedderException e) {
				e.printStackTrace();
			} catch (ArtifactResolutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ArtifactNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ProjectBuildingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CycleDetectedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (LifecycleExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BuildFailureException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DuplicateProjectException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
