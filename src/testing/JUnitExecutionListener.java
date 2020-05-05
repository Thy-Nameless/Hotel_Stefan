package testing;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

public class JUnitExecutionListener extends RunListener {

	public void testRunStarted(Description description) throws Exception {
		System.out.println("NÃºmero de tests a ejecutar: " + description.testCount());
	}

	public void testRunFinished(Result result) throws Exception {
		System.out.println("NÃºmero de tests ejecutados: " + result.getRunCount());
	}

	public void testStarted(Description description) throws Exception {
		System.out.println("Comenzando ejecución: " + description.getMethodName());
	}

	public void testFinished(Description description) throws Exception {
		System.out.println("Finalizada ejecución: " + description.getMethodName());
	}

	public void testFailure(Failure failure) throws Exception {
		System.out.println("Test fallido: " + failure.getDescription().getMethodName());
	}

	public void testAssumptionFailure(Failure failure) {
		System.out.println("Test fallido: " + failure.getDescription().getMethodName());
	}

	public void testIgnored(Description description) throws Exception {
		System.out.println("Test ignorado: " + description.getMethodName());
	}
}