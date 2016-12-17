package humankind.tools;

import static org.mockito.Mockito.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ParserContextTest {
	@Mock
	private BufferedReader in;
	@Mock
	private OutputStreamWriter out;

	@Before
	public void initInMock() throws IOException {
		when(in.readLine()).thenReturn("lala", "lala", "");
	}

	@Test
	public void shouldIterateThroughInputStreamLines() throws IOException {
		// arrange
		ParserContext sut = new ParserContext(in, out);

		// act
		sut.processInput();

		// assert
		verify(in, times(3)).readLine();
	}
}