package sawtooth.sdk.rest.client.tests;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.enterprise.inject.spi.CDI;
import javax.ws.rs.core.Response;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import sawtooth.sdk.reactive.rest.model.RESTState;
import sawtooth.sdk.reactive.rest.ops.RESTStateOps;

@Test
public class TestListState extends BaseTest{

	RESTStateOps underTest;
	
	@BeforeClass
	public void getCDIBeans() throws ClassNotFoundException {
		underTest = (RESTStateOps) CDI.current().select(RESTStateOps.class).get();
	}
	
	
	@Test
	public void testCDI() {
		assertNotNull(underTest);
	}
	
	@Test(dependsOnMethods= {"testCDI"})
	public void testListAll() throws InterruptedException, ExecutionException {
		Future<RESTState> futureResult = underTest.getState(null,null, null, 100, false);
		assertNotNull(futureResult);
		assertFalse(((CompletableFuture<RESTState>)futureResult).isCompletedExceptionally());
		RESTState result =futureResult.get(); 
		assertNotNull(result);
		assertTrue(futureResult.isDone());
	}

}
