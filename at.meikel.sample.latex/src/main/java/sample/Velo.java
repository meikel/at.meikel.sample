package sample;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

public class Velo {

	public static void main(String[] args) {
		new Velo().xxx();
	}

	private void xxx() {
		File file = new File("."/* System.getProperty("user.dir") */,
				"work/src/main/velocity");

		Velocity.init();

		VelocityContext context = new VelocityContext();

		context.put("title", new String("A LaTeX Article"));
		context.put("author", new String("Osterhase"));

		Template template = null;

		try {
			template = Velocity.getTemplate("work/src/main/velocity/"
					+ "sample.vm");
		} catch (ResourceNotFoundException rnfe) {
			// couldn't find the template
		} catch (ParseErrorException pee) {
			// syntax error: problem parsing the template
		} catch (MethodInvocationException mie) {
			// something invoked in the template
			// threw an exception
		} catch (Exception e) {
		}

		// StringWriter sw = new StringWriter();
		// template.merge( context, sw );
		// System.out.println(sw);

		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream("work/src/main/latex/sample/"
							+ "sample.tex")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if ((template != null) && (writer != null)) {
			try {
				template.merge(context, writer);
			} catch (ResourceNotFoundException e) {
				e.printStackTrace();
			} catch (ParseErrorException e) {
				e.printStackTrace();
			} catch (MethodInvocationException e) {
				e.printStackTrace();
			}

			try {
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
